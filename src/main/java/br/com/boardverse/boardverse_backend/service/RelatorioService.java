package br.com.boardverse.boardverse_backend.service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import br.com.boardverse.boardverse_backend.database.enums.StatusPedidoEnum;
import br.com.boardverse.boardverse_backend.database.model.CategoriaEntity;
import br.com.boardverse.boardverse_backend.database.repository.CategoriaRepository;
import br.com.boardverse.boardverse_backend.database.repository.ItemPedidoRepository;
import br.com.boardverse.boardverse_backend.dto.relatorio.PontoMensalDto;
import br.com.boardverse.boardverse_backend.dto.relatorio.VendasMensaisResponseDto;
import br.com.boardverse.boardverse_backend.exception.BadRequestException;
import br.com.boardverse.boardverse_backend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RelatorioService {

    private final ItemPedidoRepository itemPedidoRepository;
    private final CategoriaRepository categoriaRepository;

    public VendasMensaisResponseDto consultar(LocalDate inicio, LocalDate fim, List<Integer> categoriasIds) {
        if (fim.isBefore(inicio)) {
            throw new BadRequestException("A data de fim não pode ser anterior à data de início");
        }

        if (categoriasIds == null || categoriasIds.isEmpty()) {
            throw new BadRequestException("Selecione ao menos uma categoria");
        }

        List<CategoriaEntity> categorias = categoriaRepository.findAllById(categoriasIds);

        if (categorias.size() != categoriasIds.size()) {
            throw new NotFoundException("Alguma das categorias informadas não existe");
        }

        var linhas = itemPedidoRepository.somarVendasMensaisPorCategoria(
                StatusPedidoEnum.ENTREGUE,
                inicio.atStartOfDay(),
                fim.plusDays(1).atStartOfDay(),
                categoriasIds);

        Map<YearMonth, Map<String, BigDecimal>> pontos = new LinkedHashMap<>();

        for (YearMonth ym = YearMonth.from(inicio); !ym.isAfter(YearMonth.from(fim)); ym = ym.plusMonths(1)) {
            Map<String, BigDecimal> valores = new LinkedHashMap<>();
            categorias.forEach(c -> valores.put(c.getNome(), BigDecimal.ZERO));
            pontos.put(ym, valores);
        }
        linhas.forEach(l -> pontos.get(YearMonth.of(l.ano(), l.mes())).put(l.categoriaNome(), l.total()));

        return new VendasMensaisResponseDto(
                categorias.stream().map(CategoriaEntity::getNome).toList(),
                pontos.entrySet().stream()
                        .map(e -> new PontoMensalDto(e.getKey().toString(), e.getValue()))
                        .toList());
    }

}

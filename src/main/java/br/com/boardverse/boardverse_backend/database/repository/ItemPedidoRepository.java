package br.com.boardverse.boardverse_backend.database.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.boardverse.boardverse_backend.database.enums.StatusPedidoEnum;
import br.com.boardverse.boardverse_backend.database.model.ItemPedidoEntity;
import br.com.boardverse.boardverse_backend.dto.relatorio.VendaMensalCategoriaDto;

public interface ItemPedidoRepository extends JpaRepository<ItemPedidoEntity, Integer> {

    @Query("""
            SELECT new br.com.boardverse.boardverse_backend.dto.relatorio.VendaMensalCategoriaDto(
                YEAR(p.criadoEm), MONTH(p.criadoEm), c.id, c.nome,
                SUM(i.precoUnitario * i.quantidade))
            FROM ItemPedidoEntity i
                JOIN i.pedido p
                JOIN i.produto pr
                JOIN pr.categoria c
            WHERE p.status = :status
              AND p.criadoEm >= :inicio
              AND p.criadoEm < :fimExclusivo
              AND c.id IN :categoriaIds
            GROUP BY YEAR(p.criadoEm), MONTH(p.criadoEm), c.id, c.nome
            """)
    List<VendaMensalCategoriaDto> somarVendasMensaisPorCategoria(
            @Param("status") StatusPedidoEnum status,
            @Param("inicio") LocalDateTime inicio,
            @Param("fimExclusivo") LocalDateTime fimExclusivo,
            @Param("categoriaIds") List<Integer> categoriaIds);
}

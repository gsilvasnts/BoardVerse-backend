package br.com.boardverse.boardverse_backend.dto.relatorio;

import java.math.BigDecimal;

public record VendaMensalCategoriaDto(
        Integer ano, Integer mes, Integer categoriaId, String categoriaNome, BigDecimal total) {
}

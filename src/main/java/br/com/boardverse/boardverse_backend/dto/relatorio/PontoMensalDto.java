package br.com.boardverse.boardverse_backend.dto.relatorio;

import java.math.BigDecimal;
import java.util.Map;

public record PontoMensalDto(
        String periodo, Map<String, BigDecimal> valores) {
}

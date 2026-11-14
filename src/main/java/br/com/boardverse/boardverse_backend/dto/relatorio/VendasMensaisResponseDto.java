package br.com.boardverse.boardverse_backend.dto.relatorio;

import java.util.List;

public record VendasMensaisResponseDto (
    List<String> categorias, List<PontoMensalDto> pontos){
}

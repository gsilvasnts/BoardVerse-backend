package br.com.boardverse.boardverse_backend.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.boardverse.boardverse_backend.dto.relatorio.VendasMensaisResponseDto;
import br.com.boardverse.boardverse_backend.service.RelatorioService;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/v1/relatorios")
@RequiredArgsConstructor
@Validated
public class RelatorioController {

    private final RelatorioService service;

    @GetMapping("/vendas")
    public VendasMensaisResponseDto consultar(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim,
            @RequestParam List<Integer> categorias) {
        return service.consultar(inicio, fim, categorias);
    }
}

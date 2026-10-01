package br.com.boardverse.boardverse_backend.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import br.com.boardverse.boardverse_backend.dto.cliente.*;
import br.com.boardverse.boardverse_backend.service.ClienteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/v1/cliente")
@RequiredArgsConstructor
@Validated
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void cadastrarCliente(@Valid @RequestBody ClienteRequestDto clienteDto) {
        clienteService.cadastrarCliente(clienteDto);
    }

    @GetMapping("/{clienteId}")
    public ClienteResponseDto buscarPorId(@PathVariable Integer clienteId) {
        return clienteService.buscarPorId(clienteId);
    }

    @PutMapping("/{clienteId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizar(
            @PathVariable Integer clienteId,
            @Valid @RequestBody ClienteUpdateRequestDto clienteDto) {
        clienteService.atualizar(clienteId, clienteDto);
    }

    @PatchMapping("/{clienteId}/inativar")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void inativar(@PathVariable Integer clienteId) {
        clienteService.inativar(clienteId);
    }

    @GetMapping()
    public List<ClienteResponseDto> listarTodos() {
        return clienteService.listarTodos();
    }

}

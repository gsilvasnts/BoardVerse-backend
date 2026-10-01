package br.com.boardverse.boardverse_backend.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import br.com.boardverse.boardverse_backend.converter.ClienteMapper;
import br.com.boardverse.boardverse_backend.database.model.ClienteEntity;
import br.com.boardverse.boardverse_backend.database.model.EnderecoEntity;
import br.com.boardverse.boardverse_backend.database.repository.ClienteRepository;
import br.com.boardverse.boardverse_backend.dto.cliente.ClienteRequestDto;
import br.com.boardverse.boardverse_backend.dto.cliente.ClienteResponseDto;
import br.com.boardverse.boardverse_backend.dto.cliente.ClienteUpdateRequestDto;
import br.com.boardverse.boardverse_backend.exception.BadRequestException;
import br.com.boardverse.boardverse_backend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import br.com.boardverse.boardverse_backend.database.enums.TipoEnderecoEnum;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public void cadastrarCliente(ClienteRequestDto clienteDto) throws BadRequestException {

        if (clienteRepository.existsByCpf(clienteDto.getCpf())) {
            throw new BadRequestException("Cliente já cadastrado");
        }

        // validarEnderecos();

        ClienteEntity cliente = ClienteEntity.builder()
                .genero(clienteDto.getGenero())
                .nome(clienteDto.getNome())
                .cpf(clienteDto.getCpf())
                .dataNascimento(clienteDto.getDataNascimento())
                .email(clienteDto.getEmail())
                .senha(clienteDto.getSenha())
                .ativo(true)
                .build();

        clienteRepository.save(cliente);
    }

    public ClienteResponseDto buscarPorId(Integer clienteId) {
        ClienteEntity cliente = buscarEntidadePorId(clienteId);
        return clienteMapper.toResponseDto(cliente);
    }

    public void atualizar(Integer clienteId, ClienteUpdateRequestDto clienteDto)
            throws NotFoundException, BadRequestException {
        ClienteEntity cliente = buscarEntidadePorId(clienteId);

        if (!cliente.getCpf().equals(clienteDto.getCpf())
                && clienteRepository.existsByCpf(clienteDto.getCpf())) {
            throw new BadRequestException("CPF já cadastrado");
        }

        cliente.setNome(clienteDto.getNome());
        cliente.setDataNascimento(clienteDto.getDataNascimento());
        cliente.setEmail(clienteDto.getEmail());
        cliente.setGenero(clienteDto.getGenero());
        cliente.setAtivo(clienteDto.getAtivo());

        clienteRepository.save(cliente);
    }

    public void inativar(Integer clienteId) {
        ClienteEntity cliente = buscarEntidadePorId(clienteId);
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }

    public List<ClienteResponseDto> listarTodos() {
        return clienteRepository.findAll()
            .stream()
            .map(clienteMapper::toResponseDto)
            .toList();
    }

    private ClienteEntity buscarEntidadePorId(Integer clienteId) throws NotFoundException {
        return clienteRepository.findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    public void validarEnderecos(Set<EnderecoEntity> enderecos) {
        boolean possuiCobranca = enderecos.stream()
                .anyMatch(endereco -> endereco.getTipoEndereco() == TipoEnderecoEnum.COBRANCA);

        boolean possuiEntrega = enderecos.stream()
                .anyMatch(endereco -> endereco.getTipoEndereco() == TipoEnderecoEnum.ENTREGA);

        if (!possuiCobranca) {
            throw new BadRequestException("Cliente deve possuir pelo menos um endereço de cobrança.");
        }

        if (!possuiEntrega) {
            throw new BadRequestException("Cliente deve possuir pelo menos um endereço de entrega.");
        }
    }

}

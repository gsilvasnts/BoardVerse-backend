package br.com.boardverse.boardverse_backend.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.boardverse.boardverse_backend.converter.CarrinhoMapper;
import br.com.boardverse.boardverse_backend.database.model.CarrinhoEntity;
import br.com.boardverse.boardverse_backend.database.model.ClienteEntity;
import br.com.boardverse.boardverse_backend.database.model.ItemCarrinhoEntity;
import br.com.boardverse.boardverse_backend.database.model.ProdutoEntity;
import br.com.boardverse.boardverse_backend.database.repository.CarrinhoRepository;
import br.com.boardverse.boardverse_backend.database.repository.ClienteRepository;
import br.com.boardverse.boardverse_backend.database.repository.ItemCarrinhoRepository;
import br.com.boardverse.boardverse_backend.database.repository.ProdutoRepository;
import br.com.boardverse.boardverse_backend.dto.carrinho.AdicionarItemCarrinhoRequestDto;
import br.com.boardverse.boardverse_backend.dto.carrinho.CarrinhoResponseDto;
import br.com.boardverse.boardverse_backend.exception.NotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository carrinhoRepository;
    private final CarrinhoMapper carrinhoMapper;
    private final ClienteRepository clienteRepository;
    private final ItemCarrinhoRepository itemCarrinhoRepository;
    private final ProdutoRepository produtoRepository;

    public CarrinhoResponseDto buscarCarrinho(Integer carrinhoId) {
        CarrinhoEntity carrinho = buscarEntidadePorId(carrinhoId);
        return carrinhoMapper.toResponseDto(carrinho);
    }

    public void adicionarItem(Integer clienteId, AdicionarItemCarrinhoRequestDto dto) {
        CarrinhoEntity carrinho = buscarOuCriarCarrinho(clienteId);

        ProdutoEntity produto = buscarProdutoPorId(dto.getProdutoId());

        // Validar estoque

        Optional<ItemCarrinhoEntity> itemExistente = itemCarrinhoRepository.findByCarrinhoIdAndProdutoId(carrinho.getId(), produto.getId());

        if (itemExistente.isPresent()) {
            ItemCarrinhoEntity itemCarrinho = itemExistente.get();

            itemCarrinho.setQuantidade(itemCarrinho.getQuantidade() + dto.getQuantidade());

            itemCarrinhoRepository.save(itemCarrinho);
        } else {
            ItemCarrinhoEntity itemCarrinho = ItemCarrinhoEntity.builder()
                    .carrinho(carrinho)
                    .produto(produto)
                    .quantidade(dto.getQuantidade())
                    .build();
            itemCarrinhoRepository.save(itemCarrinho);
        }
    }

    public void alterarQuantidade(Integer clienteId, Integer itemId, Integer quantidade) {
        ItemCarrinhoEntity itemCarrinho = buscarItemDoCarrinho(clienteId, itemId);

        itemCarrinho.setQuantidade(quantidade);

        itemCarrinhoRepository.save(itemCarrinho);
    }

    public void removerItem(Integer clienteId, Integer itemId) {
        ItemCarrinhoEntity itemCarrinho = buscarItemDoCarrinho(clienteId, itemId);

        itemCarrinhoRepository.delete(itemCarrinho);
    }

    private CarrinhoEntity buscarEntidadePorId(Integer carrinhoId) throws NotFoundException {
        return carrinhoRepository.findById(carrinhoId)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));
    }

    private CarrinhoEntity buscarOuCriarCarrinho(Integer clienteId) {
        return carrinhoRepository
                .findByClienteId(clienteId).orElseGet(() -> criarCarrinho(clienteId));
    }

    private CarrinhoEntity criarCarrinho(Integer clienteId) {
        ClienteEntity cliente = clienteRepository
                .findById(clienteId)
                .orElseThrow(() -> new NotFoundException("Cliente não encontrado"));

        CarrinhoEntity carrinho = CarrinhoEntity.builder()
                .cliente(cliente)
                .build();

        return carrinhoRepository.save(carrinho);
    }

    private ProdutoEntity buscarProdutoPorId(Integer produtoId) {
        return produtoRepository
                .findById(produtoId)
                .orElseThrow(() -> new NotFoundException("Produto não encontrado"));
    }

    private ItemCarrinhoEntity buscarItemDoCarrinho(Integer clienteId, Integer itemId) {
        return itemCarrinhoRepository
                .findByIdAndCarrinhoClienteId(itemId, clienteId)
                .orElseThrow(() -> new NotFoundException("Item não encontrado no carrinho"));
    }
}
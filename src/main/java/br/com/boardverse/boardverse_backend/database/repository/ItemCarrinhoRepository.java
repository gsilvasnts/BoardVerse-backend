package br.com.boardverse.boardverse_backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.boardverse.boardverse_backend.database.model.ItemCarrinhoEntity;

public interface ItemCarrinhoRepository extends JpaRepository<ItemCarrinhoEntity, Integer> {

    Optional<ItemCarrinhoEntity> findByCarrinhoIdAndProdutoId(Integer carrinhoId, Integer produtoId);

    Optional<ItemCarrinhoEntity> findByIdAndCarrinhoClienteId(Integer itemId, Integer clienteId);

}

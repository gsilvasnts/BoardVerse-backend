package br.com.boardverse.boardverse_backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.boardverse.boardverse_backend.database.model.CarrinhoEntity;

public interface CarrinhoRepository extends JpaRepository<CarrinhoEntity, Integer>{
    
    Optional<CarrinhoEntity> findByClienteId(Integer clienteId);
}

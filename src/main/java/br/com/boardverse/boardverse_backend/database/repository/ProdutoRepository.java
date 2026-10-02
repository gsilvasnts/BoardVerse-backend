package br.com.boardverse.boardverse_backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.boardverse.boardverse_backend.database.model.ProdutoEntity;

public interface ProdutoRepository extends JpaRepository<ProdutoEntity, Integer>{
    
}

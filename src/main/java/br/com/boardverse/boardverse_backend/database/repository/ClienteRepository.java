package br.com.boardverse.boardverse_backend.database.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.boardverse.boardverse_backend.database.model.ClienteEntity;

public interface ClienteRepository extends JpaRepository<ClienteEntity, Integer> {

    Optional<ClienteEntity> findByEmail(String email);

    Optional<ClienteEntity> findByCpf(String cpf);

    boolean existsByCpf(String cpf);
}

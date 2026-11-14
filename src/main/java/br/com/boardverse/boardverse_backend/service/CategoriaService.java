package br.com.boardverse.boardverse_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.boardverse.boardverse_backend.database.model.CategoriaEntity;
import br.com.boardverse.boardverse_backend.database.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public List<CategoriaEntity> listarTodas() {
        return categoriaRepository.findAll();
    }
}

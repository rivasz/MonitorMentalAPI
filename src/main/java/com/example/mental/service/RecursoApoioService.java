package com.example.mental.service;

import com.example.mental.dto.*;
import com.example.mental.entity.RecursoApoio;
import com.example.mental.exception.ResourceNotFoundException;
import com.example.mental.repository.RecursoApoioRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecursoApoioService {

    private final RecursoApoioRepository repo;

    public RecursoApoioService(RecursoApoioRepository repo) {
        this.repo = repo;
    }

    public List<RecursoApoioResponseDTO> findAll() {
        return repo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public RecursoApoioResponseDTO findById(Long id) {
        RecursoApoio r = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
        return toDto(r);
    }

    public RecursoApoioResponseDTO create(RecursoApoioRequestDTO dto) {
        RecursoApoio r = toEntity(dto);
        return toDto(repo.save(r));
    }

    public RecursoApoioResponseDTO update(Long id, RecursoApoioRequestDTO dto) {
        RecursoApoio r = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
        r.setTitulo(dto.getTitulo());
        r.setTipo(dto.getTipo());
        r.setLink(dto.getLink());
        r.setDescricao(dto.getDescricao());
        return toDto(repo.save(r));
    }

    public void delete(Long id) {
        RecursoApoio r = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recurso não encontrado: " + id));
        repo.delete(r);
    }

    private RecursoApoioResponseDTO toDto(RecursoApoio r) {
        RecursoApoioResponseDTO dto = new RecursoApoioResponseDTO();
        dto.setId(r.getId());
        dto.setTitulo(r.getTitulo());
        dto.setTipo(r.getTipo());
        dto.setLink(r.getLink());
        dto.setDescricao(r.getDescricao());
        return dto;
    }

    private RecursoApoio toEntity(RecursoApoioRequestDTO dto) {
        RecursoApoio r = new RecursoApoio();
        r.setTitulo(dto.getTitulo());
        r.setTipo(dto.getTipo());
        r.setLink(dto.getLink());
        r.setDescricao(dto.getDescricao());
        return r;
    }
}

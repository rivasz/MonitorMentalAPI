package com.example.mental.service;

import com.example.mental.dto.*;
import com.example.mental.entity.Paciente;
import com.example.mental.exception.ResourceNotFoundException;
import com.example.mental.mapper.MapperUtil;
import com.example.mental.repository.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PacienteService {
    private final PacienteRepository repo;

    public PacienteService(PacienteRepository repo) {
        this.repo = repo;
    }

    public List<PacienteResponseDTO> findAll() {
        return repo.findAll().stream().map(MapperUtil::toDto).collect(Collectors.toList());
    }

    public PacienteResponseDTO findById(Long id) {
        Paciente p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado: " + id));
        return MapperUtil.toDto(p);
    }

    public PacienteResponseDTO create(PacienteRequestDTO dto) {
        Paciente p = MapperUtil.toEntity(dto);
        Paciente saved = repo.save(p);
        return MapperUtil.toDto(saved);
    }

    public PacienteResponseDTO update(Long id, PacienteRequestDTO dto) {
        Paciente p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado: " + id));
        p.setNome(dto.getNome());
        p.setEmail(dto.getEmail());
        p.setDataNascimento(dto.getDataNascimento());
        return MapperUtil.toDto(repo.save(p));
    }

    public void delete(Long id) {
        Paciente p = repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado: " + id));
        repo.delete(p);
    }
}

package com.example.mental.service;

import com.example.mental.dto.*;
import com.example.mental.entity.ProfissionalSaude;
import com.example.mental.exception.ResourceNotFoundException;
import com.example.mental.mapper.MapperUtil;
import com.example.mental.repository.ProfissionalSaudeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProfissionalSaudeService {

    private final ProfissionalSaudeRepository repo;

    public ProfissionalSaudeService(ProfissionalSaudeRepository repo) {
        this.repo = repo;
    }

    public List<ProfissionalSaudeResponseDTO> findAll() {
        return repo.findAll().stream().map(MapperUtil::toDto).collect(Collectors.toList());
    }

    public ProfissionalSaudeResponseDTO findById(Long id) {
        ProfissionalSaude p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado: " + id));
        return MapperUtil.toDto(p);
    }

    public ProfissionalSaudeResponseDTO create(ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaude p = MapperUtil.toEntity(dto);
        return MapperUtil.toDto(repo.save(p));
    }

    public ProfissionalSaudeResponseDTO update(Long id, ProfissionalSaudeRequestDTO dto) {
        ProfissionalSaude p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado: " + id));
        p.setNome(dto.getNome());
        p.setEmail(dto.getEmail());
        p.setEspecialidade(dto.getEspecialidade());
        return MapperUtil.toDto(repo.save(p));
    }

    public void delete(Long id) {
        ProfissionalSaude p = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado: " + id));
        repo.delete(p);
    }
}

package com.example.mental.service;

import com.example.mental.dto.*;
import com.example.mental.entity.*;
import com.example.mental.exception.ResourceNotFoundException;
import com.example.mental.mapper.MapperUtil;
import com.example.mental.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RegistroDiarioService {
    private final RegistroDiarioRepository registroRepo;
    private final PacienteRepository pacienteRepo;

    public RegistroDiarioService(RegistroDiarioRepository registroRepo, PacienteRepository pacienteRepo) {
        this.registroRepo = registroRepo;
        this.pacienteRepo = pacienteRepo;
    }

    public List<RegistroDiarioResponseDTO> findAll() {
        return registroRepo.findAll().stream().map(MapperUtil::toDto).collect(Collectors.toList());
    }

    public RegistroDiarioResponseDTO findById(Long id) {
        RegistroDiario r = registroRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado: " + id));
        return MapperUtil.toDto(r);
    }

    public RegistroDiarioResponseDTO create(RegistroDiarioRequestDTO dto) {
        Paciente p = pacienteRepo.findById(dto.getPacienteId()).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado: " + dto.getPacienteId()));
        RegistroDiario r = new RegistroDiario();
        r.setDataRegistro(dto.getDataRegistro());
        r.setNivelHumor(dto.getNivelHumor());
        r.setObservacao(dto.getObservacao());
        r.setPaciente(p);
        return MapperUtil.toDto(registroRepo.save(r));
    }

    public RegistroDiarioResponseDTO update(Long id, RegistroDiarioRequestDTO dto) {
        RegistroDiario r = registroRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado: " + id));
        Paciente p = pacienteRepo.findById(dto.getPacienteId()).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado: " + dto.getPacienteId()));
        r.setDataRegistro(dto.getDataRegistro());
        r.setNivelHumor(dto.getNivelHumor());
        r.setObservacao(dto.getObservacao());
        r.setPaciente(p);
        return MapperUtil.toDto(registroRepo.save(r));
    }

    public void delete(Long id) {
        RegistroDiario r = registroRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado: " + id));
        registroRepo.delete(r);
    }

    public List<RegistroDiarioResponseDTO> findByPacienteId(Long pacienteId) {
        return registroRepo.findByPacienteId(pacienteId).stream().map(MapperUtil::toDto).collect(Collectors.toList());
    }
}

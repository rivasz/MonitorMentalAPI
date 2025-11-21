package com.example.mental.service;

import com.example.mental.dto.*;
import com.example.mental.entity.*;
import com.example.mental.exception.ResourceNotFoundException;
import com.example.mental.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsultaService {

    private final ConsultaRepository consultaRepo;
    private final PacienteRepository pacienteRepo;
    private final ProfissionalSaudeRepository profissionalRepo;

    public ConsultaService(ConsultaRepository consultaRepo, PacienteRepository pacienteRepo, ProfissionalSaudeRepository profissionalRepo) {
        this.consultaRepo = consultaRepo;
        this.pacienteRepo = pacienteRepo;
        this.profissionalRepo = profissionalRepo;
    }

    public List<ConsultaResponseDTO> findAll() {
        return consultaRepo.findAll().stream().map(this::toDto).collect(Collectors.toList());
    }

    public ConsultaResponseDTO findById(Long id) {
        Consulta consulta = consultaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada: " + id));
        return toDto(consulta);
    }

    public ConsultaResponseDTO create(ConsultaRequestDTO dto) {
        Paciente paciente = pacienteRepo.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado: " + dto.getPacienteId()));
        ProfissionalSaude prof = profissionalRepo.findById(dto.getProfissionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado: " + dto.getProfissionalId()));

        Consulta c = new Consulta();
        c.setDataHora(dto.getDataHora());
        c.setStatus(StatusConsulta.valueOf(dto.getStatus()));
        c.setObservacao(dto.getObservacao());
        c.setPaciente(paciente);
        c.setProfissional(prof);

        return toDto(consultaRepo.save(c));
    }

    public ConsultaResponseDTO update(Long id, ConsultaRequestDTO dto) {
        Consulta c = consultaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada: " + id));

        Paciente paciente = pacienteRepo.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado: " + dto.getPacienteId()));
        ProfissionalSaude prof = profissionalRepo.findById(dto.getProfissionalId())
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado: " + dto.getProfissionalId()));

        c.setDataHora(dto.getDataHora());
        c.setStatus(StatusConsulta.valueOf(dto.getStatus()));
        c.setObservacao(dto.getObservacao());
        c.setPaciente(paciente);
        c.setProfissional(prof);

        return toDto(consultaRepo.save(c));
    }

    public void delete(Long id) {
        Consulta c = consultaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta não encontrada: " + id));
        consultaRepo.delete(c);
    }

    private ConsultaResponseDTO toDto(Consulta c) {
        ConsultaResponseDTO dto = new ConsultaResponseDTO();
        dto.setId(c.getId());
        dto.setDataHora(c.getDataHora());
        dto.setStatus(c.getStatus().name());
        dto.setObservacao(c.getObservacao());
        dto.setPacienteId(c.getPaciente().getId());
        dto.setProfissionalId(c.getProfissional().getId());
        return dto;
    }
}

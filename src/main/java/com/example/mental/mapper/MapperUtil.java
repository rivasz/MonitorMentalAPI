package com.example.mental.mapper;

import com.example.mental.dto.*;
import com.example.mental.entity.*;

public class MapperUtil {

    // Paciente
    public static PacienteResponseDTO toDto(Paciente p) {
        if (p == null) return null;
        PacienteResponseDTO dto = new PacienteResponseDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setEmail(p.getEmail());
        dto.setDataNascimento(p.getDataNascimento());
        return dto;
    }

    public static Paciente toEntity(PacienteRequestDTO dto) {
        if (dto == null) return null;
        Paciente p = new Paciente();
        p.setNome(dto.getNome());
        p.setEmail(dto.getEmail());
        p.setDataNascimento(dto.getDataNascimento());
        return p;
    }

    // RegistroDiario
    public static RegistroDiarioResponseDTO toDto(RegistroDiario r) {
        if (r == null) return null;
        RegistroDiarioResponseDTO dto = new RegistroDiarioResponseDTO();
        dto.setId(r.getId());
        dto.setDataRegistro(r.getDataRegistro());
        dto.setNivelHumor(r.getNivelHumor());
        dto.setObservacao(r.getObservacao());
        if (r.getPaciente() != null) dto.setPacienteId(r.getPaciente().getId());
        return dto;
    }

    // ProfissionalSaude
    public static ProfissionalSaudeResponseDTO toDto(ProfissionalSaude p) {
        if (p == null) return null;
        ProfissionalSaudeResponseDTO dto = new ProfissionalSaudeResponseDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setEmail(p.getEmail());
        dto.setEspecialidade(p.getEspecialidade());
        return dto;
    }

    public static ProfissionalSaude toEntity(ProfissionalSaudeRequestDTO dto) {
        if (dto == null) return null;
        ProfissionalSaude p = new ProfissionalSaude();
        p.setNome(dto.getNome());
        p.setEmail(dto.getEmail());
        p.setEspecialidade(dto.getEspecialidade());
        return p;
    }

}

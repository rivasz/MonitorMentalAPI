package com.example.mental.repository;

import com.example.mental.entity.RegistroDiario;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistroDiarioRepository extends JpaRepository<RegistroDiario, Long> {
    List<RegistroDiario> findByPacienteId(Long pacienteId);
}

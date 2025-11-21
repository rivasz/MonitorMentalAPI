package com.example.mental.config;

import com.example.mental.entity.*;
import com.example.mental.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {

    private final PacienteRepository pacienteRepo;
    private final RegistroDiarioRepository registroRepo;

    public DataSeeder(PacienteRepository pacienteRepo, RegistroDiarioRepository registroRepo) {
        this.pacienteRepo = pacienteRepo;
        this.registroRepo = registroRepo;
    }

    @Override
    public void run(String... args) throws Exception {
        // evita duplicar seeds se já existir
        if (pacienteRepo.count() == 0) {
            Paciente p1 = new Paciente();
            p1.setNome("Ana Silva");
            p1.setEmail("ana.silva@example.com");
            p1.setDataNascimento(LocalDate.of(1990, 5, 10));
            pacienteRepo.save(p1);

            Paciente p2 = new Paciente();
            p2.setNome("Carlos Pereira");
            p2.setEmail("carlos.p@example.com");
            p2.setDataNascimento(LocalDate.of(1985, 8, 20));
            pacienteRepo.save(p2);

            RegistroDiario r1 = new RegistroDiario();
            r1.setDataRegistro(LocalDate.now().minusDays(1));
            r1.setNivelHumor(3);
            r1.setObservacao("Dia cansativo, mas ok.");
            r1.setPaciente(p1);
            registroRepo.save(r1);

            RegistroDiario r2 = new RegistroDiario();
            r2.setDataRegistro(LocalDate.now());
            r2.setNivelHumor(4);
            r2.setObservacao("Melhorou após caminhada.");
            r2.setPaciente(p1);
            registroRepo.save(r2);

            RegistroDiario r3 = new RegistroDiario();
            r3.setDataRegistro(LocalDate.now());
            r3.setNivelHumor(2);
            r3.setObservacao("Ansioso pela manhã.");
            r3.setPaciente(p2);
            registroRepo.save(r3);
        }
    }
}

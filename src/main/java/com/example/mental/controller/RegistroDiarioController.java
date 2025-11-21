package com.example.mental.controller;

import com.example.mental.dto.*;
import com.example.mental.service.RegistroDiarioService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/registros")
public class RegistroDiarioController {

    private final RegistroDiarioService service;

    public RegistroDiarioController(RegistroDiarioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RegistroDiarioResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // outros endpoints omitidos
}

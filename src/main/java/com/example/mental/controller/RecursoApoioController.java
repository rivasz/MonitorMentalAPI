package com.example.mental.controller;

import com.example.mental.dto.*;
import com.example.mental.service.RecursoApoioService;
import jakarta.validation.Valid;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/recursos")
public class RecursoApoioController {

    private final RecursoApoioService service;

    public RecursoApoioController(RecursoApoioService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RecursoApoioResponseDTO>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecursoApoioResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<RecursoApoioResponseDTO> create(@Valid @RequestBody RecursoApoioRequestDTO dto) {
        RecursoApoioResponseDTO created = service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecursoApoioResponseDTO> update(@PathVariable Long id, @Valid @RequestBody RecursoApoioRequestDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

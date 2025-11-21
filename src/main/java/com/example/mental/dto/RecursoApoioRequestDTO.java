package com.example.mental.dto;

import jakarta.validation.constraints.NotBlank;

public class RecursoApoioRequestDTO {
    @NotBlank
    private String titulo;
    @NotBlank
    private String tipo;
    @NotBlank
    private String link;
    private String descricao;

    // Getters e setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}

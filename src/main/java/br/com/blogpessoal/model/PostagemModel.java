package br.com.blogpessoal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

@Entity(name = "tb_postagens")
public class PostagemModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull(message = "Titulo não pode ficar em branco")
    @Size(min = 5, max = 60, message = "Titulo deve estar entre 5 a 60 caracteres")
    private String titulo;
    @NotNull(message = "Texto não pode ficar em branco")
    @Size(min = 1, max = 255, message = "Texto deve estar entre 1 a 255 caracteres")
    private String texto;
    @NotNull(message = "Data não pode ficar em branco")
    private LocalDateTime data;

    public PostagemModel() {}
    public PostagemModel(long id, String titulo, String texto, LocalDateTime data) {
        this.id = id;
        this.titulo = titulo;
        this.texto = texto;
        this.data = data;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
}

package br.com.blogpessoal.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record TemaUpdateDto(
        @NotNull(message = "O campo id de tema é obrigatório para  atualizar")
        long id,
        @NotNull(message = "Insira a descrição de tema para atualizar")
        @Size(min = 5, message = "Mínimo de 5 caracteres para criar a descrição")
        String descricao
) { }

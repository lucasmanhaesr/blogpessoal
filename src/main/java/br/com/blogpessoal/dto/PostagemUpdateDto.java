package br.com.blogpessoal.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDateTime;

public record PostagemUpdateDto(
        @NotNull(message = "O campo id é obrigatório para atualização de postagem")
        long id,
        @NotNull(message = "Titulo não pode ficar em branco")
        @Size(min = 5, max = 60, message = "Titulo deve estar entre 5 a 60 caracteres")
        String titulo,
        @NotNull(message = "Texto não pode ficar em branco")
        @Size(min = 1, max = 255, message = "Texto deve estar entre 1 a 255 caracteres")
        String texto,
        @NotNull(message = "Data não pode ficar em branco ex: YYYY-MM-DDT00:00:00")
        LocalDateTime data
) {}

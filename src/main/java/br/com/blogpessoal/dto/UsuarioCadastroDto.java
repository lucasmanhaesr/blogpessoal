package br.com.blogpessoal.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UsuarioCadastroDto(
    @NotNull(message = "O Atributo Nome é Obrigatório!")
    String nome,

    @NotNull(message = "O Atributo Usuário é Obrigatório!")
    @Email(message = "O Atributo Usuário deve ser um email válido!")
    String usuario,

    @NotBlank(message = "O Atributo Senha é Obrigatório!")
    @Size(min = 8, message = "A Senha deve ter no mínimo 8 caracteres")
    String senha,

    @Size(max = 5000, message = "O link da foto não pode ser maior do que 5000 caracteres")
    String foto
) {
}

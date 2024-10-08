package br.edu.ifsul.cstsi.yuri_tads.api.autenticacao;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record UsuarioDTO(
    @Email @NotBlank
    String email,
    @NotBlank
    String senha) {
}

package com.microservice.ms_usuario.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.UUID;

public record UsuarioRecordDto(@NotBlank String name,
                               @NotBlank @Email String email) {
}

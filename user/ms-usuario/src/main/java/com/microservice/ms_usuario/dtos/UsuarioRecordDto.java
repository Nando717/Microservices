package com.microservice.ms_usuario.dtos;

import java.util.UUID;

public record UsuarioRecordDto(UUID userID,String name, String email) {
}

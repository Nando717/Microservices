package com.microservice.ms_usuario.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmailDto {

    private UUID userID;
    private String emailTo;
    private String subject;
    private String text;


}

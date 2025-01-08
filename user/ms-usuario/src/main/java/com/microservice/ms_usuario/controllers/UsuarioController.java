package com.microservice.ms_usuario.controllers;


import com.microservice.ms_usuario.dtos.UsuarioRecordDto;
import com.microservice.ms_usuario.models.UsuarioModel;
import com.microservice.ms_usuario.repositories.UsuarioRepository;
import com.microservice.ms_usuario.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/app")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    UsuarioService usuarioService;


    @PostMapping("/usuario")
    public ResponseEntity<UsuarioModel>criarUsuario(@RequestBody @Valid UsuarioRecordDto usuarioRecordDto){


        var usuarioModel = new UsuarioModel();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);

        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.criarUsuario(usuarioModel));
    }

    @GetMapping("/usuarios")
    public ResponseEntity<List<UsuarioModel>>listarUsuario(UsuarioModel usuarioModel){

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarUsuario(usuarioModel));
    }


}

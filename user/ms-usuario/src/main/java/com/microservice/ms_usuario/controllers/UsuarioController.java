package com.microservice.ms_usuario.controllers;


import com.microservice.ms_usuario.dtos.UsuarioRecordDto;
import com.microservice.ms_usuario.models.UsuarioModel;
import com.microservice.ms_usuario.repositories.UsuarioRepository;
import com.microservice.ms_usuario.services.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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


    @GetMapping("/usuario/{id}")
    public ResponseEntity<Object>ListarPorId(@PathVariable(value = "id")UUID id){
        Optional<UsuarioModel> usU = usuarioRepository.findById(id);

        if (usU.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario não encontrado");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.listarPorId(id));
    }

    @PutMapping("/usuario/{id}")
    public ResponseEntity<Object>atualizarUsuario(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid UsuarioRecordDto usuarioRecordDto){

        Optional<UsuarioModel>usU = usuarioRepository.findById(id);

        if (usU.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario não encontrado");

        }

        var usuarioModel = usU.get();
        BeanUtils.copyProperties(usuarioRecordDto, usuarioModel);

        return ResponseEntity.status(HttpStatus.OK).body(usuarioRepository.save(usuarioModel));




    }

    @DeleteMapping("/usuario/{id}")
    public ResponseEntity<Object>deletarUsuario(@PathVariable(value = "id") UUID id){

        Optional<UsuarioModel>usU = usuarioRepository.findById(id);

        if (usU.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("usuario não encontrado");
        }

        usuarioRepository.delete(usU.get());

        return ResponseEntity.status(HttpStatus.OK).body("usuario deletado");
    }


}

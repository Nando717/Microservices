package com.microservice.ms_usuario.services;


import com.microservice.ms_usuario.models.UsuarioModel;
import com.microservice.ms_usuario.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;



    @Transactional
    public UsuarioModel criarUsuario(UsuarioModel usuarioModel){

        return  usuarioRepository.save(usuarioModel);
    }


    @Transactional
    public List<UsuarioModel> listarUsuario(UsuarioModel usuarioModel){

        return usuarioRepository.findAll();
    }
}

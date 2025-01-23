package com.microservice.ms_usuario.producers;

import com.microservice.ms_usuario.dtos.EmailDto;
import com.microservice.ms_usuario.models.UsuarioModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UsuarioProducer {


   final RabbitTemplate rabbitTemplate;
   public UsuarioProducer(RabbitTemplate rabbitTemplate){
       this.rabbitTemplate = rabbitTemplate;
   }


    @Value(value = "${broker.queue.email.name}")
    private String routingKey;


    public void publishMenssageEmail(UsuarioModel usuarioModel){
        var emailDto = new EmailDto();
        emailDto.setUserID(usuarioModel.getUserId());
        emailDto.setEmailTo(usuarioModel.getEmail());
        emailDto.setSubject("Cadastro Realizado Com Sucesso");
        emailDto.setText(usuarioModel.getName()+ ", seja Bem Vindo(a)! \nAgradeçemos o seu cadastro, aproveite agora todos os recursos da plataforma");

          rabbitTemplate.convertAndSend("", routingKey, emailDto);
    }
}

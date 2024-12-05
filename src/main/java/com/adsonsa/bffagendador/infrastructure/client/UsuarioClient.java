package com.adsonsa.bffagendador.infrastructure.client;

import com.adsonsa.bffagendador.business.dto.in.EnderecoDTORequest;
import com.adsonsa.bffagendador.business.dto.in.LoginRequestDTO;
import com.adsonsa.bffagendador.business.dto.in.TelefoneDTORequest;
import com.adsonsa.bffagendador.business.dto.in.UsuarioDTORequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "usuario", url = "${usuario.url}")
public interface UsuarioClient {


    @GetMapping
    UsuarioDTORequest buscarPorEmail(@RequestParam("email") String email,
                                     @RequestHeader("Authorization") String token);
    @PostMapping
    UsuarioDTORequest salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO);


    // metodo para fazer login
    @PostMapping("/login")
    String login(@RequestBody LoginRequestDTO usuarioDTO);

    // deletar usuario pelo email
    @DeleteMapping("/{email}")
    void deletarPorEmail(@PathVariable String email,
                         @RequestHeader("Authorization") String token);
    // update de usuario
    @PutMapping
    UsuarioDTORequest atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                           @RequestHeader("Authorization") String token);
    // metodo para atualizar endereco id
    @PutMapping("/endereco")
    EnderecoDTORequest atualizaEndereco(@RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id,
                                        @RequestHeader("Authorization") String token);
    // metodo para atualizar telefone id
    @PutMapping("/telefone")
    TelefoneDTORequest atualizaTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id,
                                        @RequestHeader("Authorization") String token);

    // Metodo para cadastrar novo endereco
    @PostMapping("/endereco")
    EnderecoDTORequest cadastroEnderco(@RequestBody EnderecoDTORequest dto, @RequestHeader("Authorization") String token);
    // Metodo para cadastrar novo telefone
    @PostMapping("/telefone")
    TelefoneDTORequest cadastroTelefone(@RequestBody TelefoneDTORequest dto, @RequestHeader("Authorization") String token);
}

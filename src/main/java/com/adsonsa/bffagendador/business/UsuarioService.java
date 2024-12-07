package com.adsonsa.bffagendador.business;

import com.adsonsa.bffagendador.business.dto.in.EnderecoDTORequest;
import com.adsonsa.bffagendador.business.dto.in.LoginRequestDTO;
import com.adsonsa.bffagendador.business.dto.in.TelefoneDTORequest;
import com.adsonsa.bffagendador.business.dto.in.UsuarioDTORequest;
import com.adsonsa.bffagendador.infrastructure.client.UsuarioClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioClient client;

    public UsuarioDTORequest salvaUsuario(UsuarioDTORequest usuarioDTO) {

        return client.salvaUsuario(usuarioDTO);
    }
    // metodo para fazer login
    public String login(LoginRequestDTO usuarioDTO){
        return client.login(usuarioDTO);
    }


    public UsuarioDTORequest buscarUsuarioPorEmail(String email, String token) {

        return client.buscarPorEmail(email, token);
    }
    // deletar usuario pelo email
    public void deletarUsuarioPorEmail(String email, String token){
        client.deletarPorEmail(email, token);
    }
    public UsuarioDTORequest atualizaDadosUsuario(String token , UsuarioDTORequest dto){

        return client.atualizaDadosUsuario(dto, token);
    }
    public EnderecoDTORequest atualizaEndereco(Long id_endereco, EnderecoDTORequest dto, String token){

        return client.atualizaEndereco(dto, id_endereco, token);

    }
    public TelefoneDTORequest atualizaTelefone(Long id_telefone, TelefoneDTORequest dto, String token){

        return client.atualizaTelefone(dto, id_telefone, token);
    }
    // Cadastro novo endereco
    public EnderecoDTORequest cadastraEndereco(String token, EnderecoDTORequest dto){

        return client.cadastroEnderco(dto, token);
    }
    // Cadastro novo telefone
    public TelefoneDTORequest cadastraTelefone(String token, TelefoneDTORequest dto){

        return client.cadastroTelefone(dto, token);
    }
}

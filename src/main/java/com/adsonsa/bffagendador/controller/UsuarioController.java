package com.adsonsa.bffagendador.controller;

import com.adsonsa.bffagendador.business.UsuarioService;
import com.adsonsa.bffagendador.business.dto.in.EnderecoDTORequest;
import com.adsonsa.bffagendador.business.dto.in.LoginRequestDTO;
import com.adsonsa.bffagendador.business.dto.in.TelefoneDTORequest;
import com.adsonsa.bffagendador.business.dto.in.UsuarioDTORequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
@Tag(name = "Usuario", description = "Cadastro e login e usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    // metodo para cadastrar um novo usuario
    @PostMapping
    @Operation(summary = "Salvar Usuarios", description = "Criar um novo usuario")
    @ApiResponse(responseCode = "200", description = "Usuario salvo com sucesso")
    @ApiResponse(responseCode = "400", description = "Usuario já cadastrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTORequest> salvaUsuario(@RequestBody UsuarioDTORequest usuarioDTO) {
        return ResponseEntity.ok(usuarioService.salvaUsuario(usuarioDTO));
    }

    // metodo para fazer login
    @PostMapping("/login")
    @Operation(summary = "Login Usuarios", description = "Login do usuario")
    @ApiResponse(responseCode = "200", description = "Usuario logado com sucesso")
    @ApiResponse(responseCode = "401", description = "Credenciais enválidas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public String login(@RequestBody LoginRequestDTO usuarioDTO){
        return usuarioService.login(usuarioDTO);
    }

    // metodo para buscar usuario pelo email
    @GetMapping
    @Operation(summary = "Buscar dados de Usurios por email", description = "Buscar Dados do Usuario")
    @ApiResponse(responseCode = "200", description = "Usuario encontrado")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTORequest> buscarPorEmail(@RequestParam("email") String email,
                                                            @RequestHeader(name = "Authorization", required =false) String token){
        return ResponseEntity.ok(usuarioService.buscarUsuarioPorEmail(email, token));
    }
    // deletar usuario pelo email
    @DeleteMapping("/{email}")
    @Operation(summary = "Deleta Usuarios por Id", description = "Deleta usuario")
    @ApiResponse(responseCode = "200", description = "Usuario atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<Void> deletarPorEmail(@PathVariable String email,
                                                @RequestHeader(name = "Authorization", required =false) String token){
        usuarioService.deletarUsuarioPorEmail(email, token);
        return ResponseEntity.ok().build();
    }
    // update de usuario
    @PutMapping
    @Operation(summary = "Atualizar dados do Usuarios", description = "Atualizar dados do Usuarios")
    @ApiResponse(responseCode = "200", description = "Usuario deletado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<UsuarioDTORequest> atualizaDadosUsuario(@RequestBody UsuarioDTORequest dto,
                                                                  @RequestHeader(name = "Authorization", required =false) String token){
        return ResponseEntity.ok(usuarioService.atualizaDadosUsuario(token, dto));
    }
    // metodo para atualizar endereco id
    @PutMapping("/endereco")
    @Operation(summary = "Atualizar endereço do Usuarios", description = "Atualizar endereço do Usuarios")
    @ApiResponse(responseCode = "200", description = "Endereço Atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTORequest> atualizaEndereco(@RequestBody EnderecoDTORequest dto, @RequestParam("id") Long id,
                                                               @RequestHeader(name = "Authorization", required =false) String token){
        return ResponseEntity.ok(usuarioService.atualizaEndereco(id, dto, token));
    }
    // metodo para atualizar telefone id
    @PutMapping("/telefone")
    @Operation(summary = "Atualizar telefone do Usuarios", description = "Atualizar telefone do Usuarios")
    @ApiResponse(responseCode = "200", description = "telefone Atualizado com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTORequest> atualizaTelefone(@RequestBody TelefoneDTORequest dto, @RequestParam("id") Long id,
                                                               @RequestHeader(name = "Authorization", required =false) String token){
        return ResponseEntity.ok(usuarioService.atualizaTelefone(id, dto, token));
    }

    // Metodo para cadastrar novo endereco
    @PostMapping("/endereco")
    @Operation(summary = "Salvar endereco do Usuario", description = "Salvar endereco do Usuario")
    @ApiResponse(responseCode = "200", description = "Endereço salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<EnderecoDTORequest> cadastroEnderco(@RequestBody EnderecoDTORequest dto, @RequestHeader(name = "Authorization", required =false




    ) String token){
        return ResponseEntity.ok(usuarioService.cadastraEndereco(token, dto));
    }
    // Metodo para cadastrar novo telefone
    @PostMapping("/telefone")
    @Operation(summary = "Salvar telefone do Usuario", description = "Salvar telefone do Usuario")
    @ApiResponse(responseCode = "200", description = "telefone salvo com sucesso")
    @ApiResponse(responseCode = "404", description = "Usuario não encontrado")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TelefoneDTORequest> cadastroTelefone(@RequestBody TelefoneDTORequest dto, @RequestHeader(name = "Authorization", required =false) String token){
        return ResponseEntity.ok(usuarioService.cadastraTelefone(token, dto));
    }
}

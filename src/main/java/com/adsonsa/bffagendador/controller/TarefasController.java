package com.adsonsa.bffagendador.controller;

import com.adsonsa.bffagendador.business.TarefasService;
import com.adsonsa.bffagendador.business.dto.in.TarefasDTORequest;
import com.adsonsa.bffagendador.business.dto.out.TarefasDTOResponse;
import com.adsonsa.bffagendador.infrastructure.enums.StatusNotificacaoEnums;
import com.adsonsa.bffagendador.infrastructure.security.SecurityConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
@Tag(name = "Tarefas", description = "Cadastro tarefas de usuários")
@SecurityRequirement(name = SecurityConfig.SECURITY_SCHEME)
public class TarefasController {

    private final TarefasService tarefaService;


    @PostMapping
    @Operation(summary = "Salvar Tarefas de Usuarios", description = "Criar uma nova tarefa")
    @ApiResponse(responseCode = "200", description = "Tarefa salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefasDTOResponse> gravraTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestHeader(name = "Authorization", required =false) String token) {
        return ResponseEntity.ok(tarefaService.gravarTarefa(token, dto));
    }

    // metodo de para pegar eventos
    @GetMapping("/eventos")
    @Operation(summary = "Busca Tarefas por Período", description = "Busca Tarefas cadastradas por Período")
    @ApiResponse(responseCode = "200", description = "Tarefas encontradas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscarTarefaAgendadasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader(name = "Authorization", required =false) String token) {

        return ResponseEntity.ok(tarefaService.buscarTarefaAgendadasPorPeriodo(dataInicial, dataFinal, token));
    }

    // buscar Tarefas por email
    @GetMapping
    @Operation(summary = "Busca lista de Tarefas por email de Usuario", description = "Busca tarefas cadastradas por usuário")
    @ApiResponse(responseCode = "200", description = "Tarefa salvo com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "403", description = "Email não encontrado")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<List<TarefasDTOResponse>> buscaTarefaPorEmail(@RequestHeader(name = "Authorization", required =false) String token) {
        return ResponseEntity.ok(tarefaService.buscaTarefaPorEmail(token));
    }

    // Deletar por id
    @DeleteMapping
    @Operation(summary = "Delete tarefas por Id", description = "Deleta tarefas cadastradas por ID")
    @ApiResponse(responseCode = "200", description = "Tarefa deletadas")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<Void> deletarTarefaPorId(@RequestParam("id") String id,
                                                   @RequestHeader(name = "Authorization", required =false) String token) {
        tarefaService.deletarTarefaPorId(id, token);
        return ResponseEntity.ok().build();
    }

    // medotodo de alterar  tarefas
    @PatchMapping
    @Operation(summary = "Altera status de Tarefas", description = "Altera status de Tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Status da alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    @ApiResponse(responseCode = "403", description = "Tarefa não encontrada")
    @ApiResponse(responseCode = "401", description = "Usuario não autorizado")
    public ResponseEntity<TarefasDTOResponse> alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnums Status,
                                                                       @RequestParam("id") String id,
                                                                       @RequestHeader(name = "Authorization", required =false) String token) {
        return ResponseEntity.ok(tarefaService.alteraStatus(id, Status, token));
    }

    // metodo de alterar tarefas Put
    @PutMapping
    @Operation(summary = "Altera dados de Tarefas", description = "Altera dados de Tarefas cadastradas")
    @ApiResponse(responseCode = "200", description = "Tarefa alterada com sucesso")
    @ApiResponse(responseCode = "500", description = "Erro no servidor")
    public ResponseEntity<TarefasDTOResponse> updateTarefas(@RequestBody TarefasDTORequest dto,
                                                            @RequestParam("id") String id,
                                                            @RequestHeader(name = "Authorization", required =false) String token) {
        return ResponseEntity.ok(tarefaService.updateTarefas(id, dto, token));
    }
}


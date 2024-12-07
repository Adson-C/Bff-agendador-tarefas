package com.adsonsa.bffagendador.infrastructure.client;

import com.adsonsa.bffagendador.business.dto.in.TarefasDTORequest;
import com.adsonsa.bffagendador.business.dto.out.TarefasDTOResponse;
import com.adsonsa.bffagendador.infrastructure.enums.StatusNotificacaoEnums;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@FeignClient(name = "agendador-tarefas", url = "${agendador-tarefas.url}")
public interface TarefaClient {

    @PostMapping
    TarefasDTOResponse gravraTarefas(@RequestBody TarefasDTORequest dto,
                                     @RequestHeader("Authorization") String token);

    // metodo de para pegar eventos
    @GetMapping("/eventos")
    List<TarefasDTOResponse> buscarTarefaAgendadasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dataFinal,
            @RequestHeader("Authorization") String token);

    // buscar Tarefas por email
    @GetMapping
    List<TarefasDTOResponse> buscaTarefaPorEmail(@RequestHeader("Authorization") String token);

    // Deletar por id
    @DeleteMapping
    void deletarTarefaPorId(@RequestParam("id") String id, @RequestHeader("Authorization") String token);

    // medotodo de alterar  tarefas
    @PatchMapping
    TarefasDTOResponse alterarStatusNotificacao(@RequestParam("status") StatusNotificacaoEnums Status,
                                                @RequestParam("id") String id,
                                                @RequestHeader("Authorization") String token);

    // metodo de alterar tarefas Put
    @PutMapping
    TarefasDTOResponse updateTarefas(@RequestBody TarefasDTORequest dto, @RequestParam("id") String id, @RequestHeader("Authorization") String token);
}

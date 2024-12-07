package com.adsonsa.bffagendador.business;
import com.adsonsa.bffagendador.business.dto.in.TarefasDTORequest;
import com.adsonsa.bffagendador.business.dto.out.TarefasDTOResponse;
import com.adsonsa.bffagendador.infrastructure.client.TarefaClient;
import com.adsonsa.bffagendador.infrastructure.enums.StatusNotificacaoEnums;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefaClient tarefaClient;

    public TarefasDTOResponse gravarTarefa(String token, TarefasDTORequest dto) {
        return tarefaClient.gravraTarefas(dto, token);
    }

    // metodo para buscar por tarefas por dataCriacao
    public List<TarefasDTOResponse> buscarTarefaAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal, String token) {

        return tarefaClient.buscarTarefaAgendadasPorPeriodo(dataInicial, dataFinal, token);
    }

    // metodo para deletar por email
    public List<TarefasDTOResponse> buscaTarefaPorEmail(String token) {
        return tarefaClient.buscaTarefaPorEmail(token);
    }

    //  deleta tarefa por Id
    public void deletarTarefaPorId(String id, String token) {
        tarefaClient.deletarTarefaPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(String id, StatusNotificacaoEnums status, String token) {
        return tarefaClient.alterarStatusNotificacao(status, id, token);
    }

    // upadate de tarefas **
    public TarefasDTOResponse updateTarefas(String id, TarefasDTORequest dto, String token) {
        return tarefaClient.updateTarefas(dto, id, token);
    }
}

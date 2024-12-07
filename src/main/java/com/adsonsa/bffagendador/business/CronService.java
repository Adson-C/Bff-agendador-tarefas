package com.adsonsa.bffagendador.business;

import com.adsonsa.bffagendador.business.dto.in.LoginRequestDTO;
import com.adsonsa.bffagendador.business.dto.out.TarefasDTOResponse;
import com.adsonsa.bffagendador.infrastructure.enums.StatusNotificacaoEnums;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {

    private final TarefasService tarefasService;
    private final EmailService emailService;
    private final UsuarioService usuarioService;

    @Value("${usuario.email}")
    private String email;
    @Value("${usuario.senha}")
    private String senha;

    @Scheduled(cron = "${cron.horario}")
    public void buscarTarefasProximaHora(){

        String token = login(converterParaRequestDTO());
        log.info("Iniciando busca de tarefas");

        LocalDateTime horaFtura = LocalDateTime.now().plusHours(1);
        LocalDateTime horaFturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
        List<TarefasDTOResponse> lisaTarefas = tarefasService.buscarTarefaAgendadasPorPeriodo(horaFtura, horaFturaMaisCinco,token);
        log.info("Tarefas encontradas: " + lisaTarefas.size());
        lisaTarefas.forEach(tarefa -> {
            emailService.enviarEmail(tarefa);
            log.info("Email enviado para usuário: " + tarefa.getEmailUsuario());
            tarefasService.alteraStatus(tarefa.getId(), StatusNotificacaoEnums.NOTIFICADO,token);
        });
        log.info("Busca de tarefas finalizada");
    }
    public String login(LoginRequestDTO dto){
        return usuarioService.login(dto);
    }
    public LoginRequestDTO converterParaRequestDTO(){
        return LoginRequestDTO.builder()
                .email(email)
                .senha(senha)
                .build();
    }
}

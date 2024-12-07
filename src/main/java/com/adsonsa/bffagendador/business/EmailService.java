package com.adsonsa.bffagendador.business;

import com.adsonsa.bffagendador.business.dto.in.TarefasDTORequest;
import com.adsonsa.bffagendador.business.dto.out.TarefasDTOResponse;
import com.adsonsa.bffagendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviarEmail(TarefasDTOResponse dto) {
        emailClient.enviarEmail(dto);
    }

}

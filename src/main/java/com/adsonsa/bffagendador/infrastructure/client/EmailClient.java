package com.adsonsa.bffagendador.infrastructure.client;

import com.adsonsa.bffagendador.business.dto.out.TarefasDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notificaco", url = "${notificaco.url}")
public interface EmailClient {

    @PostMapping
    void enviarEmail(@RequestBody TarefasDTOResponse dto);
}

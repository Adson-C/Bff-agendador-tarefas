package com.adsonsa.bffagendador.infrastructure.client.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    public FeignError feignError(){
        return new FeignError();
    }
}

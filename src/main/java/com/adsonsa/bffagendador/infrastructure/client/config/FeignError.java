package com.adsonsa.bffagendador.infrastructure.client.config;

import com.adsonsa.bffagendador.infrastructure.exceptions.BusinessException;
import com.adsonsa.bffagendador.infrastructure.exceptions.ConflictException;
import com.adsonsa.bffagendador.infrastructure.exceptions.ResourceNotFoundException;
import com.adsonsa.bffagendador.infrastructure.exceptions.UnauthorizedException;
import feign.Response;
import feign.codec.ErrorDecoder;

public class FeignError implements ErrorDecoder {
    @Override
    public Exception decode(String s, Response response) {

        switch (response.status()){
            case 409:
                return new ConflictException("Erro atributo já existente");
            case 403:
                return new ResourceNotFoundException("Erro atribudo não encontrado");
            case 401:
                return new UnauthorizedException("Erro usuario não autorizado");
            default:
                return new BusinessException("Erro de servidor");
        }
    }
}

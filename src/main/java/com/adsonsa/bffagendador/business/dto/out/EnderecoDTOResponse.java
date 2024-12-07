package com.adsonsa.bffagendador.business.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EnderecoDTOResponse {

    private String rua;
    private Long numero;
    private String complemento;

    private String cidade;

    private String estado;
    private String cep;

}

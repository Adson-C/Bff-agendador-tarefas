package com.adsonsa.bffagendador.business.dto.out;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTOResponse {

    private String nome;
    private String email;
    private String senha;

    private List<EnderecoDTOResponse> enderecos;
    private List<TelefoneDTOResponse>  telefones;
}

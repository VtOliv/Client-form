package com.project.forms.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressValidationResponse {
    private String cep;
    
    private String logradouro;
    
    private String complemento;
    
    private String bairro;
    
    private String localidade;
    
    private String uf;
    
    private Boolean erro;

    public Boolean getErro() {
        return erro != null && erro;
    }
}

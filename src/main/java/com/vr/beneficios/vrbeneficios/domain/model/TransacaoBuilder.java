package com.vr.beneficios.vrbeneficios.domain.model;

import com.vr.beneficios.vrbeneficios.api.model.input.TransacaoInput;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransacaoBuilder {

    private TransacaoInput transacaoInput;
    private CartaoRepository cartaoRepository;
    
}

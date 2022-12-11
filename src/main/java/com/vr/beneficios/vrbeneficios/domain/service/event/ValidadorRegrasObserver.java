package com.vr.beneficios.vrbeneficios.domain.service.event;

import org.springframework.context.ApplicationEvent;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import com.vr.beneficios.vrbeneficios.domain.model.TransacaoBuilder;
import com.vr.beneficios.vrbeneficios.api.model.input.TransacaoInput;
import com.vr.beneficios.vrbeneficios.domain.exception.DefaultException;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;

import org.springframework.http.HttpStatus;

public class ValidadorRegrasObserver extends ApplicationEvent {

    private final CartaoRepository cartaoRepository;
    private final TransacaoInput transacaoInput;

    public ValidadorRegrasObserver(Object source, TransacaoBuilder item) {
        super(source);
        this.cartaoRepository = item.getCartaoRepository();
        this.transacaoInput = item.getTransacaoInput();
        this.validarCartao(transacaoInput);
        this.validarSenha(transacaoInput);
        this.validarSaldo(transacaoInput);
    }

    public void validarCartao(TransacaoInput transacaoInput){
        cartaoRepository.findById(transacaoInput.getNumeroCartao())
             .orElseThrow(() -> new DefaultException(HttpStatus.UNPROCESSABLE_ENTITY,"CARTAO_INEXISTENTE"));
     }
 
     public void validarSenha(TransacaoInput transacaoInput){
         cartaoRepository.findByNumeroCartaoAndSenha(transacaoInput.getNumeroCartao(),transacaoInput.getSenhaCartao())
             .orElseThrow(() -> new DefaultException(HttpStatus.UNPROCESSABLE_ENTITY,"SENHA_INVALIDA"));
     }
 
     public void validarSaldo(TransacaoInput transacaoInput){
         cartaoRepository.findByNumeroCartaoAndSaldoGreaterThanEqual(transacaoInput.getNumeroCartao(),transacaoInput.getValor())
             .orElseThrow(() -> new DefaultException(HttpStatus.UNPROCESSABLE_ENTITY,"SALDO_INSUFICIENTE"));
     }
 
    
}

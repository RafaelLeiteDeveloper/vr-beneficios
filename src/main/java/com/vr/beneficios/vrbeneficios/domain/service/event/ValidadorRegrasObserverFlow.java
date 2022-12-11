package com.vr.beneficios.vrbeneficios.domain.service.event;

import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;
import com.vr.beneficios.vrbeneficios.domain.model.TransacaoBuilder;
import com.vr.beneficios.vrbeneficios.api.model.input.TransacaoInput;
import com.vr.beneficios.vrbeneficios.domain.exception.DefaultException;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;

import org.springframework.http.HttpStatus;

import javax.transaction.Transactional;

public class ValidadorRegrasObserverFlow<T> implements Subscriber<TransacaoBuilder> {
    
    private CartaoRepository cartaoRepository;
    private TransacaoInput transacaoInput;

    @Override
    public void onSubscribe(Subscription subscription) {
        subscription.request(1);
    }

    @Override
    @Transactional
    public void onComplete() {
        var cartao = cartaoRepository.findById(transacaoInput.getNumeroCartao())
        .orElseThrow(() -> new DefaultException(HttpStatus.NOT_FOUND,"CARTAO_INEXISTENTE"));

        cartao.setSaldo(cartao.getSaldo().subtract(transacaoInput.getValor()));

        cartaoRepository.save(cartao);

    }

    @Override
    public void onNext(TransacaoBuilder item) {
        this.cartaoRepository = item.getCartaoRepository();
        this.transacaoInput = item.getTransacaoInput();
        this.validarCartao(transacaoInput);
        this.validarSenha(transacaoInput);
        this.validarSaldo(transacaoInput);
        
    }

    @Override
    public void onError(Throwable throwable) {}

    public void validarCartao(TransacaoInput transacaoInput){
       cartaoRepository.findById(transacaoInput.getNumeroCartao())
            .orElseThrow(() -> new DefaultException(HttpStatus.NOT_FOUND,"CARTAO_INEXISTENTE"));
    }

    public void validarSenha(TransacaoInput transacaoInput){
        cartaoRepository.findByNumeroCartaoAndSenha(transacaoInput.getNumeroCartao(),transacaoInput.getSenhaCartao())
            .orElseThrow(() -> new DefaultException(HttpStatus.NOT_FOUND,"SENHA_INVALIDA"));
    }

    public void validarSaldo(TransacaoInput transacaoInput){
        cartaoRepository.findByNumeroCartaoAndSaldoGreaterThanEqual(transacaoInput.getNumeroCartao(),transacaoInput.getValor())
            .orElseThrow(() -> new DefaultException(HttpStatus.NOT_FOUND,"SALDO_INSUFICIENTE"));
    }

   
}
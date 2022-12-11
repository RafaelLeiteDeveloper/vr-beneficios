package com.vr.beneficios.vrbeneficios.domain.service;

import java.util.concurrent.SubmissionPublisher;

import javax.transaction.Transactional;

import com.vr.beneficios.vrbeneficios.api.model.input.TransacaoInput;
import com.vr.beneficios.vrbeneficios.domain.exception.DefaultException;
import com.vr.beneficios.vrbeneficios.domain.model.TransacaoBuilder;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;
import com.vr.beneficios.vrbeneficios.domain.service.event.ValidadorRegrasObserver;
import com.vr.beneficios.vrbeneficios.domain.service.event.ValidadorRegrasObserverFlow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransacaoService {

    private final CartaoRepository cartaoRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    
    public void realizarTransacao(TransacaoInput transacaoInput){

        // Utilizando o designer pattern builder para incapsular as variaveis
         var transacaoBuilder = TransacaoBuilder.builder()
                                .cartaoRepository(cartaoRepository)
                                .transacaoInput(transacaoInput)
                                .build();

        // Utilizando o designer pattern observer para retirar o acoplamento das validações
        ValidadorRegrasObserver customSpringEvent = new ValidadorRegrasObserver(this, transacaoBuilder);
        applicationEventPublisher.publishEvent(customSpringEvent);
        
        this.finalizarTransacao(transacaoInput);
    }

    @Transactional
    @Async("threadPoolTaskExecutor")
    public void finalizarTransacao(TransacaoInput transacaoInput){
        var cartao = cartaoRepository.findById(transacaoInput.getNumeroCartao())
        .orElseThrow(() -> new DefaultException(HttpStatus.NOT_FOUND,"CARTAO_INEXISTENTE"));
        
        cartao.setSaldo(cartao.getSaldo().subtract(transacaoInput.getValor()));
        cartaoRepository.save(cartao);
    }

   
    
}

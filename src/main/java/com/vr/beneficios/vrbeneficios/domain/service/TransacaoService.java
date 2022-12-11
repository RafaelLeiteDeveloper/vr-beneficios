package com.vr.beneficios.vrbeneficios.domain.service;

import java.util.concurrent.SubmissionPublisher;

import com.vr.beneficios.vrbeneficios.api.model.input.TransacaoInput;
import com.vr.beneficios.vrbeneficios.domain.model.TransacaoBuilder;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;
import com.vr.beneficios.vrbeneficios.domain.service.event.ValidadorRegrasObserverFlow;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransacaoService {

    private final CartaoRepository cartaoRepository;

    public void realizarTransacao(TransacaoInput transacaoInput){

        // Utilizando o designer pattern builder para incapsular as variaveis
        var transacaoBuilder = TransacaoBuilder.builder()
                                .cartaoRepository(cartaoRepository)
                                .transacaoInput(transacaoInput)
                                .build();

        // Utilizando o designer pattern observer para abstrair a logica de validação
        // Utilizando o API flow do Java 9 para realizar requisições concorrentes de forma assíncrona
        SubmissionPublisher<TransacaoBuilder> publisher = new SubmissionPublisher<>();
        ValidadorRegrasObserverFlow<TransacaoBuilder> subscriber = new ValidadorRegrasObserverFlow<>();
        publisher.subscribe(subscriber);
        publisher.submit(transacaoBuilder);
        publisher.close();
        

    }

   
    
}

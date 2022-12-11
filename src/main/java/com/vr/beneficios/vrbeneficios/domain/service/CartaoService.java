package com.vr.beneficios.vrbeneficios.domain.service;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vr.beneficios.vrbeneficios.domain.exception.DefaultException;
import com.vr.beneficios.vrbeneficios.domain.model.Cartao;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;
import com.vr.beneficios.vrbeneficios.domain.repository.ParametrosRepository;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CartaoService {

	private final CartaoRepository cartaoRepository;
	private final ParametrosRepository parametrosRepository;
	
    private static final String valorPadraoSaldo = "VALOR_PADRAO_SALDO";

    public Cartao buscarOuFalhar(Long numeroCartao) {
		return cartaoRepository.findById(numeroCartao)
			.orElseThrow(() -> new DefaultException(HttpStatus.NOT_FOUND,"Cartão não encontrado"));
	}

    @Transactional
	public Cartao salvar(Cartao cartao) {

		this.verificarExistenciaEFalhar(cartao);
		this.atribuirValorPadraoSaldo(cartao);
		
		return cartaoRepository.save(cartao);	
	}

	public void atribuirValorPadraoSaldo(Cartao cartao){
		parametrosRepository.findByChave(valorPadraoSaldo).ifPresentOrElse(
			(obj)
				-> cartao.setSaldo(new BigDecimal(obj.getValor())),
			()
				-> { throw new DefaultException(HttpStatus.UNPROCESSABLE_ENTITY,"Valor Padrão de saldo não atribuido"); });

	}

	public void verificarExistenciaEFalhar(Cartao cartao){
		cartaoRepository.findById(cartao.getNumeroCartao())
			.ifPresent(ex -> {throw new DefaultException(HttpStatus.UNPROCESSABLE_ENTITY,new ObjectMapper().valueToTree(cartao));});
	}
    
}

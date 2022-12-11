package com.vr.beneficios.vrbeneficios;

import com.vr.beneficios.vrbeneficios.api.model.input.TransacaoInput;
import com.vr.beneficios.vrbeneficios.domain.exception.DefaultException;
import com.vr.beneficios.vrbeneficios.domain.model.Cartao;
import com.vr.beneficios.vrbeneficios.domain.model.TransacaoBuilder;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;
import com.vr.beneficios.vrbeneficios.domain.service.event.ValidadorRegrasObserver;
import com.vr.beneficios.vrbeneficios.domain.service.event.ValidadorRegrasObserverFlow;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import java.math.BigDecimal;
import java.util.concurrent.SubmissionPublisher;

@SpringBootTest
class TransacaoTests {

	@Mock
	private CartaoRepository cartaoRepository;

	@Mock
	private ApplicationEventPublisher applicationEventPublisher;

	@Test
	void testeCartaoNaoExiste() {
		var mock = mockTransacaoInput();

		var transacaoBuilder = TransacaoBuilder.builder()
				.cartaoRepository(cartaoRepository)
				.transacaoInput(mock)
				.build();

		when(cartaoRepository.findById(any())).thenReturn(Optional.empty());

		assertThrows(DefaultException.class, () ->  new ValidadorRegrasObserver(this, transacaoBuilder));


	}

	@Test
	void testeCartaoSenhaInvalida() {
		var mock = mockTransacaoInput();
		var mockCartao = mockCartao();

		var transacaoBuilder = TransacaoBuilder.builder()
				.cartaoRepository(cartaoRepository)
				.transacaoInput(mock)
				.build();

		when(cartaoRepository.findById(any())).thenReturn(Optional.of(mockCartao));
		when(cartaoRepository.findByNumeroCartaoAndSenha(any(),any())).thenReturn(Optional.empty());

		assertThrows(DefaultException.class, () ->  new ValidadorRegrasObserver(this, transacaoBuilder));

	}

	@Test
	void testeSaldoInsuficiente() {
		var mock = mockTransacaoInput();
		var mockCartao = mockCartao();

		var transacaoBuilder = TransacaoBuilder.builder()
				.cartaoRepository(cartaoRepository)
				.transacaoInput(mock)
				.build();

		when(cartaoRepository.findById(any())).thenReturn(Optional.of(mockCartao));
		when(cartaoRepository.findByNumeroCartaoAndSenha(any(),any())).thenReturn(Optional.of(mockCartao));
		when(cartaoRepository.findByNumeroCartaoAndSaldoGreaterThanEqual(any(),any())).thenReturn(Optional.empty());

		assertThrows(DefaultException.class, () ->  new ValidadorRegrasObserver(this, transacaoBuilder));

	}

	Cartao mockCartao(){
		Cartao cartao = new Cartao();
		cartao.setSaldo(new BigDecimal(500));
		cartao.setSenha("123");
		cartao.setNumeroCartao(123l);
		return cartao;

	}
	TransacaoInput mockTransacaoInput(){
		return TransacaoInput.builder()
				.valor(new BigDecimal(200))
				.senhaCartao("123")
				.numeroCartao(123l)
				.build();
	}



}

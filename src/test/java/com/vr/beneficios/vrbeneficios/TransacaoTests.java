package com.vr.beneficios.vrbeneficios;

import com.vr.beneficios.vrbeneficios.api.model.input.TransacaoInput;
import com.vr.beneficios.vrbeneficios.domain.exception.DefaultException;
import com.vr.beneficios.vrbeneficios.domain.model.Cartao;
import com.vr.beneficios.vrbeneficios.domain.model.TransacaoBuilder;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;
import com.vr.beneficios.vrbeneficios.domain.service.event.ValidadorRegrasObserverFlow;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
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

	@Test
	void testeCartaoNaoExiste() {
		var mock = mockTransacaoInput();

		var transacaoBuilder = TransacaoBuilder.builder()
				.cartaoRepository(cartaoRepository)
				.transacaoInput(mock)
				.build();

		when(cartaoRepository.findById(any())).thenReturn(Optional.empty());

		SubmissionPublisher<TransacaoBuilder> publisher = new SubmissionPublisher<>();
		ValidadorRegrasObserverFlow<TransacaoBuilder> subscriber = new ValidadorRegrasObserverFlow<>();
		publisher.subscribe(subscriber);
		publisher.submit(transacaoBuilder);
		publisher.close();

		assertThrows(DefaultException.class, () -> subscriber.validarCartao(mock));


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

		SubmissionPublisher<TransacaoBuilder> publisher = new SubmissionPublisher<>();
		ValidadorRegrasObserverFlow<TransacaoBuilder> subscriber = new ValidadorRegrasObserverFlow<>();
		publisher.subscribe(subscriber);
		publisher.submit(transacaoBuilder);
		publisher.close();

		assertThrows(DefaultException.class, () -> subscriber.validarSenha(mock));

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

		SubmissionPublisher<TransacaoBuilder> publisher = new SubmissionPublisher<>();
		ValidadorRegrasObserverFlow<TransacaoBuilder> subscriber = new ValidadorRegrasObserverFlow<>();
		publisher.subscribe(subscriber);
		publisher.submit(transacaoBuilder);
		publisher.close();

		assertThrows(DefaultException.class, () -> subscriber.validarSaldo(mock));

	}

	Cartao mockCartao(){
		return Cartao.builder()
				.senha("123")
				.saldo(new BigDecimal(500))
				.numeroCartao(123l)
				.build();
	}

	Cartao mockCartaoMinus(){
		return Cartao.builder()
				.senha("123")
				.saldo(new BigDecimal(300))
				.numeroCartao(123l)
				.build();
	}


	TransacaoInput mockTransacaoInput(){
		return TransacaoInput.builder()
				.valor(new BigDecimal(200))
				.senhaCartao("123")
				.numeroCartao(123l)
				.build();
	}



}

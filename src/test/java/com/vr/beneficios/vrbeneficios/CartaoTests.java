package com.vr.beneficios.vrbeneficios;

import com.vr.beneficios.vrbeneficios.api.model.input.TransacaoInput;
import com.vr.beneficios.vrbeneficios.domain.exception.DefaultException;
import com.vr.beneficios.vrbeneficios.domain.model.Cartao;
import com.vr.beneficios.vrbeneficios.domain.model.Parametros;
import com.vr.beneficios.vrbeneficios.domain.model.TransacaoBuilder;
import com.vr.beneficios.vrbeneficios.domain.repository.CartaoRepository;
import com.vr.beneficios.vrbeneficios.domain.repository.ParametrosRepository;
import com.vr.beneficios.vrbeneficios.domain.service.CartaoService;
import com.vr.beneficios.vrbeneficios.domain.service.TransacaoService;
import com.vr.beneficios.vrbeneficios.domain.service.event.ValidadorRegrasObserverFlow;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.concurrent.SubmissionPublisher;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.*;
import java.math.BigDecimal;
@SpringBootTest
class CartaoTests {

	@Mock
	private CartaoRepository cartaoRepository;
	@Mock
	private ParametrosRepository parametrosRepository;
	@InjectMocks
	private CartaoService cartaoService;

	private static final String valorPadraoSaldo = "VALOR_PADRAO_SALDO";


	@Test
	void testeBuscarSaldo() {
		var mockCartao = mockCartao();

		when(cartaoRepository.findById(any())).thenReturn(Optional.of(mockCartao));
		var cartao = cartaoService.buscarOuFalhar(123l);

		assertThat(cartao.getSaldo(), is(equalTo(new BigDecimal(500))));
	}
	@Test
	void testeSalvarCartao() {
		var mockCartao = mockCartao();
		var mockParametros = mockParametros();

		when(cartaoRepository.findById(123l)).thenReturn(Optional.empty());
		when(parametrosRepository.findByChave(valorPadraoSaldo)).thenReturn(Optional.of(mockParametros));
		cartaoService.salvar(mockCartao);

		verify(cartaoRepository, times(1)).save(mockCartao);
	}

	@Test
	void testeCartaoExiste() {
		var mockCartao = mockCartao();

		when(cartaoRepository.findById(123l)).thenReturn(Optional.of(mockCartao));

		assertThrows(DefaultException.class, () -> cartaoService.salvar(mockCartao));
	}


	@Test
	void testeParametroNaoExiste() {
		var mockCartao = mockCartao();

		when(cartaoRepository.findById(123l)).thenReturn(Optional.empty());
		when(parametrosRepository.findByChave(valorPadraoSaldo)).thenReturn(Optional.empty());

		assertThrows(DefaultException.class, () -> cartaoService.salvar(mockCartao));
	}


	Cartao mockCartao(){

		Cartao cartao = new Cartao();
		cartao.setSaldo(new BigDecimal(500));
		cartao.setSenha("123");
		cartao.setNumeroCartao(123l);
		return cartao;

	}

	Parametros mockParametros(){
		return Parametros.builder().chave(valorPadraoSaldo).valor("500").build();
	}



}

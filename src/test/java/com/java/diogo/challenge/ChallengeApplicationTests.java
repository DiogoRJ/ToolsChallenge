package com.java.diogo.challenge;

import com.java.diogo.challenge.dto.DescricaoDTO;
import com.java.diogo.challenge.dto.FormaPagamentoDTO;
import com.java.diogo.challenge.dto.PagamentoRequestDTO;
import com.java.diogo.challenge.dto.PagamentoResponseDTO;
import com.java.diogo.challenge.entity.Descricao;
import com.java.diogo.challenge.entity.FormaPagamento;
import com.java.diogo.challenge.entity.Pagamento;
import com.java.diogo.challenge.enums.StatusEnum;
import com.java.diogo.challenge.enums.TipoPagamentoEnum;
import com.java.diogo.challenge.exception.NotFoundException;
import com.java.diogo.challenge.mapper.Mappable;
import com.java.diogo.challenge.repository.PagamentoRepository;
import com.java.diogo.challenge.service.impl.PagamentoServiceImpl;
import com.java.diogo.challenge.util.NumberUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class ChallengeApplicationTests implements Mappable {

	@InjectMocks
	private PagamentoServiceImpl service;
	@Mock
	private PagamentoRepository pagamentoRepository;
	private PagamentoRequestDTO pagamentoRequest;
	private FormaPagamento formaPagamento;
	private Descricao descricao;
	private Pagamento pagamento;
	private Optional<Pagamento> pagamentoOpt;
	private String cartao = "1234567890123456";
	private Long id = 1L;
	private String idPagamento = "1";
	private String valor = "500.00";
	private String dataHora = "19/04/2022 22:00:01";
	private String estabelecimento = "PetShop";
	private String nsu = "654321";
	private String codigoAutorizacao = "0987654321";
	private StatusEnum status = StatusEnum.AUTORIZADO;
	private TipoPagamentoEnum tipo = TipoPagamentoEnum.AVISTA;
	private String parcelas = "1";

	public static final String MSG_OBJECT_NOT_FOUND = "Não foi possivel encontrar o registro.";

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
		start();
	}

	@Test
	void requestERetornarUmPagamentoResponse() {
		Mockito.when(pagamentoRepository.save((Mockito.any()))).thenReturn(pagamento);

		PagamentoResponseDTO response = service.realizarPagamento(pagamentoRequest);

		Assertions.assertNotNull(response);
		Assertions.assertNotNull(pagamento.getId());
	}

	@Test
	void consultarTodosPagamentosERetornarUmaListaResponse() {
		Mockito.when(pagamentoRepository.save((Mockito.any()))).thenReturn(pagamento);
		service.realizarPagamento(pagamentoRequest);
		Mockito.when(pagamentoRepository.findAll()).thenReturn(List.of(pagamento));

		List<PagamentoResponseDTO> response = service.consultarTodosPagamentos();

		Assertions.assertNotNull(response);
		Assertions.assertEquals(1, response.size());
		Assertions.assertEquals(PagamentoResponseDTO.class, response.get(0).getClass());
	}

	@Test
	void consultarPorIdERetornarUmResponse() {
		Mockito.when(pagamentoRepository.findById(Mockito.anyString())).thenReturn(pagamentoOpt);

		PagamentoResponseDTO response = service.consultarPagamento(idPagamento);

		Assertions.assertNotNull(response);
		Assertions.assertEquals(PagamentoResponseDTO.class, response.getClass());
	}

	@Test
	void consultarPorIdERetornarUmaExcecao() {
		String idPagamentoTest = String.valueOf(Mockito.anyInt());
		Mockito.when(pagamentoRepository.findById(idPagamentoTest))
				.thenThrow(new NotFoundException(MSG_OBJECT_NOT_FOUND));

		try {
			service.consultarPagamento(idPagamentoTest);
		} catch (Exception ex) {
			Assertions.assertEquals(NotFoundException.class, ex.getClass());
			Assertions.assertEquals(MSG_OBJECT_NOT_FOUND, ex.getMessage());
		}
	}

	private void start() {
		pagamento = Pagamento.builder()
				.id(idPagamento)
				.cartao(cartao)
				.build();

		descricao = Descricao.builder()
				.id(id)
				.valor(valor)
				.dataHora(dataHora)
				.estabelecimento(estabelecimento)
				.nsu(NumberUtils.random())
				.codigoAutorizacao(NumberUtils.random())
				.status(status)
				.build();

		formaPagamento = FormaPagamento.builder()
				.id(id)
				.tipo(tipo)
				.parcelas(parcelas)
				.build();

		descricao.setPagamento(pagamento);
		formaPagamento.setPagamento(pagamento);
		pagamento.setDescricao(descricao);
		pagamento.setFormaPagamento(formaPagamento);

		// valor, dataHora, estabelecimento
		DescricaoDTO descricaoReq = DescricaoDTO.builder()
				.valor(valor)
				.dataHora(dataHora)
				.estabelecimento(estabelecimento)
				.nsu(NumberUtils.random())
				.codigoAutorizacao(NumberUtils.random())
				.status(status)
				.build();

		// tipo, parcelas
		FormaPagamentoDTO formaPagamentoReq = FormaPagamentoDTO.builder()
				.tipo(tipo)
				.parcelas(parcelas)
				.build();

		// cartao, id, descricaoReq, formaPagamentoReq
		pagamentoRequest = PagamentoRequestDTO.builder()
				.id(idPagamento)
				.cartao(cartao)
				.descricao(descricaoReq)
				.formaPagamento(formaPagamentoReq)
				.build();

//		service.realizarPagamento(pagamentoRequest);
	}
}

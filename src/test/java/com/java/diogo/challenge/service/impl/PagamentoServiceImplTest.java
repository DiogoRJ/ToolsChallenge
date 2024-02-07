package com.java.diogo.challenge.service.impl;

import com.java.diogo.challenge.dto.DescricaoDTO;
import com.java.diogo.challenge.dto.FormaPagamentoDTO;
import com.java.diogo.challenge.dto.PagamentoRequestDTO;
import com.java.diogo.challenge.entity.Descricao;
import com.java.diogo.challenge.entity.FormaPagamento;
import com.java.diogo.challenge.entity.Pagamento;
import com.java.diogo.challenge.enums.StatusEnum;
import com.java.diogo.challenge.enums.TipoPagamentoEnum;
import com.java.diogo.challenge.repository.PagamentoRepository;
import com.java.diogo.challenge.util.NumberUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class PagamentoServiceImplTest {

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

    @Autowired
    @InjectMocks
    private PagamentoServiceImpl service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.initMocks(this);
        start();
    }

    @Test
    @DisplayName("Realizar operação de pagamento com sucesso!")
    void realizarPagamento() {
        service.realizarPagamento(pagamentoRequest);
        verify(pagamentoRepository, times(1)).save(any());
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

    }
}
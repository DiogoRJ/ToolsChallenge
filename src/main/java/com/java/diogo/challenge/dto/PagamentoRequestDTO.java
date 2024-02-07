package com.java.diogo.challenge.dto;

import com.java.diogo.challenge.entity.Descricao;
import com.java.diogo.challenge.entity.FormaPagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoRequestDTO {
    private String id;
    private String cartao;
    private DescricaoDTO descricao;
    private FormaPagamentoDTO formaPagamento;
}

package com.java.diogo.challenge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransacaoRequestDTO {
    private PagamentoRequestDTO transacao;
}

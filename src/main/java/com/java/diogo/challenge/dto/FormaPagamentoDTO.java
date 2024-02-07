package com.java.diogo.challenge.dto;

import com.java.diogo.challenge.enums.StatusEnum;
import com.java.diogo.challenge.enums.TipoPagamentoEnum;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormaPagamentoDTO {
    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tipo;
    private String parcelas;
}

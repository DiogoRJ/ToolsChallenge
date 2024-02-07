package com.java.diogo.challenge.dto;

import com.java.diogo.challenge.enums.StatusEnum;
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
public class DescricaoDTO {
    private String valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu;
    private String codigoAutorizacao;
    @Enumerated(EnumType.STRING)
    private StatusEnum status;
}

package com.java.diogo.challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.diogo.challenge.enums.StatusEnum;
import com.java.diogo.challenge.util.NumberUtils;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@SuperBuilder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@ToString(exclude = {"pagamento"})
public class Descricao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String valor;
    private String dataHora;
    private String estabelecimento;
    private String nsu = NumberUtils.random();
    private String codigoAutorizacao = NumberUtils.random();
    @Enumerated(EnumType.STRING)
    private StatusEnum status = StatusEnum.AUTORIZADO;
    @OneToOne
    @JoinColumn(name = "pagamento_id", unique = true)
    private Pagamento pagamento;


}

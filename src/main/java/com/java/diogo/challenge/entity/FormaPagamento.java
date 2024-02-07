package com.java.diogo.challenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.java.diogo.challenge.enums.TipoPagamentoEnum;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
@ToString(exclude = {"pagamento"})
public class FormaPagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    private TipoPagamentoEnum tipo;
    private String parcelas;
    @OneToOne
    @JoinColumn(name = "pagamento_id", unique = true)
    private Pagamento pagamento;
}

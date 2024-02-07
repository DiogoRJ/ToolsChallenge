package com.java.diogo.challenge.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of="id")
public class Pagamento {
    @Id
    private String id;
    private String cartao;
    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL)
    @JoinColumn(name = "descricao_id")
    private Descricao descricao;
    @OneToOne(mappedBy = "pagamento", cascade = CascadeType.ALL)
    @JoinColumn
    private FormaPagamento formaPagamento;

}

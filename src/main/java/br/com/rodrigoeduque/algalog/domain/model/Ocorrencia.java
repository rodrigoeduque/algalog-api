package br.com.rodrigoeduque.algalog.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ocorrencia {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "entrega_id")
    private Entrega entrega;

    private String descricao;

    private OffsetDateTime dataRegistro;

}

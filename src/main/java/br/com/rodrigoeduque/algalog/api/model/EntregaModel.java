package br.com.rodrigoeduque.algalog.api.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Getter
@Setter
public class EntregaModel {

    private Long id;

    private ClienteResumoModel cliente;

    private DestinatarioModel destinatario;

    private BigDecimal taxa;

    private String status;

    private OffsetDateTime dataPedido;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private OffsetDateTime dataFinalizacao;


}

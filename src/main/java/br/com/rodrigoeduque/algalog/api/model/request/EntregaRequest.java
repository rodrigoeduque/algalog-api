package br.com.rodrigoeduque.algalog.api.model.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class EntregaRequest {

    @Valid
    @NotNull
    private ClienteIdRequest clienteIdRequest;

    @Valid
    @NotNull
    private DestinatarioRequest destinatarioRequest;

    @NotNull
    private BigDecimal taxa;

}

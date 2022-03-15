package br.com.rodrigoeduque.algalog.api.service;

import br.com.rodrigoeduque.algalog.domain.model.Cliente;
import br.com.rodrigoeduque.algalog.domain.model.Entrega;
import br.com.rodrigoeduque.algalog.domain.model.StatusEntrega;
import br.com.rodrigoeduque.algalog.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class SolicitacaoEntregaService {

    @Autowired
    private EntregaRepository entregaRepository;

    @Autowired
    private CatalogoClienteServices clienteServices;

    public Entrega solicitarEntrega(Entrega entrega) {

        Cliente cliente = clienteServices.buscarPorId(entrega.getCliente().getId());

        entrega.setCliente(cliente);
        entrega.setStatus(StatusEntrega.PENDENTE);
        entrega.setDataPedido(OffsetDateTime.now());
        return entregaRepository.save(entrega);
    }

}

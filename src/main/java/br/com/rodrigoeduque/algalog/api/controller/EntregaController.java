package br.com.rodrigoeduque.algalog.api.controller;

import br.com.rodrigoeduque.algalog.api.service.SolicitacaoEntregaService;
import br.com.rodrigoeduque.algalog.domain.model.Cliente;
import br.com.rodrigoeduque.algalog.domain.model.Entrega;
import br.com.rodrigoeduque.algalog.domain.repository.EntregaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/entregas")
public class EntregaController {

    @Autowired
    private SolicitacaoEntregaService service;

    @Autowired
    private EntregaRepository entregaRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Entrega solicitar(@Valid @RequestBody Entrega entrega){
        return service.solicitarEntrega(entrega);
    }

    @GetMapping
    public List<Entrega> listar (){
        return entregaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrega> buscarPorId(@PathVariable Long id){
        return entregaRepository.findById(id).map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
    }
}

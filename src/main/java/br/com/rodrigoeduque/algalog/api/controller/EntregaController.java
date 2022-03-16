package br.com.rodrigoeduque.algalog.api.controller;

import br.com.rodrigoeduque.algalog.api.mapper.EntregaMapper;
import br.com.rodrigoeduque.algalog.api.model.EntregaModel;
import br.com.rodrigoeduque.algalog.api.model.request.EntregaRequest;
import br.com.rodrigoeduque.algalog.api.service.SolicitacaoEntregaService;
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

    @Autowired
    private EntregaMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EntregaModel solicitar(@Valid @RequestBody EntregaRequest request) {

        Entrega entrega = mapper.toEntity(request);
        Entrega novaEntrega = service.solicitarEntrega(entrega);
        return mapper.toModel(novaEntrega);
    }

    @GetMapping
    public ResponseEntity<List<EntregaModel>> listar() {

        List<Entrega> entregas = entregaRepository.findAll();
        List<EntregaModel> entregasModel = mapper.toModelList(entregas);
        return ResponseEntity.ok(entregasModel);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntregaModel> buscarPorId(@PathVariable Long id) {

        return entregaRepository.findById(id)
          .map(entrega -> ResponseEntity.ok(mapper.toModel(entrega)))
          .orElse(ResponseEntity.notFound().build());
    }
}

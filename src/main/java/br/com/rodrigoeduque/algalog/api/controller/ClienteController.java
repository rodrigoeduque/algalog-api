package br.com.rodrigoeduque.algalog.api.controller;

import br.com.rodrigoeduque.algalog.api.service.CatalogoClienteServices;
import br.com.rodrigoeduque.algalog.domain.model.Cliente;
import br.com.rodrigoeduque.algalog.domain.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private CatalogoClienteServices clienteServices;

    @GetMapping
    public List<Cliente> listar() {

        List<Cliente> clientes = repository.findAll();

        return clientes;

    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscar(@PathVariable Long id) {

        return repository.findById(id)
          .map(ResponseEntity::ok)
          .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
        return clienteServices.salvarCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizar(@Valid @PathVariable Long id , @RequestBody Cliente cliente) {

        if(! repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        cliente.setId(id);
        cliente = clienteServices.salvarCliente(cliente);
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> remover(@PathVariable Long id) {

        if(! repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clienteServices.excluir(id);
        return ResponseEntity.noContent().build();
    }

}

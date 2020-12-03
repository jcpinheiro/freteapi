package edu.ifma.lpweb.freteapi.controller;


import edu.ifma.lpweb.freteapi.model.Cliente;
import edu.ifma.lpweb.freteapi.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }


    @GetMapping
    public List<Cliente> lista(String nome ) {
        if (nome == null ) {
            return clienteService.todos();
        } else {
            return clienteService.buscaPor(nome );
        }
    }

    //versao 01
/*
    @GetMapping("/{id}")
    public Cliente buscaPor(@PathVariable Integer id ) {
        return clienteService.buscaPor(id ).get();
    }
*/


    //versao 02
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscaPor(@PathVariable Integer id ) {
        Optional<Cliente> optional = clienteService.buscaPor(id );
        if (optional.isPresent() ) {
            return ResponseEntity.ok(optional.get() );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /*
    @PostMapping
    public Cliente cadastrar(@RequestBody Cliente cliente ) {
        return clienteService.salva(cliente );
    }
*/

    @PostMapping
    public ResponseEntity<Cliente> cadastro(@RequestBody @Valid Cliente cliente, UriComponentsBuilder builder) {
        final Cliente clienteSalvo = clienteService.salva(cliente);
        final URI uri = builder.path("/clientes/{id}").buildAndExpand(clienteSalvo.getId()).toUri();

        return ResponseEntity.created(uri).body(clienteSalvo );

    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualiza(@PathVariable Integer id, @RequestBody Cliente cliente) {
        Optional<Cliente> optional = clienteService.buscaPor(id);

        if (optional.isPresent() ) {
            cliente.setId(id );
            Cliente clienteAtualizado = clienteService.salva(cliente);
            return ResponseEntity.ok(clienteAtualizado );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> remover(@PathVariable Integer id) {
        Optional<Cliente> optional = clienteService.buscaPor(id );

        if (optional.isPresent()) {
            clienteService.removePelo(id );
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

}

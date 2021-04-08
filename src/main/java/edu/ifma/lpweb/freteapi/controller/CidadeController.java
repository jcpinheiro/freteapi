package edu.ifma.lpweb.freteapi.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.ifma.lpweb.freteapi.model.Cidade;
import edu.ifma.lpweb.freteapi.model.Cliente;
import edu.ifma.lpweb.freteapi.service.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.lang.reflect.Field;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeController {

    private final CidadeService cidadeService;

    @Autowired
    public CidadeController(CidadeService cidadeService) {
        this.cidadeService = cidadeService;
    }


    @GetMapping
    @Cacheable(value = "listaDeCidades")
    public List<Cidade> lista(String nome) {
        if (nome == null) {
            return cidadeService.todos();
        } else {
            return cidadeService.buscaPor(nome);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> buscaPor(@PathVariable Integer id) {
        Optional<Cidade> optional = cidadeService.buscaPor(id);

        if (optional.isPresent()) {
            return ResponseEntity.ok(optional.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    @CacheEvict(value = "listaDeCidades", allEntries = true)
    public ResponseEntity<Cidade> cadastro(@RequestBody @Valid Cidade cidade,
                                           UriComponentsBuilder builder) {

        final Cidade cidadeSalva = cidadeService.salva(cidade);
        final URI uri = builder
                .path("/cidades/{id}")
                .buildAndExpand(cidadeSalva.getId()).toUri();

        return ResponseEntity.created(uri).body(cidadeSalva);
    }

    @PutMapping("/{id}")
    @CacheEvict( value = "listaDeCidades", allEntries = true)
    public ResponseEntity<Cidade> atualiza(@PathVariable Integer id,
                                            @RequestBody @Valid Cidade cidade) {
        Optional<Cidade> optional = cidadeService.buscaPor(id);

        if (optional.isPresent()) {
            cidade.setId(id);
            Cidade cidadeAtualizado = cidadeService.salva(cidade );
            return ResponseEntity.ok(cidadeAtualizado );
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @CacheEvict( value = "listaDeCidades", allEntries = true)
    public ResponseEntity<?> remover(@PathVariable Integer id) {
        Optional<Cidade> optional = cidadeService.buscaPor(id);

        if (optional.isPresent()) {
            cidadeService.removePelo(id);
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Cidade> atualizacaoParcial(@PathVariable Integer id,
                                                     @RequestBody Map<String, Object> campos) {
        Optional<Cidade> optional = cidadeService.buscaPor(id);

        if (optional.isPresent()) {
            Cidade cidadeAtual = optional.get();

            merge(campos, cidadeAtual);
            return this.atualiza(id, cidadeAtual);

        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private void merge(Map<String, Object> campos, Cidade cidadeDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Cidade cidadeOrigem = objectMapper.convertValue(campos, Cidade.class);

        campos.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Cliente.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, cidadeOrigem);

            ReflectionUtils.setField(field, cidadeDestino, novoValor);
        });
    }
}

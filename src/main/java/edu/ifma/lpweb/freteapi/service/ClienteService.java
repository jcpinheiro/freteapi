package edu.ifma.lpweb.freteapi.service;


import edu.ifma.lpweb.freteapi.model.Cliente;
import edu.ifma.lpweb.freteapi.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public List<Cliente> todos() {
        return clienteRepository.findAll();
    }

    public List<Cliente> buscaPor(String nome ) {
        return clienteRepository.findByNomeContaining(nome );
    }

    @Transactional
    public Cliente salva(Cliente cliente) {
        return clienteRepository.save(cliente );
    }

    public Optional<Cliente> buscaPor(Integer id) {
        return clienteRepository.findById(id );
    }

    @Transactional
    public void removePelo(Integer id) {
        clienteRepository.deleteById(id );
    }
}

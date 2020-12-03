package edu.ifma.lpweb.freteapi.repository;

import edu.ifma.lpweb.freteapi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContaining(String nome );
}

package edu.ifma.lpweb.freteapi.repository;

import edu.ifma.lpweb.freteapi.model.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNomeContaining(String nome );
    Page<Cliente> findByNomeContaining(String nome, Pageable paginacao);
}

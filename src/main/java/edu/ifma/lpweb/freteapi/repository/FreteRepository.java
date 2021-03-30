package edu.ifma.lpweb.freteapi.repository;

import edu.ifma.lpweb.freteapi.model.Frete;
import edu.ifma.lpweb.freteapi.repository.frete.FreteRepositoryQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FreteRepository extends JpaRepository<Frete, Integer>, FreteRepositoryQuery {

    List<Frete> findByDescricaoContaining(String descricao );
    Page<Frete> findByDescricaoContaining(String descricao, Pageable paginacao);
}

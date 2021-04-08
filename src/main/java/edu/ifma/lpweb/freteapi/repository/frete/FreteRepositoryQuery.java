package edu.ifma.lpweb.freteapi.repository.frete;

import edu.ifma.lpweb.freteapi.model.Frete;
import edu.ifma.lpweb.freteapi.repository.filter.FreteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FreteRepositoryQuery {
    Page<Frete> filtra(FreteFiltro freteFiltro, Pageable pageable );
}

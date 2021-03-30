package edu.ifma.lpweb.freteapi.service;

import edu.ifma.lpweb.freteapi.model.Frete;
import edu.ifma.lpweb.freteapi.repository.FreteRepository;
import edu.ifma.lpweb.freteapi.repository.filter.FreteFiltro;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class FreteService {

    private final FreteRepository freteRepository;

    @Autowired
    public FreteService(FreteRepository freteRepository) {
        this.freteRepository = freteRepository;
    }

    @Transactional(readOnly = true)
    public Page<Frete> busca(FreteFiltro filtro, Pageable page) {
        return freteRepository.filtra(filtro, page );
    }
}

package edu.ifma.lpweb.freteapi.repository.frete;

import edu.ifma.lpweb.freteapi.model.Frete;
import edu.ifma.lpweb.freteapi.repository.filter.FreteFiltro;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FreteRepositoryImpl implements FreteRepositoryQuery {

    @PersistenceContext
    private EntityManager manager;

    @Override
    public Page<Frete> filtra(FreteFiltro filtro, Pageable pageable) {

        // Usamos o CriteriaBuilder(cb) para criar a CriteriaQueyr (cQuery)
        // com a tipagem do tipo a ser selecionado (Frete)
        CriteriaBuilder cBuilder = manager.getCriteriaBuilder();


        // 1. Select f From Frete f
        CriteriaQuery<Frete> cQuery = cBuilder.createQuery(Frete.class );

        // 2. clausula from e joins
        Root<Frete> freteRoot = cQuery.from(Frete.class );

        // 3. adiciona as restrições (os predicados) que serão passadas na clausula where
        Predicate[] restricoes = this.criaRestricoes(filtro, cBuilder, freteRoot  );


        // 4. monta a consulta com as restrições
        cQuery.select(freteRoot)
                .where(restricoes )
                .orderBy( cBuilder.asc(freteRoot.get("descricao")) );

        // 5. cria e executa a consulta
        TypedQuery<Frete> query = manager.createQuery(cQuery);
        adicionaRestricoesDePaginacao(query, pageable);

        return new PageImpl<>(query.getResultList(), pageable, total(filtro) );
    }


    private Predicate[] criaRestricoes(FreteFiltro filtro, CriteriaBuilder cBuilder,
                                       Root<Frete> freteRoot) {

        List<Predicate> predicates = new ArrayList<>();

        if ( filtro.getDescricao() != null && !(filtro.getDescricao().isEmpty()) ) {
            // where descricao like %Computador%
            predicates.add(cBuilder.like( cBuilder.lower(freteRoot.get("descricao")),
                                  "%" + filtro.getDescricao().toLowerCase() + "%" ) );

        }
        if ( Objects.nonNull(filtro.getValorDe()) ) {
            predicates.add( cBuilder.ge( freteRoot.get("valor"), filtro.getValorDe() ));
        }

        if( Objects.nonNull( filtro.getValorAte()  ) ) {
            predicates.add( cBuilder.le( freteRoot.get("valor"), filtro.getValorAte() ));
        }

        if ( Objects.nonNull(filtro.getPesoDe() ) ) {
            predicates.add( cBuilder.ge( freteRoot.get("peso"), filtro.getPesoDe() ));
        }

        if( Objects.nonNull( filtro.getPesoAte()  ) ) {
            predicates.add( cBuilder.le( freteRoot.get("peso"), filtro.getPesoAte() ));
        }

        if (Objects.nonNull(filtro.getClienteId()) ) {

            // antes fazemos o join com categorias
            Path<Integer> categoriaPath = freteRoot.join("cliente").<Integer>get("id");

            // semelhante a clausula "on" do critério de junção
            predicates.add ( cBuilder.equal(categoriaPath, filtro.getClienteId() ) );
        }

        return predicates.toArray(new Predicate[ predicates.size() ] );
    }



    private void adicionaRestricoesDePaginacao(TypedQuery<Frete> query, Pageable pageable) {
        Integer paginaAtual = pageable.getPageNumber();
        Integer totalObjetosPorPagina = pageable.getPageSize();
        Integer primeiroObjetoDaPagina = paginaAtual * totalObjetosPorPagina;

        // 0 a n-1, n - (2n -1), ...
        query.setFirstResult(primeiroObjetoDaPagina );
        query.setMaxResults(totalObjetosPorPagina );

    }

    private Long total(FreteFiltro filtro) {
        CriteriaBuilder cBuilder = manager.getCriteriaBuilder();
        CriteriaQuery<Long> cQuery = cBuilder.createQuery(Long.class);

        Root<Frete> rootFrete = cQuery.from(Frete.class);

        Predicate[] predicates = criaRestricoes(filtro, cBuilder, rootFrete);

        cQuery.where(predicates );
        cQuery.select( cBuilder.count(rootFrete) );

        return manager.createQuery(cQuery).getSingleResult();
    }
}
package com.qqd.edicria.repositories.tabelasPrincipais;

import com.qqd.edicria.entities.tabelasPrincipais.Autor;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    boolean existsByNome(String nome);

    Optional<Autor> findByNome(String nome);

    Example<? extends Autor> nome(String nome);
}

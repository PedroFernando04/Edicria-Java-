package com.qqd.edicria.repositories.tabelasPrincipais;

import com.qqd.edicria.entities.tabelasPrincipais.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorReposritory extends JpaRepository<Autor, Integer> {
}

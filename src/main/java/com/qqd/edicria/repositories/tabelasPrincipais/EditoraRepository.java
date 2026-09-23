package com.qqd.edicria.repositories.tabelasPrincipais;

import com.qqd.edicria.entities.tabelasPrincipais.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EditoraRepository extends JpaRepository<Editora, Integer> {
}

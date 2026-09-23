package com.qqd.edicria.repositories.tabelasAuxiliares;

import com.qqd.edicria.entities.tabelasAuxiliares.LivroCliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroClienteRepository extends JpaRepository<LivroCliente, Integer> {
}

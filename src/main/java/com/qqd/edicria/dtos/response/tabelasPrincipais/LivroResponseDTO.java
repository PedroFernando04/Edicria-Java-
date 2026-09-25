package com.qqd.edicria.dtos.response.tabelasPrincipais;

import com.qqd.edicria.entities.enums.livros.EnumCategoriasLivro;
import com.qqd.edicria.entities.enums.livros.EnumFormatoLivro;
import com.qqd.edicria.entities.tabelasPrincipais.Autor;
import com.qqd.edicria.entities.tabelasPrincipais.Editora;

import java.time.LocalDate;

public record LivroResponseDTO(

        Long id,

        String titulo,

        Autor autor,

        Editora editora,

        LocalDate dataLancamento,

        EnumCategoriasLivro categoria,

        EnumFormatoLivro formato
) {}

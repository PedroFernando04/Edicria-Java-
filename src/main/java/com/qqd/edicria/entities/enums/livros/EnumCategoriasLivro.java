package com.qqd.edicria.entities.enums.livros;

import lombok.Getter;

@Getter
public enum EnumCategoriasLivro {

    INFANTIL("Infantil"),
    INFANTOJUVENIL("Infantojuvenil"),
    JUVENIL("Juvenil"),
    YOUNG_ADULT("Young Adult"),
    ADULTO("Adulto"),

    ACADEMICO("Acadêmico"),
    UNIVERSITARIO("Universitário"),
    DIDATICO("Didático"),
    TECNICO("Técnico"),
    PROFISSIONAL("Profissional"),

    HQ("História em Quadrinhos"),
    GRAPHIC_NOVEL("Graphic Novel"),
    MANGA("Mangá"),
    ARTBOOK("Artbook"),

    POESIA("Poesia"),
    CONTOS("Contos"),
    CRONICAS("Crônicas"),

    AUTOAJUDA("Autoajuda"),
    REFERENCIA("Referência"),
    BIOGRAFIA("Biografia");


    private String descricao;

    EnumCategoriasLivro(String descricao) {
        this.descricao = descricao;
    }
}
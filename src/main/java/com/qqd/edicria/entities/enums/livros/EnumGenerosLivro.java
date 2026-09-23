package com.qqd.edicria.entities.enums.livros;

import lombok.Getter;

@Getter
public enum EnumGenerosLivro {

    FANTASIA("Fantasia"),
    FICCAO_CIENTIFICA("Ficção Científica"),
    DISTOPIA("Distopia"),
    FICCAO_HISTORICA("Ficção Histórica"),
    FICCAO("Ficção"),

    ROMANCE("Romance"),
    ROMANCE_HISTORICO("Romance Histórico"),
    ROMANCE_POLICIAL("Romance Policial"),

    MISTERIO("Mistério"),
    SUSPENSE("Suspense"),
    THRILLER("Thriller"),
    TERROR("Terror"),

    AVENTURA("Aventura"),
    ACAO("Ação"),
    DRAMA("Drama"),
    COMEDIA("Comédia"),

    BIOGRAFIA("Biografia"),
    AUTOBIOGRAFIA("Autobiografia"),
    MEMORIAS("Memórias"),

    HISTORIA("História"),
    FILOSOFIA("Filosofia"),
    POLITICA("Política"),
    SOCIOLOGIA("Sociologia"),
    PSICOLOGIA("Psicologia"),

    RELIGIAO("Religião"),
    ESPIRITUALIDADE("Espiritualidade"),

    POESIA("Poesia"),
    CRONICAS("Crônicas"),
    CONTOS("Contos"),

    AUTOAJUDA("Autoajuda"),
    DESENVOLVIMENTO_PESSOAL("Desenvolvimento Pessoal"),

    NEGOCIOS("Negócios"),
    ECONOMIA("Economia"),
    ADMINISTRACAO("Administração"),
    EMPREENDEDORISMO("Empreendedorismo"),

    TECNOLOGIA("Tecnologia"),
    PROGRAMACAO("Programação"),
    CIENCIA("Ciência"),

    EDUCACAO("Educação"),
    DIDATICO("Didático"),

    CULINARIA("Culinária"),
    VIAGEM("Viagem"),
    ESPORTES("Esportes"),
    SAUDE("Saúde");

    private String descricao;

    EnumGenerosLivro(String descricao) {
        this.descricao = descricao;
    }
}
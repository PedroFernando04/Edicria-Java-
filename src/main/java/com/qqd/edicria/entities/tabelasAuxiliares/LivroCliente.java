package com.qqd.edicria.entities.tabelasAuxiliares;

import com.qqd.edicria.entities.enums.EnumLingua;
import com.qqd.edicria.entities.enums.livros.EnumStatusLivro;
import com.qqd.edicria.entities.tabelasPrincipais.Livro;
import com.qqd.edicria.entities.tabelasPrincipais.Usuario;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
public class LivroCliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Usuario usuario;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Livro livro;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumStatusLivro status;

    @Column(nullable = true)
    private Date dataLeitura;

    @Column(nullable = true, precision = 3, scale = 1)
    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private BigDecimal nota;

    @Column(nullable = true, length = 3000)
    private String resenha;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EnumLingua linguaLida;
}

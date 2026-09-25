package com.qqd.edicria.controllers.tabelasPrincipais;

import com.qqd.edicria.dtos.request.tabelasPrincipais.Livro.LivroRequestDTO;
import com.qqd.edicria.dtos.response.tabelasPrincipais.LivroResponseDTO;
import com.qqd.edicria.services.tabelasPrincipais.LivroService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @PostMapping
    public ResponseEntity<LivroResponseDTO> createLivro(
            @Valid @RequestBody LivroRequestDTO dto
    ){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(livroService.createLivro(dto));
    }
}

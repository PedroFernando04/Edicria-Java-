package com.qqd.edicria.controllers.tabelasPrincipais;

import com.qqd.edicria.dtos.request.tabelasPrincipais.UsuarioRequestDTO;
import com.qqd.edicria.dtos.response.tabelasPrincipais.UsuarioResponseDTO;
import com.qqd.edicria.services.tabelasPrincipais.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> criarUsuario(
            @Valid @RequestBody UsuarioRequestDTO usuarioRequestDTO
    ){
        UsuarioResponseDTO usuarioCriado =
                usuarioService.createUsuario(usuarioRequestDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioCriado);
    }
}

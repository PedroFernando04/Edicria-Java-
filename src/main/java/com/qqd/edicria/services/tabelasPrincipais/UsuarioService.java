package com.qqd.edicria.services.tabelasPrincipais;

import com.qqd.edicria.dtos.request.tabelasPrincipais.UsuarioRequestDTO;
import com.qqd.edicria.dtos.response.tabelasPrincipais.UsuarioResponseDTO;
import com.qqd.edicria.entities.tabelasPrincipais.Usuario;
import com.qqd.edicria.exceptions.tabelasPrincipais.Usuario.EmailJaCadastradoException;
import com.qqd.edicria.exceptions.tabelasPrincipais.Usuario.NomeJaCadastradoException;
import com.qqd.edicria.mappers.tabelasPrincipais.UsuarioMapper;
import com.qqd.edicria.repositories.tabelasPrincipais.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final UsuarioMapper usuarioMapper;

    public UsuarioService(UsuarioRepository usuarioRepository,
                          PasswordEncoder passwordEncoder,
                          UsuarioMapper usuarioMapper) {

        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.usuarioMapper = usuarioMapper;
    }

    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO dto) {

        if(usuarioRepository.existsByEmail(dto.email())){
            throw new EmailJaCadastradoException("Email já cadastrado");
        }
        if(usuarioRepository.existsByNome(dto.nome())){
            throw new NomeJaCadastradoException("Nome já cadastrado");
        }

        Usuario usuario = usuarioMapper.toEntity(dto);

        usuario.setSenha(passwordEncoder.encode(dto.senha()));
        usuario.setAdm(false);

        usuarioRepository.save(usuario);

        return usuarioMapper.toResponseDTO(usuario);
    }
}

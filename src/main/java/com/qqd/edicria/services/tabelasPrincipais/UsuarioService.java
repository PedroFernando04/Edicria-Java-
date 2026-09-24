package com.qqd.edicria.services.tabelasPrincipais;

import com.qqd.edicria.dtos.request.tabelasAuxiliares.LoginRequestDTO;
import com.qqd.edicria.dtos.request.tabelasPrincipais.UsuarioRequestDTO;
import com.qqd.edicria.dtos.response.tabelasPrincipais.UsuarioResponseDTO;
import com.qqd.edicria.entities.tabelasPrincipais.Usuario;
import com.qqd.edicria.exceptions.tabelasPrincipais.Usuario.EmailJaCadastradoException;
import com.qqd.edicria.exceptions.tabelasPrincipais.Usuario.NomeJaCadastradoException;
import com.qqd.edicria.exceptions.tabelasPrincipais.Usuario.UsuarioNaoEncontrado;
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

    public void atualizarUsuario(UsuarioRequestDTO dto, Integer id) {

        Usuario usuario = usuarioRepository.findById(id).orElse(null);

        if(usuario != null){

            if(!usuario.getNome().equals(dto.nome())){
                usuario.setNome(dto.nome());
            }
            if(!usuario.getEmail().equals(dto.email())){
                usuario.setEmail(dto.email());
            }
            if(!passwordEncoder.matches(dto.senha(), usuario.getSenha())){
                usuario.setSenha(passwordEncoder.encode(dto.senha()));
            }
            if(!usuario.getGenero().equals(dto.genero())){
                usuario.setGenero(dto.genero());
            }
            if(!usuario.getPaisOrigem().equals(dto.pais())){
                usuario.setPaisOrigem(dto.pais());
            }
            if(!usuario.getDataNascimento().equals(dto.dataNascimento())){
                usuario.setDataNascimento(dto.dataNascimento());
            }

            usuarioRepository.save(usuario);
        }

        else throw new UsuarioNaoEncontrado("Usuário não encontrado");

    }
}

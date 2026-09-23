package com.qqd.edicria.services.tabelasPrincipais;

import com.qqd.edicria.dtos.response.tabelasPrincipais.UsuarioResponseDTO;
import com.qqd.edicria.entities.enums.EnumGeneroPessoa;
import com.qqd.edicria.entities.enums.EnumPaises;
import com.qqd.edicria.entities.tabelasPrincipais.Usuario;
import com.qqd.edicria.repositories.tabelasPrincipais.UsuarioRepository;
import com.qqd.edicria.mappers.tabelasPrincipais.UsuarioMapper;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.qqd.edicria.dtos.request.tabelasPrincipais.UsuarioRequestDTO;
import com.qqd.edicria.exceptions.tabelasPrincipais.Usuario.EmailJaCadastradoException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UsuarioMapper usuarioMapper;

    @InjectMocks
    private UsuarioService usuarioService;

    @Test
    void deveLancarExceptionQuandoEmailJaEstiverCadastrado() {

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Pedro Fernando",
                "pedro@email.com",
                "123456",
                null,
                null,
                null
        );

        when(usuarioRepository.existsByEmail(dto.email()))
                .thenReturn(true);

        assertThrows(
                EmailJaCadastradoException.class,
                () -> usuarioService.createUsuario(dto)
        );
    }

    @Test
    void naoDeveSalvarUsuarioQuandoEmailJaEstiverCadastrado() {

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Pedro Fernando",
                "pedro@email.com",
                "123456",
                null,
                null,
                null
        );

        when(usuarioRepository.existsByEmail(dto.email()))
                .thenReturn(true);

        try{
            usuarioService.createUsuario(dto);
        } catch(EmailJaCadastradoException ignored){}

        verify(usuarioRepository, never()).save(any());
    }

    @Test
    void deveCriarUsuarioComSucesso() {

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Pedro Fernando",
                "pedro@email.com",
                "123456",
                EnumGeneroPessoa.MASCULINO,
                EnumPaises.BRASIL,
                LocalDate.of(2002, 5, 15)
        );

        Usuario usuario = new Usuario();
        usuario.setNome("Pedro Fernando");
        usuario.setEmail("pedro@email.com");
        usuario.setSenha("senha-criptografada");
        usuario.setGenero(EnumGeneroPessoa.MASCULINO);
        usuario.setPaisOrigem(EnumPaises.BRASIL);
        usuario.setDataNascimento(LocalDate.of(2002, 5, 15));
        usuario.setAdm(false);

        UsuarioResponseDTO responseDTO = new UsuarioResponseDTO(
                1L,
                "Pedro Fernando",
                "pedro@email.com",
                EnumGeneroPessoa.MASCULINO,
                EnumPaises.BRASIL,
                LocalDate.of(2002, 5, 15),
                false
        );

        when(usuarioRepository.existsByEmail(dto.email()))
                .thenReturn(false);
        when(usuarioRepository.existsByNome(dto.nome()))
                .thenReturn(false);

        when(usuarioMapper.toEntity(dto))
                .thenReturn(usuario);

        when(passwordEncoder.encode(dto.senha()))
                .thenReturn("senha-criptografada");

        when(usuarioMapper.toResponseDTO(usuario))
                .thenReturn(responseDTO);

        when(usuarioRepository.save(usuario))
                .thenReturn(usuario);

        UsuarioResponseDTO resultado =
                usuarioService.createUsuario(dto);

        assertNotNull(resultado);
        assertEquals("Pedro Fernando", resultado.nome());
        assertEquals("pedro@email.com", resultado.email());

        verify(usuarioRepository).save(usuario);
    }

    @Test
    void deveCriptografarSenhaAntesDeSalvar(){

        UsuarioRequestDTO dto = new UsuarioRequestDTO(
                "Pedro Fernando",
                "pedro@email.com",
                "123456",
                EnumGeneroPessoa.MASCULINO,
                EnumPaises.BRASIL,
                LocalDate.of(2002, 5, 15)
        );

        Usuario usuario = new Usuario();
        usuario.setEmail(dto.email());
        usuario.setNome(dto.nome());

        when(usuarioRepository.existsByEmail(dto.email()))
        .thenReturn(false);
        when(usuarioRepository.existsByNome(dto.nome()))
        .thenReturn(false);

        when(usuarioMapper.toEntity(dto))
        .thenReturn(usuario);

        when(passwordEncoder.encode(dto.senha()))
        .thenReturn("senha-criptografada");

        usuarioService.createUsuario(dto);

        verify(passwordEncoder).encode(dto.senha());
        assertEquals("senha-criptografada", usuario.getSenha());

    }
}
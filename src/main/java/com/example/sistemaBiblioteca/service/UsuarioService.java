package com.example.sistemaBiblioteca.service;

import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.mapper.UsuarioMapper;
import com.example.sistemaBiblioteca.repository.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioMapper mapper;

    private final UsuarioDAO repository;

    public UsuarioService(UsuarioMapper mapper, UsuarioDAO repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public CriacaoUsuarioRespostaDto criarUsuario(CriacaoUsuarioRequisicaoDto requisicaoDto) throws SQLException {
        return mapper.paraRespostaDto(repository.inserirUsuario(mapper.paraEntidade(requisicaoDto)));
    }

    public List<CriacaoUsuarioRespostaDto> listarUsuarios() throws SQLException{
        return repository.buscarTodosUsuarios().stream().map(mapper::paraRespostaDto).toList();
    }

    public CriacaoUsuarioRespostaDto listarUsuarioPorId(int id) throws SQLException{
        Usuario usuario = repository.buscarUsuarioPorID(id);

        if(usuario == null){
            throw new RuntimeException("Usuário inexistente!");
        }
        return mapper.paraRespostaDto(repository.buscarUsuarioPorID(id));
    }

    /*public Usuario atualizarUsuario(int id, Usuario usuario) throws SQLException{
        List<Usuario> usuarios = repository.buscarTodosUsuarios();

        for(Usuario u : usuarios){
            if(u.getId() == id){
                usuario.setId(id);
                repository.atualizarUsuario(usuario);
                return usuario;
            }
        }
        throw new RuntimeException("ID do usuário não existe!");
    }*/

    public void deletarUsuario(int id) throws SQLException{
        Usuario usuario = repository.buscarUsuarioPorID(id);
        if(!repository.usuarioExiste(id)){
            throw new RuntimeException("Usuário inexistente!");
        }
         repository.deletarUsuario(id);
    }
}

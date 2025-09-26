package com.example.sistemaBiblioteca.service;

import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.repository.UsuarioDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioDAO repository;

    public UsuarioService(UsuarioDAO repository) {
        this.repository = repository;
    }

    public Usuario criarUsuario(Usuario usuario) throws SQLException{
        return repository.inserirUsuario(usuario);
    }

    public List<Usuario> listarUsuarios() throws SQLException{
        return repository.buscarTodosUsuarios();
    }

    public Usuario listarUsuarioPorId(int id) throws SQLException{
        return repository.buscarUsuarioPorID(id);
    }

    public Usuario atualizarUsuario(int id, Usuario usuario) throws SQLException{
        List<Usuario> usuarios = repository.buscarTodosUsuarios();

        for(Usuario u : usuarios){
            if(u.getId() == id){
                usuario.setId(id);
                repository.atualizarUsuario(usuario);
                return usuario;
            }
        }
        throw new RuntimeException("ID do usuário não existe!");
    }

    public Usuario deletarUsuario(int id) throws SQLException{
        repository.deletarUsuario(id);
        return null;
    }
}

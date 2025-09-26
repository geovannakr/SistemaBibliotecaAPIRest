package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Usuario criarUsuario(
        @RequestBody Usuario usuario
    ){
        Usuario newUser = new Usuario();
        try{
            newUser = service.criarUsuario(usuario);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newUser;
    }

    @GetMapping
    public List<Usuario> buscarTodosUsuarios(){
        List<Usuario> usuarios =new ArrayList<>();

        try{
            usuarios = service.listarUsuarios();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return usuarios;
    }

    @GetMapping("/{id}")
    public Usuario buscarUsuarioPorID(@PathVariable int id){
        Usuario newUsuario = new Usuario();
        try{
            newUsuario = service.listarUsuarioPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newUsuario;
    }

    @PostMapping("/{id}")
    public
}

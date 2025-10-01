package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Usuario> criarUsuario(
        @RequestBody Usuario usuario
    ){
        Usuario newUser = new Usuario();
        try{
            newUser = service.criarUsuario(usuario);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> buscarTodosUsuarios(){
        List<Usuario> usuarios =new ArrayList<>();

        try{
            usuarios = service.listarUsuarios();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorID(@PathVariable int id){
        Usuario newUsuario = new Usuario();
        try{
            newUsuario = service.listarUsuarioPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(newUsuario);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        Usuario newUsuario = new Usuario();
        try{
            newUsuario = service.atualizarUsuario(id, usuario);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(newUsuario);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable int id){
        try{
            service.deletarUsuario(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

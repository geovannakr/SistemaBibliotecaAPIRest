package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRespostaDto;
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
    public ResponseEntity<CriacaoUsuarioRespostaDto> criarUsuario(
            @RequestBody CriacaoUsuarioRequisicaoDto requisicaoUsuario
            ){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criarUsuario(requisicaoUsuario));
        }catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoUsuarioRespostaDto>> buscarTodosUsuarios(){
        List<Usuario> usuarios =new ArrayList<>();
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.listarUsuarios());
        }catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoUsuarioRespostaDto> buscarUsuarioPorID(@PathVariable int id){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.listarUsuarioPorId(id));
        }catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable int id, @RequestBody Usuario usuario){
        Usuario newUsuario = new Usuario();
        try{
            newUsuario = service.atualizarUsuario(id, usuario);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(newUsuario);
    }*/

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

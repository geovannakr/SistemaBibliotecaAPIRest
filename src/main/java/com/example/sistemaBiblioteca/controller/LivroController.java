package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.model.Livro;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.service.LivroService;
import com.example.sistemaBiblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@RestController
@RequestMapping("/livro")
public class LivroController {

        private final LivroService service;

        public LivroController(LivroService service) {
            this.service = service;
        }

        @PostMapping
        public Livro criarLivro(
                @RequestBody Livro livro
        ){
            Livro newUser = new Livro();
            try{
                newUser = service.criarLivro(livro);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return newUser;
        }

        @GetMapping
        public List<Livro> buscarTodosLivros(){
            List<Livro> livros =new ArrayList<>();

            try{
                livros = service.listarLivros();
            }catch (SQLException e){
                e.printStackTrace();
            }
            return livros;
        }

        @GetMapping("/{id}")
        public Livro buscarLivroPorId(@PathVariable int id){
            Livro newLivro = new Livro();
            try{
                newLivro = service.listarLivroPorID(id);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return newLivro;
        }

        @PutMapping("/{id}")
        public Livro atualizarLivro(@PathVariable int id, @RequestBody Livro livro){
            Livro newLivro = new Livro();
            try{
                newLivro = service.atualizarLivro(id, livro);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return newLivro;
        }

        @DeleteMapping("/{id}")
        public Livro deletarLivro(@PathVariable int id){
            Livro newLivro = new Livro();
            try{
                newLivro = service.deletarLivro(id);
            }catch (SQLException e){
                e.printStackTrace();
            }
            return newLivro;
        }
    }
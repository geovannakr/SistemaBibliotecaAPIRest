package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.model.Emprestimo;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.service.EmprestimoService;
import com.example.sistemaBiblioteca.service.UsuarioService;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
public class EmprestimoController {

    private final EmprestimoService service;

    public EmprestimoController(EmprestimoService service) {
        this.service = service;
    }

    @PostMapping
    public Emprestimo criarEmprestimo(
            @RequestBody Emprestimo emprestimo
    ){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            newEmprestimo = service.criarEmprestimo(emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newEmprestimo;
    }

    @GetMapping
    public List<Emprestimo> buscarTodosEmprestimos(){
        List<Emprestimo> emprestimos =new ArrayList<>();

        try{
            emprestimos = service.listarEmprestimo();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return emprestimos;
    }

    @GetMapping("/{id}")
    public Emprestimo buscarEmprestimoPorID(@PathVariable int id){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            newEmprestimo = service.listarEmprestimoPorId(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newEmprestimo;
    }

    @PutMapping("/{id}")
    public Emprestimo atualizarEmprestimo(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            newEmprestimo = service.atualizarEmprestimo(id, emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newEmprestimo;
    }

    @DeleteMapping("/{id}")
    public Emprestimo deletarEmprestimo(@PathVariable int id){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            newEmprestimo = service.deletarEmprestimo(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return newEmprestimo;
    }
}

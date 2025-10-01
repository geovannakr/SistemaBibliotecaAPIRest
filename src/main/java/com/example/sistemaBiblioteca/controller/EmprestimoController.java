package com.example.sistemaBiblioteca.controller;

import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.sistemaBiblioteca.model.Emprestimo;
import com.example.sistemaBiblioteca.service.EmprestimoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CriacaoEmprestimoRespostaDto> criarEmprestimo(
            @RequestBody CriacaoEmprestimoRequisicaoDto requisicaoEmprestimo
    ){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(service.criarEmprestimo(requisicaoEmprestimo));
        }catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<CriacaoEmprestimoRespostaDto>> buscarTodosEmprestimos(){
        List<Emprestimo> emprestimos =new ArrayList<>();

        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.listarEmprestimo());
        }catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CriacaoEmprestimoRespostaDto> buscarEmprestimoPorID(@PathVariable int id){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            return ResponseEntity.status(HttpStatus.OK).body(service.listarEmprestimoPorId(id));
        }catch (SQLException e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Emprestimo> atualizarEmprestimo(@PathVariable int id, @RequestBody Emprestimo emprestimo){
        Emprestimo newEmprestimo = new Emprestimo();
        try{
            newEmprestimo = service.atualizarEmprestimo(id, emprestimo);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.OK).body(newEmprestimo);
    }*/

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEmprestimo(@PathVariable int id){
        try{
            service.deletarEmprestimo(id);
        }catch (SQLException e){
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

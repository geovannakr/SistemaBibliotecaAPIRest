package com.example.sistemaBiblioteca.service;

import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.sistemaBiblioteca.mapper.EmprestimoMapper;
import com.example.sistemaBiblioteca.model.Emprestimo;
import com.example.sistemaBiblioteca.model.Usuario;
import com.example.sistemaBiblioteca.repository.EmprestimoDAO;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class EmprestimoService {
    private final EmprestimoMapper mapper;
    private final EmprestimoDAO repository;

    public EmprestimoService(EmprestimoDAO repository, EmprestimoMapper mapper) {
        this.repository = repository; this.mapper = mapper;
    }

    public CriacaoEmprestimoRespostaDto criarEmprestimo(CriacaoEmprestimoRequisicaoDto requisicaoDto) throws SQLException {
        return mapper.paraRespostaDto(repository.inserirEmprestimo(mapper.paraEntidade(requisicaoDto)));
    }

    public List<CriacaoEmprestimoRespostaDto> listarEmprestimo() throws SQLException{
        return repository.buscarTodosEmprestimos().stream().map(mapper::paraRespostaDto).toList();
    }

    public CriacaoEmprestimoRespostaDto listarEmprestimoPorId(int id) throws SQLException{
        Emprestimo emprestimo = repository.buscarEmprestimoPorID(id);

        if(emprestimo == null){
            throw new RuntimeException("Empréstimo inexistente");
        }
        return mapper.paraRespostaDto(repository.buscarEmprestimoPorID(id));
    }

    /*public Emprestimo atualizarEmprestimo(int id, Emprestimo emprestimo) throws SQLException{
        List<Emprestimo> emprestimos = repository.buscarTodosEmprestimos();

        for(Emprestimo emp : emprestimos){
            if(emp.getId() == id){
                emprestimo.setId(id);
                repository.atualizarEmprestimo(emprestimo);
                return emprestimo;
            }
        }
        throw new RuntimeException("ID do usuário não existe!");
    }*/

    public void deletarEmprestimo(int id) throws SQLException{
        Emprestimo emprestimo = repository.buscarEmprestimoPorID(id);

        if(!repository.emprestimoExiste(id)){
            throw new RuntimeException("Empréstimo inexistente");
        }
        repository.deletarEmprestimo(id);
    }
}

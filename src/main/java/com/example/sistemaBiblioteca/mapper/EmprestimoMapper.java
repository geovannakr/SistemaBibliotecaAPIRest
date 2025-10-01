package com.example.sistemaBiblioteca.mapper;

import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRequisicaoDto;
import com.example.sistemaBiblioteca.dto.emprestimo.CriacaoEmprestimoRespostaDto;
import com.example.sistemaBiblioteca.model.Emprestimo;
import org.springframework.stereotype.Component;

@Component
public class EmprestimoMapper {

    public Emprestimo paraEntidade(CriacaoEmprestimoRequisicaoDto requisicaoDto){
        return new Emprestimo(requisicaoDto.idLivro(), requisicaoDto.idUsuario(), requisicaoDto.dataEmprestimo(), requisicaoDto.dataDevolucao());
    }

    public CriacaoEmprestimoRespostaDto paraRespostaDto(Emprestimo emprestimo){
        return new CriacaoEmprestimoRespostaDto(emprestimo.getId(), emprestimo.getIdLivro(), emprestimo.getIdUsuario(), emprestimo.getDataEmprestimo(), emprestimo.getDataDevolucao());
    }
}

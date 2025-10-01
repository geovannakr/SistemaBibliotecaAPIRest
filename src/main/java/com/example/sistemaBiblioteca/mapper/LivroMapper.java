package com.example.sistemaBiblioteca.mapper;

import com.example.sistemaBiblioteca.dto.livro.CriacaoLivroRequisicaoDto;
import com.example.sistemaBiblioteca.dto.livro.CriacaoLivroRespostaDto;
import com.example.sistemaBiblioteca.model.Livro;
import org.springframework.stereotype.Component;

@Component
public class LivroMapper {

    public Livro paraLivro(CriacaoLivroRequisicaoDto requisicaoDto){
        return new Livro(requisicaoDto.titulo(), requisicaoDto.autor(), requisicaoDto.anoPublicacao());
    }

    public CriacaoLivroRespostaDto paraRequisicaoDto(Livro livro){
        return new CriacaoLivroRespostaDto(livro.getId(), livro.getTitulo(), livro.getAutor(), livro.getAnoPublicacao());
    }
}

package com.example.sistemaBiblioteca.mapper;

import com.example.sistemaBiblioteca.dto.livro.CriacaoLivroRequisicaoDto;
import com.example.sistemaBiblioteca.dto.livro.CriacaoLivroRespostaDto;
import com.example.sistemaBiblioteca.dto.usuario.CriacaoUsuarioRequisicaoDto;
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

    public Livro paraUpdate(CriacaoLivroRequisicaoDto requisicaoDto, Livro livro){
        if(requisicaoDto.titulo() != livro.getTitulo() && requisicaoDto.titulo() != null){
            livro.setTitulo(requisicaoDto.titulo());
        }

        if(requisicaoDto.autor() != livro.getAutor() && requisicaoDto.autor() != null){
            livro.setAutor(requisicaoDto.autor());
        }

        if(requisicaoDto.anoPublicacao() != livro.getAnoPublicacao() && requisicaoDto.anoPublicacao() == 0){
            livro.setAnoPublicacao(requisicaoDto.anoPublicacao());
        }

        return livro;
    }
}

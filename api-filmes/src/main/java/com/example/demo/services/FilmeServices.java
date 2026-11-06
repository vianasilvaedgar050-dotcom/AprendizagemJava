package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.models.Filme;
import com.example.demo.repositories.FilmeRepository;

@Service
public class FilmeServices {
    @Autowired
    private FilmeRepository filmeRepository;

    public Filme saveFilme(Filme filme) {
       var filmeExistente = filmeRepository.findByTituloIgnoreCase(filme.getTitulo());
        if (filmeExistente.isPresent()) {
            throw new RuntimeException("Filme com o mesmo título já existe.");
        }
        return filmeRepository.save(filme);
    }

    public List<Filme> getAllFilmes() {
        return filmeRepository.findAll();
    }
}
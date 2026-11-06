package com.example.demo.controllers;

import com.example.demo.models.Filme;
import com.example.demo.services.FilmeServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/api/filmes")
public class FilmeController {
    @Autowired
    private FilmeServices filmeServices;

    @PostMapping
    public ResponseEntity<Filme> createFilme(@Valid @RequestBody Filme filme) {
        try {
            Filme savedFilme = filmeServices.saveFilme(filme);
            return new ResponseEntity<>(savedFilme, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>((Filme) null, HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    public ResponseEntity<List<Filme>> getAllFilmes() {
        List<Filme> filmes = filmeServices.getAllFilmes();
        return new ResponseEntity<>(filmes, HttpStatus.OK);
    }
}

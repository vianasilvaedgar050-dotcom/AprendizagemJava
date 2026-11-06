package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Filme;

@Repository
public interface FilmeRepository extends JpaRepository<Filme, Long> {
    Optional<Filme> findByTituloIgnoreCase(String titulo);
}

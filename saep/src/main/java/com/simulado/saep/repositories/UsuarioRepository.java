package com.simulado.saep.repositories;

import com.simulado.saep.entities.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioEntity, Long> {

    // O Spring faz a "mágica" de criar a query no banco só lendo o nome deste método
    Optional<UsuarioEntity> findByEmail(String email);}



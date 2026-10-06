package com.simulado.saep.repositories;

import com.simulado.saep.entities.MovimentacaoEstoqueEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovimentacaoEstoqueRepository extends JpaRepository<MovimentacaoEstoqueEntity,Long> {

    List<MovimentacaoEstoqueEntity> findAllByOrderByDataMovimentacaoDesc();

    boolean existsByProdutoId(Long produtoId);
}
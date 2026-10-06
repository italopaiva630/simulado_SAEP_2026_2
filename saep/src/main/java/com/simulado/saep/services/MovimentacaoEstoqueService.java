package com.simulado.saep.services;

import com.simulado.saep.dtos.MovimentacaoEstoqueDto;
import com.simulado.saep.dtos.ProdutoRespostaDto;
import com.simulado.saep.entities.MovimentacaoEstoqueEntity;
import com.simulado.saep.entities.ProdutoEntity;
import com.simulado.saep.entities.TipoMovimentacaoEstoque;
import com.simulado.saep.entities.UsuarioEntity;
import com.simulado.saep.exceptions.EstoqueInsuficienteException;
import com.simulado.saep.exceptions.ProdutoNaoEncontradoException;
import com.simulado.saep.repositories.MovimentacaoEstoqueRepository;
import com.simulado.saep.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimentacaoEstoqueService {

    private final MovimentacaoEstoqueRepository movimentacaoEstoqueRepository;
    private final ProdutoRepository produtoRepository;

    public MovimentacaoEstoqueService(MovimentacaoEstoqueRepository movimentacaoEstoqueRepository, ProdutoRepository produtoRepository) {
        this.movimentacaoEstoqueRepository = movimentacaoEstoqueRepository;
        this.produtoRepository = produtoRepository;
    }

    public List<ProdutoRespostaDto> listarProdutosCadastrados() {
        return produtoRepository.findAllByOrderByNomeAsc()
                .stream()
                .map(ProdutoRespostaDto::toProdutoRespostaDto)
                .toList();
    }

    public List<MovimentacaoEstoqueEntity> listarHistoricoMovimentacoes() {
        return movimentacaoEstoqueRepository.findAllByOrderByDataMovimentacaoDesc();
    }

    @Transactional
    public void registrarEntrada(MovimentacaoEstoqueDto dto, UsuarioEntity usuarioLogado) {
        ProdutoEntity produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado no sistema."));

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + dto.getQuantidade());
        produtoRepository.save(produto);

        movimentacaoEstoqueRepository.save(dto.toMovimentacao(produto, usuarioLogado,
                TipoMovimentacaoEstoque.ENTRADA));
    }

    @Transactional
    public void registrarSaida(MovimentacaoEstoqueDto dto, UsuarioEntity usuarioLogado) {
        ProdutoEntity produto = produtoRepository.findById(dto.getProdutoId())
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado no sistema"));

        if (dto.getQuantidade() > produto.getQuantidadeEstoque()) {
            throw new EstoqueInsuficienteException("Quantidade insuficiente em estoque");
        }

        produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - dto.getQuantidade());
        produtoRepository.save(produto);

        movimentacaoEstoqueRepository.save(dto.toMovimentacao(produto, usuarioLogado,
                TipoMovimentacaoEstoque.SAIDA));
    }

    public String verificarAlertaEstoqueMinimo(Long produtoId) {
        ProdutoEntity produto = produtoRepository.findById(produtoId)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado"));

        if (produto.getQuantidadeEstoque() < produto.getEstoqueMinimo()) {
            return String.format("O produto '%s' ficou abaixo do estoque mínimo (%d unidades).",
                    produto.getNome(), produto.getEstoqueMinimo());
        }
        return null;
    }
}
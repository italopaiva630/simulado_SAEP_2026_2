package com.simulado.saep.controllers;

import com.simulado.saep.dtos.MovimentacaoEstoqueDto;
import com.simulado.saep.sessoes.SessaoUtil;
import com.simulado.saep.services.MovimentacaoEstoqueService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MovimentacaoPageController {

    private final MovimentacaoEstoqueService service;

    // CONSTRUTOR EXPLÍCITO ADICIONADO AQUI
    public MovimentacaoPageController(MovimentacaoEstoqueService service) {
        this.service = service;
    }

    @GetMapping("/movimentacoes")
    public String getMovimentacaoLista(HttpSession session, Model model){
        if (SessaoUtil.obterSessao(session) == null) {
            return "redirect:/login";
        }

        model.addAttribute("listaProdutos", service.listarProdutosCadastrados());
        model.addAttribute("listaMovimentacoes", service.listarHistoricoMovimentacoes());
        model.addAttribute("usuarioLogado", SessaoUtil.obterSessao(session));

        model.addAttribute("movimentacaoDto", new MovimentacaoEstoqueDto(null, null));

        return "movimentacoes/listarmovimentacoes";
    }
}
//package com.simulado.saep.controllers;
//
//import com.simulado.saep.entities.OperadorEntity;
//
//import com.simulado.saep.exceptions.UsuarioException;
//import com.simulado.saep.services.OperadorService;
//
//import com.simulado.saep.sessoes.SessaoDto;
//import com.simulado.saep.sessoes.SessaoUtil;
//import jakarta.servlet.http.HttpSession;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//public class OperadorController {
//
//    private final OperadorService operadorService;
//
//    public OperadorController(OperadorService operadorService) {
//        this.operadorService = operadorService;
//    }
//
//    @GetMapping("/")
//    public String index() {
//        return "redirect:/login";
//    }
//
//    @GetMapping("/login")
//    public String loginForm() {
//        return "login";
//    }
//
//    @PostMapping("/login")
//    public String realizarLogin(@RequestParam String email,
//                                @RequestParam String senha,
//                                HttpSession session,
//                                Model model) {
//        try {
//            OperadorEntity operador = operadorService.fazerLogin(email, senha);
//
//            SessaoDto sessao = new SessaoDto();
//            sessao.setUsuarioId(operador.getId());
//            sessao.setUsuarioNome(operador.getNome());
//            SessaoUtil.registrarSessao(session, sessao);
//
//            return "redirect:/operadores";
//        } catch (UsuarioException e) {
//            model.addAttribute("erro", e.getMessage());
//            return "login";
//        } catch (Exception e) {
//            e.printStackTrace();
//            model.addAttribute("erro", e.getMessage() != null ? e.getMessage() : "Erro inesperado ao realizar login.");
//            return "login";
//        }
//    }
//
//    @GetMapping("/operadores")
//    public String inicio(HttpSession session, Model model) {
//        SessaoDto sessao = SessaoUtil.obterSessao(session);
//        if (sessao == null) {
//            return "redirect:/login";
//        }
//
//        model.addAttribute("nomeUsuario", sessao.getUsuarioNome());
//        return "operadores";
//    }
//
//    @GetMapping("/logout")
//    public String logout(HttpSession session) {
//        SessaoUtil.removerSessao(session);
//        return "redirect:/login";
//    }
//
//
//}
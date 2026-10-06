//package com.simulado.saep.services;
//
//
//import com.simulado.saep.entities.OperadorEntity;
//import com.simulado.saep.exceptions.UsuarioException;
//import com.simulado.saep.repositories.OperadorRepository;
//import org.springframework.stereotype.Service;
//D
//import java.time.LocalDate;
//import java.time.Period;
//
//
//@Service
//public class OperadorService {
//
//    private final OperadorRepository repository;
//
//    public OperadorService(OperadorRepository repository) {
//        this.repository = repository;
//    }
//
//
//
//    public OperadorEntity fazerLogin(String email, String senha) {
//
//        OperadorEntity usuario = repository.findByEmail(email)
//                .orElseThrow(() ->
//                        new UsuarioException(
//                                "Usuario ou Senha Inválidos!"
//                        ));
//
//        if (!usuario.getSenha().equals(senha)) {
//            throw new UsuarioException(
//                    "Usuario ou Senha Inválidos!"
//            );
//        }
//
//        return usuario;
//    }
//
//    private void validarFormatoSenha(String senha) {
//
//        if (senha == null || senha.trim().isEmpty()) {
//            throw new UsuarioException(
//                    "A senha é obrigatória."
//            );
//        }
//
//        String regexSenha =
//                "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&.]).{5,}$";
//
//        if (!senha.matches(regexSenha)) {
//            throw new UsuarioException(
//                    "A senha deve conter no mínimo 5 caracteres, incluindo letras, números e pelo menos um caractere especial (ex: @, $, !, %, *, #, ?, &, .)."
//            );
//        }
//    }
//
//    private void validarIdade(LocalDate dataNascimento) {
//
//        if (dataNascimento == null) {
//            throw new UsuarioException(
//                    "A data de nascimento é obrigatória."
//            );
//        }
//
//        int idade =
//                Period.between(dataNascimento, LocalDate.now()).getYears();
//
//        if (idade < 18) {
//            throw new UsuarioException(
//                    "Operação negada: O colaborador deve ter pelo menos 18 anos."
//            );
//        }
//
//        if (idade > 500) {
//            throw new UsuarioException(
//                    "Operação negada: Data de nascimento inválida (limite de 500 anos excedido)."
//            );
//        }
//    }
//}

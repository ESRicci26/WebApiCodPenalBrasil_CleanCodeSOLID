package com.javaricci.infraestrutura.web.manipuladorexcecao;

import com.javaricci.dominio.excecao.DadosInvalidosExcecao;
import com.javaricci.dominio.excecao.RegistroNaoEncontradoExcecao;
import com.javaricci.infraestrutura.web.controlador.CodigoPenalWebControlador;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Manipulador de excecoes exclusivo do controlador Web/Thymeleaf, exibindo
 * uma pagina amigavel de erro em vez de uma resposta JSON.
 */
@ControllerAdvice(assignableTypes = CodigoPenalWebControlador.class)
public class ManipuladorWebDeExcecoes {

    @ExceptionHandler(RegistroNaoEncontradoExcecao.class)
    public String tratarRegistroNaoEncontrado(RegistroNaoEncontradoExcecao erro, Model modelo) {
        modelo.addAttribute("mensagemErro", erro.getMessage());
        return "codigopenal/erro";
    }

    @ExceptionHandler(DadosInvalidosExcecao.class)
    public String tratarDadosInvalidos(DadosInvalidosExcecao erro, Model modelo) {
        modelo.addAttribute("mensagemErro", erro.getMessage());
        return "codigopenal/erro";
    }
}

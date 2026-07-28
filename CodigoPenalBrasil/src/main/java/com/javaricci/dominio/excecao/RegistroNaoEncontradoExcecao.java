package com.javaricci.dominio.excecao;

/**
 * Excecao de dominio lancada quando um registro do Codigo Penal
 * nao e encontrado na base de dados pelo identificador informado.
 */
public class RegistroNaoEncontradoExcecao extends RuntimeException {

    public RegistroNaoEncontradoExcecao(Integer id) {
        super("Registro do Codigo Penal com id " + id + " nao foi encontrado.");
    }

    public RegistroNaoEncontradoExcecao(String mensagem) {
        super(mensagem);
    }
}

package com.javaricci.dominio.excecao;

/**
 * Excecao de dominio lancada quando os dados de um Codigo Penal
 * nao atendem as regras minimas de negocio para serem persistidos.
 */
public class DadosInvalidosExcecao extends RuntimeException {

    public DadosInvalidosExcecao(String mensagem) {
        super(mensagem);
    }
}

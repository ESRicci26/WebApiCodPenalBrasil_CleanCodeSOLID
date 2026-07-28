package com.javaricci.dominio.portas.entrada;

/**
 * Porta de entrada (input port): caso de uso de exclusao (DELETE) de um artigo pelo id.
 */
public interface ExcluirCodigoPenalCasoDeUsoPorta {

    void executar(Integer id);
}

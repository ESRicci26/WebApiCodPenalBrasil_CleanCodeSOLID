package com.javaricci.dominio.portas.entrada;

import com.javaricci.dominio.modelo.CodigoPenal;

/**
 * Porta de entrada (input port): caso de uso de cadastro (INSERT) de um novo artigo.
 */
public interface CadastrarCodigoPenalCasoDeUsoPorta {

    CodigoPenal executar(CodigoPenal codigoPenal);
}

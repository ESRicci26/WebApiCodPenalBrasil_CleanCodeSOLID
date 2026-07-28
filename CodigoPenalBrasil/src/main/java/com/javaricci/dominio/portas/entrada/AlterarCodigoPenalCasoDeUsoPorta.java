package com.javaricci.dominio.portas.entrada;

import com.javaricci.dominio.modelo.CodigoPenal;

/**
 * Porta de entrada (input port): caso de uso de alteracao (UPDATE) de um artigo existente.
 */
public interface AlterarCodigoPenalCasoDeUsoPorta {

    void executar(CodigoPenal codigoPenal);
}

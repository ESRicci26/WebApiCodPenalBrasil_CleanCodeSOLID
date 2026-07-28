package com.javaricci.dominio.portas.entrada;

import com.javaricci.dominio.modelo.CodigoPenal;

/**
 * Porta de entrada (input port): caso de uso de busca de um artigo pelo id.
 */
public interface BuscarCodigoPenalPorIdCasoDeUsoPorta {

    CodigoPenal executar(Integer id);
}

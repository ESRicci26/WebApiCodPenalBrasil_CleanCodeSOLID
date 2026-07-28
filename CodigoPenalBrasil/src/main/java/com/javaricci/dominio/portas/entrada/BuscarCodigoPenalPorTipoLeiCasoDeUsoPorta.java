package com.javaricci.dominio.portas.entrada;

import com.javaricci.dominio.modelo.CodigoPenal;

import java.util.List;

/**
 * Porta de entrada (input port): caso de uso de pesquisa por tipo de lei.
 */
public interface BuscarCodigoPenalPorTipoLeiCasoDeUsoPorta {

    List<CodigoPenal> executar(String tipoLei);
}

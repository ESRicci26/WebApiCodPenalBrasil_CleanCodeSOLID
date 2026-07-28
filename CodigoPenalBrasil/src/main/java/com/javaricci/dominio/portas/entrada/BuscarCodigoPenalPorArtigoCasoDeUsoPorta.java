package com.javaricci.dominio.portas.entrada;

import com.javaricci.dominio.modelo.CodigoPenal;

import java.util.List;

/**
 * Porta de entrada (input port): caso de uso de pesquisa textual na descricao do artigo.
 */
public interface BuscarCodigoPenalPorArtigoCasoDeUsoPorta {

    List<CodigoPenal> executar(String termoPesquisa);
}

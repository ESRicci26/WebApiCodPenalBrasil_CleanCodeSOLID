package com.javaricci.dominio.portas.entrada;

import com.javaricci.dominio.modelo.CodigoPenal;

import java.util.List;

/**
 * Porta de entrada (input port): caso de uso de listagem completa dos artigos do Codigo Penal.
 */
public interface ListarCodigosPenaisCasoDeUsoPorta {

    List<CodigoPenal> executar();
}

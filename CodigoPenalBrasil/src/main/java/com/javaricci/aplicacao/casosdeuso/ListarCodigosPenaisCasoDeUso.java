package com.javaricci.aplicacao.casosdeuso;

import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.entrada.ListarCodigosPenaisCasoDeUsoPorta;
import com.javaricci.dominio.portas.saida.CodigoPenalRepositorioPorta;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso: LISTAR todos os artigos do Codigo Penal.
 * Responsabilidade unica (SRP): apenas orquestrar a listagem.
 */
@Service
public class ListarCodigosPenaisCasoDeUso implements ListarCodigosPenaisCasoDeUsoPorta {

    private final CodigoPenalRepositorioPorta repositorio;

    public ListarCodigosPenaisCasoDeUso(CodigoPenalRepositorioPorta repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<CodigoPenal> executar() {
        return repositorio.listarTodos();
    }
}

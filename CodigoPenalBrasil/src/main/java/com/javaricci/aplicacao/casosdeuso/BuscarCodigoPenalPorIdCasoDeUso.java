package com.javaricci.aplicacao.casosdeuso;

import com.javaricci.dominio.excecao.RegistroNaoEncontradoExcecao;
import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.entrada.BuscarCodigoPenalPorIdCasoDeUsoPorta;
import com.javaricci.dominio.portas.saida.CodigoPenalRepositorioPorta;
import org.springframework.stereotype.Service;

/**
 * Caso de uso: BUSCAR um artigo do Codigo Penal pelo id.
 */
@Service
public class BuscarCodigoPenalPorIdCasoDeUso implements BuscarCodigoPenalPorIdCasoDeUsoPorta {

    private final CodigoPenalRepositorioPorta repositorio;

    public BuscarCodigoPenalPorIdCasoDeUso(CodigoPenalRepositorioPorta repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public CodigoPenal executar(Integer id) {
        return repositorio.buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoExcecao(id));
    }
}

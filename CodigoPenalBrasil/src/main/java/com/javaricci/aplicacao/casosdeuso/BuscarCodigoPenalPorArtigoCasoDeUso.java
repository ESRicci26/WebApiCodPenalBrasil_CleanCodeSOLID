package com.javaricci.aplicacao.casosdeuso;

import com.javaricci.dominio.excecao.DadosInvalidosExcecao;
import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.entrada.BuscarCodigoPenalPorArtigoCasoDeUsoPorta;
import com.javaricci.dominio.portas.saida.CodigoPenalRepositorioPorta;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso: PESQUISAR artigos do Codigo Penal por trecho da descricao.
 */
@Service
public class BuscarCodigoPenalPorArtigoCasoDeUso implements BuscarCodigoPenalPorArtigoCasoDeUsoPorta {

    private final CodigoPenalRepositorioPorta repositorio;

    public BuscarCodigoPenalPorArtigoCasoDeUso(CodigoPenalRepositorioPorta repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<CodigoPenal> executar(String termoPesquisa) {
        if (termoPesquisa == null || termoPesquisa.trim().isEmpty()) {
            throw new DadosInvalidosExcecao("Informe um termo para pesquisar.");
        }
        return repositorio.buscarPorDescricaoArtigo(termoPesquisa.trim());
    }
}

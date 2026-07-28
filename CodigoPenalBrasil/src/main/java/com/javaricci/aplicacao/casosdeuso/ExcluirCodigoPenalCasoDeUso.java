package com.javaricci.aplicacao.casosdeuso;

import com.javaricci.dominio.excecao.RegistroNaoEncontradoExcecao;
import com.javaricci.dominio.portas.entrada.ExcluirCodigoPenalCasoDeUsoPorta;
import com.javaricci.dominio.portas.saida.CodigoPenalRepositorioPorta;
import org.springframework.stereotype.Service;

/**
 * Caso de uso: EXCLUIR (DELETE) um artigo do Codigo Penal pelo id.
 */
@Service
public class ExcluirCodigoPenalCasoDeUso implements ExcluirCodigoPenalCasoDeUsoPorta {

    private final CodigoPenalRepositorioPorta repositorio;

    public ExcluirCodigoPenalCasoDeUso(CodigoPenalRepositorioPorta repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void executar(Integer id) {
        repositorio.buscarPorId(id)
                .orElseThrow(() -> new RegistroNaoEncontradoExcecao(id));
        repositorio.excluir(id);
    }
}

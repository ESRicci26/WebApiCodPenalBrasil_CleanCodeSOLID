package com.javaricci.aplicacao.casosdeuso;

import com.javaricci.dominio.excecao.DadosInvalidosExcecao;
import com.javaricci.dominio.excecao.RegistroNaoEncontradoExcecao;
import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.entrada.AlterarCodigoPenalCasoDeUsoPorta;
import com.javaricci.dominio.portas.saida.CodigoPenalRepositorioPorta;
import org.springframework.stereotype.Service;

/**
 * Caso de uso: ALTERAR (UPDATE) um artigo do Codigo Penal ja existente.
 */
@Service
public class AlterarCodigoPenalCasoDeUso implements AlterarCodigoPenalCasoDeUsoPorta {

    private final CodigoPenalRepositorioPorta repositorio;

    public AlterarCodigoPenalCasoDeUso(CodigoPenalRepositorioPorta repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public void executar(CodigoPenal codigoPenal) {
        if (codigoPenal.getId() == null) {
            throw new DadosInvalidosExcecao("O id do registro e obrigatorio para alteracao.");
        }
        if (!codigoPenal.possuiDadosValidos()) {
            throw new DadosInvalidosExcecao("Artigo e Descricao do Artigo sao obrigatorios para a alteracao.");
        }
        repositorio.buscarPorId(codigoPenal.getId())
                .orElseThrow(() -> new RegistroNaoEncontradoExcecao(codigoPenal.getId()));

        repositorio.atualizar(codigoPenal);
    }
}

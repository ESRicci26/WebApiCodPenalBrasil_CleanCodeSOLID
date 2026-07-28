package com.javaricci.aplicacao.casosdeuso;

import com.javaricci.dominio.excecao.DadosInvalidosExcecao;
import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.entrada.CadastrarCodigoPenalCasoDeUsoPorta;
import com.javaricci.dominio.portas.saida.CodigoPenalRepositorioPorta;
import org.springframework.stereotype.Service;

/**
 * Caso de uso: CADASTRAR (INSERT) um novo artigo do Codigo Penal.
 */
@Service
public class CadastrarCodigoPenalCasoDeUso implements CadastrarCodigoPenalCasoDeUsoPorta {

    private final CodigoPenalRepositorioPorta repositorio;

    public CadastrarCodigoPenalCasoDeUso(CodigoPenalRepositorioPorta repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public CodigoPenal executar(CodigoPenal codigoPenal) {
        if (!codigoPenal.possuiDadosValidos()) {
            throw new DadosInvalidosExcecao("Artigo e Descricao do Artigo sao obrigatorios para o cadastro.");
        }
        return repositorio.inserir(codigoPenal);
    }
}

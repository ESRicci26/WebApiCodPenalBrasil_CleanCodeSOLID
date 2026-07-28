package com.javaricci.aplicacao.casosdeuso;

import com.javaricci.dominio.excecao.DadosInvalidosExcecao;
import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.entrada.BuscarCodigoPenalPorTipoLeiCasoDeUsoPorta;
import com.javaricci.dominio.portas.saida.CodigoPenalRepositorioPorta;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Caso de uso: PESQUISAR artigos do Codigo Penal por tipo de lei.
 */
@Service
public class BuscarCodigoPenalPorTipoLeiCasoDeUso implements BuscarCodigoPenalPorTipoLeiCasoDeUsoPorta {

    private final CodigoPenalRepositorioPorta repositorio;

    public BuscarCodigoPenalPorTipoLeiCasoDeUso(CodigoPenalRepositorioPorta repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    public List<CodigoPenal> executar(String tipoLei) {
        if (tipoLei == null || tipoLei.trim().isEmpty()) {
            throw new DadosInvalidosExcecao("Informe o tipo de lei para pesquisar.");
        }
        return repositorio.buscarPorTipoLei(tipoLei.trim());
    }
}

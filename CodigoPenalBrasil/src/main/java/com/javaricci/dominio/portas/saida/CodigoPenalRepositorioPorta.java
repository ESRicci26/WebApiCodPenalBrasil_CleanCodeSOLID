package com.javaricci.dominio.portas.saida;

import com.javaricci.dominio.modelo.CodigoPenal;

import java.util.List;
import java.util.Optional;

/**
 * Porta de saida (output port) do dominio.
 * <p>
 * Define o contrato de persistencia que a infraestrutura precisa implementar
 * (ex.: adaptador JDBC/MySQL), mantendo o dominio independente de detalhes
 * de banco de dados, conforme o Principio de Inversao de Dependencia (SOLID/DIP).
 */
public interface CodigoPenalRepositorioPorta {

    List<CodigoPenal> listarTodos();

    Optional<CodigoPenal> buscarPorId(Integer id);

    CodigoPenal inserir(CodigoPenal codigoPenal);

    void atualizar(CodigoPenal codigoPenal);

    void excluir(Integer id);

    List<CodigoPenal> buscarPorDescricaoArtigo(String termoPesquisa);

    List<CodigoPenal> buscarPorTipoLei(String tipoLei);
}

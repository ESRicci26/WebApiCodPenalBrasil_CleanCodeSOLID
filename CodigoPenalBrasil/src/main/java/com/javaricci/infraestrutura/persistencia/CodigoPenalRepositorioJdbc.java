package com.javaricci.infraestrutura.persistencia;

import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.saida.CodigoPenalRepositorioPorta;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

/**
 * Adaptador de saida (output adapter) que implementa a porta de repositorio
 * do dominio utilizando Spring JDBC (JdbcTemplate) sobre o banco MySQL
 * "codigopenalbrasil", tabela "codigopenal".
 * <p>
 * Cada operacao de escrita (Insert, Update, Delete) e leitura (Lista) possui
 * seu proprio metodo dedicado, conforme solicitado.
 */
@Repository
public class CodigoPenalRepositorioJdbc implements CodigoPenalRepositorioPorta {

    private static final String TABELA = "codigopenal";

    private static final String SQL_LISTAR_TODOS =
            "SELECT id, Artigo, DescricaoArtigo, TituloLei, TipoLei FROM " + TABELA + " ORDER BY id";

    private static final String SQL_BUSCAR_POR_ID =
            "SELECT id, Artigo, DescricaoArtigo, TituloLei, TipoLei FROM " + TABELA + " WHERE id = ?";

    private static final String SQL_INSERIR =
            "INSERT INTO " + TABELA + " (Artigo, DescricaoArtigo, TituloLei, TipoLei) VALUES (?, ?, ?, ?)";

    private static final String SQL_ATUALIZAR =
            "UPDATE " + TABELA + " SET Artigo = ?, DescricaoArtigo = ?, TituloLei = ?, TipoLei = ? WHERE id = ?";

    private static final String SQL_EXCLUIR =
            "DELETE FROM " + TABELA + " WHERE id = ?";

    private static final String SQL_BUSCAR_POR_DESCRICAO_ARTIGO =
            "SELECT id, Artigo, DescricaoArtigo, TituloLei, TipoLei FROM " + TABELA
                    + " WHERE LOWER(Artigo) LIKE LOWER(?) OR LOWER(DescricaoArtigo) LIKE LOWER(?) ORDER BY id";

    private static final String SQL_BUSCAR_POR_TIPO_LEI =
            "SELECT id, Artigo, DescricaoArtigo, TituloLei, TipoLei FROM " + TABELA
                    + " WHERE LOWER(TipoLei) LIKE LOWER(?) ORDER BY id";

    private final JdbcTemplate jdbcTemplate;
    private final CodigoPenalMapeadorDeLinha mapeadorDeLinha;

    public CodigoPenalRepositorioJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        this.mapeadorDeLinha = new CodigoPenalMapeadorDeLinha();
    }

    /**
     * LISTA: retorna todos os artigos cadastrados no Codigo Penal.
     */
    @Override
    public List<CodigoPenal> listarTodos() {
        return jdbcTemplate.query(SQL_LISTAR_TODOS, mapeadorDeLinha);
    }

    @Override
    public Optional<CodigoPenal> buscarPorId(Integer id) {
        List<CodigoPenal> resultado = jdbcTemplate.query(SQL_BUSCAR_POR_ID, mapeadorDeLinha, id);
        return resultado.stream().findFirst();
    }

    /**
     * INSERT: cadastra um novo artigo do Codigo Penal e retorna o objeto com o id gerado.
     */
    @Override
    public CodigoPenal inserir(CodigoPenal codigoPenal) {
        KeyHolder chaveGerada = new GeneratedKeyHolder();

        jdbcTemplate.update(conexao -> {
            PreparedStatement comandoPreparado = conexao.prepareStatement(SQL_INSERIR, Statement.RETURN_GENERATED_KEYS);
            comandoPreparado.setString(1, codigoPenal.getArtigo());
            comandoPreparado.setString(2, codigoPenal.getDescricaoArtigo());
            comandoPreparado.setString(3, codigoPenal.getTituloLei());
            comandoPreparado.setString(4, codigoPenal.getTipoLei());
            return comandoPreparado;
        }, chaveGerada);

        Number idGerado = chaveGerada.getKey();
        if (idGerado != null) {
            codigoPenal.setId(idGerado.intValue());
        }
        return codigoPenal;
    }

    /**
     * UPDATE: altera um artigo do Codigo Penal ja existente.
     */
    @Override
    public void atualizar(CodigoPenal codigoPenal) {
        jdbcTemplate.update(SQL_ATUALIZAR,
                codigoPenal.getArtigo(),
                codigoPenal.getDescricaoArtigo(),
                codigoPenal.getTituloLei(),
                codigoPenal.getTipoLei(),
                codigoPenal.getId());
    }

    /**
     * DELETE: exclui um artigo do Codigo Penal pelo id.
     */
    @Override
    public void excluir(Integer id) {
        jdbcTemplate.update(SQL_EXCLUIR, id);
    }

    @Override
    public List<CodigoPenal> buscarPorDescricaoArtigo(String termoPesquisa) {
        String termoComCuringa = "%" + termoPesquisa + "%";
        return jdbcTemplate.query(SQL_BUSCAR_POR_DESCRICAO_ARTIGO, mapeadorDeLinha, termoComCuringa, termoComCuringa);
    }

    @Override
    public List<CodigoPenal> buscarPorTipoLei(String tipoLei) {
        String termoComCuringa = "%" + tipoLei + "%";
        return jdbcTemplate.query(SQL_BUSCAR_POR_TIPO_LEI, mapeadorDeLinha, termoComCuringa);
    }
}

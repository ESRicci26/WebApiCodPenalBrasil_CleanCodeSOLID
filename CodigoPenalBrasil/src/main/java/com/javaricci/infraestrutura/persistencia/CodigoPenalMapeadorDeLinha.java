package com.javaricci.infraestrutura.persistencia;

import com.javaricci.dominio.modelo.CodigoPenal;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Adaptador responsavel por mapear cada linha do ResultSet retornado pelo
 * JdbcTemplate para a entidade de dominio {@link CodigoPenal}.
 */
public class CodigoPenalMapeadorDeLinha implements RowMapper<CodigoPenal> {

    @Override
    public CodigoPenal mapRow(ResultSet resultado, int numeroLinha) throws SQLException {
        CodigoPenal codigoPenal = new CodigoPenal();
        codigoPenal.setId(resultado.getInt("id"));
        codigoPenal.setArtigo(resultado.getString("Artigo"));
        codigoPenal.setDescricaoArtigo(resultado.getString("DescricaoArtigo"));
        codigoPenal.setTituloLei(resultado.getString("TituloLei"));
        codigoPenal.setTipoLei(resultado.getString("TipoLei"));
        return codigoPenal;
    }
}

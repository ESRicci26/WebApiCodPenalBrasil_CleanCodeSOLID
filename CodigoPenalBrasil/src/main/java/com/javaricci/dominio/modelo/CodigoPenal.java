package com.javaricci.dominio.modelo;

import java.util.Objects;

/**
 * Entidade de dominio que representa um artigo do Codigo Penal Brasileiro.
 * <p>
 * Classe pura de dominio (sem anotacoes de framework), respeitando o
 * principio da Arquitetura Limpa em que o nucleo de negocio nao depende
 * de detalhes de infraestrutura (Spring, JDBC, Thymeleaf, etc).
 */
public class CodigoPenal {

    private Integer id;
    private String artigo;
    private String descricaoArtigo;
    private String tituloLei;
    private String tipoLei;

    public CodigoPenal() {
    }

    public CodigoPenal(Integer id, String artigo, String descricaoArtigo, String tituloLei, String tipoLei) {
        this.id = id;
        this.artigo = artigo;
        this.descricaoArtigo = descricaoArtigo;
        this.tituloLei = tituloLei;
        this.tipoLei = tipoLei;
    }

    /**
     * Construtor de conveniencia para cadastro (sem id, pois e gerado pelo banco).
     */
    public CodigoPenal(String artigo, String descricaoArtigo, String tituloLei, String tipoLei) {
        this(null, artigo, descricaoArtigo, tituloLei, tipoLei);
    }

    /**
     * Regra de negocio simples: valida se os dados minimos do artigo estao preenchidos.
     */
    public boolean possuiDadosValidos() {
        return artigo != null && !artigo.trim().isEmpty()
                && descricaoArtigo != null && !descricaoArtigo.trim().isEmpty();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArtigo() {
        return artigo;
    }

    public void setArtigo(String artigo) {
        this.artigo = artigo;
    }

    public String getDescricaoArtigo() {
        return descricaoArtigo;
    }

    public void setDescricaoArtigo(String descricaoArtigo) {
        this.descricaoArtigo = descricaoArtigo;
    }

    public String getTituloLei() {
        return tituloLei;
    }

    public void setTituloLei(String tituloLei) {
        this.tituloLei = tituloLei;
    }

    public String getTipoLei() {
        return tipoLei;
    }

    public void setTipoLei(String tipoLei) {
        this.tipoLei = tipoLei;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CodigoPenal)) return false;
        CodigoPenal that = (CodigoPenal) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "CodigoPenal{" +
                "id=" + id +
                ", artigo='" + artigo + '\'' +
                ", tituloLei='" + tituloLei + '\'' +
                ", tipoLei='" + tipoLei + '\'' +
                '}';
    }
}

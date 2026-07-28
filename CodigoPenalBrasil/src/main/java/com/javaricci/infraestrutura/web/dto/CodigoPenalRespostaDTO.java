package com.javaricci.infraestrutura.web.dto;

/**
 * DTO de saida (resposta) retornado pela API REST com os dados de um artigo do Codigo Penal.
 */
public class CodigoPenalRespostaDTO {

    private Integer id;
    private String artigo;
    private String descricaoArtigo;
    private String tituloLei;
    private String tipoLei;

    public CodigoPenalRespostaDTO() {
    }

    public CodigoPenalRespostaDTO(Integer id, String artigo, String descricaoArtigo, String tituloLei, String tipoLei) {
        this.id = id;
        this.artigo = artigo;
        this.descricaoArtigo = descricaoArtigo;
        this.tituloLei = tituloLei;
        this.tipoLei = tipoLei;
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
}

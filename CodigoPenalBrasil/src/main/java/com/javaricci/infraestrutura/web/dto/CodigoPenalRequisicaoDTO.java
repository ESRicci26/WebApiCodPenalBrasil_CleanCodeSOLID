package com.javaricci.infraestrutura.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO (Data Transfer Object) de entrada usado para cadastro e alteracao
 * de um artigo do Codigo Penal, tanto pela API REST quanto pelos formularios
 * Thymeleaf.
 */
public class CodigoPenalRequisicaoDTO {

    private Integer id;

    @NotBlank(message = "O campo Artigo e obrigatorio.")
    @Size(max = 20, message = "O campo Artigo deve ter no maximo 20 caracteres.")
    private String artigo;

    @NotBlank(message = "O campo Descricao do Artigo e obrigatorio.")
    private String descricaoArtigo;

    private String tituloLei;

    private String tipoLei;

    public CodigoPenalRequisicaoDTO() {
    }

    public CodigoPenalRequisicaoDTO(Integer id, String artigo, String descricaoArtigo, String tituloLei, String tipoLei) {
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

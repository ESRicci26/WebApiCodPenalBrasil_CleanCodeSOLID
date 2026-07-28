package com.javaricci.infraestrutura.web.dto;

import com.javaricci.dominio.modelo.CodigoPenal;

/**
 * Componente responsavel por converter entre a entidade de dominio {@link CodigoPenal}
 * e os DTOs expostos pela camada web (REST/Thymeleaf), evitando que o dominio
 * vaze detalhes de apresentacao e vice-versa.
 */
public final class CodigoPenalDTOMapeador {

    private CodigoPenalDTOMapeador() {
    }

    public static CodigoPenal paraDominio(CodigoPenalRequisicaoDTO dto) {
        return new CodigoPenal(dto.getId(), dto.getArtigo(), dto.getDescricaoArtigo(), dto.getTituloLei(), dto.getTipoLei());
    }

    public static CodigoPenalRespostaDTO paraRespostaDTO(CodigoPenal codigoPenal) {
        return new CodigoPenalRespostaDTO(
                codigoPenal.getId(),
                codigoPenal.getArtigo(),
                codigoPenal.getDescricaoArtigo(),
                codigoPenal.getTituloLei(),
                codigoPenal.getTipoLei());
    }
}

package com.javaricci.infraestrutura.web.dto;

/**
 * DTO simples para retornar mensagens de sucesso/erro em operacoes que nao retornam dados (ex.: excluir).
 */
public class CodigoPenalMensagemDTO {

    private String mensagem;

    public CodigoPenalMensagemDTO() {
    }

    public CodigoPenalMensagemDTO(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }
}

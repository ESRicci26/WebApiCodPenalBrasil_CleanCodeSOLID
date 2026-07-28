package com.javaricci.dominio.modelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CodigoPenalTest {

    @Test
    void devePossuirDadosValidosQuandoArtigoEDescricaoPreenchidos() {
        CodigoPenal codigoPenal = new CodigoPenal("Art. 121", "Matar alguem", "Decreto-Lei 2848/1940", "Codigo Penal");
        assertTrue(codigoPenal.possuiDadosValidos());
    }

    @Test
    void naoDevePossuirDadosValidosQuandoDescricaoEstiverEmBranco() {
        CodigoPenal codigoPenal = new CodigoPenal("Art. 121", "   ", "Decreto-Lei 2848/1940", "Codigo Penal");
        assertFalse(codigoPenal.possuiDadosValidos());
    }

    @Test
    void naoDevePossuirDadosValidosQuandoArtigoForNulo() {
        CodigoPenal codigoPenal = new CodigoPenal(null, "Matar alguem", "Decreto-Lei 2848/1940", "Codigo Penal");
        assertFalse(codigoPenal.possuiDadosValidos());
    }
}

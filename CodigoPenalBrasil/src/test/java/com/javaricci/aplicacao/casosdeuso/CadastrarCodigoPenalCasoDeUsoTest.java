package com.javaricci.aplicacao.casosdeuso;

import com.javaricci.dominio.excecao.DadosInvalidosExcecao;
import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.saida.CodigoPenalRepositorioPorta;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarCodigoPenalCasoDeUsoTest {

    @Mock
    private CodigoPenalRepositorioPorta repositorio;

    @Test
    void deveCadastrarQuandoDadosValidos() {
        CadastrarCodigoPenalCasoDeUso casoDeUso = new CadastrarCodigoPenalCasoDeUso(repositorio);
        CodigoPenal codigoPenal = new CodigoPenal("Art. 121", "Matar alguem", "Decreto-Lei 2848/1940", "Codigo Penal");

        when(repositorio.inserir(any(CodigoPenal.class))).thenReturn(codigoPenal);

        casoDeUso.executar(codigoPenal);

        verify(repositorio).inserir(codigoPenal);
    }

    @Test
    void deveLancarExcecaoQuandoDadosInvalidos() {
        CadastrarCodigoPenalCasoDeUso casoDeUso = new CadastrarCodigoPenalCasoDeUso(repositorio);
        CodigoPenal codigoPenalInvalido = new CodigoPenal("", "", "", "");

        assertThrows(DadosInvalidosExcecao.class, () -> casoDeUso.executar(codigoPenalInvalido));
    }
}

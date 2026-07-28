package com.javaricci.infraestrutura.web.manipuladorexcecao;

import com.javaricci.dominio.excecao.DadosInvalidosExcecao;
import com.javaricci.dominio.excecao.RegistroNaoEncontradoExcecao;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Manipulador global de excecoes da API REST. Converte as excecoes de
 * dominio/aplicacao em respostas HTTP padronizadas, mantendo os controladores
 * livres de tratamento repetitivo de erros (SRP).
 */
@RestControllerAdvice
public class ManipuladorGlobalDeExcecoes {

    @ExceptionHandler(RegistroNaoEncontradoExcecao.class)
    public ResponseEntity<Map<String, Object>> tratarRegistroNaoEncontrado(RegistroNaoEncontradoExcecao erro) {
        return construirCorpoDeErro(HttpStatus.NOT_FOUND, erro.getMessage());
    }

    @ExceptionHandler(DadosInvalidosExcecao.class)
    public ResponseEntity<Map<String, Object>> tratarDadosInvalidos(DadosInvalidosExcecao erro) {
        return construirCorpoDeErro(HttpStatus.BAD_REQUEST, erro.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> tratarValidacaoDeCampos(MethodArgumentNotValidException erro) {
        String mensagens = erro.getBindingResult().getFieldErrors().stream()
                .map(campo -> campo.getField() + ": " + campo.getDefaultMessage())
                .collect(Collectors.joining("; "));
        return construirCorpoDeErro(HttpStatus.BAD_REQUEST, mensagens);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> tratarErroGenerico(Exception erro) {
        return construirCorpoDeErro(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno: " + erro.getMessage());
    }

    private ResponseEntity<Map<String, Object>> construirCorpoDeErro(HttpStatus status, String mensagem) {
        Map<String, Object> corpo = new LinkedHashMap<>();
        corpo.put("dataHora", LocalDateTime.now());
        corpo.put("status", status.value());
        corpo.put("erro", status.getReasonPhrase());
        corpo.put("mensagem", mensagem);
        return ResponseEntity.status(status).body(corpo);
    }
}

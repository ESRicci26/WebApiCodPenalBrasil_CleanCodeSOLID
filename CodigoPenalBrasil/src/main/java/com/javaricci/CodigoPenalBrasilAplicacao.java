package com.javaricci;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Classe principal responsavel por inicializar a aplicacao Spring Boot.
 * <p>
 * Projeto: CodigoPenalBrasil
 * Arquitetura: Clean Architecture / Hexagonal
 * <ul>
 *     <li>dominio -&gt; regras de negocio e portas (independente de frameworks)</li>
 *     <li>aplicacao -&gt; casos de uso (orquestram o dominio)</li>
 *     <li>infraestrutura -&gt; adaptadores de entrada (REST/Web) e saida (JDBC/MySQL)</li>
 * </ul>
 * Frontend (Thymeleaf): http://localhost:8080/codigospenais
 * API REST: http://localhost:8080/api/codigospenais
 */
@SpringBootApplication
public class CodigoPenalBrasilAplicacao {

    public static void main(String[] args) {
        SpringApplication.run(CodigoPenalBrasilAplicacao.class, args);
    }
}

package com.javaricci.configuracao;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuracao MVC responsavel por redirecionar a raiz da aplicacao
 * diretamente para a tela de listagem dos artigos do Codigo Penal.
 */
@Configuration
public class ConfiguracaoWebMvc implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registro) {
        registro.addRedirectViewController("/", "/codigospenais");
    }
}

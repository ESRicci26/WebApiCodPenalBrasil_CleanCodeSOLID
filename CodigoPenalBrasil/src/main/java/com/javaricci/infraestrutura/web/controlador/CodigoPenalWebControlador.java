package com.javaricci.infraestrutura.web.controlador;

import com.javaricci.dominio.excecao.RegistroNaoEncontradoExcecao;
import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.entrada.AlterarCodigoPenalCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.BuscarCodigoPenalPorArtigoCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.BuscarCodigoPenalPorIdCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.BuscarCodigoPenalPorTipoLeiCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.CadastrarCodigoPenalCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.ExcluirCodigoPenalCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.ListarCodigosPenaisCasoDeUsoPorta;
import com.javaricci.infraestrutura.web.dto.CodigoPenalDTOMapeador;
import com.javaricci.infraestrutura.web.dto.CodigoPenalRequisicaoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Adaptador de entrada (input adapter) Web/MVC.
 * <p>
 * Controlador responsavel por renderizar as paginas Thymeleaf (listagem,
 * formulario de cadastro/edicao e pesquisa) reaproveitando os mesmos
 * casos de uso utilizados pela API REST.
 */
@Controller
@RequestMapping("/codigospenais")
public class CodigoPenalWebControlador {

    private final ListarCodigosPenaisCasoDeUsoPorta listarCodigosPenaisCasoDeUso;
    private final BuscarCodigoPenalPorIdCasoDeUsoPorta buscarCodigoPenalPorIdCasoDeUso;
    private final CadastrarCodigoPenalCasoDeUsoPorta cadastrarCodigoPenalCasoDeUso;
    private final AlterarCodigoPenalCasoDeUsoPorta alterarCodigoPenalCasoDeUso;
    private final ExcluirCodigoPenalCasoDeUsoPorta excluirCodigoPenalCasoDeUso;
    private final BuscarCodigoPenalPorArtigoCasoDeUsoPorta buscarCodigoPenalPorArtigoCasoDeUso;
    private final BuscarCodigoPenalPorTipoLeiCasoDeUsoPorta buscarCodigoPenalPorTipoLeiCasoDeUso;

    public CodigoPenalWebControlador(ListarCodigosPenaisCasoDeUsoPorta listarCodigosPenaisCasoDeUso,
                                      BuscarCodigoPenalPorIdCasoDeUsoPorta buscarCodigoPenalPorIdCasoDeUso,
                                      CadastrarCodigoPenalCasoDeUsoPorta cadastrarCodigoPenalCasoDeUso,
                                      AlterarCodigoPenalCasoDeUsoPorta alterarCodigoPenalCasoDeUso,
                                      ExcluirCodigoPenalCasoDeUsoPorta excluirCodigoPenalCasoDeUso,
                                      BuscarCodigoPenalPorArtigoCasoDeUsoPorta buscarCodigoPenalPorArtigoCasoDeUso,
                                      BuscarCodigoPenalPorTipoLeiCasoDeUsoPorta buscarCodigoPenalPorTipoLeiCasoDeUso) {
        this.listarCodigosPenaisCasoDeUso = listarCodigosPenaisCasoDeUso;
        this.buscarCodigoPenalPorIdCasoDeUso = buscarCodigoPenalPorIdCasoDeUso;
        this.cadastrarCodigoPenalCasoDeUso = cadastrarCodigoPenalCasoDeUso;
        this.alterarCodigoPenalCasoDeUso = alterarCodigoPenalCasoDeUso;
        this.excluirCodigoPenalCasoDeUso = excluirCodigoPenalCasoDeUso;
        this.buscarCodigoPenalPorArtigoCasoDeUso = buscarCodigoPenalPorArtigoCasoDeUso;
        this.buscarCodigoPenalPorTipoLeiCasoDeUso = buscarCodigoPenalPorTipoLeiCasoDeUso;
    }

    /**
     * Pagina inicial: lista todos os artigos, com pesquisa opcional por termo ou tipo de lei.
     */
    @GetMapping
    public String listar(@RequestParam(value = "termo", required = false) String termo,
                          @RequestParam(value = "tipoLei", required = false) String tipoLei,
                          Model modelo) {

        if (termo != null && !termo.trim().isEmpty()) {
            modelo.addAttribute("listaCodigosPenais", buscarCodigoPenalPorArtigoCasoDeUso.executar(termo));
            modelo.addAttribute("termo", termo);
        } else if (tipoLei != null && !tipoLei.trim().isEmpty()) {
            modelo.addAttribute("listaCodigosPenais", buscarCodigoPenalPorTipoLeiCasoDeUso.executar(tipoLei));
            modelo.addAttribute("tipoLei", tipoLei);
        } else {
            modelo.addAttribute("listaCodigosPenais", listarCodigosPenaisCasoDeUso.executar());
        }
        return "codigopenal/lista";
    }

    /**
     * Exibe os detalhes de um unico artigo.
     */
    @GetMapping("/{id}")
    public String detalhar(@PathVariable Integer id, Model modelo) {
        CodigoPenal codigoPenal = buscarCodigoPenalPorIdCasoDeUso.executar(id);
        modelo.addAttribute("codigoPenal", codigoPenal);
        return "codigopenal/detalhe";
    }

    /**
     * Exibe o formulario para cadastro de um novo artigo.
     */
    @GetMapping("/novo")
    public String exibirFormularioDeCadastro(Model modelo) {
        modelo.addAttribute("codigoPenalRequisicaoDTO", new CodigoPenalRequisicaoDTO());
        modelo.addAttribute("modoEdicao", false);
        return "codigopenal/formulario";
    }

    /**
     * Processa o cadastro (INSERT) de um novo artigo enviado pelo formulario.
     */
    @PostMapping
    public String cadastrar(@Valid @ModelAttribute("codigoPenalRequisicaoDTO") CodigoPenalRequisicaoDTO requisicaoDTO,
                             BindingResult resultadoValidacao,
                             Model modelo,
                             RedirectAttributes atributosRedirecionamento) {
        if (resultadoValidacao.hasErrors()) {
            modelo.addAttribute("modoEdicao", false);
            return "codigopenal/formulario";
        }
        cadastrarCodigoPenalCasoDeUso.executar(CodigoPenalDTOMapeador.paraDominio(requisicaoDTO));
        atributosRedirecionamento.addFlashAttribute("mensagemSucesso", "Artigo do Codigo Penal cadastrado com sucesso!");
        return "redirect:/codigospenais";
    }

    /**
     * Exibe o formulario preenchido para edicao de um artigo existente.
     */
    @GetMapping("/{id}/editar")
    public String exibirFormularioDeEdicao(@PathVariable Integer id, Model modelo) {
        CodigoPenal codigoPenal = buscarCodigoPenalPorIdCasoDeUso.executar(id);
        CodigoPenalRequisicaoDTO requisicaoDTO = new CodigoPenalRequisicaoDTO(
                codigoPenal.getId(), codigoPenal.getArtigo(), codigoPenal.getDescricaoArtigo(),
                codigoPenal.getTituloLei(), codigoPenal.getTipoLei());
        modelo.addAttribute("codigoPenalRequisicaoDTO", requisicaoDTO);
        modelo.addAttribute("modoEdicao", true);
        return "codigopenal/formulario";
    }

    /**
     * Processa a alteracao (UPDATE) de um artigo existente enviado pelo formulario.
     */
    @PostMapping("/{id}")
    public String alterar(@PathVariable Integer id,
                           @Valid @ModelAttribute("codigoPenalRequisicaoDTO") CodigoPenalRequisicaoDTO requisicaoDTO,
                           BindingResult resultadoValidacao,
                           Model modelo,
                           RedirectAttributes atributosRedirecionamento) {
        if (resultadoValidacao.hasErrors()) {
            modelo.addAttribute("modoEdicao", true);
            return "codigopenal/formulario";
        }
        requisicaoDTO.setId(id);
        alterarCodigoPenalCasoDeUso.executar(CodigoPenalDTOMapeador.paraDominio(requisicaoDTO));
        atributosRedirecionamento.addFlashAttribute("mensagemSucesso", "Artigo do Codigo Penal alterado com sucesso!");
        return "redirect:/codigospenais";
    }

    /**
     * Processa a exclusao (DELETE) de um artigo.
     */
    @PostMapping("/{id}/excluir")
    public String excluir(@PathVariable Integer id, RedirectAttributes atributosRedirecionamento) {
        try {
            excluirCodigoPenalCasoDeUso.executar(id);
            atributosRedirecionamento.addFlashAttribute("mensagemSucesso", "Artigo do Codigo Penal excluido com sucesso!");
        } catch (RegistroNaoEncontradoExcecao erro) {
            atributosRedirecionamento.addFlashAttribute("mensagemErro", erro.getMessage());
        }
        return "redirect:/codigospenais";
    }
}

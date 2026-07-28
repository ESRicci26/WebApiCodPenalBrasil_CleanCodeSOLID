package com.javaricci.infraestrutura.web.controlador;

import com.javaricci.dominio.modelo.CodigoPenal;
import com.javaricci.dominio.portas.entrada.AlterarCodigoPenalCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.BuscarCodigoPenalPorArtigoCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.BuscarCodigoPenalPorIdCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.BuscarCodigoPenalPorTipoLeiCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.CadastrarCodigoPenalCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.ExcluirCodigoPenalCasoDeUsoPorta;
import com.javaricci.dominio.portas.entrada.ListarCodigosPenaisCasoDeUsoPorta;
import com.javaricci.infraestrutura.web.dto.CodigoPenalDTOMapeador;
import com.javaricci.infraestrutura.web.dto.CodigoPenalMensagemDTO;
import com.javaricci.infraestrutura.web.dto.CodigoPenalRequisicaoDTO;
import com.javaricci.infraestrutura.web.dto.CodigoPenalRespostaDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Adaptador de entrada (input adapter) REST.
 * <p>
 * Expoe a API do Codigo Penal Brasileiro. Cada operacao delega diretamente
 * a um caso de uso especifico (Insert, Update, Delete, Lista, Buscar).
 */
@RestController
@RequestMapping("/api/codigospenais")
public class CodigoPenalRestControlador {

    private final ListarCodigosPenaisCasoDeUsoPorta listarCodigosPenaisCasoDeUso;
    private final BuscarCodigoPenalPorIdCasoDeUsoPorta buscarCodigoPenalPorIdCasoDeUso;
    private final CadastrarCodigoPenalCasoDeUsoPorta cadastrarCodigoPenalCasoDeUso;
    private final AlterarCodigoPenalCasoDeUsoPorta alterarCodigoPenalCasoDeUso;
    private final ExcluirCodigoPenalCasoDeUsoPorta excluirCodigoPenalCasoDeUso;
    private final BuscarCodigoPenalPorArtigoCasoDeUsoPorta buscarCodigoPenalPorArtigoCasoDeUso;
    private final BuscarCodigoPenalPorTipoLeiCasoDeUsoPorta buscarCodigoPenalPorTipoLeiCasoDeUso;

    public CodigoPenalRestControlador(ListarCodigosPenaisCasoDeUsoPorta listarCodigosPenaisCasoDeUso,
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
     * LISTA: GET /api/codigospenais
     */
    @GetMapping
    public ResponseEntity<List<CodigoPenalRespostaDTO>> listar() {
        List<CodigoPenalRespostaDTO> lista = listarCodigosPenaisCasoDeUso.executar().stream()
                .map(CodigoPenalDTOMapeador::paraRespostaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    /**
     * BUSCA POR ID: GET /api/codigospenais/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<CodigoPenalRespostaDTO> buscarPorId(@PathVariable Integer id) {
        CodigoPenal codigoPenal = buscarCodigoPenalPorIdCasoDeUso.executar(id);
        return ResponseEntity.ok(CodigoPenalDTOMapeador.paraRespostaDTO(codigoPenal));
    }

    /**
     * PESQUISA POR ARTIGO/DESCRICAO: GET /api/codigospenais/pesquisar?termo=furto
     */
    @GetMapping("/pesquisar")
    public ResponseEntity<List<CodigoPenalRespostaDTO>> pesquisarPorArtigo(@RequestParam("termo") String termo) {
        List<CodigoPenalRespostaDTO> lista = buscarCodigoPenalPorArtigoCasoDeUso.executar(termo).stream()
                .map(CodigoPenalDTOMapeador::paraRespostaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    /**
     * PESQUISA POR TIPO DE LEI: GET /api/codigospenais/tipo-lei?tipoLei=decreto-lei
     */
    @GetMapping("/tipo-lei")
    public ResponseEntity<List<CodigoPenalRespostaDTO>> pesquisarPorTipoLei(@RequestParam("tipoLei") String tipoLei) {
        List<CodigoPenalRespostaDTO> lista = buscarCodigoPenalPorTipoLeiCasoDeUso.executar(tipoLei).stream()
                .map(CodigoPenalDTOMapeador::paraRespostaDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    /**
     * INSERT: POST /api/codigospenais
     */
    @PostMapping
    public ResponseEntity<CodigoPenalRespostaDTO> cadastrar(@Valid @RequestBody CodigoPenalRequisicaoDTO requisicaoDTO) {
        CodigoPenal codigoPenalCadastrado = cadastrarCodigoPenalCasoDeUso.executar(CodigoPenalDTOMapeador.paraDominio(requisicaoDTO));
        return ResponseEntity.status(HttpStatus.CREATED).body(CodigoPenalDTOMapeador.paraRespostaDTO(codigoPenalCadastrado));
    }

    /**
     * UPDATE: PUT /api/codigospenais/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<CodigoPenalMensagemDTO> alterar(@PathVariable Integer id,
                                                            @Valid @RequestBody CodigoPenalRequisicaoDTO requisicaoDTO) {
        requisicaoDTO.setId(id);
        alterarCodigoPenalCasoDeUso.executar(CodigoPenalDTOMapeador.paraDominio(requisicaoDTO));
        return ResponseEntity.ok(new CodigoPenalMensagemDTO("Codigo Penal alterado com sucesso!"));
    }

    /**
     * DELETE: DELETE /api/codigospenais/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<CodigoPenalMensagemDTO> excluir(@PathVariable Integer id) {
        excluirCodigoPenalCasoDeUso.executar(id);
        return ResponseEntity.ok(new CodigoPenalMensagemDTO("Codigo Penal excluido com sucesso!"));
    }
}

package com.invext.app.controller;

import com.invext.app.model.Atendente;
import com.invext.app.model.Solicitacao;
import com.invext.app.model.TimeAtendimento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.invext.app.constants.TipoSolicitacao.CONTRATACAO_EMPRESTIMO;
import static com.invext.app.constants.TipoSolicitacao.OUTROS;
import static com.invext.app.constants.TipoSolicitacao.PROBLEMA_CARTAO;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/solicitacoes")
public class SolicitacaoController {

    private final Map<String, TimeAtendimento> times = new HashMap<>();

    @Autowired
    public SolicitacaoController() {
        Atendente atendenteCartao1 = new Atendente("AT1_Caio");
        Atendente atendenteCartao2 = new Atendente("AT2_Eduarda");
        Atendente atendenteCartao3 = new Atendente("AT3_Oliveira");
        Atendente atendenteEmprestimos = new Atendente("Lucy");
        Atendente atendenteOutros = new Atendente("Lucy");

        TimeAtendimento timeCartoes = new TimeAtendimento("Cartoes", List.of(atendenteCartao1, atendenteCartao2, atendenteCartao3));
        TimeAtendimento timeEmprestimos = new TimeAtendimento("Emprestimos", List.of(atendenteEmprestimos));
        TimeAtendimento timeOutros = new TimeAtendimento("Outros Assuntos", List.of(atendenteOutros));

        times.put(PROBLEMA_CARTAO.name(), timeCartoes);
        times.put(CONTRATACAO_EMPRESTIMO.name(), timeEmprestimos);
        times.put(OUTROS.name(), timeOutros);
    }

    @PostMapping
    public ResponseEntity<String> criarSolicitacao(@RequestBody Solicitacao solicitacao) {
        TimeAtendimento time = times.getOrDefault(solicitacao.getTipo().name(), times.get(OUTROS.name()));
        time.adicionarSolicitacao(solicitacao);
        return ResponseEntity.status(CREATED).body("Solicitação criada com sucesso.");
    }
}

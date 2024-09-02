package com.invext.app.model;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Data
@Builder
@Slf4j
public class Atendente {
    private final String nome;
    private int solicitacoesAtuais;
    private int solicitacoesTotais;
    private final ExecutorService executorService;

    public Atendente(String nome) {
        this.nome = nome;
        this.solicitacoesAtuais = 0;
        this.solicitacoesTotais = 0;
        this.executorService = Executors.newFixedThreadPool(3); // Permite até 3 solicitações simultâneas por atendente
    }

    public synchronized boolean podeAtender() {
        return solicitacoesAtuais < 3;
    }

    public synchronized void adicionarSolicitacao(Solicitacao solicitacao, Runnable callback) {
        solicitacoesTotais++;
        solicitacoesAtuais++;
        final int solicitacaoAtual = solicitacoesTotais;
        executorService.submit(() -> {
            try {
                // Simula o tempo de atendimento
                log.info("Atendente {} está fazendo atendimento {} referente a {}", nome, solicitacaoAtual, solicitacao.getTipo());
                TimeUnit.SECONDS.sleep(10); // Simula um tempo de atendimento de 10 segundos
                log.info("Atendente {} concluiu atendimento {} referente a {}", nome, solicitacaoAtual, solicitacao.getTipo());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } finally {
                synchronized (this) {
                    solicitacoesAtuais--;
                    callback.run(); // Chama o callback: processar a fila se solicitacoes
                }
            }
        });
    }
}
package com.invext.app.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TimeAtendimento {
    private final String nome;
    private List<Atendente> atendentes;
    private final Queue<Solicitacao> fila;

    public TimeAtendimento(String nome, List<Atendente> atendente) {
        this.nome = nome;
        this.atendentes = atendente;
        this.fila = new LinkedList<>();
    }

    public boolean temAtendenteLivre() {
        return atendentes.stream().anyMatch(Atendente::podeAtender);
    }

    public synchronized void adicionarSolicitacao(Solicitacao solicitacao) {
        if (temAtendenteLivre()) {
            Atendente atendenteLivre = atendentes.stream().filter(Atendente::podeAtender).findFirst().get();
            atendenteLivre.adicionarSolicitacao(solicitacao, this::processarFila);
        } else {
            fila.add(solicitacao);
            System.out.println("Todos os atendentes do time " + nome + " estão ocupados. Solicitação enfileirada.");
        }
    }

    public synchronized void processarFila() {
        while (!fila.isEmpty() && temAtendenteLivre()) {
            Solicitacao solicitacao = fila.poll();
            if (solicitacao != null) {
                Atendente atendenteLivre = atendentes.stream().filter(Atendente::podeAtender).findFirst().get();
                atendenteLivre.adicionarSolicitacao(solicitacao, this::processarFila);
            }
        }
    }
}
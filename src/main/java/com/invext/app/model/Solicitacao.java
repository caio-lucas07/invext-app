package com.invext.app.model;

import com.invext.app.constants.TipoSolicitacao;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Solicitacao {
    private final TipoSolicitacao tipo;
    private String descricao;

    public Solicitacao(TipoSolicitacao tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }
}
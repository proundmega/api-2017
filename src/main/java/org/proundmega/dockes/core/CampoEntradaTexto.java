package org.proundmega.dockes.core;

import lombok.Data;

@Data
public class CampoEntradaTexto {
    private CampoTemplate template;
    private String valor;

    public CampoEntradaTexto(CampoTemplate template) {
        this.template = template;
    }
}

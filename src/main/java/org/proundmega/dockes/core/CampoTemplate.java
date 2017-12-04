package org.proundmega.dockes.core;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CampoTemplate {
    private String nombreCampo;
    private TipoCampo tipoCampo;
}

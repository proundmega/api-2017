package org.proundmega.dockes.core;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import org.proundmega.dockes.configuracion.Archivos;

@Getter
@EqualsAndHashCode
@ToString
public class Entrada {
    private int id;
    private Template template;
    private List<CampoEntradaTexto> camposTexto;
    private List<CampoEntradaArchivo> camposArchivo;
    
    public Entrada(int id) {
        this.id = id;
        crearListas();
    }

    private void crearListas() {
        this.camposTexto = new ArrayList<>();
        this.camposArchivo = new ArrayList<>();
    }
    
    public void setTemplate(Template template) {
        this.template = template;
        crearListas();
        crearCamposTexto();
        crearCamposArchivo();
    }
    
    private void crearCamposTexto() {
        for(CampoTemplate campo : template.getCamposTexto()) {
            camposTexto.add(new CampoEntradaTexto(campo));
        }
    }
    
    private void crearCamposArchivo() {
        for(CampoTemplate campo : template.getCamposArchivo()) {
            camposArchivo.add(new CampoEntradaArchivo(campo, getPath()));
        }
    }

    public String getPath() {
        return template.getPath() + id;
    }
    
    public void crearArchivos() throws IOException {
        Files.createDirectories(new File(getPath()).toPath());
        
        for(CampoEntradaArchivo entradaArchivo : camposArchivo) {
            entradaArchivo.guardarArchivo();
        }
    }
    
    public String getValorDeCampoTexto(CampoTemplate template) {
        return camposTexto.stream()
                .filter(campo -> campo.getTemplate().equals(template))
                .map(campo -> campo.getValor())
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
    
}

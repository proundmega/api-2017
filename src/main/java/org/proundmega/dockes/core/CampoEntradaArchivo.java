package org.proundmega.dockes.core;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.CopyOption;
import java.nio.file.Files;
import javax.servlet.http.Part;
import lombok.Data;
import org.proundmega.dockes.configuracion.Archivos;

@Data
public class CampoEntradaArchivo {
    private CampoTemplate template;
    private String nombreArchivo;
    private Part parte;
    private String pathPadre;

    public CampoEntradaArchivo(CampoTemplate template, String pathPadre) {
        this.template = template;
        this.pathPadre = pathPadre;
    }
    
    public void guardarArchivo() throws IOException {
        try (InputStream stream = parte.getInputStream()) {
            Files.copy(stream, new File(pathPadre, parte.getSubmittedFileName()).toPath());
        }
        nombreArchivo = parte.getSubmittedFileName();
    }
    
    private String getPathCompleto() {
        return pathPadre + File.separator + nombreArchivo;
    }
    
    public Documento getDocumento() {
        return new Documento(getPathCompleto());
    }
}

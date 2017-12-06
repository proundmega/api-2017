package org.proundmega.dockes.core;

import java.io.File;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.Value;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

@Value
public class Documento implements Serializable {
    private String pathArchivo;
    private File archivo;
    
    public Documento(String pathArchivo) {
        this.pathArchivo = pathArchivo;
        this.archivo = new File(pathArchivo);
    }
    
    public String getNombre() {
        return archivo.getName();
    }
    
    public String getExtensionArchivo() {
        return FilenameUtils.getExtension(pathArchivo);
    }
    
    public String getTamañoArchivo() {
        long tamaño = archivo.length();
        return FileUtils.byteCountToDisplaySize(tamaño);
    }
}

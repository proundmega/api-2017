package org.proundmega.dockes.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import org.proundmega.dockes.configuracion.Archivos;

@Data
@AllArgsConstructor
public class Template {
    private String nombre;
    private List<CampoTemplate> campos;

    public Template(String nombre) {
        this.nombre = nombre;
        this.campos = new ArrayList<>();
    }
    
    public void addCampo(CampoTemplate campo) {
        campos.add(campo);
    }
    
    public void remove(CampoTemplate campo) {
        campos.remove(campo);
    }
    
    public void addArchivo() {
        addCampo(new CampoTemplate("Nuevo archivo", TipoCampo.ARCHIVO));
    }
    
    public void addTexto() {
        addCampo(new CampoTemplate("Nuevo campo", TipoCampo.TEXTO));
    }
    
    public List<CampoTemplate> getCamposArchivo() {
        List<CampoTemplate> lista = new ArrayList<>();
        for(CampoTemplate campo : campos) {
            if(campo.getTipoCampo() == TipoCampo.ARCHIVO) 
                lista.add(campo);
        }
        
        return lista;
    }
    
    public List<CampoTemplate> getCamposTexto() {
        List<CampoTemplate> lista = new ArrayList<>();
        for(CampoTemplate campo : campos) {
            if(campo.getTipoCampo() == TipoCampo.TEXTO) 
                lista.add(campo);
        }
        
        return lista;
    }
    
    public String getPath() {
        return Archivos.getPathAlmacenaje() + nombre + File.separator;
    }
}

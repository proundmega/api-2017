package org.proundmega.dockes.webforms;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.proundmega.dockes.core.CampoEntradaTexto;
import org.proundmega.dockes.core.Entrada;
import org.proundmega.dockes.core.Template;
import org.proundmega.dockes.ejb.AlmacenEntrada;
import org.proundmega.dockes.ejb.AlmacenTemplates;

@Named(value = "frmTabla")
@RequestScoped
public class FrmTabla {

    @EJB
    private AlmacenTemplates almacenTemplates;
    
    @EJB
    private AlmacenEntrada almacenEntradas;
    
    @Getter
    @Setter
    private Template templateSeleccionada;
    
    public FrmTabla() {
    }
    
    @PostConstruct
    public void hola() {
        templateSeleccionada = almacenTemplates.getTemplatesGuardadas().get(0);
    }
    
    public List<Entrada> getEntradasQuerySeleccionado() {
        List<Entrada> entradas = new ArrayList<>();
        entradas.add(crearEntradaFicticia1(1));
        entradas.add(crearEntradaFicticia1(2));
        
        return entradas;
    }
    
    private Entrada crearEntradaFicticia1(int id) {
        Entrada entrada = new Entrada(id);
        entrada.setTemplate(almacenTemplates.getTemplatesGuardadas().get(0));
        for(CampoEntradaTexto campo : entrada.getCamposTexto()) {
            campo.setValor("Prueba " + id);
        }
        
        return entrada;
    }
    
}

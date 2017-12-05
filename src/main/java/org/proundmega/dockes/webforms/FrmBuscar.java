package org.proundmega.dockes.webforms;

import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.proundmega.dockes.core.Entrada;
import org.proundmega.dockes.core.Template;
import org.proundmega.dockes.ejb.AlmacenEntrada;
import org.proundmega.dockes.ejb.AlmacenTemplates;

@Named
@ViewScoped
public class FrmBuscar implements Serializable {
    @Getter
    @Setter
    private Template templateSeleccionada;
    
    @Getter
    @Setter
    private Entrada entradaQuery;
    
    @Getter
    private List<Entrada> entradasResultantes;
    
    @EJB
    private AlmacenTemplates almacenTemplates;
    
    @EJB
    private AlmacenEntrada almacenEntradas;
    
    @Getter
    @Setter
    private Entrada entradaSeleccionada;
    
    public FrmBuscar() {
    }
    
    @PostConstruct
    private void init() {
        entradaQuery = almacenEntradas.crearEntradaNueva();
        entradasResultantes = almacenEntradas.findAll();
    }
    
    public List<Template> getTemplatesDisponibles() {
        return almacenTemplates.getTemplatesGuardadas();
    }
    
    public void onTemplateSeleccionada() {
        if(templateSeleccionada != null) {
            entradaQuery.setTemplate(templateSeleccionada);
        }
    }
    
    public void onQueryChange() {
        entradasResultantes = almacenEntradas.findLikeEntrada(entradaQuery);
    }
    
    public List<Entrada> getEntradasQuerySeleccionado() {
        return entradasResultantes;
    }
    
}

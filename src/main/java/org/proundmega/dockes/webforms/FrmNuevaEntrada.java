package org.proundmega.dockes.webforms;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.proundmega.dockes.core.Entrada;
import org.proundmega.dockes.core.Template;
import org.proundmega.dockes.ejb.AlmacenEntrada;
import org.proundmega.dockes.ejb.AlmacenTemplates;

@Data
@Named
@ViewScoped
public class FrmNuevaEntrada implements Serializable {
    private Template templateSeleccionada;
    private Entrada entrada;
    
    private UIComponent componente;
    
    @EJB
    @Setter(AccessLevel.NONE)
    private AlmacenTemplates almacenTemplates;
    
    @EJB
    @Setter(AccessLevel.NONE)
    private AlmacenEntrada almacenEntradas;
    
    @PostConstruct
    private void init() {
        entrada = almacenEntradas.crearEntradaNueva();
    }
    
    public List<Template> getTemplatesDisponibles() {
        return almacenTemplates.getTemplatesGuardadas();
    }
    
    public void onTemplateSeleccionada() {
        if(templateSeleccionada != null) {
            entrada.setTemplate(templateSeleccionada);
        }
    }
    
    public String guardar() {
        try {
            almacenEntradas.guardarEntrada(entrada);
            
            return "exito_entrada";
        } catch (IOException ex) {
            Logger.getLogger(FrmNuevaEntrada.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new RuntimeException("No pude crear los archivos...", ex);
        }
    }
}

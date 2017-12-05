package org.proundmega.dockes.webforms;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import org.proundmega.dockes.core.Template;
import org.proundmega.dockes.ejb.AlmacenTemplates;

@Named
@ViewScoped
public class FrmNuevaTemplate implements Serializable {
    @Getter
    private Template template;
    
    @EJB
    private AlmacenTemplates almacen;

    public FrmNuevaTemplate() {
    }
    
    @PostConstruct
    private void initBean() {
        clear();
    }
    
    public void clear() {
        template = new Template("Mi nueva template");
    }
    
    public String guardarTemplate() {
        try {
            almacen.addTemplate(template);
            clear();
            return "nueva_template";
        } catch (IOException ex) {
            Logger.getLogger(FrmNuevaTemplate.class.getName()).log(Level.SEVERE, null, ex);
            
            throw new RuntimeException("No pude crear el directorio del template", ex);
        }
    }
    
}

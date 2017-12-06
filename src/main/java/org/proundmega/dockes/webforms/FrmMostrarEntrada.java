package org.proundmega.dockes.webforms;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.servlet.http.HttpServletRequest;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.omnifaces.util.Faces;
import org.proundmega.dockes.core.Documento;
import org.proundmega.dockes.core.Entrada;
import org.proundmega.dockes.ejb.AlmacenEntrada;

@Named
@ViewScoped
public class FrmMostrarEntrada implements Serializable{
    @Getter
    @Setter
    private Entrada entrada;
    
    @EJB
    private AlmacenEntrada almacenEntradas;
    
    @Getter
    @Setter
    private Documento documentoSeleccionado;
    
    public FrmMostrarEntrada() {
    }

    @PostConstruct
    private void obtenerIdDePath() {
        HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext()
                .getRequest();

        int idEntrada = Integer.parseInt(request.getParameter("idEntrada"));
        
        entrada = almacenEntradas.findById(idEntrada);
        
        System.out.println(entrada.getCamposArchivo());
    }
    
    public void descargarArchivo() throws IOException {
        Faces.sendFile(documentoSeleccionado.getArchivo(), true);
    }
    
}

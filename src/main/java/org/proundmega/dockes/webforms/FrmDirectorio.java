package org.proundmega.dockes.webforms;

import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.faces.view.ViewScoped;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;
import org.proundmega.dockes.core.CampoEntradaTexto;
import org.proundmega.dockes.core.Entrada;
import org.proundmega.dockes.core.Template;
import org.proundmega.dockes.ejb.AlmacenEntrada;
import org.proundmega.dockes.ejb.AlmacenTemplates;

@Named
@ViewScoped
public class FrmDirectorio implements Serializable {
    
    @Getter
    private Class<Entrada> entradaClase;
    
    @Getter
    @Setter
    private Entrada entradaSeleccionada;
    
    @EJB
    private AlmacenEntrada almacenEntrada;
    
    @EJB
    private AlmacenTemplates almacenTemplate;
    
    @PostConstruct
    private void init() {
        entradaClase = Entrada.class;
    }
    
    public TreeNode getNodoRoot() {
        TreeNode nodoRoot = new DefaultTreeNode("Entradas", null);
        crearNodosEntradas(nodoRoot);
        
        return nodoRoot;
    }
    
    private void crearNodosEntradas(TreeNode nodoRoot) {
        for(Template template : almacenTemplate.getTemplatesGuardadas()) {
            TreeNode nodoTemplate = new DefaultTreeNode(template.getNombre(), nodoRoot);
            crearEntradasDeTemplate(template, nodoTemplate);
        }
    }
    
    private void crearEntradasDeTemplate(Template template, TreeNode nodoTemplate) {
        for(Entrada entrada : almacenEntrada.findByTemplate(template)) {
            TreeNode nodoEntrada = new DefaultTreeNode(entrada, nodoTemplate);
            crearRaizDeCamposDeEntrada(entrada, nodoEntrada);
        }
    }
    
    private void crearRaizDeCamposDeEntrada(Entrada entrada, TreeNode nodoEntrada) {
        for(CampoEntradaTexto entradaTexto : entrada.getCamposTexto()) {
            TreeNode entradaHija = new DefaultTreeNode(
                    entradaTexto.getTemplate().getNombreCampo() 
                            + ": " + entradaTexto.getValor()
                    , nodoEntrada);
        }
    }
    
    public void listen(Entrada entrada) {
        System.out.println(entrada);
    }
    
}

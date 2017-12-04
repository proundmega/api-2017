package org.proundmega.dockes.ejb;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import lombok.Getter;
import lombok.ToString;
import org.proundmega.dockes.core.CampoTemplate;
import org.proundmega.dockes.core.Template;
import org.proundmega.dockes.core.TipoCampo;

@Singleton
public class AlmacenTemplates {
    private List<Template> templates;
    
    @PostConstruct
    private void initAlmacen() {
        try {
            templates = new ArrayList<>();
            addTemplate(getTemplatePrueba());
        } catch (IOException ex) {
            throw new RuntimeException("No se pudo crear directorio para template de prueba...", ex);
        }
    }
    
    private Template getTemplatePrueba() {
        Template template = new Template("Partida de nacimiento");
        template.addCampo(new CampoTemplate("nombre", TipoCampo.TEXTO));
        template.addCampo(new CampoTemplate("partida de nacimiento", TipoCampo.ARCHIVO));
        
        return template;
    }
    
    public void addTemplate(Template nuevaTemplate) throws IOException {
        Files.createDirectory(new File(nuevaTemplate.getPath()).toPath());
        
        templates.add(nuevaTemplate);
    }
    
    public List<Template> getTemplatesGuardadas() {
        return Collections.unmodifiableList(templates);
    }
}

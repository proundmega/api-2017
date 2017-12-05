package org.proundmega.dockes.ejb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import org.proundmega.dockes.core.CampoEntradaTexto;
import org.proundmega.dockes.core.CampoTemplate;
import org.proundmega.dockes.core.Entrada;

@Singleton
public class AlmacenEntrada {
    private AtomicInteger numero;
    private List<Entrada> entradas;
    
    @PostConstruct
    private void iniciarCampos() {
        numero = new AtomicInteger(1);
        entradas = new ArrayList<>();
    }
    
    public Entrada crearEntradaNueva() {
        return new Entrada(numero.incrementAndGet());
    }
    
    public void guardarEntrada(Entrada entrada) throws IOException {
        entrada.crearArchivos();
        entradas.add(entrada);
    }
    
    public List<Entrada> findAll() {
        return entradas;
    }
    
    public List<Entrada> findLikeEntrada(Entrada query) {
        return entradas.stream()
                .filter(entrada -> entidadLikeTodosLosCampos(entrada, query))
                .collect(Collectors.toList());
    }

    private boolean entidadLikeTodosLosCampos(Entrada entradaAProbar, Entrada query) { 
        Map<CampoTemplate, String> valores = entradaAProbar.getCamposTexto().stream()
                .collect(Collectors.toMap(CampoEntradaTexto::getTemplate
                        , CampoEntradaTexto::getValor));
        
        return query.getCamposTexto().stream()
                .allMatch(tupla -> stringLike(
                        valores.get(tupla.getTemplate()), tupla.getValor()
                ));
    }
    
    private boolean stringLike(String aProbar, String patron) {
        return aProbar.toLowerCase().contains(patron.trim().toLowerCase());
    }
}

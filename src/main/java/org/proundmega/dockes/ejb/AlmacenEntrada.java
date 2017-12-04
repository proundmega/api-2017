package org.proundmega.dockes.ejb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
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
}

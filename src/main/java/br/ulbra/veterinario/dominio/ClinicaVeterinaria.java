package main.java.br.ulbra.veterinario.dominio;

import main.java.br.ulbra.veterinario.cobranca.CobrancaCachorro;
import main.java.br.ulbra.veterinario.cobranca.CobrancaGato;
import main.java.br.ulbra.veterinario.cobranca.PoliticaDeCobranca;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClinicaVeterinaria {
    private final List<Consultorio> consultorios = new ArrayList<>();

    public ClinicaVeterinaria(int quantidadeConsultorios) {
        for (int i = 1; i <= quantidadeConsultorios; i++) {
            consultorios.add(new Consultorio(i)); // Composição
        }
    }

    // Lista protegida (não modificável por fora)
    public List<Consultorio> getConsultorios() {
        return Collections.unmodifiableList(consultorios);
    }

    public int consultoriosLivres() {
        int livres = 0;
        for (Consultorio c : consultorios) {
            if (c.estaLivre()) {
                livres++;
            }
        }
        return livres;
    }

    // Único ponto do código que decide a política pelo tipo do animal (RN1)
    private PoliticaDeCobranca escolherPolitica(Animal animal) {
        if (animal.getTipo().equals("CACHORRO")) {
            return new CobrancaCachorro();
        }
        return new CobrancaGato();
    }

    // RN4: Entrada ocupa consultório livre
    public FichaAtendimento iniciarAtendimento(Animal animal, LocalDateTime entrada) {
        Consultorio livre = buscarConsultorioLivre();
        if (livre == null) {
            throw new IllegalStateException("Nenhum consultório livre disponível.");
        }
        livre.ocupar(animal);
        PoliticaDeCobranca politica = escolherPolitica(animal);
        return new FichaAtendimento(animal, politica, entrada);
    }

    // RN5: Saída libera consultório e cobra
    public double finalizarAtendimento(FichaAtendimento ficha) {
        for (Consultorio c : consultorios) {
            if (!c.estaLivre() && c.getAnimal().equals(ficha.getAnimal())) {
                c.liberar();
                break;
            }
        }
        return ficha.finalizar();
    }

    private Consultorio buscarConsultorioLivre() {
        for (Consultorio c : consultorios) {
            if (c.estaLivre()) {
                return c;
            }
        }
        return null;
    }
}
package main.java.br.ulbra.veterinario.dominio;

import java.time.LocalDateTime;
import main.java.br.ulbra.veterinario.cobranca.PoliticaDeCobranca;

public class FichaAtendimento {
    private final Animal animal;
    private final PoliticaDeCobranca politica;
    private final LocalDateTime entrada;

    public FichaAtendimento(Animal animal, PoliticaDeCobranca politica, LocalDateTime entrada) {
        this.animal = animal;
        this.politica = politica;
        this.entrada = entrada;
    }

    public double finalizar() {
        return politica.calcularValor();
    }

    public Animal getAnimal() {
        return animal;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }
}
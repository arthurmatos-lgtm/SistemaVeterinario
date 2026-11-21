package main.java.br.ulbra.veterinario.dominio;

import main.java.br.ulbra.veterinario.cobranca.PoliticaDeCobranca;
import java.time.LocalDateTime;

public class FichaAtendimento {
    private final Animal animal;
    private final PoliticaDeCobranca politica; // Variável do tipo da interface
    private final LocalDateTime entrada;

    public FichaAtendimento(Animal animal, PoliticaDeCobranca politica, LocalDateTime entrada) {
        this.animal = animal;
        this.politica = politica;
        this.entrada = entrada;
    }

    public double finalizar() {
        return politica.calcularValor(); // Execução polimórfica
    }

    public Animal getAnimal() {
        return animal;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }
}
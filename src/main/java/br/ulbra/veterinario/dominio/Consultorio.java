package main.java.br.ulbra.veterinario.dominio;

public class Consultorio {
    private final int numero;
    private Animal animal;

    Consultorio(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public Animal getAnimal() {
        return animal;
    }

    public boolean estaLivre() {
        return animal == null;
    }

    void ocupar(Animal animal) {
        this.animal = animal;
    }

    void liberar() {
        this.animal = null;
    }
}
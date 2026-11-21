package main.java.br.ulbra.veterinario.dominio;

public class Gato extends Animal {
    public Gato(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return "GATO";
    }
}
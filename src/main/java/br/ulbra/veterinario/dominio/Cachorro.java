package main.java.br.ulbra.veterinario.dominio;

public class Cachorro extends Animal {
    public Cachorro(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return "CACHORRO";
    }
}
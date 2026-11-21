package main.java.br.ulbra.veterinario.dominio;

public abstract class Animal {
    private final String nome;

    protected Animal(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do animal não pode ser vazio.");
        }
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract String getTipo();
}
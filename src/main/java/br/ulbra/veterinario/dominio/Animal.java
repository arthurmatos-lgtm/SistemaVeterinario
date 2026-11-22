package main.java.br.ulbra.veterinario.dominio;

public abstract class Animal {
    private final String nome;

    protected Animal(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new DadosAnimalInvalidosException(
                    "Nome do animal inválido: não pode ser vazio. Informe um nome válido para o cadastro.");
        }
        this.nome = nome.trim();
    }

    public String getNome() {
        return nome;
    }

    public abstract String getTipo();
}
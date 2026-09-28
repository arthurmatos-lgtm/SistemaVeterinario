package br.com.sistemaVeterinario.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Tutor {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    // Interface List + inicialização com ArrayList
    private final List<Animal> animais = new ArrayList<>();

    public Tutor(int id, String nome, String cpf, String telefone, String email) {
        if (id <= 0) throw new IllegalArgumentException("ID do Tutor deve ser maior que zero.");
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome é obrigatório.");
        if (cpf == null || !cpf.contains(".") || !cpf.contains("-")) {
            throw new IllegalArgumentException("CPF deve estar formatado.");
        }
        if (email == null || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido.");
        }

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    // --- INCLUSÃO COM VALIDAÇÃO DE NULL E DUPLICIDADE ---
    public void adicionarAnimal(Animal animal) {
        if (animal == null) {
            throw new IllegalArgumentException("Animal não pode ser nulo.");
        }
        if (animais.contains(animal)) {
            throw new IllegalStateException("Este Animal já está cadastrado para este tutor.");
        }
        animais.add(animal);
    }

    // --- CONSULTA COM OPTIONAL ---
    public Optional<Animal> buscarAnimalPorId(int idAnimal) {
        return animais.stream()
                .filter(a -> a.getId() == idAnimal)
                .findFirst();
    }

    // --- REMOÇÃO ---
    public boolean removerAnimal(Animal animal) {
        return animais.remove(animal);
    }

    // --- PROTEÇÃO DE ENCAPSULAMENTO ---
    public List<Animal> getAnimais() {
        return List.copyOf(animais); // Retorna cópia imutável
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    public void setTelefone(String telefone) { this.telefone = telefone; }
    public void setEmail(String email) {
        if (email == null || !email.contains("@")) throw new IllegalArgumentException("E-mail inválido.");
        this.email = email;
    }
}

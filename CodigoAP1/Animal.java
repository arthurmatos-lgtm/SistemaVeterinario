package br.com.sistemaVeterinario.model;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal {
    private int id;
    private String nome;
    private String raca;
    private float peso;
    private Tutor tutor;
    private final List<Consulta> historicoConsultas = new ArrayList<>();

    public Animal(int id, String nome, String raca, float peso, Tutor tutor) {
        if (id <= 0) throw new IllegalArgumentException("ID deve ser maior que zero.");
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome é obrigatório.");
        if (peso <= 0) throw new IllegalArgumentException("Peso deve ser maior que zero.");
        if (tutor == null) throw new IllegalArgumentException("Animal exige um Tutor associado.");

        this.id = id;
        this.nome = nome;
        this.raca = raca;
        this.peso = peso;
        this.tutor = tutor;
        tutor.adicionarAnimal(this);
    }

    // --- MÉTODOS ESPECIALIZADOS / SOBRESCRITOS (@Override nas subclasses) ---
    public abstract float calcularNecessidadeCaloricaDiaria();

    public String gerarRecomendacaoCuidados() {
        return "Manter vacinação anual e vermifugação em dia.";
    }

    // --- GERENCIAMENTO DO HISTÓRICO DE CONSULTAS ---
    public void registrarConsulta(Consulta consulta) {
        if (consulta == null) throw new IllegalArgumentException("Consulta não pode ser nula.");
        historicoConsultas.add(consulta);
    }

    public void atualizarPeso(float novoPeso) {
        if (novoPeso <= 0) throw new IllegalArgumentException("Novo peso deve ser maior que zero.");
        this.peso = novoPeso;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getRaca() { return raca; }
    public float getPeso() { return peso; }
    public Tutor getTutor() { return tutor; }
    public List<Consulta> getHistoricoConsultas() { return List.copyOf(historicoConsultas); }
}

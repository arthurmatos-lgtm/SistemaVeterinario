package br.com.sistemaVeterinario.model;

public class Cachorro extends Animal {
    private boolean adestrado;

    public Cachorro(int id, String nome, String raca, float peso, Tutor tutor, boolean adestrado) {
        super(id, nome, raca, peso, tutor); // Invoca construtor da superclasse
        this.adestrado = adestrado;
    }

    @Override
    public float calcularNecessidadeCaloricaDiaria() {
        // Cálculo metabólico canino baseado no peso corporal
        return getPeso() * 30.0f + 70.0f;
    }

    @Override
    public String gerarRecomendacaoCuidados() {
        return super.gerarRecomendacaoCuidados() + " Realizar passeios diários para gasto de energia.";
    }

    public boolean isAdestrado() { return adestrado; }
}

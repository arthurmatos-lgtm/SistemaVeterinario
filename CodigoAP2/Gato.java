public class Gato extends Animal {
    private boolean ehCastrado;

    public Gato(int id, String nome, String raca, float peso, Tutor tutor, boolean ehCastrado) {
        super(id, nome, raca, peso, tutor);
        this.ehCastrado = ehCastrado;
    }

    @Override
    public float calcularNecessidadeCaloricaDiaria() {
        float fator = ehCastrado ? 1.2f : 1.4f;
        return (getPeso() * 30.0f + 70.0f) * fator;
    }

    @Override
    public String gerarRecomendacaoCuidados() {
        return super.gerarRecomendacaoCuidados() + " Disponibilizar pontos de água corrente (fontes) e ambiente verticalizado.";
    }

    public boolean isEhCastrado() { return ehCastrado; }
}
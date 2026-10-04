
public class Cachorro extends Animal {
    private boolean adestrado;

    public Cachorro(int id, String nome, String raca, float peso, Tutor tutor, boolean adestrado) {
        super(id, nome, raca, peso, tutor);
        this.adestrado = adestrado;
    }

    @Override
    public float calcularNecessidadeCaloricaDiaria() {
        return getPeso() * 30.0f + 70.0f;
    }

    @Override
    public String gerarRecomendacaoCuidados() {
        return super.gerarRecomendacaoCuidados() + " Realizar passeios diários para gasto de energia.";
    }

    public boolean isAdestrado() { return adestrado; }
}
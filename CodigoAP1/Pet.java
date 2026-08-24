
public class Pet {
    private int id;
    private String nome;
    private EspeciePet especie;
    private String raca;
    private float peso;
    private String alergias;
    private Tutor tutor;

    public Pet(int id, String nome, EspeciePet especie, String raca, float peso, String alergias, Tutor tutor) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do Pet deve ser maior que zero.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do Pet é obrigatório.");
        }
        if (especie == null) {
            throw new IllegalArgumentException("A espécie é obrigatória.");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("O peso deve ser maior que zero.");
        }
        if (tutor == null) {
            throw new IllegalArgumentException("Todo Pet precisa ter um Tutor associado.");
        }

        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.peso = peso;
        this.alergias = alergias;
        this.tutor = tutor;
    }

    public void atualizarPeso(float novoPeso) {
        if (novoPeso <= 0) {
            throw new IllegalArgumentException("O novo peso aferido deve ser maior que zero.");
        }
        this.peso = novoPeso;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public EspeciePet getEspecie() { return especie; }
    public String getRaca() { return raca; }
    public float getPeso() { return peso; }
    public String getAlergias() { return alergias; }
    public Tutor getTutor() { return tutor; }
}
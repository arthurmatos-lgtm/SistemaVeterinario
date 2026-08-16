public class Pet {
    private int id;
    private String nome;
    private EspeciePet especie;
    private String raca;
    private float peso;
    private String alergias;
    private Tutor tutor;

    public Pet(int id, String nome, EspeciePet especie, String raca, float peso, String alergias, Tutor tutor) {
        if (id <= 0) throw new IllegalArgumentException("O ID do Pet deve ser um número positivo.");
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("O nome do Pet não pode ser vazio.");
        if (especie == null) throw new IllegalArgumentException("A espécie do Pet deve ser especificada.");
        if (peso <= 0) throw new IllegalArgumentException("O peso do Pet deve ser maior que zero.");
        if (tutor == null) throw new IllegalArgumentException("O Pet precisa ter um Tutor associado.");

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
            throw new IllegalArgumentException("O novo peso deve ser maior que zero.");
        }
        System.out.println("Alterando peso do pet " + this.nome + " de " + this.peso + "kg para " + novoPeso + "kg.");
        this.peso = novoPeso;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public EspeciePet getEspecie() { return especie; }
    public float getPeso() { return peso; }
    public Tutor getTutor() { return tutor; }
}

public class Pet {
    private int id;
    private String nome;
    private EspeciePet especie;
    private String raca;
    private float peso;
    private String alergias;
    private Tutor tutor;

    // Construtor completo com as validações
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

    // Método para alterar o peso
    public void atualizarPeso(float novoPeso) {
        if (novoPeso <= 0) {
            throw new IllegalArgumentException("O novo peso deve ser maior que zero.");
        }
        this.peso = novoPeso;
    }

    // GETTERS 
    public int getId() { return id; }
    public String getNome() { return nome; }
    public EspeciePet getEspecie() { return especie; }
    public String getRaca() { return raca; }
    public float getPeso() { return peso; }
    public String getAlergias() { return alergias; }
    public Tutor getTutor() { return tutor; }
}

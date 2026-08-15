public class Pet {
    private int id;
    private String nome;
    private String especie;
    private String raca;
    private float peso;
    private String alergias;
    private Tutor tutor;

    public Pet(int id, String nome, String especie, String raca, float peso, String alergias, Tutor tutor) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do Pet deve ser um número positivo.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do Pet não pode ser vazio.");
        }
        if (peso <= 0) {
            throw new IllegalArgumentException("O peso do Pet deve ser maior que zero.");
        }
        if (tutor == null) {
            throw new IllegalArgumentException("O Pet precisa obrigatoriamente estar associado a um Tutor.");
        }

        this.id = id;
        this.nome = nome;
        this.especie = especie;
        this.raca = raca;
        this.peso = peso;
        this.alergias = alergias;
        this.tutor = tutor;
    }

    public void cadastrar() {
        System.out.println("Pet " + this.nome + " (" + this.especie + ") cadastrado(a) para o tutor " + this.tutor.getNome() + "!");
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getEspecie() { return especie; }
    public String getRaca() { return raca; }
    public float getPeso() { return peso; }
    public String getAlergias() { return alergias; }
    public Tutor getTutor() { return tutor; }
}
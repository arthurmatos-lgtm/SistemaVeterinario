public class Tutor {
    //
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    // Construtor
    public Tutor(int id, String nome, String cpf, String telefone, String email) {
        if (id <= 0) throw new IllegalArgumentException("O ID deve ser um número positivo.");
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("O nome é obrigatório.");

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    // GETTERS
    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    // SETTERS 
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

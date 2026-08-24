public class Tutor {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Tutor(int id, String nome, String cpf, String telefone, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID do Tutor deve ser um número positivo.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do Tutor não pode ser vazio ou nulo.");
        }
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do Tutor não pode ser vazio ou nulo.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("O e-mail do Tutor não pode ser vazio ou nulo.");
        }

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public void cadastrar() {
        System.out.println("Tutor(a) " + this.nome + " cadastrado(a) com sucesso!");
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }
}
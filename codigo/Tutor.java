// Tutor.java
public class Tutor {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Tutor(int id, String nome, String cpf, String telefone, String email) {
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
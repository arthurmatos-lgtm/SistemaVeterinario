public class Tutor {
    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;

    public Tutor(int id, String nome, String cpf, String telefone, String email) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do Tutor deve ser maior que zero.");
        }
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome do Tutor é obrigatório.");
        }
        
        // Validação de CPF formatado
        if (cpf == null || cpf.trim().isEmpty()) {
            throw new IllegalArgumentException("CPF do Tutor é obrigatório.");
        }
        if (!cpf.contains(".") || !cpf.contains("-")) {
            throw new IllegalArgumentException("CPF deve estar formatado (ex: 000.000.000-00).");
        }

        // Validação de E-mail com @
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("E-mail do Tutor é obrigatório.");
        }
        if (!email.contains("@")) {
            throw new IllegalArgumentException("E-mail do Tutor deve conter o caractere '@'.");
        }

        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.email = email;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
    public String getTelefone() { return telefone; }
    public String getEmail() { return email; }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void setEmail(String email) {
        if (email == null || email.trim().isEmpty() || !email.contains("@")) {
            throw new IllegalArgumentException("E-mail inválido. Deve conter o caractere '@'.");
        }
        this.email = email;
    }
}

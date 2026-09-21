import java.time.LocalDateTime;

public class Receituario {
    private int id;
    private String medicamentos;
    private String modoDeUso;
    private LocalDateTime dataEmissao;
    private Consulta consulta;

    // Construtor com acesso restrito ao pacote: garante que apenas a Consulta crie a instância
    protected Receituario(int id, String medicamentos, String modoDeUso, Consulta consulta) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID do receituário deve ser maior que zero.");
        }
        if (medicamentos == null || medicamentos.trim().isEmpty()) {
            throw new IllegalArgumentException("Medicamentos não podem estar em branco.");
        }
        if (modoDeUso == null || modoDeUso.trim().isEmpty()) {
            throw new IllegalArgumentException("Modo de uso não pode estar em branco.");
        }
        if (consulta == null) {
            throw new IllegalArgumentException("Receituário exige uma consulta válida associada.");
        }

        this.id = id;
        this.medicamentos = medicamentos;
        this.modoDeUso = modoDeUso;
        this.consulta = consulta;
        this.dataEmissao = LocalDateTime.now();
    }

    // CÁLCULO / PROCESSAMENTO DELEGADO
    // A própria parte assume a responsabilidade de formatar seus dados e calcular linhas de prescrição
    public String gerarPrescricaoFormatada() {
        return String.format(
            "| RECEITUARIO #%d | Data: %s |\nMedicamento(s): %s\nInstrucoes de Uso: %s",
            this.id,
            this.dataEmissao.toLocalDate().toString(),
            this.medicamentos.toUpperCase(),
            this.modoDeUso
        );
    }

    public int getId() { return id; }
    public String getMedicamentos() { return medicamentos; }
    public String getModoDeUso() { return modoDeUso; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public Consulta getConsulta() { return consulta; }
}

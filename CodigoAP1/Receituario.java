import java.time.LocalDateTime;

public class Receituario {
    private int id;
    private String medicamentos;
    private String modoDeUso;
    private LocalDateTime dataEmissao;
    private Consulta consulta;

    // Construtor com visibilidade de pacote (package-private) ou usado pela Consulta
    protected Receituario(int id, String medicamentos, String modoDeUso, Consulta consulta) {
        if (id <= 0) throw new IllegalArgumentException("ID do Receituário deve ser maior que zero.");
        if (medicamentos == null || medicamentos.trim().isEmpty()) {
            throw new IllegalArgumentException("Medicamentos são obrigatórios.");
        }
        if (modoDeUso == null || modoDeUso.trim().isEmpty()) {
            throw new IllegalArgumentException("Modo de uso é obrigatório.");
        }
        if (consulta == null) {
            throw new IllegalArgumentException("Consulta vinculada é obrigatória.");
        }

        this.id = id;
        this.medicamentos = medicamentos;
        this.modoDeUso = modoDeUso;
        this.consulta = consulta;
        this.dataEmissao = LocalDateTime.now();
    }

    // Processamento/Cálculo delegado
    public String gerarTextoFormatado() {
        return String.format("Receita #%d [Emissão: %s] - Remédios: %s | Como usar: %s",
                this.id, this.dataEmissao.toString(), this.medicamentos, this.modoDeUso);
    }

    public int getId() { return id; }
    public String getMedicamentos() { return medicamentos; }
    public String getModoDeUso() { return modoDeUso; }
    public LocalDateTime getDataEmissao() { return dataEmissao; }
    public Consulta getConsulta() { return consulta; }
}

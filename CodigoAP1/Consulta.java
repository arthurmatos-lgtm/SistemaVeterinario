import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private LocalDateTime dataHora;
    private String sintomas;
    private float pesoAferido;
    private StatusConsulta status;
    private Pet pet;

    // COMPOSIÇÃO: Objeto 'parte' mantido internamente
    private Receituario receituario;

    public Consulta(int id, LocalDateTime dataHora, String sintomas, float pesoAferido, Pet pet) {
        if (id <= 0) throw new IllegalArgumentException("ID da Consulta deve ser maior que zero.");
        if (dataHora == null) throw new IllegalArgumentException("Data/Hora é obrigatória.");
        if (pet == null) throw new IllegalArgumentException("Pet é obrigatório.");

        this.id = id;
        this.dataHora = dataHora;
        this.sintomas = sintomas;
        this.pesoAferido = pesoAferido;
        this.pet = pet;
        this.status = StatusConsulta.AGENDADA;
        this.receituario = null;
    }

    // CRIAÇÃO INTERNA DA PARTE (COMPOSIÇÃO)
    public void emitirReceituario(int idReceituario, String medicamentos, String modoDeUso) {
        if (this.status != StatusConsulta.REALIZADA) {
            throw new IllegalStateException("Só é possível emitir receituário para consultas REALIZADAS.");
        }
        // A parte nasce aqui dentro, recebendo 'this' como referência da consulta
        this.receituario = new Receituario(idReceituario, medicamentos, modoDeUso, this);
    }

    // REMOÇÃO / ESVAZIAMENTO DA PARTE
    public void cancelarConsulta() {
        this.status = StatusConsulta.CANCELADA;
        // Destruição da parte: o receituário deixa de existir com o cancelamento da consulta
        this.receituario = null;
    }

    public void removerReceituario() {
        if (this.receituario == null) {
            throw new IllegalStateException("Não há receituário ativo nesta consulta.");
        }
        this.receituario = null;
    }

    // DELEGAÇÃO DE PROCESSAMENTO
    public String obterTextoReceita() {
        if (this.receituario == null) {
            return "Sem receituário associado.";
        }
        // DELEGAÇÃO: repassa a responsabilidade de formatação para a classe Receituario
        return this.receituario.gerarPrescricaoFormatada();
    }

    public void realizar() {
        this.status = StatusConsulta.REALIZADA;
        if (this.pet != null) {
            this.pet.atualizarPeso(this.pesoAferido);
        }
    }

    public int getId() { return id; }
    public StatusConsulta getStatus() { return status; }
    public Receituario getReceituario() { return receituario; }
    public Pet getPet() { return pet; }
}

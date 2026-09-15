package br.com.sistemaVeterinario.model;

import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private LocalDateTime dataHora;
    private String sintomas;
    private float pesoAferido;
    private StatusConsulta status;
    private Pet pet;
    
    // COMPOSIÇÃO: O Receituario (parte) é gerenciado integralmente pela Consulta (todo)
    private Receituario receituario;

    public Consulta(int id, LocalDateTime dataHora, String sintomas, float pesoAferido, Pet pet) {
        if (id <= 0) throw new IllegalArgumentException("ID da Consulta deve ser maior que zero.");
        if (dataHora == null) throw new IllegalArgumentException("Data/hora é obrigatória.");
        if (pet == null) throw new IllegalArgumentException("Pet é obrigatório.");

        this.id = id;
        this.dataHora = dataHora;
        this.sintomas = sintomas;
        this.pesoAferido = pesoAferido;
        this.pet = pet;
        this.status = StatusConsulta.AGENDADA;
        this.receituario = null; // Inicia sem receituário
    }

    // CRIAÇÃO INTERNA DA PARTE (COMPOSIÇÃO)
    public void emitirReceituario(int idReceituario, String medicamentos, String modoDeUso) {
        if (this.status != StatusConsulta.REALIZADA) {
            throw new IllegalStateException("Só é possível emitir receituário para consultas já REALIZADAS.");
        }
        // A parte nasce AQUI DENTRO, garantindo que o ciclo de vida depende da Consulta
        this.receituario = new Receituario(idReceituario, medicamentos, modoDeUso, this);
    }

    // ESBOÇO DO MÉTODO DE REMOÇÃO
    public void cancelarReceituario() {
        if (this.receituario == null) {
            throw new IllegalStateException("Não há receituário associado a esta consulta para ser cancelado.");
        }
        // Ao anular a referência, a parte deixa de existir no contexto desta consulta
        this.receituario = null;
    }

    // ESBOÇO DO CÁLCULO/PROCESSAMENTO DELEGADO À PARTE
    public String obterResumoMedicacao() {
        if (this.receituario == null) {
            return "Nenhum medicamento prescrito para esta consulta.";
        }
        // Delegação de responsabilidade: a Consulta pede as informações formatadas para o Receituario
        return this.receituario.gerarTextoFormatado();
    }

    public void agendar() {
        this.status = StatusConsulta.AGENDADA;
    }

    public void realizar() {
        this.status = StatusConsulta.REALIZADA;
        if (this.pet != null) {
            this.pet.atualizarPeso(this.pesoAferido);
        }
    }

    // Getters
    public int getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getSintomas() { return sintomas; }
    public float getPesoAferido() { return pesoAferido; }
    public StatusConsulta getStatus() { return status; }
    public Pet getPet() { return pet; }
    public Receituario getReceituario() { return receituario; }
}

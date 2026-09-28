package br.com.sistemaVeterinario.model;

import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private LocalDateTime dataHora;
    private String sintomas;
    private float pesoAferido;
    private StatusConsulta status;
    private Animal animal;

    // COMPOSIÇÃO: O Receituario (parte) é mantido e gerenciado internamente pela Consulta
    private Receituario receituario;

    public Consulta(int id, LocalDateTime dataHora, String sintomas, float pesoAferido, Animal animal) {
        if (id <= 0) throw new IllegalArgumentException("ID da Consulta deve ser maior que zero.");
        if (dataHora == null) throw new IllegalArgumentException("Data/Hora é obrigatória.");
        if (animal == null) throw new IllegalArgumentException("Animal é obrigatório.");

        this.id = id;
        this.dataHora = dataHora;
        this.sintomas = sintomas;
        this.pesoAferido = pesoAferido;
        this.animal = animal;
        this.status = StatusConsulta.AGENDADA;
        this.receituario = null;
        
        animal.registrarConsulta(this);
    }

    // --- CRIAÇÃO INTERNA DA PARTE (COMPOSIÇÃO) ---
    public void emitirReceituario(int idReceituario, String medicamentos, String modoDeUso) {
        if (this.status != StatusConsulta.REALIZADA) {
            throw new IllegalStateException("Só é possível emitir receituário para consultas REALIZADAS.");
        }
        // A parte nasce internamente no todo
        this.receituario = new Receituario(idReceituario, medicamentos, modoDeUso, this);
    }

    // --- REMOÇÃO / ESVAZIAMENTO DA PARTE ---
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

    // --- DELEGAÇÃO DE PROCESSAMENTO ---
    public String obterTextoReceita() {
        if (this.receituario == null) {
            return "Sem receituário associado.";
        }
        // DELEGAÇÃO: repassa a formatação/cálculo da prescrição para o Receituario
        return this.receituario.gerarPrescricaoFormatada();
    }

    public void realizar() {
        this.status = StatusConsulta.REALIZADA;
        if (this.animal != null) {
            this.animal.atualizarPeso(this.pesoAferido);
        }
    }

    public int getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getSintomas() { return sintomas; }
    public float getPesoAferido() { return pesoAferido; }
    public StatusConsulta getStatus() { return status; }
    public Animal getAnimal() { return animal; }
    public Receituario getReceituario() { return receituario; }
}

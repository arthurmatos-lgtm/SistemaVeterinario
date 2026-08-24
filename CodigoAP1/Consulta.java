

import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private LocalDateTime dataHora;
    private String sintomas;
    private float pesoAferido;
    private StatusConsulta status;
    private Pet pet;

    public Consulta(int id, LocalDateTime dataHora, String sintomas, float pesoAferido, Pet pet) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID da Consulta deve ser maior que zero.");
        }
        if (dataHora == null) {
            throw new IllegalArgumentException("Data e hora são obrigatórias.");
        }
        if (pesoAferido <= 0) {
            throw new IllegalArgumentException("O peso aferido na consulta deve ser maior que zero.");
        }
        if (pet == null) {
            throw new IllegalArgumentException("Consulta exige um Pet associado.");
        }

        this.id = id;
        this.dataHora = dataHora;
        this.sintomas = sintomas;
        this.pesoAferido = pesoAferido;
        this.pet = pet;
        this.status = StatusConsulta.PENDENTE;
    }

    public void agendar() {
        if (this.status == StatusConsulta.CANCELADA) {
            throw new IllegalStateException("Consulta cancelada não pode ser agendada.");
        }
        this.status = StatusConsulta.AGENDADA;
    }

    public void realizar() {
        if (this.status != StatusConsulta.AGENDADA) {
            throw new IllegalStateException("A consulta precisa estar AGENDADA para ser realizada.");
        }
        this.status = StatusConsulta.REALIZADA;
        this.pet.atualizarPeso(this.pesoAferido);
    }

    public int getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getSintomas() { return sintomas; }
    public float getPesoAferido() { return pesoAferido; }
    public StatusConsulta getStatus() { return status; }
    public Pet getPet() { return pet; }
}
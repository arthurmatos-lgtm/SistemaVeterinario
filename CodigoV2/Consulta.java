import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Consulta {
    private int id;
    private LocalDateTime dataHora;
    private String sintomas;
    private StatusConsulta status;
    private float pesoAferido;
    private Pet pet;

    public Consulta(int id, LocalDateTime dataHora, String sintomas, float pesoAferido, Pet pet) {
        if (id <= 0) {
            throw new IllegalArgumentException("O ID da Consulta deve ser um número positivo.");
        }
        if (dataHora == null) {
            throw new IllegalArgumentException("A data e hora da Consulta não podem ser nulas.");
        }
        if (pesoAferido <= 0) {
            throw new IllegalArgumentException("O peso aferido na consulta deve ser maior que zero.");
        }
        if (pet == null) {
            throw new IllegalArgumentException("A Consulta precisa ter um Pet associado.");
        }

        this.id = id;
        this.dataHora = dataHora;
        this.sintomas = sintomas;
        this.pesoAferido = pesoAferido;
        this.pet = pet;
        this.status = StatusConsulta.PENDENTE;
    }

    public void agendar() {
        this.status = StatusConsulta.AGENDADA;
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        System.out.println("Consulta #" + this.id + " agendada para " + this.dataHora.format(fmt) + " (Pet: " + this.pet.getNome() + ").");
    }

    public void registrar() {
        this.status = StatusConsulta.REALIZADA;
        System.out.println("Consulta #" + this.id + " realizada. Sintomas: '" + this.sintomas + "', Peso Aferido: " + this.pesoAferido + " kg.");
    }

    public int getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getSintomas() { return sintomas; }
    public StatusConsulta getStatus() { return status; }
    public float getPesoAferido() { return pesoAferido; }
    public Pet getPet() { return pet; }
}
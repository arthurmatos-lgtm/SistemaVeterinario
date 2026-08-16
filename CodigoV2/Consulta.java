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
        if (id <= 0) throw new IllegalArgumentException("ID da Consulta inválido.");
        if (dataHora == null) throw new IllegalArgumentException("Data/Hora inválida.");
        if (pesoAferido <= 0) throw new IllegalArgumentException("Peso aferido inválido.");
        if (pet == null) throw new IllegalArgumentException("Pet inválido.");

        this.id = id;
        this.dataHora = dataHora;
        this.sintomas = sintomas;
        this.pesoAferido = pesoAferido;
        this.pet = pet;
        this.status = StatusConsulta.PENDENTE; 
    }

    public void agendar() {
        if (this.status == StatusConsulta.CANCELADA) {
            throw new IllegalStateException("Não é possível agendar uma consulta cancelada.");
        }
        this.status = StatusConsulta.AGENDADA;
        System.out.println("Consulta #" + this.id + " alterada para status: " + this.status);
    }

    public void realizar() {
        if (this.status != StatusConsulta.AGENDADA) {
            throw new IllegalStateException("A consulta precisa estar agendada para ser realizada.");
        }
        this.status = StatusConsulta.REALIZADA;
        this.pet.atualizarPeso(this.pesoAferido); // Atualiza o estado do Pet também
        System.out.println("Consulta #" + this.id + " alterada para status: " + this.status);
    }

    public void cancelar() {
        if (this.status == StatusConsulta.REALIZADA) {
            throw new IllegalStateException("Não é possível cancelar uma consulta já realizada.");
        }
        this.status = StatusConsulta.CANCELADA;
        System.out.println("Consulta #" + this.id + " alterada para status: " + this.status);
    }

    public int getId() { return id; }
    public StatusConsulta getStatus() { return status; }
    public Pet getPet() { return pet; }
}

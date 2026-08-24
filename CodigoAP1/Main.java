import br.com.sistemaVeterinario.model.*;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        System.out.println("   CASO DE USO: ATENDIMENTO VETERINÁRIO COMPLETO  ");


        // Cadastrar Tutor e Pet no sistema
        Tutor tutor = new Tutor(1, "Ana Souza", "123.456.789-00", "(11) 98888-7777", "ana@email.com");
        Pet pet = new Pet(10, "Mel", EspeciePet.GATO, "Siames", 4.2f, "Nenhuma", tutor);

        System.out.println("[CADASTRO]");
        System.out.println("Tutor: " + tutor.getNome());
        System.out.println("Pet: " + pet.getNome() + " (" + pet.getEspecie() + ") - Peso atual: " + pet.getPeso() + " kg\n");

        // Criar a consulta com novo peso medido na recepção
        Consulta consulta = new Consulta(1001, LocalDateTime.now(), "Perda de apetite", 4.5f, pet);
        System.out.println("[CONSULTA CRIADA] Status: " + consulta.getStatus());

        // Confirmar agendamento
        consulta.agendar();
        System.out.println("[AGENDAMENTO] Status alterado para: " + consulta.getStatus());

        // Veterinário realiza o atendimento
        consulta.realizar();
        System.out.println("[ATENDIMENTO] Status alterado para: " + consulta.getStatus());

        System.out.println("RESULTADO DO ATENDIMENTO:");
        System.out.println("Status da Consulta: " + consulta.getStatus());
        System.out.println("Novo peso registrado no Pet: " + pet.getPeso() + " kg");
    }
}
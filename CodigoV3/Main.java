import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("= SISTEMA VETERINÁRIO - DEMONSTRAÇÃO =\n");

        // 1. Criando o Tutor
        Tutor tutor = new Tutor(1, "Carla Oliveira", "123.456.789-00", "(11) 99999-9999", "carla@email.com");
        System.out.println("Tutor cadastrado: " + tutor.getNome());

        // 2. Criando o Pet associado ao Tutor
        Pet pet = new Pet(1, "Thor", EspeciePet.CAO, "Golden Retriever", 30.0f, "Nenhuma", tutor);
        System.out.println("Pet cadastrado: " + pet.getNome() + " | Peso inicial: " + pet.getPeso() + "kg\n");

        // 3. Criando a Consulta
        Consulta consulta = new Consulta(101, LocalDateTime.now().plusDays(1), "Checkup e Pesagem", 32.5f, pet);
        System.out.println("1️Estado inicial da Consulta: " + consulta.getStatus());

        // 4. Mudança de Estado 1: Agendando a consulta
        consulta.agendar();
        System.out.println("Estado após agendamento: " + consulta.getStatus());

        // 5. Mudança de Estado 2: Realizando a consulta
        consulta.realizar();
        System.out.println("Estado final da Consulta: " + consulta.getStatus());

        // 6. Confirmação do novo peso do Pet refletido após a realização
        System.out.println("\n RESULTADO FINAL DO PET ");
        System.out.println("Nome: " + pet.getNome());
        System.out.println("Novo peso aferido: " + pet.getPeso() + "kg");
    }
}

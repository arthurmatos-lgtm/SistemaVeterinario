package br.com.sistemaVeterinario;

import br.com.sistemaVeterinario.model.*;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA VETERINÁRIO - DEMONSTRAÇÃO COMPLETA ===\n");

        // 1. CRIANDO O TUTOR
        Tutor tutor = new Tutor(
            1, 
            "Ana Souza", 
            "123.456.789-00", 
            "(51) 98888-7777", 
            "ana.souza@email.com"
        );
        System.out.println("Tutor cadastrado: " + tutor.getNome() + " (CPF: " + tutor.getCpf() + ")");

        // 2. INSTANCIANDO AS SUBCLASSES DE ANIMAL (POLIMORFISMO E HERANÇA)
        // O construtor das subclasses invoca super(...) e cadastra automaticamente o animal na lista do Tutor
        Animal dog = new Cachorro(101, "Thor", "Golden Retriever", 28.5f, tutor, true);
        Animal cat = new Gato(102, "Mimi", "Siamês", 4.2f, tutor, true); // Gato castrado

        System.out.println("\n--- Animais Cadastrados do Tutor (via List<Animal>) ---");
        for (Animal animal : tutor.getAnimais()) {
            System.out.println("- " + animal.getNome() + " | Raça: " + animal.getRaca() + " | Peso: " + animal.getPeso() + "kg");
            
            // Invocação de métodos polimórficos (@Override)
            System.out.println("  > Gasto calórico diário: " + animal.calcularNecessidadeCaloricaDiaria() + " kcal");
            System.out.println("  > Recomendação: " + animal.gerarRecomendacaoCuidados());
        }

        // 3. REGISTRANDO CONSULTA E COMPOSIÇÃO COM RECEITUÁRIO
        System.out.println("\n--- Agendando e Realizando Consulta ---");
        Consulta consultaCachorro = new Consulta(
            5001, 
            LocalDateTime.now(), 
            "Avaliação dermatológica e check-up", 
            29.0f, 
            dog
        );

        System.out.println("Status da Consulta inicial: " + consultaCachorro.getStatus());

        // Concretizando a consulta
        consultaCachorro.realizar();
        System.out.println("Status após realização: " + consultaCachorro.getStatus());
        System.out.println("Peso atualizado do " + dog.getNome() + ": " + dog.getPeso() + "kg");

        // Emitindo receituário (COMPOSIÇÃO: criado pela própria Consulta)
        consultaCachorro.emitirReceituario(
            301, 
            "Shampoo Terapêutico Clorexidina e Omega 3", 
            "Banhos 2x por semana. Administrar 1 cápsula de Omega 3 ao dia por 30 dias."
        );

        // Delegação de processamento: a Consulta chama a formatação do Receituário
        System.out.println("\n--- Receituário Emitido (via Delegação) ---");
        System.out.println(consultaCachorro.obterTextoReceita());

        // 4. DEMONSTRAÇÃO DO HISTÓRICO NO ANIMAL
        System.out.println("\n--- Histórico de Consultas do Pet " + dog.getNome() + " ---");
        for (Consulta c : dog.getHistoricoConsultas()) {
            System.out.println("Consulta ID: " + c.getId() + " | Sintomas: " + c.getSintomas() + " | Status: " + c.getStatus());
        }

        // 5. TESTE DE ENCAPSULAMENTO E SEGURANÇA (COLEÇÃO PROTEGIDA)
        System.out.println("\n--- Teste de Segurança do Encapsulamento ---");
        try {
            tutor.getAnimais().clear(); // Tenta limpar a lista externa
        } catch (UnsupportedOperationException e) {
            System.out.println(" [OK] Proteção ativada: A lista de animais do Tutor é imutável via getter!");
        }

        System.out.println("\n=== FIM DA DEMONSTRAÇÃO ===");
    }
}

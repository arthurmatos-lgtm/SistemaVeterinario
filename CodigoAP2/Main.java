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

        // 2. INSTANCIANDO AS SUBCLASSES DE ANIMAL
        Animal dog = new Cachorro(101, "Thor", "Golden Retriever", 28.5f, tutor, true);
        Animal cat = new Gato(102, "Mimi", "Siamês", 4.2f, tutor, true);

        System.out.println("\n--- Animais Cadastrados do Tutor (via List<Animal>) ---");
        for (Animal animal : tutor.getAnimais()) {
            System.out.println("- " + animal.getNome() + " | Raça: " + animal.getRaca() + " | Peso: " + animal.getPeso() + "kg");
            System.out.println("  > Gasto calórico diário: " + animal.calcularNecessidadeCaloricaDiaria() + " kcal");
            System.out.println("  > Recomendação: " + animal.gerarRecomendacaoCuidados());
        }

        // 3. REGISTRANDO CONSULTA COM ESTRATÉGIA DE COBRANÇA
        System.out.println("\n--- Agendando e Realizando Consulta ---");
        Consulta consultaCachorro = new Consulta(
            5001, 
            LocalDateTime.now(), 
            "Avaliação dermatológica e check-up", 
            29.0f, 
            250.0, 
            dog, 
            new CobrancaPlanoFidelidade(15.0) // 15% de desconto
        );

        System.out.println("Status da Consulta inicial: " + consultaCachorro.getStatus());
        System.out.println("Detalhes de Cobrança: " + consultaCachorro.obterDetalhesCobranca());

        // Concretizando a consulta
        consultaCachorro.realizar();
        System.out.println("Status após realização: " + consultaCachorro.getStatus());
        System.out.println("Peso atualizado do " + dog.getNome() + ": " + dog.getPeso() + "kg");

        // Emitindo receituário (COMPOSIÇÃO)
        consultaCachorro.emitirReceituario(
            301, 
            "Shampoo Terapêutico Clorexidina e Omega 3", 
            "Banhos 2x por semana. Administrar 1 cápsula de Omega 3 ao dia por 30 dias."
        );

        System.out.println("\n--- Receituário Emitido (via Delegação) ---");
        System.out.println(consultaCachorro.obterTextoReceita());

        // 4. HISTÓRICO DE CONSULTAS
        System.out.println("\n--- Histórico de Consultas do Pet " + dog.getNome() + " ---");
        for (Consulta c : dog.getHistoricoConsultas()) {
            System.out.println("Consulta ID: " + c.getId() + " | Sintomas: " + c.getSintomas() + " | Status: " + c.getStatus());
        }

        // 5. TESTE DE SEGURANÇA DO ENCAPSULAMENTO
        System.out.println("\n--- Teste de Segurança do Encapsulamento ---");
        try {
            tutor.getAnimais().clear();
        } catch (UnsupportedOperationException e) {
            System.out.println(" [OK] Proteção ativada: A lista de animais do Tutor é imutável via getter!");
        }

        System.out.println("\n=== FIM DA DEMONSTRAÇÃO ===");
    }
}
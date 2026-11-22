package main.java.br.ulbra.veterinario.app;

import java.time.LocalDateTime;
import main.java.br.ulbra.veterinario.dominio.Animal;
import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.Gato;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import main.java.br.ulbra.veterinario.dominio.VeterinariaException;
import main.java.br.ulbra.veterinario.repositorio.RepositorioException;
import main.java.br.ulbra.veterinario.servico.AtendimentoService;

public class ConsoleClinica {
    private final AtendimentoService service;

    public ConsoleClinica(AtendimentoService service) {
        this.service = service;
    }

    public void executarDemonstracao() {
        LocalDateTime agora = LocalDateTime.of(2026, 11, 21, 14, 0);

        System.out.println("=== INICIANDO ATENDIMENTOS NA CLÍNICA (1 CONSULTÓRIO) ===\n");

        try {
            System.out.println("[Tentativa 1] Cadastrando animal com nome em branco...");
            Animal invalido = new Cachorro("   ");
            service.iniciarAtendimento(invalido, agora);
        } catch (VeterinariaException e) {
            System.out.println("-> Erro de negócio tratado: " + e.getMessage());
        } finally {
            System.out.println("-> Consultórios livres após Tentativa 1: " + service.consultoriosLivres() + "\n");
        }

        FichaAtendimento fichaRex = service.iniciarAtendimento(new Cachorro("Rex"), agora);
        System.out.println("[Sucesso] Atendimento iniciado para Rex. Consultórios livres: " + service.consultoriosLivres() + "\n");

        try {
            System.out.println("[Tentativa 2] Tentando iniciar atendimento para Mingau sem consultório livre...");
            service.iniciarAtendimento(new Gato("Mingau"), agora.plusMinutes(5));
        } catch (VeterinariaException e) {
            System.out.println("-> Erro de negócio tratado: " + e.getMessage());
        } finally {
            System.out.println("-> Consultórios livres após Tentativa 2: " + service.consultoriosLivres() + "\n");
        }

        try {
            System.out.println("[Tentativa 3] Tentando encerrar atendimento de Rex com horário anterior à entrada...");
            service.encerrarAtendimento(fichaRex, agora.minusMinutes(10));
        } catch (VeterinariaException e) {
            System.out.println("-> Erro de negócio tratado: " + e.getMessage());
        } finally {
            System.out.println("-> Consultórios livres após Tentativa 3 (estado preservado): " + service.consultoriosLivres() + "\n");
        }

        try {
            RegistroAtendimento reg = service.encerrarAtendimento(fichaRex, agora.plusMinutes(30));
            System.out.printf("[Sucesso] Atendimento finalizado: %s (%s) - Valor: R$ %.2f%n",
                    reg.nomeAnimal(), reg.tipoAnimal(), reg.valorCobrado());
            System.out.println("-> Consultórios livres ao fim: " + service.consultoriosLivres());
            System.out.println("-> Total de registros persistidos no histórico: " + service.listarHistorico().size());
        } catch (VeterinariaException | RepositorioException e) {
            System.out.println("-> Falha ao encerrar atendimento: " + e.getMessage());
        }
    }
}
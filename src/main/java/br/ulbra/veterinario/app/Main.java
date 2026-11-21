package main.java.br.ulbra.veterinario.app;

import main.java.br.ulbra.veterinario.dominio.Animal;
import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.Gato;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import main.java.br.ulbra.veterinario.repositorio.ArquivoAtendimentoRepositorio;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        Path arquivo = Path.of("dados", "atendimentos.csv");
        var repositorio = new ArquivoAtendimentoRepositorio(arquivo);
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(3, repositorio);

        System.out.println("=== Histórico antes dos novos atendimentos ===");
        System.out.println("Total de registros salvos: " + clinica.listarHistorico().size());

        Animal rex = new Cachorro("Rex");
        Animal mingau = new Gato("Mingau");

        LocalDateTime agora = LocalDateTime.now();
        FichaAtendimento ficha1 = clinica.iniciarAtendimento(rex, agora);
        FichaAtendimento ficha2 = clinica.iniciarAtendimento(mingau, agora);

        clinica.finalizarAtendimento(ficha1, agora.plusMinutes(30));
        clinica.finalizarAtendimento(ficha2, agora.plusMinutes(20));

        System.out.println("\n=== Histórico atualizado no arquivo ===");
        for (RegistroAtendimento reg : clinica.listarHistorico()) {
            System.out.printf("- %s (%s) | Valor: R$ %.2f%n",
                    reg.nomeAnimal(), reg.tipoAnimal(), reg.valorCobrado());
        }
    }
}
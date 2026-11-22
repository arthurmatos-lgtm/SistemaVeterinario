package main.java.br.ulbra.veterinario.app;

import java.nio.file.Path;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.repositorio.ArquivoAtendimentoRepositorio;
import main.java.br.ulbra.veterinario.servico.AtendimentoService;

public class Main {
    public static void main(String[] args) {
        var repositorio = new ArquivoAtendimentoRepositorio(Path.of("dados", "atendimentos.csv"));
        var clinica = new ClinicaVeterinaria(1);
        var service = new AtendimentoService(clinica, repositorio);
        var console = new ConsoleClinica(service);

        console.executarDemonstracao();
    }
}
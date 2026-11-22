package test.java.br.ulbra.veterinario.repositorio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.time.LocalDateTime;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import main.java.br.ulbra.veterinario.repositorio.ArquivoAtendimentoRepositorio;
import main.java.br.ulbra.veterinario.repositorio.AtendimentoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class ArquivoAtendimentoRepositorioTest {

    private static final LocalDateTime ENTRADA = LocalDateTime.of(2026, 11, 9, 19, 0);

    @TempDir
    Path pasta;

    @Test
    public void arquivoInexistenteDeveListarVazio() {
        AtendimentoRepositorio repositorio = new ArquivoAtendimentoRepositorio(pasta.resolve("atendimentos.csv"));

        assertTrue(repositorio.listarTodos().isEmpty());
    }

    @Test
    public void dadosDevemSobreviverAUmaNovaInstancia() {
        Path arquivo = pasta.resolve("atendimentos.csv");
        AtendimentoRepositorio primeiro = new ArquivoAtendimentoRepositorio(arquivo);
        primeiro.salvar(new RegistroAtendimento("Rex", "CACHORRO", ENTRADA, ENTRADA.plusMinutes(30), 100.0));
        primeiro.salvar(new RegistroAtendimento("Mingau", "GATO", ENTRADA, ENTRADA.plusMinutes(20), 80.0));

        AtendimentoRepositorio segundo = new ArquivoAtendimentoRepositorio(arquivo);

        assertEquals(2, segundo.listarTodos().size());
        assertEquals("Mingau", segundo.listarTodos().get(1).nomeAnimal());
        assertEquals(80.0, segundo.listarTodos().get(1).valorCobrado(), 0.001);
    }
}
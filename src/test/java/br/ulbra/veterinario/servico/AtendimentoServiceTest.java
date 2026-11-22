package test.java.br.ulbra.veterinario.servico;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import main.java.br.ulbra.veterinario.repositorio.ArquivoAtendimentoRepositorio;
import main.java.br.ulbra.veterinario.repositorio.MemoriaAtendimentoRepositorio;
import main.java.br.ulbra.veterinario.repositorio.RepositorioException;
import main.java.br.ulbra.veterinario.servico.AtendimentoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class AtendimentoServiceTest {

    @Test
    public void servicoDeveOrquestrarAtendimentoESalvarNoRepositorioEmMemoria() {
        var repositorio = new MemoriaAtendimentoRepositorio();
        var clinica = new ClinicaVeterinaria(2);
        var service = new AtendimentoService(clinica, repositorio);
        LocalDateTime entrada = LocalDateTime.of(2026, 11, 21, 15, 0);

        FichaAtendimento ficha = service.iniciarAtendimento(new Cachorro("Thor"), entrada);
        RegistroAtendimento registro = service.encerrarAtendimento(ficha, entrada.plusMinutes(40));

        assertEquals(100.00, registro.valorCobrado(), 0.001);
        assertEquals(2, service.consultoriosLivres());
        assertEquals(1, service.listarHistorico().size());
    }

    @Test
    void repositorioDeveLancarRepositorioExceptionParaLinhaCorrompida(@TempDir Path tempDir) throws Exception {
        Path arquivoCorrompido = tempDir.resolve("corrompido.csv");
        Files.writeString(arquivoCorrompido, "LinhaInvalidaSemPontoEVirgula\n");

        var repo = new ArquivoAtendimentoRepositorio(arquivoCorrompido);

        RepositorioException ex = assertThrows(RepositorioException.class, repo::listarTodos);
        assertTrue(ex.getMessage().contains("Arquivo corrompido"));
    }
}
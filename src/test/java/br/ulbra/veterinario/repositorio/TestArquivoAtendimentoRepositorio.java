package test.java.br.ulbra.veterinario.repositorio;

import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import main.java.br.ulbra.veterinario.repositorio.ArquivoAtendimentoRepositorio;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class TestArquivoAtendimentoRepositorio {

    @Test
    void dadosDevemSobreviverAUmaNovaInstanciaDoRepositorio(@TempDir Path tempDir) {
        // Arrange
        Path arquivo = tempDir.resolve("atendimentos_teste.csv");
        var repo1 = new ArquivoAtendimentoRepositorio(arquivo);
        LocalDateTime entrada = LocalDateTime.of(2026, 11, 21, 9, 0);
        RegistroAtendimento registro = new RegistroAtendimento("Thor", "CACHORRO", entrada, entrada.plusMinutes(30), 100.00);

        // Act
        repo1.salvar(registro);
        var repo2 = new ArquivoAtendimentoRepositorio(arquivo); // Nova instância lendo o mesmo arquivo
        List<RegistroAtendimento> carregados = repo2.listarTodos();

        // Assert
        assertEquals(1, carregados.size());
        assertEquals("Thor", carregados.get(0).nomeAnimal());
        assertEquals(100.00, carregados.get(0).valorCobrado(), 0.001);
    }
}
package test.java.br.ulbra.veterinario.dominio;

import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.Gato;
import main.java.br.ulbra.veterinario.repositorio.MemoriaAtendimentoRepositorio;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TestClinicaVeterinaria {

    @Test
    void deveSalvarRegistroEmMemoriaAoFinalizarAtendimento() {
        // Arrange
        var repo = new MemoriaAtendimentoRepositorio();
        var clinica = new ClinicaVeterinaria(2, repo);
        LocalDateTime entrada = LocalDateTime.of(2026, 11, 21, 10, 0);
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Cachorro("Rex"), entrada);

        // Act
        clinica.finalizarAtendimento(ficha, entrada.plusMinutes(40));

        // Assert
        assertEquals(1, repo.listarTodos().size());
        assertEquals("Rex", repo.listarTodos().get(0).nomeAnimal());
        assertEquals(100.00, repo.listarTodos().get(0).valorCobrado(), 0.001);
    }

    @Test
    void deveRecusarSaidaAnteriorAEntrada() {
        // Arrange
        var repo = new MemoriaAtendimentoRepositorio();
        var clinica = new ClinicaVeterinaria(2, repo);
        LocalDateTime entrada = LocalDateTime.of(2026, 11, 21, 14, 0);
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Gato("Mingau"), entrada);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> {
            clinica.finalizarAtendimento(ficha, entrada.minusMinutes(10));
        });
    }

    @Test
    void deveRecusarCriacaoDeClinicaSemRepositorio() {
        // Arrange, Act & Assert
        assertThrows(NullPointerException.class, () -> {
            new ClinicaVeterinaria(2, null);
        });
    }
}
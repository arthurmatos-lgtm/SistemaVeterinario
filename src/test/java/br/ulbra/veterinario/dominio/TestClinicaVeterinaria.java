package test.java.br.ulbra.veterinario.dominio;

import main.java.br.ulbra.veterinario.cobranca.CobrancaCachorro;
import main.java.br.ulbra.veterinario.cobranca.CobrancaGato;
import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.Gato;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

public class TestClinicaVeterinaria {

    @Test
    void cobrarCachorroDeveDarCemReais() {
        // Arrange
        var politica = new CobrancaCachorro();
        // Act
        double valor = politica.calcularValor();
        // Assert
        assertEquals(100.00, valor, 0.001);
    }

    @Test
    void cobrarGatoDeveDarOitentaReais() {
        // Arrange
        var politica = new CobrancaGato();
        // Act
        double valor = politica.calcularValor();
        // Assert
        assertEquals(80.00, valor, 0.001);
    }

    @Test
    void recusarAtendimentoQuandoNaoHouverConsultorioLivre() {
        // Arrange
        var clinica = new ClinicaVeterinaria(1);
        clinica.iniciarAtendimento(new Cachorro("Rex"), LocalDateTime.now());

        // Act & Assert
        assertThrows(IllegalStateException.class, () -> {
            clinica.iniciarAtendimento(new Gato("Mingau"), LocalDateTime.now());
        });
    }
}
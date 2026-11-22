package test.java.br.ulbra.veterinario.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.ConsultorioIndisponivelException;
import main.java.br.ulbra.veterinario.dominio.DadosAnimalInvalidosException;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.Gato;
import main.java.br.ulbra.veterinario.dominio.HorarioAtendimentoInvalidoException;
import org.junit.jupiter.api.Test;

class RobustezExcecoesTest {

    private static final LocalDateTime ENTRADA = LocalDateTime.of(2026, 11, 21, 10, 0);

    @Test
    void deveLancarExcecaoAoCriarAnimalComNomeVazio() {
        DadosAnimalInvalidosException ex = assertThrows(
                DadosAnimalInvalidosException.class,
                () -> new Cachorro("   ")
        );
        assertTrue(ex.getMessage().contains("Nome do animal inválido"));
    }

    @Test
    void deveLancarExcecaoQuandoNaoHouverConsultorioLivre() {
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(1);
        clinica.iniciarAtendimento(new Cachorro("Rex"), ENTRADA);

        ConsultorioIndisponivelException ex = assertThrows(
                ConsultorioIndisponivelException.class,
                () -> clinica.iniciarAtendimento(new Gato("Mingau"), ENTRADA.plusMinutes(5))
        );
        assertTrue(ex.getMessage().contains("consultórios estão ocupados"));
    }

    @Test
    void deveManterEstadoConsistenteQuandoFinalizacaoFalharPorHorarioInvalido() {
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(1);
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Cachorro("Rex"), ENTRADA);
        assertEquals(0, clinica.consultoriosLivres());

        HorarioAtendimentoInvalidoException ex = assertThrows(
                HorarioAtendimentoInvalidoException.class,
                () -> clinica.finalizarAtendimento(ficha, ENTRADA.minusMinutes(15))
        );

        assertTrue(ex.getMessage().contains("não pode ser anterior à entrada"));
        // Prova que o consultório NÃO foi liberado pela metade quando a operação falhou
        assertEquals(0, clinica.consultoriosLivres());
    }
}
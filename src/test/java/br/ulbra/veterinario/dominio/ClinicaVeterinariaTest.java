package test.java.br.ulbra.veterinario.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.Gato;
import main.java.br.ulbra.veterinario.dominio.HorarioAtendimentoInvalidoException;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClinicaVeterinariaTest {

    private static final LocalDateTime ENTRADA = LocalDateTime.of(2026, 10, 19, 19, 0);

    private ClinicaVeterinaria clinica;

    @BeforeEach
    void preparar() {
        clinica = new ClinicaVeterinaria(2);
    }

    @Test
    public void saidaDeveLiberarConsultorioECobrarPeloTipoDoAnimal() {
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Gato("Mingau"), ENTRADA);

        RegistroAtendimento registro = clinica.finalizarAtendimento(ficha, ENTRADA.plusMinutes(20));

        assertEquals(80.00, registro.valorCobrado(), 0.001);
        assertEquals(2, clinica.consultoriosLivres());
    }

    @Test
    public void saidaDeveGerarRegistroComDadosDoAtendimento() {
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Cachorro("Rex"), ENTRADA);

        RegistroAtendimento registro = clinica.finalizarAtendimento(ficha, ENTRADA.plusMinutes(30));

        assertEquals("Rex", registro.nomeAnimal());
        assertEquals("CACHORRO", registro.tipoAnimal());
        assertEquals(100.0, registro.valorCobrado(), 0.001);
    }

    @Test
    public void naoDevePermitirSaidaAnteriorAEntrada() {
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Cachorro("Thor"), ENTRADA);

        assertThrows(HorarioAtendimentoInvalidoException.class,
                () -> clinica.finalizarAtendimento(ficha, ENTRADA.minusMinutes(5)));
    }

    @Test
    public void listaDeConsultoriosNaoPodeSerAlteradaDeFora() {
        assertThrows(UnsupportedOperationException.class,
                () -> clinica.getConsultorios().clear());
        assertTrue(clinica.getConsultorios().size() > 0);
    }
}
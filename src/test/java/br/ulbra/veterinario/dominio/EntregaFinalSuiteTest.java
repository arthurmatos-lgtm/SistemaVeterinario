package test.java.br.ulbra.veterinario.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.stream.Stream;
import main.java.br.ulbra.veterinario.dominio.Animal;
import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.ConsultorioIndisponivelException;
import main.java.br.ulbra.veterinario.dominio.DadosAnimalInvalidosException;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.Gato;
import main.java.br.ulbra.veterinario.dominio.HorarioAtendimentoInvalidoException;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class EntregaFinalSuiteTest {

    private static final LocalDateTime ENTRADA = LocalDateTime.of(2026, 11, 22, 10, 0);

    @Test
    public void rn2_deveCobrarCemReaisParaConsultaDeCachorro() {
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(1);
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Cachorro("Rex"), ENTRADA);

        RegistroAtendimento registro = clinica.finalizarAtendimento(ficha, ENTRADA.plusMinutes(30));

        assertEquals(100.00, registro.valorCobrado(), 0.001);
    }

    @Test
    public void rn3_deveCobrarOitentaReaisParaConsultaDeGato() {
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(1);
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Gato("Mingau"), ENTRADA);

        RegistroAtendimento registro = clinica.finalizarAtendimento(ficha, ENTRADA.plusMinutes(20));

        assertEquals(80.00, registro.valorCobrado(), 0.001);
    }

    @Test
    public void rn4_casoLimite_deveOcuparUltimoConsultorioERecusarProximoAtendimento() {
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(1);
        clinica.iniciarAtendimento(new Cachorro("Rex"), ENTRADA);

        assertEquals(0, clinica.consultoriosLivres());

        ConsultorioIndisponivelException ex = assertThrows(
                ConsultorioIndisponivelException.class,
                () -> clinica.iniciarAtendimento(new Gato("Luna"), ENTRADA.plusMinutes(5))
        );
        assertTrue(ex.getMessage().contains("consultórios estão ocupados"));
    }

    @Test
    public void rn5_casoLimite_deveAceitarSaidaNoMesmoMinutoDaEntradaELiberarConsultorio() {
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(1);
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Gato("Mia"), ENTRADA);

        RegistroAtendimento registro = clinica.finalizarAtendimento(ficha, ENTRADA);

        assertEquals(80.00, registro.valorCobrado(), 0.001);
        assertEquals(1, clinica.consultoriosLivres());
    }

    @Test
    public void rn6_deveLancarExcecaoComMensagemClaraParaNomeEmBranco() {
        DadosAnimalInvalidosException ex = assertThrows(
                DadosAnimalInvalidosException.class,
                () -> new Cachorro("   ")
        );
        assertTrue(ex.getMessage().contains("Nome do animal inválido"));
    }

    @Test
    public void rn5_deveLancarExcecaoComMensagemClaraParaSaidaAnteriorAEntrada() {
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(1);
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Cachorro("Thor"), ENTRADA);

        HorarioAtendimentoInvalidoException ex = assertThrows(
                HorarioAtendimentoInvalidoException.class,
                () -> clinica.finalizarAtendimento(ficha, ENTRADA.minusMinutes(1))
        );
        assertTrue(ex.getMessage().contains("não pode ser anterior à entrada"));
    }

    @ParameterizedTest(name = "RN1: {0} deve pagar R$ {1}")
    @MethodSource("fornecerAnimaisEValoresEsperados")
    public void rn1_deveCobrarDeAcordoComAEspecieDoAnimal(Animal animal, double valorEsperado) {
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(1);
        FichaAtendimento ficha = clinica.iniciarAtendimento(animal, ENTRADA);

        RegistroAtendimento registro = clinica.finalizarAtendimento(ficha, ENTRADA.plusMinutes(15));

        assertEquals(valorEsperado, registro.valorCobrado(), 0.001);
    }

    private static Stream<Arguments> fornecerAnimaisEValoresEsperados() {
        return Stream.of(
                Arguments.of(new Cachorro("Rex"), 100.00),
                Arguments.of(new Gato("Mingau"), 80.00)
        );
    }
}
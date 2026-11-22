package test.java.br.ulbra.veterinario.dominio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import main.java.br.ulbra.veterinario.dominio.AtendimentoRepositorio;
import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.Gato;
import main.java.br.ulbra.veterinario.repositorio.MemoriaAtendimentoRepositorio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ClinicaVeterinariaTest {

    private static final LocalDateTime ENTRADA = LocalDateTime.of(2026, 10, 19, 19, 0);

    private ClinicaVeterinaria clinica;
    private AtendimentoRepositorio repositorio;

    @BeforeEach
    void preparar() {
        repositorio = new MemoriaAtendimentoRepositorio();
        clinica = new ClinicaVeterinaria(2, repositorio);
    }

    @Test
    void saidaDeveLiberarConsultorioECobrarPeloTipoDoAnimal() {
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Gato("Mingau"), ENTRADA);

        double valor = clinica.finalizarAtendimento(ficha, ENTRADA.plusMinutes(20));

        assertEquals(80.00, valor, 0.001);
        assertEquals(2, clinica.consultoriosLivres());
    }

    @Test
    void saidaDeveSalvarORegistroNoRepositorio() {
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Cachorro("Rex"), ENTRADA);

        clinica.finalizarAtendimento(ficha, ENTRADA.plusMinutes(30));

        assertEquals(1, repositorio.listarTodos().size());
        assertEquals("Rex", repositorio.listarTodos().get(0).nomeAnimal());
        assertEquals(100.0, repositorio.listarTodos().get(0).valorCobrado(), 0.001);
    }

    @Test
    void naoDevePermitirSaidaAnteriorAEntrada() {
        FichaAtendimento ficha = clinica.iniciarAtendimento(new Cachorro("Thor"), ENTRADA);

        assertThrows(IllegalArgumentException.class,
                () -> clinica.finalizarAtendimento(ficha, ENTRADA.minusMinutes(5)));
    }

    @Test
    void listaDeConsultoriosNaoPodeSerAlteradaDeFora() {
        assertThrows(UnsupportedOperationException.class,
                () -> clinica.getConsultorios().clear());
        assertTrue(clinica.getConsultorios().size() > 0);
    }
}
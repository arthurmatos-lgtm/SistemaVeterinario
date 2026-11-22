package test.java.br.ulbra.veterinario.repositorio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.List;
import main.java.br.ulbra.veterinario.dominio.AtendimentoRepositorio;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import main.java.br.ulbra.veterinario.repositorio.MemoriaAtendimentoRepositorio;
import org.junit.jupiter.api.Test;

class MemoriaAtendimentoRepositorioTest {

    @Test
    void deveSalvarEListarOsRegistrosNaOrdem() {
        AtendimentoRepositorio repositorio = new MemoriaAtendimentoRepositorio();
        LocalDateTime entrada = LocalDateTime.of(2026, 10, 26, 19, 0);
        RegistroAtendimento cachorro = new RegistroAtendimento("Rex", "CACHORRO", entrada, entrada.plusMinutes(30), 100.0);
        RegistroAtendimento gato = new RegistroAtendimento("Mingau", "GATO", entrada, entrada.plusMinutes(20), 80.0);

        repositorio.salvar(cachorro);
        repositorio.salvar(gato);

        assertEquals(List.of(cachorro, gato), repositorio.listarTodos());
    }

    @Test
    void listaRetornadaNaoDevePermitirAlterarORepositorio() {
        AtendimentoRepositorio repositorio = new MemoriaAtendimentoRepositorio();

        assertThrows(UnsupportedOperationException.class, () -> repositorio.listarTodos().clear());
        assertEquals(0, repositorio.listarTodos().size());
    }
}
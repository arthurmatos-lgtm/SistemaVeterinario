import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TestConsulta {

    private Tutor tutor;
    private Cachorro dog;
    private Consulta consulta;

    @BeforeEach
    void setUp() {
        tutor = new Tutor(1, "Ana Maria", "111.222.333-44", "(51) 99999-0000", "ana@email.com");
        dog = new Cachorro(10, "Thor", "Beagle", 12.5f, tutor, true);
        consulta = new Consulta(100, LocalDateTime.now(), "Check-up de rotina", 12.5f, dog);
    }

    @Test
    void deveCriarReceituarioInternamenteAoRealizarConsulta() {
        consulta.realizar();
        consulta.emitirReceituario(1, "Amoxicilina 250mg", "Dar 1 comprimido a cada 12h por 7 dias");

        assertNotNull(consulta.getReceituario());
        assertEquals("Amoxicilina 250mg", consulta.getReceituario().getMedicamentos());
    }

    @Test
    void deveDelegarFormatacaoDaPrescricaoParaOReceituario() {
        consulta.realizar();
        consulta.emitirReceituario(1, "Dipirona Vet", "5 gotas se houver dor");

        String textoFormatado = consulta.obterTextoReceita();

        assertTrue(textoFormatado.contains("RECEITUARIO #1"));
        assertTrue(textoFormatado.contains("DIPIRONA VET"));
        assertTrue(textoFormatado.contains("5 gotas se houver dor"));
    }

    @Test
    void deveRemoverParteAoCancelarOTodo() {
        consulta.realizar();
        consulta.emitirReceituario(1, "Anti-inflamatório", "1 comprimido ao dia");
        assertNotNull(consulta.getReceituario());

        consulta.cancelarConsulta();

        assertNull(consulta.getReceituario());
        assertEquals(StatusConsulta.CANCELADA, consulta.getStatus());
        assertEquals("Sem receituário associado.", consulta.obterTextoReceita());
    }

    @Test
    void naoDevePermitirEmitirReceituarioEmConsultaAgendada() {
        assertThrows(IllegalStateException.class, () -> 
            consulta.emitirReceituario(1, "Vitamina C", "1 mL via oral")
        );
    }
}

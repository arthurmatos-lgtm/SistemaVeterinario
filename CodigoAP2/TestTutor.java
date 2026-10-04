import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TestTutor {

    private Tutor tutor;
    private Cachorro dog;

    @BeforeEach
    void setUp() {
        tutor = new Tutor(1, "Carlos Silva", "111.222.333-44", "(51) 99999-0000", "carlos@email.com");
        dog = new Cachorro(10, "Rex", "Labrador", 15.0f, tutor, true);
    }

    @Test
    void deveAdicionarAnimalComSucesso() {
        assertEquals(1, tutor.getAnimais().size());
        assertTrue(tutor.getAnimais().contains(dog));
    }

    @Test
    void naoDevePermitirAnimalNulo() {
        assertThrows(IllegalArgumentException.class, () -> tutor.adicionarAnimal(null));
    }

    @Test
    void naoDevePermitirAnimalDuplicado() {
        assertThrows(IllegalStateException.class, () -> tutor.adicionarAnimal(dog));
    }

    @Test
    void deveBuscarAnimalPorIdComSucesso() {
        Optional<Animal> resultado = tutor.buscarAnimalPorId(10);
        assertTrue(resultado.isPresent());
        assertEquals("Rex", resultado.get().getNome());
    }

    @Test
    void deveRemoverAnimalComSucesso() {
        boolean removeu = tutor.removerAnimal(dog);
        assertTrue(removeu);
        assertEquals(0, tutor.getAnimais().size());
    }

    @Test
    void naoDevePermitirModificarListaPeloGetter() {
        List<Animal> listaProtegida = tutor.getAnimais();
        assertThrows(UnsupportedOperationException.class, () -> listaProtegida.add(dog));
    }
}

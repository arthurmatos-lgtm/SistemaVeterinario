import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TutorTest {

    private Tutor tutor;
    private Pet pet;

    @BeforeEach
    void setUp() {
        tutor = new Tutor(1, "Carlos Silva", "111.222.333-44", "(51) 99999-0000", "carlos@email.com");
        pet = new Pet(10, "Rex", EspeciePet.CAO, "Labrador", 15.0f, "Nenhuma", tutor);
    }

    @Test
    void deveAdicionarPetComSucesso() {
        assertEquals(1, tutor.getPets().size());
        assertTrue(tutor.getPets().contains(pet));
    }

    @Test
    void naoDevePermitirPetNulo() {
        assertThrows(IllegalArgumentException.class, () -> tutor.adicionarPet(null));
    }

    @Test
    void naoDevePermitirPetDuplicado() {
        assertThrows(IllegalStateException.class, () -> tutor.adicionarPet(pet));
    }

    @Test
    void deveBuscarPetPorIdComSucesso() {
        Optional<Pet> resultado = tutor.buscarPetPorId(10);
        assertTrue(resultado.isPresent());
        assertEquals("Rex", resultado.get().getNome());
    }

    @Test
    void deveRemoverPetComSucesso() {
        boolean removeu = tutor.removerPet(pet);
        assertTrue(removeu);
        assertEquals(0, tutor.getPets().size());
    }

    @Test
    void naoDevePermitirModificarListaPeloGetter() {
        List<Pet> listaProtegida = tutor.getPets();
        assertThrows(UnsupportedOperationException.class, () -> listaProtegida.add(pet));
    }
}

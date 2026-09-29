package br.com.sistemaVeterinario.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TestAnimal {

    private Tutor tutor;

    @BeforeEach
    void setUp() {
        tutor = new Tutor(1, "Ana Maria", "111.222.333-44", "(51) 99999-0000", "ana@email.com");
    }

    @Test
    void deveReutilizarValidacoesEAtributosDaSuperclasse() {
        Cachorro dog = new Cachorro(10, "Thor", "Beagle", 15.0f, tutor, true);

        assertEquals("Thor", dog.getNome());
        assertEquals(15.0f, dog.getPeso());
        assertEquals("Ana Maria", dog.getTutor().getNome());
        assertTrue(dog.isAdestrado());
    }

    @Test
    void deveCalcularMetabolismoEComportamentoEspecializado() {
        Animal dog = new Cachorro(1, "Thor", "Beagle", 10.0f, tutor, true);
        Animal cat = new Gato(2, "Felix", "Persa", 4.0f, tutor, true); // Castrado

        // Cachorro (10kg): 10 * 30 + 70 = 370 kcal
        assertEquals(370.0f, dog.calcularNecessidadeCaloricaDiaria());

        // Gato (4kg castrado): (4 * 30 + 70) * 1.2 = 190 * 1.2 = 228 kcal
        assertEquals(228.0f, cat.calcularNecessidadeCaloricaDiaria(), 0.01f);

        assertTrue(dog.gerarRecomendacaoCuidados().contains("passeios diários"));
        assertTrue(cat.gerarRecomendacaoCuidados().contains("pontos de água corrente"));
    }

    @Test
    void deveValidarDadosInvalidosNaSuperclasse() {
        assertThrows(IllegalArgumentException.class, () ->
            new Cachorro(1, "", "Poodle", 5.0f, tutor, false)
        );
    }
}

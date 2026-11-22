package main.java.br.ulbra.veterinario.dominio;

import java.time.LocalDateTime;

public record RegistroAtendimento(
        String nomeAnimal,
        String tipoAnimal,
        LocalDateTime entrada,
        LocalDateTime saida,
        double valorCobrado
) {
}
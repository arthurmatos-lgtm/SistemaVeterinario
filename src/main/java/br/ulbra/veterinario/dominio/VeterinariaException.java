package main.java.br.ulbra.veterinario.dominio;

public class VeterinariaException extends RuntimeException {
    public VeterinariaException(String mensagem) {
        super(mensagem);
    }
}
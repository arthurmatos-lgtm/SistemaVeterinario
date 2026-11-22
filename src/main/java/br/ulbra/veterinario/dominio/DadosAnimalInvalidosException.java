package main.java.br.ulbra.veterinario.dominio;

public class DadosAnimalInvalidosException extends VeterinariaException {
    public DadosAnimalInvalidosException(String mensagem) {
        super(mensagem);
    }
}
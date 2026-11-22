package main.java.br.ulbra.veterinario.repositorio;

public class RepositorioException extends RuntimeException {
    public RepositorioException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }

    public RepositorioException(String mensagem) {
        super(mensagem);
    }
}
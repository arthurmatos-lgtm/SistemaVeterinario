package main.java.br.ulbra.veterinario.dominio;

public class ConsultorioIndisponivelException extends VeterinariaException {
    public ConsultorioIndisponivelException(String mensagem) {
        super(mensagem);
    }
}
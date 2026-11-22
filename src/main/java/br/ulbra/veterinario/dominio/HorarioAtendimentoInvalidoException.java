package main.java.br.ulbra.veterinario.dominio;

public class HorarioAtendimentoInvalidoException extends VeterinariaException {
    public HorarioAtendimentoInvalidoException(String mensagem) {
        super(mensagem);
    }
}
package main.java.br.ulbra.veterinario.cobranca;

public class CobrancaGato implements PoliticaDeCobranca {
    private static final double VALOR_CONSULTA = 80.00;

    @Override
    public double calcularValor() {
        return VALOR_CONSULTA;
    }
}
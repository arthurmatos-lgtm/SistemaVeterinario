package main.java.br.ulbra.veterinario.cobranca;

public class CobrancaCachorro implements PoliticaDeCobranca {
    private static final double VALOR_CONSULTA = 100.00;

    @Override
    public double calcularValor() {
        return VALOR_CONSULTA;
    }
}

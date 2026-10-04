public class CobrancaParticular implements EstrategiaCobranca {

    @Override
    public double calcularValorFinal(double valorBaseConsulta) {
        if (valorBaseConsulta <= 0) {
            throw new IllegalArgumentException("Valor base da consulta deve ser maior que zero.");
        }
        return valorBaseConsulta;
    }

    @Override
    public String obterDescricaoCobranca() {
        return "Atendimento Particular - Valor integral sem descontos aplicados.";
    }
}
public class CobrancaPlanoFidelidade implements EstrategiaCobranca {
    private final double percentualDesconto;

    public CobrancaPlanoFidelidade(double percentualDesconto) {
        if (percentualDesconto <= 0 || percentualDesconto > 100) {
            throw new IllegalArgumentException("Percentual de desconto deve estar entre 1% e 100%.");
        }
        this.percentualDesconto = percentualDesconto;
    }

    @Override
    public double calcularValorFinal(double valorBaseConsulta) {
        if (valorBaseConsulta <= 0) {
            throw new IllegalArgumentException("Valor base da consulta deve ser maior que zero.");
        }
        return valorBaseConsulta * (1.0 - (this.percentualDesconto / 100.0));
    }

    @Override
    public String obterDescricaoCobranca() {
        return "Plano Fidelidade - Desconto aplicado de " + this.percentualDesconto + "%.";
    }
}
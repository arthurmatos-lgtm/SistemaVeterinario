/**
 * Contrato para o cálculo polimórfico de cobrança de consultas no Sistema Veterinário.
 */
public interface EstrategiaCobranca {

    /**
     * Calcula o valor final da consulta aplicável ao cliente.
     *
     * @param valorBaseConsulta o valor nominal original da consulta médica veterinária.
     * @return o valor financeiro final a ser pago.
     */
    double calcularValorFinal(double valorBaseConsulta);

    /**
     * Retorna a descrição detalhada da regra de cobrança aplicada.
     *
     * @return String contendo o detalhamento da modalidade de cobrança.
     */
    String obterDescricaoCobranca();
}
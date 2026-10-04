
import java.time.LocalDateTime;

public class Consulta {
    private int id;
    private LocalDateTime dataHora;
    private String sintomas;
    private float pesoAferido;
    private double valorBase;
    private StatusConsulta status;
    private Animal animal;

    private Receituario receituario;
    private EstrategiaCobranca estrategiaCobranca;

    public Consulta(int id, LocalDateTime dataHora, String sintomas, float pesoAferido, double valorBase, Animal animal, EstrategiaCobranca estrategiaCobranca) {
        if (id <= 0) throw new IllegalArgumentException("ID da Consulta deve ser maior que zero.");
        if (dataHora == null) throw new IllegalArgumentException("Data/Hora é obrigatória.");
        if (animal == null) throw new IllegalArgumentException("Animal é obrigatório.");
        if (valorBase <= 0) throw new IllegalArgumentException("Valor base da consulta deve ser maior que zero.");
        if (estrategiaCobranca == null) throw new IllegalArgumentException("Estratégia de cobrança é obrigatória.");

        this.id = id;
        this.dataHora = dataHora;
        this.sintomas = sintomas;
        this.pesoAferido = pesoAferido;
        this.valorBase = valorBase;
        this.animal = animal;
        this.estrategiaCobranca = estrategiaCobranca;
        this.status = StatusConsulta.AGENDADA;
        this.receituario = null;
        
        animal.registrarConsulta(this);
    }

    public double calcularValorTotal() {
        return this.estrategiaCobranca.calcularValorFinal(this.valorBase);
    }

    public String obterDetalhesCobranca() {
        return String.format(
            "Valor Base: R$ %.2f | Valor Final: R$ %.2f | Regra: %s",
            this.valorBase,
            calcularValorTotal(),
            this.estrategiaCobranca.obterDescricaoCobranca()
        );
    }

    public void setEstrategiaCobranca(EstrategiaCobranca novaEstrategia) {
        if (novaEstrategia == null) throw new IllegalArgumentException("Estratégia não pode ser nula.");
        this.estrategiaCobranca = novaEstrategia;
    }

    public void emitirReceituario(int idReceituario, String medicamentos, String modoDeUso) {
        if (this.status != StatusConsulta.REALIZADA) {
            throw new IllegalStateException("Só é possível emitir receituário para consultas REALIZADAS.");
        }
        this.receituario = new Receituario(idReceituario, medicamentos, modoDeUso, this);
    }

    public void cancelarConsulta() {
        this.status = StatusConsulta.CANCELADA;
        this.receituario = null;
    }

    public void removerReceituario() {
        if (this.receituario == null) {
            throw new IllegalStateException("Não há receituário ativo nesta consulta.");
        }
        this.receituario = null;
    }

    public String obterTextoReceita() {
        if (this.receituario == null) {
            return "Sem receituário associado.";
        }
        return this.receituario.gerarPrescricaoFormatada();
    }

    public void realizar() {
        this.status = StatusConsulta.REALIZADA;
        if (this.animal != null) {
            this.animal.atualizarPeso(this.pesoAferido);
        }
    }

    public int getId() { return id; }
    public LocalDateTime getDataHora() { return dataHora; }
    public String getSintomas() { return sintomas; }
    public float getPesoAferido() { return pesoAferido; }
    public double getValorBase() { return valorBase; }
    public StatusConsulta getStatus() { return status; }
    public Animal getAnimal() { return animal; }
    public Receituario getReceituario() { return receituario; }
    public EstrategiaCobranca getEstrategiaCobranca() { return estrategiaCobranca; }
}
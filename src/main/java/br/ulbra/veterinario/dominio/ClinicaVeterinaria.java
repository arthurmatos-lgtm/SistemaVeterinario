package main.java.br.ulbra.veterinario.dominio;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClinicaVeterinaria {
    private final List<Consultorio> consultorios = new ArrayList<>();

    public ClinicaVeterinaria(int quantidadeConsultorios) {
        if (quantidadeConsultorios <= 0) {
            throw new VeterinariaException(
                    "Quantidade de consultórios inválida: deve ser maior que zero. Informe ao menos 1 consultório.");
        }
        for (int i = 1; i <= quantidadeConsultorios; i++) {
            consultorios.add(new Consultorio(i));
        }
    }

    public List<Consultorio> getConsultorios() {
        return Collections.unmodifiableList(consultorios);
    }

    public int consultoriosLivres() {
        int livres = 0;
        for (Consultorio c : consultorios) {
            if (c.estaLivre()) {
                livres++;
            }
        }
        return livres;
    }

    public FichaAtendimento iniciarAtendimento(Animal animal, LocalDateTime entrada) {
        if (animal == null) {
            throw new DadosAnimalInvalidosException(
                    "Animal não informado para atendimento. Selecione um animal cadastrado.");
        }
        if (entrada == null) {
            throw new HorarioAtendimentoInvalidoException(
                    "Horário de entrada não informado. Informe a data e hora do início do atendimento.");
        }
        Consultorio livre = buscarConsultorioLivre();
        if (livre == null) {
            throw new ConsultorioIndisponivelException(
                    "Todos os consultórios estão ocupados no momento. Finalize um atendimento antes de iniciar outro.");
        }
        livre.ocupar(animal);
        return new FichaAtendimento(animal, animal.criarPoliticaDeCobranca(), entrada);
    }

    public RegistroAtendimento finalizarAtendimento(FichaAtendimento ficha, LocalDateTime saida) {
        if (ficha == null) {
            throw new VeterinariaException(
                    "Ficha de atendimento inválida. Informe uma ficha aberta para encerramento.");
        }
        if (saida == null || saida.isBefore(ficha.getEntrada())) {
            throw new HorarioAtendimentoInvalidoException(
                    "Horário de saída inválido: não pode ser anterior à entrada. Informe um horário igual ou posterior à entrada.");
        }

        double valor = ficha.finalizar();

        for (Consultorio c : consultorios) {
            if (!c.estaLivre() && c.getAnimal().equals(ficha.getAnimal())) {
                c.liberar();
                break;
            }
        }

        return new RegistroAtendimento(
                ficha.getAnimal().getNome(),
                ficha.getAnimal().getTipo(),
                ficha.getEntrada(),
                saida,
                valor
        );
    }

    private Consultorio buscarConsultorioLivre() {
        for (Consultorio c : consultorios) {
            if (c.estaLivre()) {
                return c;
            }
        }
        return null;
    }
}
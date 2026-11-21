package main.java.br.ulbra.veterinario.dominio;

import main.java.br.ulbra.veterinario.cobranca.CobrancaCachorro;
import main.java.br.ulbra.veterinario.cobranca.CobrancaGato;
import main.java.br.ulbra.veterinario.cobranca.PoliticaDeCobranca;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ClinicaVeterinaria {
    private final List<Consultorio> consultorios = new ArrayList<>();
    private final AtendimentoRepositorio repositorio;

    public ClinicaVeterinaria(int quantidadeConsultorios, AtendimentoRepositorio repositorio) {
        if (quantidadeConsultorios <= 0) {
            throw new IllegalArgumentException("A clínica deve ter ao menos 1 consultório.");
        }
        this.repositorio = Objects.requireNonNull(repositorio, "Repositório não pode ser nulo.");
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

    private PoliticaDeCobranca escolherPolitica(Animal animal) {
        if (animal.getTipo().equals("CACHORRO")) {
            return new CobrancaCachorro();
        }
        return new CobrancaGato();
    }

    public FichaAtendimento iniciarAtendimento(Animal animal, LocalDateTime entrada) {
        Consultorio livre = buscarConsultorioLivre();
        if (livre == null) {
            throw new IllegalStateException("Nenhum consultório livre disponível.");
        }
        livre.ocupar(animal);
        PoliticaDeCobranca politica = escolherPolitica(animal);
        return new FichaAtendimento(animal, politica, entrada);
    }

    public double finalizarAtendimento(FichaAtendimento ficha, LocalDateTime saida) {
        if (saida.isBefore(ficha.getEntrada())) {
            throw new IllegalArgumentException("Data/hora de saída não pode ser anterior à entrada.");
        }
        double valor = ficha.finalizar();

        RegistroAtendimento registro = new RegistroAtendimento(
                ficha.getAnimal().getNome(),
                ficha.getAnimal().getTipo(),
                ficha.getEntrada(),
                saida,
                valor
        );
        repositorio.salvar(registro);

        for (Consultorio c : consultorios) {
            if (!c.estaLivre() && c.getAnimal().equals(ficha.getAnimal())) {
                c.liberar();
                break;
            }
        }
        return valor;
    }

    public List<RegistroAtendimento> listarHistorico() {
        return repositorio.listarTodos();
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
package main.java.br.ulbra.veterinario.servico;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import main.java.br.ulbra.veterinario.dominio.Animal;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import main.java.br.ulbra.veterinario.repositorio.AtendimentoRepositorio;

public class AtendimentoService {
    private final ClinicaVeterinaria clinica;
    private final AtendimentoRepositorio repositorio;

    public AtendimentoService(ClinicaVeterinaria clinica, AtendimentoRepositorio repositorio) {
        this.clinica = Objects.requireNonNull(clinica, "A clínica não pode ser nula.");
        this.repositorio = Objects.requireNonNull(repositorio, "O repositório não pode ser nulo.");
    }

    public FichaAtendimento iniciarAtendimento(Animal animal, LocalDateTime entrada) {
        return clinica.iniciarAtendimento(animal, entrada);
    }

    public RegistroAtendimento encerrarAtendimento(FichaAtendimento ficha, LocalDateTime saida) {
        RegistroAtendimento registro = clinica.finalizarAtendimento(ficha, saida);
        repositorio.salvar(registro);
        return registro;
    }

    public int consultoriosLivres() {
        return clinica.consultoriosLivres();
    }

    public List<RegistroAtendimento> listarHistorico() {
        return repositorio.listarTodos();
    }
}
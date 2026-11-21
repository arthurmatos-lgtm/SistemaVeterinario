package main.java.br.ulbra.veterinario.repositorio;

import main.java.br.ulbra.veterinario.dominio.AtendimentoRepositorio;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MemoriaAtendimentoRepositorio implements AtendimentoRepositorio {
    private final List<RegistroAtendimento> registros = new ArrayList<>();

    @Override
    public void salvar(RegistroAtendimento registro) {
        registros.add(registro);
    }

    @Override
    public List<RegistroAtendimento> listarTodos() {
        return Collections.unmodifiableList(registros);
    }
}
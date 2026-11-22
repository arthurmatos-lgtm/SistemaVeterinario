package main.java.br.ulbra.veterinario.repositorio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;

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
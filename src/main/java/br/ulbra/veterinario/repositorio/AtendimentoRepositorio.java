package main.java.br.ulbra.veterinario.repositorio;

import java.util.List;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;

public interface AtendimentoRepositorio {
    void salvar(RegistroAtendimento registro);
    List<RegistroAtendimento> listarTodos();
}
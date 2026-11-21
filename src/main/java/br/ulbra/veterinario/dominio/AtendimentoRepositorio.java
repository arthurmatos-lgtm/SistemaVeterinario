package main.java.br.ulbra.veterinario.dominio;

import java.util.List;

public interface AtendimentoRepositorio {
    void salvar(RegistroAtendimento registro);
    List<RegistroAtendimento> listarTodos();
}
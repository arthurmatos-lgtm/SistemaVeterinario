package main.java.br.ulbra.veterinario.dominio;

import main.java.br.ulbra.veterinario.cobranca.CobrancaGato;
import main.java.br.ulbra.veterinario.cobranca.PoliticaDeCobranca;

public class Gato extends Animal {
    public Gato(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return "GATO";
    }

    @Override
    public PoliticaDeCobranca criarPoliticaDeCobranca() {
        return new CobrancaGato();
    }
}
package main.java.br.ulbra.veterinario.dominio;

import main.java.br.ulbra.veterinario.cobranca.CobrancaCachorro;
import main.java.br.ulbra.veterinario.cobranca.PoliticaDeCobranca;

public class Cachorro extends Animal {
    public Cachorro(String nome) {
        super(nome);
    }

    @Override
    public String getTipo() {
        return "CACHORRO";
    }

    @Override
    public PoliticaDeCobranca criarPoliticaDeCobranca() {
        return new CobrancaCachorro();
    }
}
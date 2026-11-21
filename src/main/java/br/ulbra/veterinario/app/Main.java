package main.java.br.ulbra.veterinario.app;

import main.java.br.ulbra.veterinario.dominio.Animal;
import main.java.br.ulbra.veterinario.dominio.Cachorro;
import main.java.br.ulbra.veterinario.dominio.ClinicaVeterinaria;
import main.java.br.ulbra.veterinario.dominio.FichaAtendimento;
import main.java.br.ulbra.veterinario.dominio.Gato;
import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ClinicaVeterinaria clinica = new ClinicaVeterinaria(3);
        System.out.println("Consultórios livres iniciais: " + clinica.consultoriosLivres());

        Animal rex = new Cachorro("Rex");
        Animal mingau = new Gato("Mingau");

        FichaAtendimento ficha1 = clinica.iniciarAtendimento(rex, LocalDateTime.now());
        FichaAtendimento ficha2 = clinica.iniciarAtendimento(mingau, LocalDateTime.now());

        double valorRex = clinica.finalizarAtendimento(ficha1);
        double valorMingau = clinica.finalizarAtendimento(ficha2);

        System.out.printf("Cachorro (%s) -> R$ %.2f%n", rex.getNome(), valorRex);
        System.out.printf("Gato (%s) -> R$ %.2f%n", mingau.getNome(), valorMingau);
        System.out.printf("Total cobrado: R$ %.2f%n", (valorRex + valorMingau));
        System.out.println("Consultórios livres ao fim: " + clinica.consultoriosLivres());
    }
}

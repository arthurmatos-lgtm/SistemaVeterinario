package main.java.br.ulbra.veterinario.repositorio;

import main.java.br.ulbra.veterinario.dominio.AtendimentoRepositorio;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ArquivoAtendimentoRepositorio implements AtendimentoRepositorio {
    private final Path caminhoArquivo;

    public ArquivoAtendimentoRepositorio(Path caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    @Override
    public void salvar(RegistroAtendimento registro) {
        try {
            if (caminhoArquivo.getParent() != null) {
                Files.createDirectories(caminhoArquivo.getParent());
            }
            String linha = String.format(
                    Locale.US,
                    "%s;%s;%s;%s;%.2f%n",
                    registro.nomeAnimal(),
                    registro.tipoAnimal(),
                    registro.entrada(),
                    registro.saida(),
                    registro.valorCobrado()
            );
            Files.writeString(
                    caminhoArquivo,
                    linha,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.APPEND
            );
        } catch (IOException e) {
            throw new UncheckedIOException("Erro ao salvar atendimento no arquivo: " + caminhoArquivo, e);
        }
    }

    @Override
    public List<RegistroAtendimento> listarTodos() {
        if (!Files.exists(caminhoArquivo)) {
            return List.of();
        }
        try {
            List<String> linhas = Files.readAllLines(caminhoArquivo, StandardCharsets.UTF_8);
            List<RegistroAtendimento> registros = new ArrayList<>();
            for (String linha : linhas) {
                if (linha.isBlank()) continue;
                String[] partes = linha.split(";");
                registros.add(new RegistroAtendimento(
                        partes[0],
                        partes[1],
                        LocalDateTime.parse(partes[2]),
                        LocalDateTime.parse(partes[3]),
                        Double.parseDouble(partes[4])
                ));
            }
            return registros;
        } catch (IOException e) {
            throw new UncheckedIOException("Erro ao ler atendimentos do arquivo: " + caminhoArquivo, e);
        }
    }
}
package main.java.br.ulbra.veterinario.repositorio;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import main.java.br.ulbra.veterinario.dominio.RegistroAtendimento;

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
            throw new RepositorioException(
                    "Falha técnica ao gravar atendimento no arquivo: " + caminhoArquivo + ". Verifique as permissões de escrita.", e);
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
            for (int i = 0; i < linhas.size(); i++) {
                String linha = linhas.get(i);
                if (linha.isBlank()) continue;
                registros.add(parsearLinha(linha, i + 1));
            }
            return registros;
        } catch (IOException e) {
            throw new RepositorioException(
                    "Falha técnica ao ler arquivo de atendimentos: " + caminhoArquivo + ". Verifique se o arquivo está acessível.", e);
        }
    }

    private RegistroAtendimento parsearLinha(String linha, int numeroLinha) {
        String[] partes = linha.split(";");
        if (partes.length != 5) {
            throw new RepositorioException(
                    "Arquivo corrompido na linha " + numeroLinha + ": esperado 5 colunas, encontrado " + partes.length + ".");
        }
        try {
            return new RegistroAtendimento(
                    partes[0],
                    partes[1],
                    LocalDateTime.parse(partes[2]),
                    LocalDateTime.parse(partes[3]),
                    Double.parseDouble(partes[4])
            );
        } catch (DateTimeParseException | NumberFormatException e) {
            throw new RepositorioException(
                    "Arquivo corrompido na linha " + numeroLinha + ": formato inválido de data ou valor.", e);
        }
    }
}
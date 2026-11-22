# Sistema Veterinário (Projeto de Demonstração)

> Projeto desenvolvido para a disciplina de Fundamentos de Programação Orientada a Objetos — ADS — ULBRA — 2026/2.

## Domínio

Sistema de controle de agendamentos, fichas de pacientes, prescrições e cobrança polimórfica em uma clínica veterinária.

## Problema

Clínicas veterinárias enfrentam dificuldades para organizar o histórico de saúde dos animais, controlar o ciclo de vida das consultas e receitas sem erros operacionais, além de aplicar políticas flexíveis de cobrança e manter validações de domínio defensivas.

## Usuários

- Recepcionista da clínica
- Veterinário / Profissional de saúde
- Tutor / Cliente

## Regras de Negócio

| Regra | Comportamento |
| :--- | :--- |
| **RN1** | O valor cobrado pela consulta depende do tipo do animal atendido. |
| **RN2** | Consulta de Cachorro custa R$ 100,00 fixo. |
| **RN3** | Consulta de Gato custa R$ 80,00 fixo. |
| **RN4** | A entrada ocupa um consultório livre; sem consultório livre, o atendimento é recusado. |
| **RN5** | A saída cobra pela política escolhida na entrada e libera o consultório após a finalização do atendimento. |
| **RN6** | O nome do animal é obrigatório e não pode ser nulo ou composto apenas por espaços em branco. |
| **RN7** | Somente os tipos `CACHORRO` e `GATO` são aceitos no atendimento clínico. |

## Requisitos e Incrementos de POO (Atividade 10)

- **Herança e Classes Abstratas:** Superclasse abstrata `Animal` como base para a especialização de `Cachorro` e `Gato`, com cálculo de necessidade calórica diária polimórfico.
- **Geração Automática de ID:** Identificador único do paciente gerado automaticamente no construtor da superclasse `Animal`.
- **Composição Estrita:** `Consulta` gerencia o ciclo de vida de `Receituario` (a parte deixa de existir caso a consulta seja cancelada).
- **Padrão Strategy (Polimorfismo):** Cálculo do valor final da consulta via interface `EstrategiaCobranca` com implementações em `CobrancaParticular` e `CobrancaPlanoFidelidade`.
- **Encapsulamento de Coleções:** Proteção do estado interno da classe `Tutor` retornando listas imutáveis via `Collections.unmodifiableList()`.
- **Testes Automatizados (JUnit 5):** Cobertura total das regras de negócio executada via Maven.
- **Limpeza de Código Legado:** Remoção completa do enum obsoleto `EspeciePet.java`.

## Persistência de Atendimentos (Atividade 12)

Cada registro salvo no repositório representa a efetivação da **RN5**, garantindo que, após a liberação do consultório, o histórico imutável do atendimento finalizado (animal, espécie, horários e valor cobrado pela política da entrada) permaneça persistido.

### Evidência de Duas Execuções Seguidas
<img width="773" height="476" alt="teste" src="https://github.com/user-attachments/assets/10af9d34-a719-426c-b371-62cc7676b875" />

## Tratamento de erros

As exceções próprias da aplicação herdam de `RuntimeException` (exceções não verificadas) porque representam violações de regras de negócio ou falhas técnicas irrecuperáveis localmente, permitindo que o fluxo de domínio e serviço permaneça limpo até que a camada de apresentação (`app`) as capture e exiba mensagens orientativas ao usuário. O bloco `finally` é utilizado na apresentação (`ConsoleClinica`) para exibir e confirmar o estado consistente dos consultórios após cada tentativa, independentemente de sucesso ou falha.

| Situação | Exceção | Camada que lança | Camada que trata | Mensagem ao usuário |
| :--- | :--- | :--- | :--- | :--- |
| Nome do animal nulo ou em branco (RN6) | `DadosAnimalInvalidosException` | `dominio` (`Animal`) | `app` (`ConsoleClinica`) | `Nome do animal inválido: não pode ser vazio. Informe um nome válido para o cadastro.` |
| Todos os consultórios ocupados (RN4) | `ConsultorioIndisponivelException` | `dominio` (`ClinicaVeterinaria`) | `app` (`ConsoleClinica`) | `Todos os consultórios estão ocupados no momento. Finalize um atendimento antes de iniciar outro.` |
| Horário de saída anterior à entrada (RN5) | `HorarioAtendimentoInvalidoException` | `dominio` (`ClinicaVeterinaria`) | `app` (`ConsoleClinica`) | `Horário de saída inválido: não pode ser anterior à entrada. Informe um horário igual ou posterior à entrada.` |
| Falha de I/O ou arquivo CSV corrompido | `RepositorioException` | `repositorio` (`ArquivoAtendimentoRepositorio`) | `app` (`ConsoleClinica`) | `Falha técnica ao gravar/ler arquivo de atendimentos...` |

### Evidência de Execução (Três erros tratados)
<img width="717" height="478" alt="TestTerminal" src="https://github.com/user-attachments/assets/689632a6-61dc-48ac-8d97-1ed4a33d769d" />


---
## Integrantes
* Arthur Passos de Matos

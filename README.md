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

---
## Integrantes
* Arthur Passos de Matos

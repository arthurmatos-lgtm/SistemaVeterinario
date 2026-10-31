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

## Requisitos e Incrementos de POO (Atividade 10)

- **Herança e Classes Abstratas:** Superclasse abstrata `Animal` como base para a especialização de `Cachorro` e `Gato`, com cálculo de necessidade calórica diária polimórfico.
- **Geração Automática de ID:** Identificador único do paciente gerado automaticamente no construtor da superclasse `Animal`.
- **Composição Estrita:** `Consulta` gerencia o ciclo de vida de `Receituario` (a parte deixa de existir caso a consulta seja cancelada).
- **Padrão Strategy (Polimorfismo):** Cálculo do valor final da consulta via interface `EstrategiaCobranca` com implementações em `CobrancaParticular` e `CobrancaPlanoFidelidade`.
- **Encapsulamento de Coleções:** Proteção do estado interno da classe `Tutor` retornando listas imutáveis via `Collections.unmodifiableList()`.
- **Testes Automatizados (JUnit 5):** Cobertura total das regras de negócio executada via Maven.
- **Limpeza de Código Legado:** Remoção completa do enum obsoleto `EspeciePet.java`.

---

## Estrutura do Projeto

```text
SistemaVeterinario/
├── pom.xml                                    # Configuração Maven e dependências (JUnit 5)
└── src/
    ├── main/
    │   └── java/
    │       └── br/com/sistemaveterinario/
    │           ├── model/                     # Entidades e regras de domínio
    │           │   ├── Animal.java            # Superclasse abstrata (ID autoincrementado)
    │           │   ├── Cachorro.java          # Subclasse concreta
    │           │   ├── Gato.java              # Subclasse concreta
    │           │   ├── Tutor.java             # Encapsulamento e Coleções imutáveis
    │           │   ├── Consulta.java          # Composição e Delegação
    │           │   ├── Receituario.java       # Parte gerida pela Consulta
    │           │   └── StatusConsulta.java    # Enum de estados do atendimento
    │           │
    │           ├── strategy/                  # Padrão Strategy (Polimorfismo)
    │           │   ├── EstrategiaCobranca.java        # Interface polimórfica
    │           │   ├── CobrancaParticular.java        # Implementação concreta
    │           │   └── CobrancaPlanoFidelidade.java   # Implementação concreta
    │           │
    │           └── service/
    │               └── Main.java              # Ponto de entrada (Demonstração completa)
    │
    └── test/
        └── java/
            └── br/com/sistemaveterinario/     # Suíte de testes automatizados (JUnit 5)
                ├── TestAnimal.java            # Valida Herança e ID automático
                ├── TestConsulta.java          # Valida Strategy e Composição
                └── TestTutor.java             # Valida coleção de animais do tutor

```
---
## Integrantes
* Arthur Passos de Matos

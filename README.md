# Sistema Veterinário (Projeto de Demonstração)

> Projeto desenvolvido para a disciplina de Fundamentos de Programação Orientada a Objetos — ADS — ULBRA — 2026/2.

## Domínio

Sistema de controle de agendamentos, fichas de pacientes e acompanhamento de consultas em uma clínica veterinária.

## Problema

Clínicas veterinárias de pequeno e médio porte enfrentam dificuldades para manter o histórico de saúde dos animais organizado, controlar as etapas dos atendimentos sem erros operacionais e validar dados essenciais (como impedimento de peso negativo ou cadastros incompletos) ao longo do processo.

## Usuários

* Recepcionista da clínica
* Veterinário / Profissional de saúde
* Tutor / Cliente

## Funcionalidades iniciais

* Cadastrar tutores e seus dados de contato
* Registrar animais de estimação associados a um tutor
* Agendar e controlar o status de consultas
* Atualizar o peso do animal automaticamente ao realizar um atendimento
* Bloquear a criação ou modificação de registros com dados inválidos
* Consultar o estado atual do atendimento do paciente

## Estrutura do projeto

```text
SistemaVeterinario/
├── model/                               
│   ├── Animal.java                      
│   ├── Cachorro.java                    
│   ├── Gato.java                       
│   ├── Tutor.java                       
│   ├── Consulta.java                    
│   ├── Receituario.java                
│   ├── EspeciePet.java                  
│   └── StatusConsulta.java             
│
├── strategy/                           
│   ├── EstrategiaCobranca.java          
│   ├── CobrancaParticular.java          
│   └── CobrancaPlanoFidelidade.java     
│
├── service/                            
│   └── Main.java                       
│
└── test/                               
    ├── TestAnimal.java                  
    ├── TestConsulta.java               
    └── TestTutor.java                   
```

## Integrantes

Arthur Passos de Matos

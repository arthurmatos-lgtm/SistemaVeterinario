# Melhorias — Encontro 14

A versão `v14` evolui a `v13` em cobertura de testes, organização do código por polimorfismo e extração de constantes, mantendo intactas as espécies atendidas, os valores das consultas, o fluxo de console e o formato CSV.

---

## Testes Acrescentados

Todos os testes das entregas anteriores foram preservados e continuaram passando sem necessidade de alteração para acomodar as refatorações.

| Cobertura nova | Verificação |
| :--- | :--- |
| `rn1_deveCobrarDeAcordoComAEspecieDoAnimal` (`@ParameterizedTest`) | `Cachorro` → `R$ 100,00` e `Gato` → `R$ 80,00`, com margem `0.001` |
| `rn2_deveCobrarCemReaisParaConsultaDeCachorro` | Consulta de cachorro cobra exatamente `R$ 100,00` |
| `rn3_deveCobrarOitentaReaisParaConsultaDeGato` | Consulta de gato cobra exatamente `R$ 80,00` |
| `rn4_casoLimite_deveOcuparUltimoConsultorioERecusarProximoAtendimento` | Ocupa o último consultório (`1` de `1`), recusa nova entrada com `ConsultorioIndisponivelException` e valida a mensagem |
| `rn5_casoLimite_deveAceitarSaidaNoMesmoMinutoDaEntradaELiberarConsultorio` | Saída igual à entrada (`0` minutos) cobra o valor fixo da espécie, não lança exceção e libera o consultório |
| `rn6_deveLancarExcecaoComMensagemClaraParaNomeEmBranco` | Nome apenas com espaços lança `DadosAnimalInvalidosException` com a mensagem orientativa esperada |
| `rn5_deveLancarExcecaoComMensagemClaraParaSaidaAnteriorAEntrada` | Saída anterior à entrada lança `HorarioAtendimentoInvalidoException` com a mensagem esperada |
| `deveManterEstadoConsistenteQuandoFinalizacaoFalharPorHorarioInvalido` | Após falha na finalização, o consultório permanece ocupado (`0` livres) até uma tentativa válida |

Os testes parametrizados e unitários somam **8 novos cenários de verificação** sobre a suíte existente. Todos utilizam horários fixos (`LocalDateTime.of(...)`), não dependem de ordem de execução e isolam operações em disco por meio de `@TempDir`.

---

## Teste que Protege de Verdade (Quebra Proposital)

* **Teste escolhido:** `rn2_deveCobrarCemReaisParaConsultaDeCachorro()` em `EntregaFinalSuiteTest`.
* **O que foi quebrado propositalmente:** Na classe `CobrancaCachorro`, a constante `VALOR_CONSULTA` foi alterada de `100.00` para `50.00`.
* **Mensagem de falha obtida:**
  ```text
  org.opentest4j.AssertionFailedError: expected: <100.0> but was: <50.0>
      at test.java.br.ulbra.veterinario.dominio.EntregaFinalSuiteTest.rn2_deveCobrarCemReaisParaConsultaDeCachorro(EntregaFinalSuiteTest.java:28)

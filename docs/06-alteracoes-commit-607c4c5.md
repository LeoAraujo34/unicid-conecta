# 6. Alterações do commit `607c4c5`

Registro do que mudou no commit `607c4c5` — *feat: adiciona tela de login, gerador de QR code e ajustes do UNICID Conecta* (autor: Sup01) — em relação ao estado anterior do projeto (commit `e717532`, *implementação RecyclerView e filtragem dos chamados*).

## Contexto

- `607c4c5` é um **commit raiz** (sem commit pai): o repositório remoto passou a ter um histórico novo, sem relação com o anterior.
- Os commits anteriores (`e83aaed`, `5699e79`, `1fb1b20`, `136428f`, `e717532`) **não estão** nesse histórico.
- Comparação feita em arquivos: 43 alterados, 1.371 linhas adicionadas e 1.821 removidas.

## O que foi adicionado

| Item | Detalhe |
|---|---|
| Login com usuário e senha | `LoginActivity` e `activity_login.xml` redesenhados; credenciais de teste fixas no código (operação e aluno) |
| QR Code na carteirinha | `CarteirinhaFragment` gera o QR com a biblioteca ZXing (`com.google.zxing:core:3.5.2`) |
| Detalhe do chamado para o aluno | `DetalheChamadoAlunoFragment` e `fragment_detalhe_aluno.xml` |
| `.gitignore` | Arquivos de build e configuração local fora do versionamento |
| Ajustes visuais | Novos layouts de Início do aluno, Dashboard e Fila; novos ícones do menu inferior |

## O que foi removido

| Item removido | Para que servia |
|---|---|
| `ChamadoAdapter`, `ChamadoItem`, `ChamadoUi`, `item_chamado.xml` | Lista de chamados com RecyclerView reaproveitável |
| `FiltroChamado`, `FiltroPrioridade` | Filtros por status, prioridade e separação ativos/resolvidos |
| `MeusChamadosFragment`, `fragment_meus_chamados.xml` | Tela "Meus chamados" do aluno, com filtro por status |
| `NavegacaoAluno`, `NavegacaoOperador` | Centralização da navegação entre telas |
| Métodos de consulta em `ChamadoRepository` | Busca/filtro e listagem de recentes (≈ 124 linhas a menos) |
| `ChamadoRepositoryTest` | Testes unitários do repositório |
| Dependência `recyclerview` | Biblioteca da lista |
| `ic_local.xml`, `ic_voltar.xml` e 32 linhas de `strings.xml` | Ícones e textos usados pelas telas removidas |

## O que foi substituído

| Antes (`e717532`) | Depois (`607c4c5`) |
|---|---|
| Fila da operação com RecyclerView, busca e chips de filtro | Fila com **3 cards fixos** (exibe no máximo 3 chamados) |
| Localização por seletores (campus, bloco, andar, sala) | Localização em **texto livre** |
| Login por RA, sem senha | Login por usuário e senha |

## Impacto nos requisitos

| Requisito | Antes | Depois |
|---|---|---|
| RF-CHA-015 — filtrar chamados por status | ✅ | ❌ |
| RF-OPE-003 — fila de chamados | ✅ | 🟡 (limitada a 3) |
| RF-OPE-006 — busca por protocolo | ✅ | ❌ |
| RF-OPE-007 — filtro por status | ✅ | ❌ |
| RF-OPE-008 — filtro por prioridade | ✅ | ❌ |
| RF-OPE-014 — separar ativos de resolvidos | ✅ | ❌ |
| RF-CHA-003 — localizações cadastradas | ✅ | 🟡 |
| RNF-MAN-002 — componentes reutilizáveis | ✅ | 🟡 |
| RF-ALU-003 — carteirinha digital | ✅ | ✅ (agora com QR Code) |

O quadro completo desta versão está em [05-rastreabilidade.md](05-rastreabilidade.md).

## Pontos a alinhar com a equipe

1. Se a remoção da lista com filtros e da tela "Meus chamados" foi intencional ou consequência da recriação do repositório.
2. Qual histórico deve ser a referência do projeto daqui em diante.
3. Se o trabalho dos commits anteriores deve ser reincorporado a esta versão.

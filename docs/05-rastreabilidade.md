# 5. Rastreabilidade dos requisitos

Situação dos requisitos no protótipo, conforme o commit `607c4c5` (branch `main` do remoto). Por ser um protótipo, itens **Parcial** ou **Não atendido** são tratados como evolução planejada.

Legenda: ✅ Atendido · 🟡 Parcial · ❌ Não atendido · ❔ A verificar

> Este quadro é um retrato do código naquele commit e deve ser atualizado pela equipe conforme o projeto evoluir.

## Autenticação e aluno

| Requisito | Situação | Onde / observação |
|---|---|---|
| RF-AUT-001 | 🟡 | `LoginActivity` — credenciais fixas no código (protótipo) |
| RF-AUT-002 | 🟡 | `LoginActivity` — perfil definido pelo usuário informado |
| RF-AUT-003 | ✅ | `LoginActivity` → `MainActivity` (aluno) / `OperacaoActivity` (operação) |
| RF-AUT-004 | ✅ | `PerfilFragment`, `AjustesOpFragment` (logoff) |
| RF-ALU-001 | ✅ | `HomeAlunoFragment` |
| RF-ALU-002 | ✅ | `HomeAlunoFragment` → chamados |
| RF-ALU-003 | ✅ | `CarteirinhaFragment` (inclui QR Code gerado com ZXing) |

## Chamados

| Requisito | Situação | Onde / observação |
|---|---|---|
| RF-CHA-001 | ✅ | `NovoChamadoFragment`, `ChamadoRepository.criar` |
| RF-CHA-002 | 🟡 | `Categoria` possui 3 valores; a tela usa apenas 2 |
| RF-CHA-003 | 🟡 | Localização informada em texto livre (campo único) |
| RF-CHA-004 | ✅ | `NovoChamadoFragment` |
| RF-CHA-005 | ❌ | Botão de foto apenas simula o anexo |
| RF-CHA-006 | ✅ | Validação de local e descrição |
| RF-CHA-007 | ✅ | `ChamadoRepository.gerarId` |
| RF-CHA-008 | ✅ | Confirmação ao enviar |
| RF-CHA-009 | ✅ | `AcompanhamentoFragment` e `HomeAlunoFragment` (`listarPorSolicitante`) |
| RF-CHA-010 | ✅ | Status exibido nas telas |
| RF-CHA-011 | ✅ | `DetalheChamadoAlunoFragment`, `AcompanhamentoFragment` |
| RF-CHA-012 | ✅ | `Chamado.getHistorico`, `EventoChamado` |
| RF-CHA-013 | ✅ | Notas do histórico |
| RF-CHA-014 | ✅ | `EventoChamado.getDataHora` |
| RF-CHA-015 | ❌ | Sem filtro por status |

## Operacional

| Requisito | Situação | Onde / observação |
|---|---|---|
| RF-OPE-001 | ✅ | `DashboardOpFragment` |
| RF-OPE-002 | ✅ | Contadores no `ChamadoRepository` |
| RF-OPE-003 | 🟡 | `FilaChamadosFragment` — exibe até 3 chamados |
| RF-OPE-004 | ✅ | `DetalheAtendimentoFragment` |
| RF-OPE-005 | ✅ | `DetalheAtendimentoFragment` |
| RF-OPE-006 | ❌ | Sem busca por protocolo |
| RF-OPE-007 | ❌ | Sem filtro por status |
| RF-OPE-008 | ❌ | Sem filtro por prioridade |
| RF-OPE-009 | ✅ | `DetalheAtendimentoFragment` |
| RF-OPE-010 | ✅ | Campo de nota em `DetalheAtendimentoFragment` |
| RF-OPE-011 | 🟡 | Autor registrado, porém fixo (`USUARIO_MOCK_OPERADOR`) |
| RF-OPE-012 | ✅ | `EventoChamado` |
| RF-OPE-013 | ✅ | Status `RESOLVIDO` |
| RF-OPE-014 | ❌ | Ativos e resolvidos não separados |
| RF-OPE-015 | 🟡 | `ChamadoRepository.atribuirResponsavel` existe; sem uso na interface |

## Não funcionais

| Requisito | Situação | Observação |
|---|---|---|
| RNF-SEG-001 / 002 | 🟡 | Interfaces separadas por perfil, sem bloqueio de acesso |
| RNF-SEG-003 | ❌ | Senhas fixas no código (protótipo) |
| RNF-SEG-004 | — | Aplica-se apenas a implantação real (sem servidor no protótipo) |
| RNF-SEG-005 | 🟡 | Validação básica de campos obrigatórios |
| RNF-SEG-006 | 🟡 | Autor registrado, porém fixo |
| RNF-SEG-007 | ❔ | A verificar |
| RNF-USA-001 / 002 / 003 | ✅ | Material Design, navegação inferior, mensagens de confirmação |
| RNF-USA-004 / 005 | 🟡 | Erros de campo exibidos; ajustar mensagens |
| RNF-USA-006 | ✅ | Status exibido em texto |
| RNF-USA-007 / 008 | ❔ | A verificar |
| RNF-DES-001 / 002 / 003 | ✅ | Dados em memória, atualização imediata |
| RNF-CON-001 | ✅ | Histórico preservado em `Chamado` |
| RNF-CON-002 | 🟡 | Botão desabilitado após envio |
| RNF-CON-003 | — | Condicionado a banco de dados (dados atuais em memória) |
| RNF-CON-004 / 005 | ❔ | A verificar |
| RNF-MAN-001 | ✅ | Separação em `model`, `repository` e telas |
| RNF-MAN-002 | 🟡 | Pouco reaproveitamento de componentes |
| RNF-MAN-003 | ✅ | Nomenclatura em português, padronizada |
| RNF-MAN-004 | ✅ | Categoria, status e prioridade centralizados em enums |

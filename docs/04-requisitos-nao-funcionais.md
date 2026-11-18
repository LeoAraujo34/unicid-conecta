# 4. Requisitos não funcionais

## 4.1 Segurança (RNF-SEG)

| ID | Requisito |
|---|---|
| RNF-SEG-001 | Restringir funcionalidades de acordo com o perfil do usuário autenticado. |
| RNF-SEG-002 | Usuários do perfil aluno não deverão possuir acesso às funcionalidades administrativas da operação/manutenção. |
| RNF-SEG-003 | Não armazenar senhas em texto simples caso utilize autenticação persistente. |
| RNF-SEG-004 | Em uma implantação real, a comunicação entre cliente e servidor deverá utilizar HTTPS. |
| RNF-SEG-005 | Validar dados recebidos antes de processá-los ou armazená-los. |
| RNF-SEG-006 | Operações de alteração de status deverão possuir identificação do usuário responsável. |
| RNF-SEG-007 | Impedir acesso não autorizado aos dados de outros usuários. |

## 4.2 Usabilidade (RNF-USA)

| ID | Requisito |
|---|---|
| RNF-USA-001 | A interface deverá manter identidade visual consistente em todas as telas. |
| RNF-USA-002 | As principais funcionalidades deverão possuir navegação simples e facilmente identificável. |
| RNF-USA-003 | Apresentar feedback visual após operações importantes, como criação e atualização de chamados. |
| RNF-USA-004 | Campos obrigatórios deverão ser claramente identificados. |
| RNF-USA-005 | Mensagens de erro deverão explicar ao usuário o que precisa ser corrigido. |
| RNF-USA-006 | Os status dos chamados deverão possuir identificação textual, não dependendo exclusivamente de cores. |
| RNF-USA-007 | A interface deverá possuir contraste adequado entre conteúdo e fundo. |
| RNF-USA-008 | O fluxo de abertura de chamado deverá exigir apenas as informações necessárias para o registro da ocorrência. |

## 4.3 Desempenho (RNF-DES)

| ID | Requisito |
|---|---|
| RNF-DES-001 | As principais telas deverão ser carregadas em tempo adequado durante condições normais de utilização do protótipo. |
| RNF-DES-002 | A abertura de um chamado deverá apresentar confirmação ao usuário sem necessidade de recarregamento manual da aplicação. |
| RNF-DES-003 | Alterações realizadas pela operação deverão ser refletidas na consulta do chamado após atualização dos dados. |

## 4.4 Confiabilidade e integridade dos dados (RNF-CON)

| ID | Requisito |
|---|---|
| RNF-CON-001 | Preservar o histórico das alterações realizadas em um chamado. |
| RNF-CON-002 | Evitar a criação duplicada de um chamado causada por múltiplos acionamentos consecutivos do botão de envio. |
| RNF-CON-003 | Os registros persistidos deverão permanecer disponíveis após nova autenticação ou atualização da aplicação, quando houver banco de dados configurado. |
| RNF-CON-004 | Uma falha durante uma operação não deverá resultar em dados parcialmente registrados. |
| RNF-CON-005 | Informar ao usuário quando uma operação não puder ser concluída. |

## 4.5 Manutenibilidade (RNF-MAN)

| ID | Requisito |
|---|---|
| RNF-MAN-001 | O código deverá ser organizado de forma modular, separando responsabilidades sempre que possível. |
| RNF-MAN-002 | Componentes de interface reutilizáveis deverão ser utilizados para elementos recorrentes. |
| RNF-MAN-003 | Nomes de variáveis, funções e componentes deverão seguir um padrão consistente definido pela equipe. |
| RNF-MAN-004 | Alterações em categorias, status ou prioridades deverão evitar dependências desnecessárias espalhadas pelo sistema. |

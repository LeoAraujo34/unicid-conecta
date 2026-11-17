# 📱 UNICID Conecta

O **UNICID Conecta** é uma aplicação nativa Android desenvolvida em Java (Android Studio) com o objetivo de otimizar e centralizar a comunicação entre os estudantes e a equipa de suporte/infraestrutura da UNICID. A aplicação permite a abertura, gestão e acompanhamento em tempo real de chamados de manutenção e infraestrutura no campus.

---

## 🚀 Funcionalidades Principais

### 👨‍🎓 Módulo do Aluno (`MainActivity`)
* **Abertura de Chamados:** Interface intuitiva para reportar problemas (ex: projetores, redes, computadores, instalações) especificando a categoria, o bloco e o local exato.
* **Acompanhamento em Tempo Real:** Tela de acompanhamento detalhada que exibe o status atual do protocolo e uma **linha do tempo interativa** (Andamento), atualizando os ícones de progresso (Recebido, Encaminhado, Técnico Atribuído, Em Manutenção, Resolvido) conforme a evolução da equipe técnica.
* **Notas da Equipe:** Visualização de atualizações e pareceres enviados diretamente pelos operadores responsáveis pelo atendimento.

### 🛠️ Módulo do Operador / Administrador (`OperacaoActivity`)
* **Fila de Atendimento Dinâmica:** Listagem em tempo real dos chamados submetidos pelos alunos, ordenados e com etiquetas visuais de prioridade e status.
* **Gestão de Detalhes e Status:** Tela dedicada onde o operador pode inspecionar os dados completos do chamado e alterar o status instantaneamente (*Recebido*, *Em Análise*, *Em Manutenção* ou *Resolvido*), com destaque visual automático (incluindo selo verde de conclusão).
* **Dashboard Operacional:** Painel de métricas sincronizado dinamicamente que exibe contadores em tempo real (total de chamados, pendentes, urgentes e resolvidos).
* **Análise Operacional:** Relatório de desempenho semanal que calcula automaticamente KPIs como a taxa de resolução percentual e distribuição de chamados por categoria com base nos dados reais do sistema.

---

## ⚙️ Arquitetura e Gestão de Estado
* **Padrão Repository:** Utilização de um repositório centralizado em memória (`ChamadoRepository` implementado em padrão *Singleton*) para garantir a sincronização bidirecional e instantânea de dados entre as visões do Aluno e do Operador sem perdas de estado.
* **Componentes Modernos:** Desenvolvido utilizando *Fragments*, *ConstraintLayout*, componentes do *Material Design* (MaterialCards, Badges, Botões customizados) e adaptação fluida de cores e estados via código.

---

## 🛠️ Tecnologias Utilizadas
* **Linguagem:** Java
* **Ambiente de Desenvolvimento:** Android Studio
* **UI/UX:** XML, ConstraintLayout, Material Components
* **Controle de Versão:** Git & GitHub

---

## 📱 Como Executar o Projeto
1. Clone este repositório ou descarregue o código-fonte.
2. Abra o **Android Studio**.
3. Selecione a opção **Open** e aponte para a pasta raiz do projeto (`UnicidConecta`).
4. Aguarde a sincronização do Gradle.
5. Execute a aplicação num emulador ou dispositivo físico Android (API 24 ou superior).

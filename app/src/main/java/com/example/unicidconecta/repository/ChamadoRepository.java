package com.example.unicidconecta.repository;

import com.example.unicidconecta.model.Categoria;
import com.example.unicidconecta.model.Chamado;
import com.example.unicidconecta.model.Prioridade;
import com.example.unicidconecta.model.Status;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class ChamadoRepository {

    private static final long MINUTO = 60 * 1000L;
    public static final String USUARIO_MOCK_ALUNO = "Leonardo Sixto";
    public static final String USUARIO_MOCK_OPERADOR = "Operador";
    private static final String AUTOR_SISTEMA = "Sistema";
    private static final String EQUIPE_TECNICA = "Equipe Técnica Tatuapé";

    private static ChamadoRepository instance;

    private final List<Chamado> chamados = new ArrayList<>();
    private int proximoNumero = 1586;
    private Chamado chamadoSelecionado; // chamado clicado na visão do operador

    private ChamadoRepository() {
        carregarDadosDeExemplo();
    }

    public static synchronized ChamadoRepository getInstance() {
        if (instance == null) {
            instance = new ChamadoRepository();
        }
        return instance;
    }

    public synchronized Chamado criar(String titulo, String descricao, String local,
                                      Categoria categoria, Prioridade prioridade,
                                      String solicitante, String imagemUri) {

        long agora = System.currentTimeMillis();
        Chamado chamado = new Chamado(gerarId(), titulo, descricao, local,
                categoria, prioridade, solicitante, agora, imagemUri);
        chamado.registrarEvento(Status.RECEBIDO, solicitante, "Chamado aberto", agora);
        chamados.add(0, chamado);
        return chamado;
    }

    public synchronized void alterarStatus(Chamado chamado, Status novoStatus, String autor, String nota) {
        if (chamado == null || novoStatus == null) return;
        chamado.registrarEvento(novoStatus, autor, nota, System.currentTimeMillis());
    }

    public synchronized void atribuirResponsavel(Chamado chamado, String responsavel) {
        if (chamado != null) {
            chamado.setResponsavel(responsavel);
        }
    }

    public synchronized List<Chamado> getListaChamados() {
        return Collections.unmodifiableList(new ArrayList<>(chamados));
    }

    public synchronized Chamado buscarPorId(String id) {
        for (Chamado c : chamados) {
            if (c.getId().equals(id)) return c;
        }
        return null;
    }

    public synchronized List<Chamado> listarPorSolicitante(String solicitante) {
        List<Chamado> resultado = new ArrayList<>();
        for (Chamado c : chamados) {
            if (c.getSolicitante().equals(solicitante)) resultado.add(c);
        }
        return resultado;
    }

    public synchronized int contarAbertos() {
        int total = 0;
        for (Chamado c : chamados) {
            if (!c.getStatus().isFinalizado()) total++;
        }
        return total;
    }

    public synchronized int contarUrgentes() {
        int total = 0;
        for (Chamado c : chamados) {
            if (!c.getStatus().isFinalizado() && c.getPrioridade().isCritica()) total++;
        }
        return total;
    }

    public synchronized int contarPorStatus(Status status) {
        int total = 0;
        for (Chamado c : chamados) {
            if (c.getStatus() == status) total++;
        }
        return total;
    }

    public synchronized int contarPorCategoria(Categoria categoria) {
        int total = 0;
        for (Chamado c : chamados) {
            if (c.getCategoria() == categoria) total++;
        }
        return total;
    }

    public synchronized int getTotal() {
        return chamados.size();
    }

    public Chamado getChamadoSelecionado() {
        return chamadoSelecionado;
    }

    public void setChamadoSelecionado(Chamado chamado) {
        this.chamadoSelecionado = chamado;
    }

    private String gerarId() {
        return String.format(Locale.US, "#%05d", proximoNumero++);
    }

    private void carregarDadosDeExemplo() {
        // Lista deixada vazia propositalmente para o sistema iniciar com zero chamados.
        // O aluno poderá criar novos chamados à medida que testar a aplicação.
    }
}
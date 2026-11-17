package com.example.unicidconecta.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Chamado {
    private final String id;
    private final String titulo;
    private final String descricao;
    private final String local;
    private final Categoria categoria;
    private final Prioridade prioridade;
    private final String solicitante;
    private final long criadoEm;
    private final String imagemUri;

    private Status status = Status.RECEBIDO;
    private String responsavel;
    private final List<EventoChamado> historico = new ArrayList<>();

    public Chamado(String id, String titulo, String descricao, String local,
                   Categoria categoria, Prioridade prioridade,
                   String solicitante, long criadoEm, String imagemUri) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.local = local;
        this.categoria = categoria;
        this.prioridade = prioridade;
        this.solicitante = solicitante;
        this.criadoEm = criadoEm;
        this.imagemUri = imagemUri;
    }

    public String getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getDescricao() { return descricao; }
    public String getLocal() { return local; }
    public Categoria getCategoria() { return categoria; }
    public Prioridade getPrioridade() { return prioridade; }
    public String getSolicitante() { return solicitante; }
    public long getCriadoEm() { return criadoEm; }
    public String getImagemUri() { return imagemUri; }
    public Status getStatus() { return status; }
    public String getResponsavel() { return responsavel; }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public List<EventoChamado> getHistorico() {
        return Collections.unmodifiableList(historico);
    }

    public void registrarEvento(Status novoStatus, String autor, String nota, long agora) {
        this.status = novoStatus;
        historico.add(new EventoChamado(novoStatus, autor, nota, agora));
    }
}

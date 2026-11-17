package com.example.unicidconecta.model;


public final class EventoChamado {
    private final Status status;
    private final String autor;
    private final String nota;
    private final long dataHora;

    public EventoChamado(Status status, String autor, String nota, long dataHora) {
        this.status = status;
        this.autor = autor;
        this.nota = nota;
        this.dataHora = dataHora;
    }

    public Status getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public String getNota() {
        return nota;
    }

    public long getDataHora() {
        return dataHora;
    }
}

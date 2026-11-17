package com.example.unicidconecta.model;

import androidx.annotation.StringRes;

import com.example.unicidconecta.R;

public enum Prioridade {
    BAIXA(R.string.prioridade_baixa),
    MEDIA(R.string.prioridade_media),
    ALTA(R.string.prioridade_alta),
    URGENTE(R.string.prioridade_urgente);

    @StringRes
    private final int rotuloRes;

    Prioridade(@StringRes int rotuloRes) {
        this.rotuloRes = rotuloRes;
    }

    @StringRes
    public int getRotuloRes() {
        return rotuloRes;
    }

    public boolean isCritica() {
        return this == ALTA || this == URGENTE;
    }
}

package com.example.unicidconecta.model;

import androidx.annotation.StringRes;
import com.example.unicidconecta.R;

public enum Status {
    RECEBIDO(R.string.status_recebido),
    EM_ANALISE(R.string.status_em_analise),
    EM_ATENDIMENTO(R.string.status_em_atendimento),
    RESOLVIDO(R.string.status_resolvido);


    @StringRes
    private final int rotuloRes;

    Status(@StringRes int rotuloRes){
        this.rotuloRes = rotuloRes;
    }

    @StringRes
    public int getRotuloRes(){
        return rotuloRes;
    }

    public boolean isFinalizado(){
        return this == RESOLVIDO;
    }
}
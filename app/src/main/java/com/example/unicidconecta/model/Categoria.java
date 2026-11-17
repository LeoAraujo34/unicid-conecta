package com.example.unicidconecta.model;

import androidx.annotation.StringRes;

import com.example.unicidconecta.R;

public enum Categoria {
    TECNOLOGIA(R.string.categoria_tecnologia),
    AUDIOVISUAL(R.string.categoria_audiovisual),
    INFRAESTRUTURA(R.string.categoria_infraestrutura);

    @StringRes
    private final int rotuloRes;

    Categoria(@StringRes int rotuloRes) {
        this.rotuloRes = rotuloRes;
    }

    @StringRes
    public int getRotuloRes() {
        return rotuloRes;
    }
}

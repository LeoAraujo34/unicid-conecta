package com.example.unicidconecta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.unicidconecta.model.Categoria;
import com.example.unicidconecta.model.Prioridade;
import com.example.unicidconecta.repository.ChamadoRepository;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class NovoChamadoFragment extends Fragment {

    private static final int TITULO_MAX_CARACTERES = 50;

    private Categoria categoriaSelecionada = Categoria.TECNOLOGIA;

    public NovoChamadoFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_novo_chamado, container, false);

        EditText etDescricao = view.findViewById(R.id.et_descricao_problema);
        EditText etLocal = view.findViewById(R.id.et_localizacao); // Novo campo de texto livre para o local
        MaterialButton btnEnviar = view.findViewById(R.id.btn_enviar_chamado);

        MaterialCardView cardComputador = view.findViewById(R.id.card_cat_computador);
        MaterialCardView cardWifi = view.findViewById(R.id.card_cat_wifi);
        MaterialCardView cardProjetor = view.findViewById(R.id.card_cat_projetor);

        View.OnClickListener categoriaListener = v -> {
            resetarCardCategoria(cardComputador);
            resetarCardCategoria(cardWifi);
            resetarCardCategoria(cardProjetor);

            MaterialCardView cardClicado = (MaterialCardView) v;
            cardClicado.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.blue_light_bg));
            cardClicado.setStrokeColor(ContextCompat.getColor(getContext(), R.color.blue_primary));
            cardClicado.setStrokeWidth(6); // Borda mais grossa para evidenciar

            if (v.getId() == R.id.card_cat_projetor) {
                categoriaSelecionada = Categoria.AUDIOVISUAL;
            } else {
                categoriaSelecionada = Categoria.TECNOLOGIA;
            }
            Toast.makeText(getContext(), "Categoria: " + getString(categoriaSelecionada.getRotuloRes()), Toast.LENGTH_SHORT).show();
        };

        if (cardComputador != null) cardComputador.setOnClickListener(categoriaListener);
        if (cardWifi != null) cardWifi.setOnClickListener(categoriaListener);
        if (cardProjetor != null) cardProjetor.setOnClickListener(categoriaListener);

        MaterialButton btnAdicionarFoto = view.findViewById(R.id.btn_adicionar_foto);
        if (btnAdicionarFoto != null) {
            btnAdicionarFoto.setOnClickListener(v -> {
                Toast.makeText(getContext(), "Foto anexada com sucesso!", Toast.LENGTH_SHORT).show();
                btnAdicionarFoto.setText("Foto anexada (1)");
            });
        }

        if (btnEnviar != null) {
            btnEnviar.setOnClickListener(v -> {
                String descricaoTexto = etDescricao != null ? etDescricao.getText().toString().trim() : "";
                String localTexto = etLocal != null ? etLocal.getText().toString().trim() : "";

                // Validação da localização obrigatória
                if (localTexto.isEmpty()) {
                    if (etLocal != null) {
                        etLocal.setError("Informe a localização exata do problema");
                    }
                    return;
                }

                // Validação da descrição obrigatória
                if (descricaoTexto.isEmpty()) {
                    if (etDescricao != null) {
                        etDescricao.setError(getString(R.string.chamado_descricao_obrigatoria));
                    }
                    return;
                }

                btnEnviar.setEnabled(false);

                // Usa o texto livre digitado pelo aluno
                String localCompleto = localTexto;

                String titulo = descricaoTexto.length() > TITULO_MAX_CARACTERES
                        ? descricaoTexto.substring(0, TITULO_MAX_CARACTERES).trim() + "…"
                        : descricaoTexto;

                ChamadoRepository.getInstance().criar(
                        titulo,
                        descricaoTexto,
                        localCompleto,
                        categoriaSelecionada,
                        Prioridade.MEDIA,
                        ChamadoRepository.USUARIO_MOCK_ALUNO,
                        null // imagem
                );

                Toast.makeText(getContext(), "Chamado enviado com sucesso!", Toast.LENGTH_SHORT).show();

                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragment_container, new AcompanhamentoFragment())
                            .commit();
                }
            });
        }

        return view;
    }

    private void resetarCardCategoria(MaterialCardView card) {
        if (card != null && getContext() != null) {
            card.setCardBackgroundColor(ContextCompat.getColor(getContext(), R.color.white));
            card.setStrokeColor(ContextCompat.getColor(getContext(), R.color.card_stroke));
            card.setStrokeWidth(2);
        }
    }
}
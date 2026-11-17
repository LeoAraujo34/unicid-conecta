package com.example.unicidconecta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.unicidconecta.model.Chamado;
import com.example.unicidconecta.repository.ChamadoRepository;
import java.util.List;

public class FilaChamadosFragment extends Fragment {

    public FilaChamadosFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fila_chamados, container, false);

        // Se estiver logado como aluno, podemos filtrar apenas os chamados dele,
        // ou listar todos se estiver no painel do operador. Vamos buscar do repositório:
        List<Chamado> lista = ChamadoRepository.getInstance().getListaChamados();

        View card1 = view.findViewById(R.id.card_fila_1);
        View card2 = view.findViewById(R.id.card_fila_2);
        View card3 = view.findViewById(R.id.card_fila_3);

        // Preencher Card 1
        if (card1 != null) {
            if (lista.size() > 0) {
                card1.setVisibility(View.VISIBLE);
                Chamado c1 = lista.get(0);
                ((TextView) view.findViewById(R.id.tv_c1_proto)).setText(c1.getId());
                ((TextView) view.findViewById(R.id.tv_c1_titulo)).setText(c1.getTitulo());
                ((TextView) view.findViewById(R.id.tv_c1_local)).setText(c1.getLocal());

                TextView tvStatus1 = view.findViewById(R.id.tv_c1_status);
                estilizarStatusBadge(tvStatus1, c1);

                card1.setOnClickListener(v -> {
                    ChamadoRepository.getInstance().setChamadoSelecionado(c1);
                    abrirDetalhes();
                });
            } else {
                card1.setVisibility(View.GONE);
            }
        }

        // Preencher Card 2
        if (card2 != null) {
            if (lista.size() > 1) {
                card2.setVisibility(View.VISIBLE);
                Chamado c2 = lista.get(1);
                ((TextView) view.findViewById(R.id.tv_c2_proto)).setText(c2.getId());
                ((TextView) view.findViewById(R.id.tv_c2_titulo)).setText(c2.getTitulo());
                ((TextView) view.findViewById(R.id.tv_c2_local)).setText(c2.getLocal());

                TextView tvStatus2 = view.findViewById(R.id.tv_c2_status);
                estilizarStatusBadge(tvStatus2, c2);

                card2.setOnClickListener(v -> {
                    ChamadoRepository.getInstance().setChamadoSelecionado(c2);
                    abrirDetalhes();
                });
            } else {
                card2.setVisibility(View.GONE);
            }
        }

        // Preencher Card 3
        if (card3 != null) {
            if (lista.size() > 2) {
                card3.setVisibility(View.VISIBLE);
                Chamado c3 = lista.get(2);
                ((TextView) view.findViewById(R.id.tv_c3_proto)).setText(c3.getId());
                ((TextView) view.findViewById(R.id.tv_c3_titulo)).setText(c3.getTitulo());
                ((TextView) view.findViewById(R.id.tv_c3_local)).setText(c3.getLocal());

                TextView tvStatus3 = view.findViewById(R.id.tv_c3_status);
                estilizarStatusBadge(tvStatus3, c3);

                card3.setOnClickListener(v -> {
                    ChamadoRepository.getInstance().setChamadoSelecionado(c3);
                    abrirDetalhes();
                });
            } else {
                card3.setVisibility(View.GONE);
            }
        }

        return view;
    }

    private void estilizarStatusBadge(TextView tvStatus, Chamado chamado) {
        if (tvStatus == null || getContext() == null) return;
        tvStatus.setText(getString(chamado.getStatus().getRotuloRes()));

        if (chamado.getStatus().isFinalizado()) {
            tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.badge_green_text));
        } else if (chamado.getPrioridade().isCritica()) {
            tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.badge_red_text));
        } else {
            tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.badge_orange_text));
        }
    }

    private void abrirDetalhes() {
        if (getActivity() != null) {
            View containerOp = getActivity().findViewById(R.id.fragment_container_op);

            int containerId;
            Fragment fragmentDestino;

            if (containerOp != null) {
                containerId = R.id.fragment_container_op;
                fragmentDestino = new DetalheAtendimentoFragment();
            } else {
                containerId = R.id.fragment_container;
                fragmentDestino = new AcompanhamentoFragment(); // Linha do tempo / detalhes do aluno
            }

            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(containerId, fragmentDestino)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
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
import com.example.unicidconecta.model.Status;
import com.example.unicidconecta.repository.ChamadoRepository;
import java.util.List;

public class DashboardOpFragment extends Fragment {

    private TextView tvAbertos, tvAtendimento, tvUrgentes, tvResolvidos;
    private View cardRecente1, cardRecente2;
    private TextView tvRec1Num, tvRec1Title, tvRec1Local, tvRec1Badge;
    private TextView tvRec2Num, tvRec2Title, tvRec2Local, tvRec2Badge;

    public DashboardOpFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard_op, container, false);

        // Mapeamento dos contadores de métricas
        tvAbertos = view.findViewById(R.id.tv_val_abertos);
        tvAtendimento = view.findViewById(R.id.tv_val_atendimento);
        tvUrgentes = view.findViewById(R.id.tv_val_urgentes);
        tvResolvidos = view.findViewById(R.id.tv_val_resolvidos);

        // Mapeamento dos cards recentes
        cardRecente1 = view.findViewById(R.id.card_recente_1);
        cardRecente2 = view.findViewById(R.id.card_recente_2);

        tvRec1Num = view.findViewById(R.id.tv_rec1_num);
        tvRec1Title = view.findViewById(R.id.tv_rec1_title);
        tvRec1Local = view.findViewById(R.id.tv_rec1_local);
        tvRec1Badge = view.findViewById(R.id.tv_rec1_badge);

        tvRec2Num = view.findViewById(R.id.tv_rec2_num);
        tvRec2Title = view.findViewById(R.id.tv_rec2_title);
        tvRec2Local = view.findViewById(R.id.tv_rec2_local);
        tvRec2Badge = view.findViewById(R.id.tv_rec2_badge);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        atualizarDashboard();
    }

    private void atualizarDashboard() {
        ChamadoRepository repo = ChamadoRepository.getInstance();

        // 1. Atualizar Métricas Reais
        int totalAbertos = repo.contarAbertos();
        int emAtendimento = repo.contarPorStatus(Status.EM_ANALISE) + repo.contarPorStatus(Status.EM_ATENDIMENTO);
        int urgentes = repo.contarUrgentes();
        int resolvidos = repo.contarPorStatus(Status.RESOLVIDO);

        if (tvAbertos != null) tvAbertos.setText(String.valueOf(totalAbertos));
        if (tvAtendimento != null) tvAtendimento.setText(String.valueOf(emAtendimento));
        if (tvUrgentes != null) tvUrgentes.setText(String.valueOf(urgentes));
        if (tvResolvidos != null) tvResolvidos.setText(String.valueOf(resolvidos));

        // 2. Preencher ou Ocultar Chamados Recentes Dinamicamente
        List<Chamado> lista = repo.getListaChamados();

        // Card Recente 1
        if (cardRecente1 != null) {
            if (lista.size() > 0) {
                cardRecente1.setVisibility(View.VISIBLE);
                Chamado c1 = lista.get(0);
                if (tvRec1Num != null) tvRec1Num.setText(c1.getId());
                if (tvRec1Title != null) tvRec1Title.setText(c1.getTitulo());
                if (tvRec1Local != null) tvRec1Local.setText(c1.getLocal());
                if (tvRec1Badge != null) {
                    tvRec1Badge.setText(c1.getPrioridade().name());
                }

                cardRecente1.setOnClickListener(v -> {
                    ChamadoRepository.getInstance().setChamadoSelecionado(c1);
                    abrirDetalhesOperador();
                });
            } else {
                cardRecente1.setVisibility(View.GONE);
            }
        }

        // Card Recente 2
        if (cardRecente2 != null) {
            if (lista.size() > 1) {
                cardRecente2.setVisibility(View.VISIBLE);
                Chamado c2 = lista.get(1);
                if (tvRec2Num != null) tvRec2Num.setText(c2.getId());
                if (tvRec2Title != null) tvRec2Title.setText(c2.getTitulo());
                if (tvRec2Local != null) tvRec2Local.setText(c2.getLocal());
                if (tvRec2Badge != null) {
                    tvRec2Badge.setText(c2.getPrioridade().name());
                }

                cardRecente2.setVisibility(View.VISIBLE);
                cardRecente2.setOnClickListener(v -> {
                    ChamadoRepository.getInstance().setChamadoSelecionado(c2);
                    abrirDetalhesOperador();
                });
            } else {
                cardRecente2.setVisibility(View.GONE);
            }
        }
    }

    private void abrirDetalhesOperador() {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_op, new DetalheAtendimentoFragment())
                    .addToBackStack(null)
                    .commit();
        }
    }
}
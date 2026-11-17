package com.example.unicidconecta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.unicidconecta.model.Chamado;
import com.example.unicidconecta.repository.ChamadoRepository;
import java.util.List;

public class HomeAlunoFragment extends Fragment {

    private View cardChamado1, cardChamado2;
    private TextView tvLocalCard1, tvDescCard1, tvStatusCard1;
    private TextView tvLocalCard2, tvDescCard2, tvStatusCard2;

    public HomeAlunoFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_aluno, container, false);

        // 1. Referências dos cards de status dos chamados
        cardChamado1 = view.findViewById(R.id.card_chamado_1);
        cardChamado2 = view.findViewById(R.id.card_chamado_2);

        tvLocalCard1 = view.findViewById(R.id.tv_local_card_1);
        tvDescCard1 = view.findViewById(R.id.tv_desc_card_1);
        tvStatusCard1 = view.findViewById(R.id.tv_status_card_1);

        tvLocalCard2 = view.findViewById(R.id.tv_local_card_2);
        tvDescCard2 = view.findViewById(R.id.tv_desc_card_2);
        tvStatusCard2 = view.findViewById(R.id.tv_status_card_2);

        // 2. Referências e cliques dos botões do menu superior/central da Home
        View cardAbrirChamado = view.findViewById(R.id.card_abrir_chamado);
        View cardMeusChamados = view.findViewById(R.id.card_meus_chamados);
        View cardCarteirinha = view.findViewById(R.id.card_carteirinha);
        View cardAvisos = view.findViewById(R.id.card_avisos);
        TextView tvVerTudo = view.findViewById(R.id.tv_ver_tudo);

        // Ação: Abrir novo chamado
        if (cardAbrirChamado != null) {
            cardAbrirChamado.setOnClickListener(v -> {
                trocarFragmento(new NovoChamadoFragment()); // Substitui pelo nome do teu fragmento de criar chamado
            });
        }

        // Ação: Ver lista de meus chamados
        if (cardMeusChamados != null || tvVerTudo != null) {
            View.OnClickListener acaoMeusChamados = v -> {
                trocarFragmento(new FilaChamadosFragment()); // Abre a lista geral de chamados do aluno
            };
            if (cardMeusChamados != null) cardMeusChamados.setOnClickListener(acaoMeusChamados);
            if (tvVerTudo != null) tvVerTudo.setOnClickListener(acaoMeusChamados);
        }

        // Ação: Ver carteirinha estudantil
        if (cardCarteirinha != null) {
            cardCarteirinha.setOnClickListener(v -> {
                trocarFragmento(new CarteirinhaFragment());
            });
        }

        // Ação: Ver avisos do campus
        if (cardAvisos != null) {
            cardAvisos.setOnClickListener(v -> {
                trocarFragmento(new AvisosFragment());
            });
        }

        // 3. Carregar os chamados reais nos cards de status da Home
        List<Chamado> listaChamados = ChamadoRepository.getInstance()
                .listarPorSolicitante(ChamadoRepository.USUARIO_MOCK_ALUNO);

        atualizarExibicaoChamados(listaChamados);

        return view;
    }

    private void atualizarExibicaoChamados(List<Chamado> lista) {
        // Filtra apenas os chamados que NÃO estão resolvidos/finalizados para aparecerem na "Situação no campus"
        List<Chamado> chamadosAtivos = new java.util.ArrayList<>();
        if (lista != null) {
            for (Chamado c : lista) {
                // Se o status NÃO for finalizado/resolvido, entra na lista de ativos da Home
                if (c.getStatus() != null && !c.getStatus().isFinalizado()) {
                    chamadosAtivos.add(c);
                }
            }
        }

        // Se não houver nenhum chamado pendente/ativo, oculta ambos os cards da Home
        if (chamadosAtivos.isEmpty()) {
            if (cardChamado1 != null) cardChamado1.setVisibility(View.GONE);
            if (cardChamado2 != null) cardChamado2.setVisibility(View.GONE);
        } else {
            // Exibe o primeiro chamado ativo, se existir
            if (chamadosAtivos.size() > 0 && cardChamado1 != null) {
                Chamado c1 = chamadosAtivos.get(0);
                cardChamado1.setVisibility(View.VISIBLE);
                if (tvLocalCard1 != null) tvLocalCard1.setText(c1.getLocal());
                if (tvDescCard1 != null) tvDescCard1.setText(c1.getTitulo());
                if (tvStatusCard1 != null) {
                    tvStatusCard1.setText(getString(c1.getStatus().getRotuloRes()));
                }

                cardChamado1.setOnClickListener(v -> {
                    ChamadoRepository.getInstance().setChamadoSelecionado(c1);
                    trocarFragmento(new AcompanhamentoFragment());
                });
            } else if (cardChamado1 != null) {
                cardChamado1.setVisibility(View.GONE);
            }

            // Exibe o segundo chamado ativo, se existir
            if (chamadosAtivos.size() > 1 && cardChamado2 != null) {
                Chamado c2 = chamadosAtivos.get(1);
                cardChamado2.setVisibility(View.VISIBLE);
                if (tvLocalCard2 != null) tvLocalCard2.setText(c2.getLocal());
                if (tvDescCard2 != null) tvDescCard2.setText(c2.getTitulo());
                if (tvStatusCard2 != null) {
                    tvStatusCard2.setText(getString(c2.getStatus().getRotuloRes()));
                }

                cardChamado2.setOnClickListener(v -> {
                    ChamadoRepository.getInstance().setChamadoSelecionado(c2);
                    trocarFragmento(new AcompanhamentoFragment());
                });
            } else if (cardChamado2 != null) {
                cardChamado2.setVisibility(View.GONE);
            }
        }
    }

    private void trocarFragmento(Fragment fragment) {
        if (getActivity() != null) {
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack(null)
                    .commit();
        }
    }
}
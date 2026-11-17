package com.example.unicidconecta;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.unicidconecta.model.Chamado;
import com.example.unicidconecta.model.EventoChamado; // Import necessário para ler o histórico
import com.example.unicidconecta.model.Status;
import com.example.unicidconecta.repository.ChamadoRepository;
import java.util.List;

public class AcompanhamentoFragment extends Fragment {

    public AcompanhamentoFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_acompanhamento, container, false);

        Chamado chamadoSelecionado = ChamadoRepository.getInstance().getChamadoSelecionado();

        if (chamadoSelecionado == null) {
            List<Chamado> lista = ChamadoRepository.getInstance()
                    .listarPorSolicitante(ChamadoRepository.USUARIO_MOCK_ALUNO);
            if (!lista.isEmpty()) {
                chamadoSelecionado = lista.get(0);
            }
        }

        if (chamadoSelecionado != null) {
            TextView tvProtocolo = view.findViewById(R.id.tv_num_protocolo);
            TextView tvStatus = view.findViewById(R.id.tv_badge_status);
            TextView tvTitulo = view.findViewById(R.id.tv_titulo_chamado);
            TextView tvLocal = view.findViewById(R.id.tv_local_chamado);
            TextView tvMensagemEquipe = view.findViewById(R.id.tv_mensagem_equipe);

            if (tvProtocolo != null) tvProtocolo.setText(chamadoSelecionado.getId());
            if (tvTitulo != null) tvTitulo.setText(chamadoSelecionado.getTitulo());
            if (tvLocal != null) tvLocal.setText(chamadoSelecionado.getLocal());

            if (tvMensagemEquipe != null) {
                List<EventoChamado> historico = chamadoSelecionado.getHistorico();
                if (historico != null && !historico.isEmpty()) {
                    EventoChamado ultimoEvento = historico.get(historico.size() - 1);
                    String nota = ultimoEvento.getNota();

                    if (nota != null && !nota.trim().isEmpty()) {
                        tvMensagemEquipe.setText(nota);
                    } else {
                        tvMensagemEquipe.setText("Atualização em andamento pela equipe técnica.");
                    }
                } else {
                    tvMensagemEquipe.setText("Aguardando análise da equipe técnica.");
                }
            }

            Status statusAtual = chamadoSelecionado.getStatus();

            if (tvStatus != null && getContext() != null) {
                tvStatus.setText(getString(statusAtual.getRotuloRes()).toUpperCase());

                // Cores do Badge Superior
                if (statusAtual.isFinalizado()) {
                    tvStatus.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.badge_green_bg)));
                    tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.badge_green_text));
                } else if (chamadoSelecionado.getPrioridade().isCritica()) {
                    tvStatus.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.badge_red_bg)));
                    tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.badge_red_text));
                } else {
                    tvStatus.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.badge_orange_bg)));
                    tvStatus.setTextColor(ContextCompat.getColor(getContext(), R.color.badge_orange_text));
                }
            }

            ImageView icon1 = view.findViewById(R.id.iv_icon_1);
            ImageView icon2 = view.findViewById(R.id.iv_icon_2);
            ImageView icon3 = view.findViewById(R.id.iv_icon_3);
            ImageView icon4 = view.findViewById(R.id.iv_icon_4);
            ImageView icon5 = view.findViewById(R.id.iv_icon_5);

            atualizarLinhaDoTempo(statusAtual, icon1, icon2, icon3, icon4, icon5);
        }

        return view;
    }

    private void atualizarLinhaDoTempo(Status status, ImageView i1, ImageView i2, ImageView i3, ImageView i4, ImageView i5) {
        if (getContext() == null) return;

        int iconeRelogio = android.R.drawable.ic_menu_recent_history;
        int iconeCinza = android.R.drawable.presence_invisible;

        switch (status) {
            case RESOLVIDO:
                setarIcone(i1, true);
                setarIcone(i2, true);
                setarIcone(i3, true);
                setarIcone(i4, true);
                setarIcone(i5, true);
                break;
            case EM_ATENDIMENTO:
                setarIcone(i1, true);
                setarIcone(i2, true);
                setarIcone(i3, true);
                if (i4 != null) { i4.setImageResource(iconeRelogio); }
                if (i5 != null) { i5.setImageResource(iconeCinza); }
                break;
            case EM_ANALISE:
                setarIcone(i1, true);
                setarIcone(i2, true);
                if (i3 != null) { i3.setImageResource(iconeRelogio); }
                if (i4 != null) { i4.setImageResource(iconeCinza); }
                if (i5 != null) { i5.setImageResource(iconeCinza); }
                break;
            case RECEBIDO:
            default:
                if (i1 != null) { i1.setImageResource(iconeRelogio); }
                if (i2 != null) { i2.setImageResource(iconeCinza); }
                if (i3 != null) { i3.setImageResource(iconeCinza); }
                if (i4 != null) { i4.setImageResource(iconeCinza); }
                if (i5 != null) { i5.setImageResource(iconeCinza); }
                break;
        }
    }

    private void setarIcone(ImageView iv, boolean concluido) {
        if (iv != null && getContext() != null) {
            if (concluido) {
                iv.setImageResource(android.R.drawable.checkbox_on_background);
                iv.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.badge_green_text)));
            } else {
                iv.setImageResource(android.R.drawable.presence_invisible);
                iv.setImageTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.text_secondary)));
            }
        }
    }

}
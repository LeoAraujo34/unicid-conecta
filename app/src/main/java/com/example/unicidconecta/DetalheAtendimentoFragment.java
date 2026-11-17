package com.example.unicidconecta;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import com.example.unicidconecta.model.Chamado;
import com.example.unicidconecta.model.Status;
import com.example.unicidconecta.repository.ChamadoRepository;
import com.google.android.material.button.MaterialButton;

public class DetalheAtendimentoFragment extends Fragment {

    private Status novoStatusSelecionado = Status.EM_ATENDIMENTO;

    private MaterialButton btnRecebido, btnAnalise, btnManutencao, btnResolvido;

    public DetalheAtendimentoFragment() {}

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe_atendimento, container, false);

        Chamado chamado = ChamadoRepository.getInstance().getChamadoSelecionado();

        btnRecebido = view.findViewById(R.id.btn_status_recebido);
        btnAnalise = view.findViewById(R.id.btn_status_analise);
        btnManutencao = view.findViewById(R.id.btn_status_manutencao);
        btnResolvido = view.findViewById(R.id.btn_status_resolvido);

        if (chamado != null) {
            TextView tvProtocolo = view.findViewById(R.id.tv_title_atendimento);
            TextView tvPrioridade = view.findViewById(R.id.tv_prioridade_badge);
            TextView tvHora = view.findViewById(R.id.tv_hora_ticket);
            TextView tvProblema = view.findViewById(R.id.tv_problema_atendimento);
            TextView tvDescricao = view.findViewById(R.id.tv_desc_atendimento);

            TextView tvLocalGrid = view.findViewById(R.id.tv_detalhe_local);
            TextView tvSolicitanteGrid = view.findViewById(R.id.tv_detalhe_solicitante);
            TextView tvCategoriaGrid = view.findViewById(R.id.tv_detalhe_categoria);
            TextView tvResponsavelGrid = view.findViewById(R.id.tv_detalhe_responsavel);

            String categoriaTexto = getString(chamado.getCategoria().getRotuloRes());
            String responsavel = chamado.getResponsavel() != null
                    ? chamado.getResponsavel() : getString(R.string.chamado_nao_atribuido);

            if (tvProtocolo != null) tvProtocolo.setText(getString(R.string.chamado_atendimento_fmt, chamado.getId()));
            if (tvPrioridade != null) tvPrioridade.setText(getString(R.string.chamado_prioridade_fmt,
                    getString(chamado.getPrioridade().getRotuloRes()).toUpperCase()));
            if (tvHora != null) tvHora.setText(DateUtils.getRelativeTimeSpanString(
                    chamado.getCriadoEm(), System.currentTimeMillis(), DateUtils.MINUTE_IN_MILLIS));
            if (tvProblema != null) tvProblema.setText(chamado.getTitulo());
            if (tvDescricao != null) tvDescricao.setText(chamado.getDescricao());

            if (tvLocalGrid != null) tvLocalGrid.setText(chamado.getLocal());
            if (tvSolicitanteGrid != null) tvSolicitanteGrid.setText(chamado.getSolicitante());
            if (tvCategoriaGrid != null) tvCategoriaGrid.setText(categoriaTexto);
            if (tvResponsavelGrid != null) tvResponsavelGrid.setText(responsavel);

            novoStatusSelecionado = chamado.getStatus();
            destacarBotaoAtivo(novoStatusSelecionado);
        }

        EditText etNota = view.findViewById(R.id.et_nota_manutencao);
        MaterialButton btnPublicar = view.findViewById(R.id.btn_publicar_att);

        if (btnRecebido != null) {
            btnRecebido.setOnClickListener(v -> {
                novoStatusSelecionado = Status.RECEBIDO;
                destacarBotaoAtivo(novoStatusSelecionado);
            });
        }
        if (btnAnalise != null) {
            btnAnalise.setOnClickListener(v -> {
                novoStatusSelecionado = Status.EM_ANALISE;
                destacarBotaoAtivo(novoStatusSelecionado);
            });
        }
        if (btnManutencao != null) {
            btnManutencao.setOnClickListener(v -> {
                novoStatusSelecionado = Status.EM_ATENDIMENTO;
                destacarBotaoAtivo(novoStatusSelecionado);
            });
        }
        if (btnResolvido != null) {
            btnResolvido.setOnClickListener(v -> {
                novoStatusSelecionado = Status.RESOLVIDO;
                destacarBotaoAtivo(novoStatusSelecionado);
            });
        }
        if (btnPublicar != null) {
            btnPublicar.setOnClickListener(v -> {
                if (chamado != null) {
                    String nota = etNota != null ? etNota.getText().toString().trim() : "";
                    // Registra o evento só quando há mudança de status ou uma nota nova
                    if (novoStatusSelecionado != chamado.getStatus() || !nota.isEmpty()) {
                        ChamadoRepository.getInstance().alterarStatus(chamado, novoStatusSelecionado,
                                ChamadoRepository.USUARIO_MOCK_OPERADOR, nota);
                    }
                }
                Toast.makeText(getContext(), getString(R.string.chamado_status_atualizado_fmt,
                        getString(novoStatusSelecionado.getRotuloRes())), Toast.LENGTH_SHORT).show();

                if (getActivity() != null) {
                    getActivity().getSupportFragmentManager().popBackStack();
                }
            });
        }

        return view;
    }
    private void destacarBotaoAtivo(Status status) {
        if (getContext() == null) return;

        resetarEstiloBotao(btnRecebido);
        resetarEstiloBotao(btnAnalise);
        resetarEstiloBotao(btnManutencao);
        resetarEstiloBotao(btnResolvido);

        MaterialButton botaoAlvo = null;
        boolean isVerde = false;

        switch (status) {
            case RECEBIDO:
                botaoAlvo = btnRecebido;
                break;
            case EM_ANALISE:
                botaoAlvo = btnAnalise;
                break;
            case EM_ATENDIMENTO:
                botaoAlvo = btnManutencao;
                break;
            case RESOLVIDO:
                botaoAlvo = btnResolvido;
                isVerde = true;
                break;
        }

        if (botaoAlvo != null) {
            if (isVerde) {
                botaoAlvo.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.badge_green_bg)));
                botaoAlvo.setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.badge_green_text)));
                botaoAlvo.setTextColor(ContextCompat.getColor(getContext(), R.color.badge_green_text));
            } else {
                botaoAlvo.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.blue_light_bg)));
                botaoAlvo.setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.blue_primary)));
                botaoAlvo.setTextColor(ContextCompat.getColor(getContext(), R.color.blue_primary));
            }
            botaoAlvo.setStrokeWidth(6);
        }
    }

    private void resetarEstiloBotao(MaterialButton btn) {
        if (btn != null && getContext() != null) {
            btn.setBackgroundTintList(ColorStateList.valueOf(ContextCompat.getColor(getContext(), android.R.color.transparent)));
            btn.setStrokeColor(ColorStateList.valueOf(ContextCompat.getColor(getContext(), R.color.card_stroke)));
            btn.setStrokeWidth(2);
            btn.setTextColor(ContextCompat.getColor(getContext(), R.color.text_primary));
        }
    }
}
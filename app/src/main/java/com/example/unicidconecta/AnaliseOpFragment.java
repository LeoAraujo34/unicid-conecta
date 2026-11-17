package com.example.unicidconecta;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.unicidconecta.model.Categoria;
import com.example.unicidconecta.model.Status;
import com.example.unicidconecta.repository.ChamadoRepository;

public class AnaliseOpFragment extends Fragment {

    public AnaliseOpFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_analise_op, container, false);

        ChamadoRepository repo = ChamadoRepository.getInstance();

        int total = repo.getTotal();
        int resolvidos = repo.contarPorStatus(Status.RESOLVIDO);
        int tecnologiaCount = repo.contarPorCategoria(Categoria.TECNOLOGIA);

        int taxaResolucao = total > 0 ? (resolvidos * 100) / total : 0;
        int progressoTecnologia = total > 0 ? (tecnologiaCount * 100) / total : 0;

        TextView tvTaxa = view.findViewById(R.id.tv_taxa_resolucao);
        TextView tvTempo = view.findViewById(R.id.tv_tempo_medio);
        ProgressBar pbTecnologia = view.findViewById(R.id.pb_tecnologia);

        if (tvTaxa != null) {
            tvTaxa.setText(taxaResolucao + "%");
        }
        if (tvTempo != null) {
            tvTempo.setText(total > 0 ? "15 min" : "0 min");
        }
        if (pbTecnologia != null) {
            pbTecnologia.setProgress(progressoTecnologia);
        }

        return view;
    }
}
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

public class DetalheChamadoAlunoFragment extends Fragment {

    private TextView tvCategoria, tvLocal, tvDescricao, tvStatus;
    private int chamadoIndex = 0;

    public static DetalheChamadoAlunoFragment newInstance(int index) {
        DetalheChamadoAlunoFragment fragment = new DetalheChamadoAlunoFragment();
        Bundle args = new Bundle();
        args.putInt("chamado_index", index);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            chamadoIndex = getArguments().getInt("chamado_index", 0);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhe_aluno, container, false);

        tvCategoria = view.findViewById(R.id.tv_aluno_categoria);
        tvLocal = view.findViewById(R.id.tv_aluno_local);
        tvDescricao = view.findViewById(R.id.tv_aluno_descricao);
        tvStatus = view.findViewById(R.id.tv_aluno_status);

        List<Chamado> lista = ChamadoRepository.getInstance().getListaChamados();
        if (lista != null && chamadoIndex >= 0 && chamadoIndex < lista.size()) {
            Chamado chamado = lista.get(chamadoIndex);

            if (tvCategoria != null && chamado.getCategoria() != null) {
                tvCategoria.setText(chamado.getCategoria().toString());
            }
            if (tvLocal != null) {
                tvLocal.setText(chamado.getLocal());
            }
            if (tvDescricao != null) {
                tvDescricao.setText(chamado.getDescricao());
            }
            if (tvStatus != null && chamado.getStatus() != null) {
                tvStatus.setText(chamado.getStatus().toString());
            }
        }

        return view;
    }
}
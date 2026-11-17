package com.example.unicidconecta;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.google.android.material.button.MaterialButton;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class CarteirinhaFragment extends Fragment {

    private TextView tvNomeAluno, tvCursoAluno, tvRgmVal, tvCampusVal, tvSituacaoVal, tvValidadeVal;
    private MaterialButton btnExpandirQr;
    private ImageView imgQrCode; // Variável para o QR Code

    public CarteirinhaFragment() {
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_carteirinha, container, false);

        tvNomeAluno = view.findViewById(R.id.tv_nome_aluno);
        tvCursoAluno = view.findViewById(R.id.tv_curso_aluno);
        tvRgmVal = view.findViewById(R.id.tv_rgm_val);
        tvCampusVal = view.findViewById(R.id.tv_campus_val);
        tvSituacaoVal = view.findViewById(R.id.tv_situacao_val);
        tvValidadeVal = view.findViewById(R.id.tv_validade_val);
        btnExpandirQr = view.findViewById(R.id.btn_expandir_qr);
        imgQrCode = view.findViewById(R.id.img_qrcode); // Certifica-te que tens este ID no XML da carteirinha

        // Dados académicos reais
        tvNomeAluno.setText("Leonardo Araújo Sixto");
        tvCursoAluno.setText("Análise e Desenvolvimento de Sistemas");
        tvRgmVal.setText("20260303");
        tvCampusVal.setText("UNICID Tatuapé");
        tvSituacaoVal.setText("Matrícula ativa");
        tvValidadeVal.setText("Dez 2026");

        // Geração automática do QR Code com os dados da carteirinha
        String dadosCarteirinha = "UNICID-CONECTA: Leonardo Araújo Sixto | RGM: 20260303 | ADS | Status: Ativo";
        Bitmap bitmap = gerarQRCode(dadosCarteirinha, 400, 400);
        if (bitmap != null && imgQrCode != null) {
            imgQrCode.setImageBitmap(bitmap);
        }

        if (btnExpandirQr != null) {
            btnExpandirQr.setOnClickListener(v -> {
                Toast.makeText(getContext(), "QR Code ampliado para leitura na catraca do campus!", Toast.LENGTH_SHORT).show();
            });
        }

        return view;
    }

    // Método auxiliar para criar a matriz e desenhar o QR Code
    private Bitmap gerarQRCode(String texto, int largura, int altura) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(texto, BarcodeFormat.QR_CODE, largura, altura);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bmp;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
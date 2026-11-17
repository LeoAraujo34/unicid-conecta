package com.example.unicidconecta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText etUsuario = findViewById(R.id.et_usuario);
        EditText etSenha = findViewById(R.id.et_senha);
        MaterialButton btnEntrar = findViewById(R.id.btn_entrar);

        btnEntrar.setOnClickListener(v -> {
            String usuario = etUsuario.getText().toString().trim();
            String senha = etSenha.getText().toString().trim();

            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(this, "Preencha o usuário e a senha", Toast.LENGTH_SHORT).show();
                return;
            }

            // Validação do Operador (opera / 1234)
            if (usuario.equals("opera") && senha.equals("1234")) {
                Toast.makeText(this, "Bem-vindo, Operador!", Toast.LENGTH_SHORT).show();
                // Redireciona para a atividade específica do Operador
                Intent intent = new Intent(LoginActivity.this, OperacaoActivity.class);
                startActivity(intent);
                finish();
            }
            // Validação do Aluno (Leonardo / 1234)
            else if (usuario.equalsIgnoreCase("Leonardo") && senha.equals("1234")) {
                Toast.makeText(this, "Bem-vindo, Leonardo!", Toast.LENGTH_SHORT).show();
                // Redireciona para a atividade principal do Aluno
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("TIPO_USUARIO", "ALUNO");
                startActivity(intent);
                finish();
            }
            // Credenciais inválidas
            else {
                Toast.makeText(this, "Usuário ou senha incorretos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
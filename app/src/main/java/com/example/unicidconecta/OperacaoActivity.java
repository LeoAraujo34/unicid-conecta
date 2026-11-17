package com.example.unicidconecta;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class OperacaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operacao);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation_op);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container_op, new DashboardOpFragment())
                    .commit();
        }

        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_op_painel) {
                selectedFragment = new DashboardOpFragment();
            } else if (itemId == R.id.nav_op_fila) {
                selectedFragment = new FilaChamadosFragment();
            } else if (itemId == R.id.nav_op_analise) {
                selectedFragment = new AnaliseOpFragment();
            } else if (itemId == R.id.nav_op_ajustes) {
                selectedFragment = new AjustesOpFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container_op, selectedFragment)
                        .commit();
                return true;
            }
            return false;
        });
    }
}
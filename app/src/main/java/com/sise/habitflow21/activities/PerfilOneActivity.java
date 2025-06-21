package com.sise.habitflow21.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.sise.habitflow21.R;
import com.sise.habitflow21.entities.FraseMotivacional;
import com.sise.habitflow21.viewmodel.FraseViewModel;

import java.util.List;
import java.util.Random;

public class PerfilOneActivity extends AppCompatActivity {

    private TextView textViewFrase;
    private Button btnFraseNueva;
    private FraseViewModel fraseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_perfil_one);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Referencias del layout
        textViewFrase = findViewById(R.id.textViewFrase);
        btnFraseNueva = findViewById(R.id.btnFraseNueva);

        // Inicializar ViewModel
        fraseViewModel = new ViewModelProvider(this).get(FraseViewModel.class);

        // Observar cambios en frases
        fraseViewModel.getListaFrases().observe(this, frases -> {
            if (frases != null && !frases.isEmpty()) {
                FraseMotivacional aleatoria = frases.get(new Random().nextInt(frases.size()));
                textViewFrase.setText(aleatoria.getFrase());
            } else {
                textViewFrase.setText("No hay frases disponibles.");
            }
        });

        // Mostrar frase al abrir la pantalla
        fraseViewModel.obtenerFrases();

        // Botón para generar nueva frase
        btnFraseNueva.setOnClickListener(v -> fraseViewModel.obtenerFrases());
    }

    public void mostrarFraseNueva(View view) {
        fraseViewModel.obtenerFrases();
    }
}
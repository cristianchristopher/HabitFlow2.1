package com.sise.habitflow21.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.ViewModelProvider;

import com.sise.habitflow21.R;
import com.sise.habitflow21.entities.FraseMotivacional;
import com.sise.habitflow21.viewmodel.FraseViewModel;

import java.util.Date;

public class FraseMotivacionalActivity extends AppCompatActivity {

    private FraseViewModel fraseViewModel;
    private EditText etFrase, etAutor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_frase_motivacional);

        etFrase = findViewById(R.id.etFrase);
        etAutor = findViewById(R.id.etAutor);

        fraseViewModel = new ViewModelProvider(this).get(FraseViewModel.class);
        observarFraseViewModel();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void observarFraseViewModel() {
        fraseViewModel.getInsertarFraseStatus().observe(this, success -> {
            String mensaje = success != null && success
                    ? "¡Frase insertada correctamente!"
                    : "¡Error al insertar frase!";
            Toast.makeText(getApplicationContext(), mensaje, Toast.LENGTH_LONG).show();

            if (success != null && success) {
                limpiarCampos();
            }
        });
    }

    public void onClickGuardarFrase(View view) {
        runOnUiThread(() -> {
            String textoFrase = etFrase.getText().toString().trim();
            String textoAutor = etAutor.getText().toString().trim();

            if (textoFrase.isEmpty() || textoAutor.isEmpty()) {
                Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            FraseMotivacional frase = new FraseMotivacional(); // ✅ Aquí usas la clase correcta
            frase.setFrase(textoFrase);
            frase.setAutor(textoAutor);
            frase.setFechaCreacion(new Date());
            frase.setEstadoAuditoria(true);

            fraseViewModel.insertarFrase(frase);
        });
    }

    private void limpiarCampos() {
        etFrase.setText("");
        etAutor.setText("");
    }
}
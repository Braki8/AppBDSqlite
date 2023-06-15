package com.example.tabs9;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class actualizarDB extends AppCompatActivity {

    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_db);

        // Obtener el objeto Cliente enviado desde el tab3
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("cliente")) {
            cliente = intent.getParcelableExtra("cliente");
        }

        // Resto de la lógica para actualizar la base de datos y mostrar los datos en la actividad
        // ...

        Button btnVolver = findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Regresar a tab3
                onBackPressed();
            }
        });
    }
}







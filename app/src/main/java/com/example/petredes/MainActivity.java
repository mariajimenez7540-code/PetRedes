package com.example.petredes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button inicio, registro;
    EditText correo, contrasena;
    String CORREO_VALIDO = "maria1@gmail.com";
    String CONTRASENA_VALIDA = "1234";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        correo = findViewById(R.id.CorreoEdit);
        contrasena = findViewById(R.id.ContrasenaEdit);
        inicio = findViewById(R.id.Ingresar);
        registro = findViewById(R.id.Registro);

        inicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailIngresado = correo.getText().toString().trim();
                String passIngresada = contrasena.getText().toString().trim();

                if (emailIngresado.isEmpty() || passIngresada.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
                } else if (emailIngresado.equals(CORREO_VALIDO) && passIngresada.equals(CONTRASENA_VALIDA)) {

                    Toast.makeText(MainActivity.this, "Bienvenido 🐾", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(MainActivity.this, "Usuario no registrado, por favor regístrate 🐶", Toast.LENGTH_SHORT).show();
                }
            }
        });

        registro = (Button) findViewById(R.id.Registro);
        registro.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent decision = new Intent(MainActivity.this, AntesRegistro.class);
                startActivity(decision);
                Toast.makeText(MainActivity.this, "Redirigiendo a registro...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}

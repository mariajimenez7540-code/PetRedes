package com.example.petredes;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AntesRegistro extends AppCompatActivity {

    ImageButton antesregistro, registroEmpresa, RegistroMascota, Registroveterinario;

    @SuppressLint({"WrongViewCast", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_antes_registro);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        antesregistro = (ImageButton) findViewById(R.id.atrasregistro);
        antesregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent decision = new Intent(AntesRegistro.this, MainActivity.class);
                startActivity(decision);
                Toast.makeText(AntesRegistro.this, "Redirigiendo a incio...", Toast.LENGTH_SHORT).show();
            }
        });

        registroEmpresa = (ImageButton) findViewById(R.id.RegisEmpresa);
        registroEmpresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent decision = new Intent(AntesRegistro.this, MainActivity.class);
                //startActivity(decision);
                Toast.makeText(AntesRegistro.this, "Empresa...", Toast.LENGTH_SHORT).show();
            }
        });

        RegistroMascota = (ImageButton) findViewById(R.id.RegisMascota);
        RegistroMascota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent decision = new Intent(AntesRegistro.this, RegistroDueno.class);
                startActivity(decision);
                Toast.makeText(AntesRegistro.this, "Mascota...", Toast.LENGTH_SHORT).show();
            }
        });

        Registroveterinario = (ImageButton) findViewById(R.id.RegisProfesional);
        Registroveterinario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent decision = new Intent(AntesRegistro.this, MainActivity.class);
                //startActivity(decision);
                Toast.makeText(AntesRegistro.this, "Profesional...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
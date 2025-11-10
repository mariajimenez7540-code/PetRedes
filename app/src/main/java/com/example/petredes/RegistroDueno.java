package com.example.petredes;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;

public class RegistroDueno extends AppCompatActivity {

    private Spinner spinnerAnimales;
    private ImageButton Atras, imagenMascotaBtn;
    private Button registrarBtn;
    private EditText nombreMascota, edadMascota, nombreDueno, apellidosDueno,
            domicilioDueno, codigoDueno, telefonoDueno, fechaNacimiento,
            correoDueno, contrasenaDueno;

    private Uri imagenSeleccionadaUri = null;

    // 📷 Seleccionar imagen desde la galería
    private final ActivityResultLauncher<String> seleccionarImagenLauncher =
            registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
                if (uri != null) {
                    imagenSeleccionadaUri = uri;
                    imagenMascotaBtn.setImageURI(uri);
                    Toast.makeText(this, "📸 Imagen seleccionada correctamente", Toast.LENGTH_SHORT).show();
                }
            });

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registro_dueno);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 🔹 Referencias UI
        Atras = findViewById(R.id.RegistroAtras);
        registrarBtn = findViewById(R.id.RegistrarDueno);
        imagenMascotaBtn = findViewById(R.id.ImagenMascota);

        spinnerAnimales = findViewById(R.id.ListaAnimales);
        nombreMascota = findViewById(R.id.NombreMascota);
        edadMascota = findViewById(R.id.EdadMascota);
        nombreDueno = findViewById(R.id.NombreDueno);
        apellidosDueno = findViewById(R.id.ApellidosDuenos);
        domicilioDueno = findViewById(R.id.DomicilioDueno);
        codigoDueno = findViewById(R.id.CodigoDueno);
        telefonoDueno = findViewById(R.id.TelefonoDueno);
        fechaNacimiento = findViewById(R.id.FechaNacimientoDueno);
        correoDueno = findViewById(R.id.CorreoDueno);
        contrasenaDueno = findViewById(R.id.ContrasenaDueno);

        // Botones
        Atras.setOnClickListener(v -> {
            Intent decision = new Intent(RegistroDueno.this, AntesRegistro.class);
            startActivity(decision);
            Toast.makeText(RegistroDueno.this, "🔙 Redirigiendo a registro...", Toast.LENGTH_SHORT).show();
        });

        imagenMascotaBtn.setOnClickListener(v -> seleccionarImagenLauncher.launch("image/*"));
        registrarBtn.setOnClickListener(v -> validarCampos());

        initSpinner();
        configurarSelectorFecha();
    }

    // 📋 Llenar spinner
    private void initSpinner() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Selecciona un animal");
        lista.add("Perro");
        lista.add("Gato");
        lista.add("Conejo");
        lista.add("Cuyo");
        lista.add("Hámster");
        lista.add("Hurón");
        lista.add("Chinchilla");
        lista.add("Rata");
        lista.add("Ratón");
        lista.add("Perico");
        lista.add("Loro");
        lista.add("Canario");
        lista.add("Cacatúa");
        lista.add("Agaporni");
        lista.add("Ninfa");
        lista.add("Perriquito australiano");
        lista.add("Betta");
        lista.add("Goldfish");
        lista.add("Pez tropical");
        lista.add("Pez marino");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, lista);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAnimales.setAdapter(adapter);
    }

    // 📅 Fecha con validación (mayor de 18 años)
    private void configurarSelectorFecha() {
        fechaNacimiento.setOnClickListener(v -> {
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    RegistroDueno.this,
                    (view, selectedYear, selectedMonth, selectedDay) -> {
                        Calendar fechaSeleccionada = Calendar.getInstance();
                        fechaSeleccionada.set(selectedYear, selectedMonth, selectedDay);

                        Calendar edadMinima = Calendar.getInstance();
                        edadMinima.add(Calendar.YEAR, -18);

                        if (fechaSeleccionada.after(edadMinima)) {
                            Toast.makeText(this, "⚠️ Debes tener al menos 18 años", Toast.LENGTH_SHORT).show();
                            fechaNacimiento.setText("");
                        } else {
                            String fecha = selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear;
                            fechaNacimiento.setText(fecha);
                        }
                    },
                    year, month, day
            );
            datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
            datePickerDialog.show();
        });
    }

    // ✅ Validar campos
    private void validarCampos() {
        String nombreMasc = nombreMascota.getText().toString().trim();
        String edadMasc = edadMascota.getText().toString().trim();
        String nombreDue = nombreDueno.getText().toString().trim();
        String apellidosDue = apellidosDueno.getText().toString().trim();
        String correo = correoDueno.getText().toString().trim();
        String codigo = codigoDueno.getText().toString().trim();
        String telefono = telefonoDueno.getText().toString().trim();

        // 🟡 Validar campos vacíos
        if (spinnerAnimales.getSelectedItemPosition() == 0 ||
                TextUtils.isEmpty(nombreMasc) ||
                TextUtils.isEmpty(edadMasc) ||
                TextUtils.isEmpty(nombreDue) ||
                TextUtils.isEmpty(apellidosDue) ||
                TextUtils.isEmpty(domicilioDueno.getText().toString().trim()) ||
                TextUtils.isEmpty(codigo) ||
                TextUtils.isEmpty(telefono) ||
                TextUtils.isEmpty(fechaNacimiento.getText().toString().trim()) ||
                TextUtils.isEmpty(correo) ||
                TextUtils.isEmpty(contrasenaDueno.getText().toString().trim())) {

            Toast.makeText(this, "⚠️ Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // 🔤 Validar nombres sin números
        if (!soloLetras(nombreMasc)) {
            Toast.makeText(this, "⚠️ El nombre de la mascota solo debe tener letras", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!soloLetras(nombreDue)) {
            Toast.makeText(this, "⚠️ El nombre del dueño solo debe tener letras", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!soloLetras(apellidosDue)) {
            Toast.makeText(this, "⚠️ Los apellidos solo deben tener letras", Toast.LENGTH_SHORT).show();
            return;
        }

        // 🔢 Validar campos numéricos
        if (!esNumero(edadMasc)) {
            Toast.makeText(this, "⚠️ La edad debe contener solo números", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!esNumero(codigo)) {
            Toast.makeText(this, "⚠️ El código postal debe contener solo números", Toast.LENGTH_SHORT).show();
            return;
        }
        if (codigo.length() != 5) {
            Toast.makeText(this, "⚠️ El código postal debe tener 5 dígitos", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!esNumero(telefono)) {
            Toast.makeText(this, "⚠️ El teléfono debe contener solo números", Toast.LENGTH_SHORT).show();
            return;
        }
        if (telefono.length() != 10) {
            Toast.makeText(this, "⚠️ El teléfono debe tener 10 dígitos", Toast.LENGTH_SHORT).show();
            return;
        }

        // 📧 Validar correo electrónico
        if (!Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
            Toast.makeText(this, "⚠️ Ingresa un correo válido (ejemplo@dominio.com)", Toast.LENGTH_SHORT).show();
            return;
        }

        registrarDueno();
    }

    // 🧮 Solo números
    private boolean esNumero(String texto) {
        return texto.matches("\\d+");
    }

    // 🔠 Solo letras (con espacios y tildes)
    private boolean soloLetras(String texto) {
        return texto.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑüÜ\\s]+$");
    }

    // ✅ Registrar dueño y regresar a MainActivity
    private void registrarDueno() {
        String mascota = nombreMascota.getText().toString();
        String tipo = spinnerAnimales.getSelectedItem().toString();
        String dueno = nombreDueno.getText().toString() + " " + apellidosDueno.getText().toString();
        String imagenUri = (imagenSeleccionadaUri != null) ? imagenSeleccionadaUri.toString() : "sin imagen";

        Toast.makeText(this,
                "✅ Registro exitoso:\nMascota: " + mascota +
                        " (" + tipo + ")\nDueño: " + dueno,
                Toast.LENGTH_LONG).show();

        // 🔹 Regresar a MainActivity después de 1.5 segundos
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(RegistroDueno.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        }, 1500);
    }
}
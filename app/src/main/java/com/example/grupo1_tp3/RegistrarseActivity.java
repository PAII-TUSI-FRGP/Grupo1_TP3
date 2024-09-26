package com.example.grupo1_tp3;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.grupo1_tp3.daoSQLite.DaoHelperUsuario;
import com.example.grupo1_tp3.entidad.Usuario;

public class RegistrarseActivity extends AppCompatActivity {
    private EditText nombre;
    private EditText correo;
    private EditText pass;
    private EditText confirmarPass;
    private Usuario usuario = new Usuario();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_registrarse);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nombre = findViewById(R.id.txtNombreRegistro);
        correo = findViewById(R.id.txtCorreoRegistro);
        pass = findViewById(R.id.txtContraseniaRegistro);
        confirmarPass = findViewById(R.id.txtRepetirContrasenia);

        Toolbar toolbar = findViewById(R.id.tb_registar);
        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_action_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void registrarse(View view) {
        if (nombre.getText().toString().isEmpty() || correo.getText().toString().isEmpty() || pass.getText().toString().isEmpty() || confirmarPass.getText().toString().isEmpty()) {
            Toast.makeText(this, "Se deben llenar todos los campos", Toast.LENGTH_SHORT).show();
        } else if (
                !pass.getText().toString().equals(confirmarPass.getText().toString())) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
        } else {
            usuario.setNombre(nombre.getText().toString());
            usuario.setEmail(correo.getText().toString());
            usuario.setPassword(pass.getText().toString());

            if (DaoHelperUsuario.insertar(usuario, this)) {
                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                irLogin();
            } else {
                Toast.makeText(this, "Error al registrarse, intente nuevamente porfavor", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void limpiarCampos() {
        nombre.setText("");
        correo.setText("");
        pass.setText("");
        confirmarPass.setText("");
    }

    private void irLogin() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
package com.example.grupo1_tp3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import entidad.Usuario;
import negocio.IUsuarioNeg;
import negocioImpl.UsuarioNegImpl;

public class MainActivity extends AppCompatActivity {
    private EditText et_nombre;
    private EditText et_password;
    private Button btn_ingresar;
    private TextView tv_registar;
    private UsuarioNegImpl usuNegImpl = new UsuarioNegImpl();

    public static final String SHARED_PREFS_LOGIN_DATA = "sharedPrefsLoginData";
    public static final String NOMBRE_USUARIO = "nombreUsuario";
    public static final String EMAIL_USUARIO = "emailUsuario";
    public static final String PASSWORD_USUARIO = "passwordUsuario";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        et_nombre = findViewById(R.id.et_nombre);
        et_password = findViewById(R.id.et_password);
        btn_ingresar = findViewById(R.id.btn_ingresar);
        tv_registar = findViewById(R.id.tv_registar);
    }

    public void iniciarSesion(View view) {
        String nombre = et_nombre.getText().toString();
        String password = et_password.getText().toString();

        if(nombre.isEmpty()) {
            Toast.makeText(this, "Debe ingresar nombre ", Toast.LENGTH_SHORT).show();
            return;
        }
        if(password.isEmpty()) {
            Toast.makeText(this, "Debe ingresar contraseña ", Toast.LENGTH_SHORT).show();
            return;
        }

        if (usuNegImpl.obtenerUno(nombre, this).getNombre() != null) {
            Toast.makeText(this, "Bienvenido " + nombre, Toast.LENGTH_SHORT).show();

            SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_LOGIN_DATA, MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(NOMBRE_USUARIO, nombre);
            editor.putString(EMAIL_USUARIO, usuNegImpl.obtenerUno(nombre, this).getEmail());
            editor.putString(PASSWORD_USUARIO, usuNegImpl.obtenerUno(nombre, this).getPassword());
            editor.commit();

            Intent intent = new Intent(this, HomeActivity.class);
            startActivity(intent);
        } else {
            Toast.makeText(this, "Usuario no encontrado, intente de nuevo porfavor", Toast.LENGTH_SHORT).show();
        }
    }
}
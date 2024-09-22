package com.example.grupo1_tp3;

import static com.example.grupo1_tp3.MainActivity.EMAIL_USUARIO;
import static com.example.grupo1_tp3.MainActivity.NOMBRE_USUARIO;
import static com.example.grupo1_tp3.MainActivity.SHARED_PREFS_LOGIN_DATA;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.grupo1_tp3.databinding.ActivityHomeBinding;

public class HomeActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private ActivityHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setSupportActionBar(binding.appBarHome.toolbar);
        binding.appBarHome.btnExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.btnExit).show();
            }
        });

        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;

        View headerView = binding.navView.getHeaderView(0);
        TextView nombreTextView = headerView.findViewById(R.id.nav_header_nombre_usuario);
        TextView emailTextView = headerView.findViewById(R.id.nav_header_email_usuario);

        // Recuperar los datos de SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_LOGIN_DATA, MODE_PRIVATE);
        String nombreUsuario = sharedPreferences.getString(NOMBRE_USUARIO, "Nombre no disponible");
        String emailUsuario = sharedPreferences.getString(EMAIL_USUARIO, "Email no disponible");
        nombreTextView.setText(nombreUsuario);
        emailTextView.setText(emailUsuario);

        // Configuración de los destinos principales del menú de navegación
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_parqueos, R.id.nav_miCuenta, R.id.nav_CerrarSesion)  // Incluimos el id de cerrar sesión
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_CerrarSesion) {
                // Limpiar los datos de SharedPreferences
          //      SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS_LOGIN_DATA, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();  // Limpia todos los datos de sesión
                editor.apply();  // Aplica los cambios

                // Redirigir al MainActivity (pantalla de login)
                Intent intent = new Intent(HomeActivity.this, MainActivity.class);
                startActivity(intent);

                // Finaliza la actividad actual para evitar que el usuario pueda volver atrás
                finish();
                return true;
            }
            return false;
        });;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_home);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
}
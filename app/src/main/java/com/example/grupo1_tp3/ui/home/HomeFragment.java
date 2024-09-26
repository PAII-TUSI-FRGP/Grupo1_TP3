package com.example.grupo1_tp3.ui.home;

import static com.example.grupo1_tp3.MainActivity.EMAIL_USUARIO;
import static com.example.grupo1_tp3.MainActivity.NOMBRE_USUARIO;
import static com.example.grupo1_tp3.MainActivity.PASSWORD_USUARIO;
import static com.example.grupo1_tp3.MainActivity.SHARED_PREFS_LOGIN_DATA;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.grupo1_tp3.R;
import com.example.grupo1_tp3.daoSQLite.DaoHelperParkeo;
import com.example.grupo1_tp3.databinding.FragmentHomeBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

import com.example.grupo1_tp3.entidad.Parkeo;
import com.example.grupo1_tp3.entidad.Usuario;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private GridView gridView;
    private ArrayList<String> parkingsList;
    private ArrayAdapter<String> adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        gridView = view.findViewById(R.id.gridView);
        parkingsList = new ArrayList<>();

        loadParkingData();

        FloatingActionButton fabRegisterParking = view.findViewById(R.id.fabRegisterParking);
        fabRegisterParking.setOnClickListener(v -> openRegisterParkingDialog());  // Abrir el diálogo al hacer clic
        // Configurar el clic en los elementos del GridView
        gridView.setOnItemClickListener((parent, view1, position, id) -> openDeleteParkingDialog(position));  // Abrir el diálogo para eliminar

        return view;
    }

    private void loadParkingData() {
        List<Parkeo> parqueos = DaoHelperParkeo.obtenerPorNombre(getContext().getSharedPreferences(SHARED_PREFS_LOGIN_DATA, Context.MODE_PRIVATE).getString(NOMBRE_USUARIO, ""), getContext());

        parkingsList.clear();

        // Recorrer la lista de parqueos obtenida
        for (Parkeo parqueo : parqueos) {
            parkingsList.add(parqueo.getMatricula());
            parkingsList.add(parqueo.getTiempo());
        }

        // Actualizar el GridView con la nueva lista de parqueos
        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, parkingsList);
        gridView.setAdapter(adapter);
    }

    private void openRegisterParkingDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_register_parking, null);
        builder.setView(dialogView);

        EditText etPlate = dialogView.findViewById(R.id.etPlate);
        EditText etTime = dialogView.findViewById(R.id.etTime);
        Button btnRegister = dialogView.findViewById(R.id.btnRegister);

        AlertDialog dialog = builder.create();

        Context context = requireContext();

        btnRegister.setOnClickListener(view -> {
            Parkeo OParkeos = new Parkeo();
            OParkeos.setMatricula(etPlate.getText().toString());
            OParkeos.setTiempo(etTime.getText().toString());

            // Recuperar los datos de SharedPreferences
            SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREFS_LOGIN_DATA, Context.MODE_PRIVATE);
            Usuario OUsuario = new Usuario();
            OUsuario.setNombre(sharedPreferences.getString(NOMBRE_USUARIO, "Nombre no disponible"));
            OUsuario.setEmail(sharedPreferences.getString(EMAIL_USUARIO, "Email no disponible"));
            OUsuario.setPassword(sharedPreferences.getString(PASSWORD_USUARIO, "Email no disponible"));
            Log.d("NOMBRE", OUsuario.getNombre());
            OParkeos.setNombre_Par(OUsuario);

            // Aquí insertarías el nuevo parqueo y luego recargarías los datos
            if (!DaoHelperParkeo.insertar(OParkeos, getContext()))
                Toast.makeText(context, "Parqueo existente", Toast.LENGTH_SHORT).show();
            loadParkingData();  // Recargar los datos después de insertar
            dialog.dismiss();

        });

        dialog.show();
    }

    private void openDeleteParkingDialog(int position) {
        // Obtener matrícula y tiempo seleccionados del GridView
        String matricula = parkingsList.get(position);
        String tiempo = parkingsList.get(position + 1);  // Dado que los datos están en pares (matrícula, tiempo)

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Eliminar Parqueo")
                .setMessage("¿Estás seguro de que deseas eliminar el parqueo de " + matricula + "?")
                .setPositiveButton("Eliminar", (dialog, which) -> {
                    // Eliminar el parqueo de la base de datos usando la matrícula
                    eliminarParqueo(matricula);

                    // Recargar los datos después de la eliminación
                    loadParkingData();
                    Toast.makeText(getActivity(), "Parqueo eliminado", Toast.LENGTH_SHORT).show();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.dismiss())  // Cerrar el diálogo sin hacer nada
                .create()
                .show();
    }

    // Método para eliminar el parqueo de la base de datos
    private void eliminarParqueo(String matricula) {
        DaoHelperParkeo.eliminar(matricula, getContext());
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
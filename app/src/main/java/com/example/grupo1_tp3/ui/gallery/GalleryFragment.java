package com.example.grupo1_tp3.ui.gallery;

import static com.example.grupo1_tp3.MainActivity.EMAIL_USUARIO;
import static com.example.grupo1_tp3.MainActivity.NOMBRE_USUARIO;
import static com.example.grupo1_tp3.MainActivity.SHARED_PREFS_LOGIN_DATA;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.grupo1_tp3.databinding.FragmentGalleryBinding;

public class GalleryFragment extends Fragment {

    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);


        // Recuperar los datos de SharedPreferences
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS_LOGIN_DATA, Context.MODE_PRIVATE);
        String nombreUsuario = sharedPreferences.getString(NOMBRE_USUARIO, "Nombre no disponible");
        String emailUsuario = sharedPreferences.getString(EMAIL_USUARIO, "Email no disponible");

        // Llamar al método para establecer los valores en el ViewModel
        galleryViewModel.setUserInfo(nombreUsuario, emailUsuario);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textGallery;
        galleryViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

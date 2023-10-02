package com.example.lab4_20192270;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.example.lab4_20192270.databinding.ActivityAppBinding;

public class AppActivity extends AppCompatActivity {
    ActivityAppBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        VistaFramgnetoViewModel vistaFramgnetoViewModel = new ViewModelProvider(AppActivity.this).get(VistaFramgnetoViewModel.class);

        binding.buttonMagnetometro.setOnClickListener(view -> {
            vistaFramgnetoViewModel.getFragmentoActual().setValue("Magnetómetro");
            Log.d("Cambio", "CAMBIO DE VISTA");
        });
    }
}
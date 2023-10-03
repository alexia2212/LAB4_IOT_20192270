package com.example.lab4_20192270.Acelerometro;

import android.hardware.SensorEventListener;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lab4_20192270.R;
import com.example.lab4_20192270.VistaFramgnetoViewModel;
import com.example.lab4_20192270.databinding.FragmentAcelerometroBinding;

import java.text.DecimalFormat;

public class AcelerometroFragment extends Fragment {

    FragmentAcelerometroBinding binding;
    private VistaFramgnetoViewModel vistaFramgnetoViewModel;
    private ListaAcelearoAdapter listaAcelearoAdapter;
    private AcelerometroFragment acelerometroViewModel;

    DecimalFormat decifmalf = new DecimalFormat('#.##');

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        vistaFramgnetoViewModel = new ViewModelProvider(requireActivity()).get(AcelerometroFragment.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentAcelerometroBinding.inflate(getLayoutInflater(), container, false);

        NavController navController = NavHostFragment.findNavController(AcelerometroFragment.this);
        VistaFramgnetoViewModel vistaFramgnetoViewModel = new ViewModelProvider(requireActivity()).get(VistaFramgnetoViewModel.class);
        vistaFramgnetoViewModel.getFragmentoActual().observe(this, vista -> {
            Log.d("Cambio", vista);
            if(vista.equals("Magnetómetro")){
                navController.navigate(R.id.action_acelerometroFragment_to_magnetometroFragment);
            }else {
                Toast.makeText(getContext(), "Mismo elemento",Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();

    }

}
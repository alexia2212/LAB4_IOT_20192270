package com.example.lab4_20192270.Magnetometro;

import static android.content.Context.SENSOR_SERVICE;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab4_20192270.DTO.Service;
import com.example.lab4_20192270.VistaFramgnetoViewModel;
import com.example.lab4_20192270.databinding.FragmentMagnetometroBinding;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MagnetometroFragment extends Fragment implements SensorEventListener {

    FragmentMagnetometroBinding binding;
    private VistaFramgnetoViewModel vistaVM;
    private ListaMagneAdapter listaMagneAdapter;
    private PersonasMagneViewModel personasMagneViewModel;
    private Sensor accelerometer;
    private Sensor magnetometer;
    private float[] accelerometerReading = new float[3];
    private float[] magnetometerReading = new float[3];
    private float[] rotationMatrix = new float[9];
    private float[] orientationAngles = new float[3];

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        vistaVM = new ViewModelProvider(requireActivity()).get(VistaFramgnetoViewModel.class);
        personasMagneViewModel = new ViewModelProvider(requireActivity()).get(PersonasMagneViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        binding = FragmentMagnetometroBinding.inflate(inflater, container, false);
        SensorManager sensorManager = (SensorManager) requireActivity().getSystemService(SENSOR_SERVICE);

        if (sensorManager != null){
            accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            magnetometer = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

            if (accelerometer != null && magnetometer != null) {
                sensorManager.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(this, magnetometer, SensorManager.SENSOR_DELAY_NORMAL);
            } else {
                Log.d("mgs-test", "Sensores no disponibles");
            }
        }

        NavController navController = NavHostFragment.findNavController(MagnetometroFragment.this);
        personasMagneViewModel.getListaPersonasMagne().observe(this,lista->{

            //listaMagneAdapter = new ListaMagnetAdapter();
            //listaMagneAdapter.setContext(getContext());
            //listaMagneAdapter.setListaMagnet(lista);
            //listaMagneAdapter.setPersonasMagnetometroVM(personasMagnetometroVM);
            //binding.recyclerMagnet.setAdapter(listaMagnetAdapter);
            //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            //binding.recyclerMagnet.setLayoutManager(linearLayoutManager);

        });

        return binding.getRoot();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
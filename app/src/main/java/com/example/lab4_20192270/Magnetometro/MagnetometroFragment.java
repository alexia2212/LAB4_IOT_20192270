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

            listaMagneAdapter = new ListaMagneAdapter();
            listaMagneAdapter.setContext(getContext());
            listaMagneAdapter.setListaMagnet(lista);
            listaMagneAdapter.setPersonasMagneViewModel(personasMagneViewModel);
            binding.recyclerMagnet.setAdapter(listaMagneAdapter);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            binding.recyclerMagnet.setLayoutManager(linearLayoutManager);

        });

        return binding.getRoot();
    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            System.arraycopy(sensorEvent.values, 0, accelerometerReading, 0, accelerometerReading.length);
        } else if (sensorEvent.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            System.arraycopy(sensorEvent.values, 0, magnetometerReading, 0, magnetometerReading.length);
        }
        SensorManager.getRotationMatrix(rotationMatrix, null, accelerometerReading, magnetometerReading);
        SensorManager.getOrientation(rotationMatrix, orientationAngles);
        float azimuthInDegress = Math.abs((float) Math.toDegrees(orientationAngles[0]));
        float startAngle = 0.0f;
        float endAngle = 180.0f;
        float opacity = 1.0f;
        if (azimuthInDegress >= startAngle && azimuthInDegress <= endAngle) {
            float range = endAngle - startAngle;
            float adjustedAngle = azimuthInDegress - startAngle;
            opacity = 1.0f - (adjustedAngle / range);
        }
        binding.recyclerMagnet.setAlpha(opacity);
    }
    @Override
    public void onStop(){
        super.onStop();
        SensorManager sensorManager = (SensorManager) requireActivity().getSystemService(SENSOR_SERVICE);
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}
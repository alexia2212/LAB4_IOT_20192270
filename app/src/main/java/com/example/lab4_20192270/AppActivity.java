package com.example.lab4_20192270;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;

import com.example.lab4_20192270.Acelerometro.PersonasAcelerometroVM;
import com.example.lab4_20192270.DTO.Result;
import com.example.lab4_20192270.DTO.Service;
import com.example.lab4_20192270.DTO.User;
import com.example.lab4_20192270.Magnetometro.PersonasMagneViewModel;
import com.example.lab4_20192270.databinding.ActivityAppBinding;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppActivity extends AppCompatActivity {
    ActivityAppBinding binding;
    String textAcelerometro = "Ir al Acelerómetro";
    String textMagnetometro = "Ir al Magnetómetro";
    Service servicio= new Retrofit.Builder()
            .baseUrl("https://randomuser.me")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Service.class);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        VistaFramgnetoViewModel vistaFramgnetoViewModel = new ViewModelProvider(AppActivity.this).get(VistaFramgnetoViewModel.class);

        PersonasMagneViewModel personasMagneViewModel = new ViewModelProvider(AppActivity.this).get(PersonasMagneViewModel.class);
        personasMagneViewModel.getListaPersonasMagne().postValue(new ArrayList<>());

        PersonasAcelerometroVM personasAcelerometroVM = new ViewModelProvider(AppActivity.this).get(PersonasAcelerometroVM.class);
        personasAcelerometroVM.getListaPersonasAcelero().postValue(new ArrayList<>());

        vistaFramgnetoViewModel.getVistaActual().postValue("Inicio");

        binding.buttonMagnetometro.setOnClickListener(view -> {
            if(binding.buttonMagnetometro.getText().toString().equals(textAcelerometro)){
                vistaFramgnetoViewModel.getVistaActual().postValue("Acelerómetro");
                binding.buttonMagnetometro.setText(textMagnetometro);
            }else{
                vistaFramgnetoViewModel.getVistaActual().postValue("Magnetómetro");
                binding.buttonMagnetometro.setText(textAcelerometro);
            }
        });

        binding.imageView.setOnClickListener(view -> {
            Log.d("msg-test-nombre"," " + binding.buttonMagnetometro.getText().toString());
            if (binding.buttonMagnetometro.getText().toString().equals(""+textMagnetometro)){
                mostrarAlertaAcelerometro();
            }else{
                mostrarAlertaMagnetometro();
            }
        });

        binding.buttonAnadir.setOnClickListener(view -> {
            binding.buttonAnadir.setEnabled(false);
            binding.buttonMagnetometro.setEnabled(false);
            if (binding.buttonMagnetometro.getText().toString().equals(textMagnetometro)){
                servicio.random().enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()){
                            ArrayList<User> listaUsuariosAcelerómetro = personasAcelerometroVM.getListaPersonasAcelero().getValue();
                            User persona = response.body().getResults().get(0);
                            listaUsuariosAcelerómetro.add(persona);
                            personasAcelerometroVM.getListaPersonasAcelero().postValue(listaUsuariosAcelerómetro);
                            binding.buttonAnadir.setEnabled(true);
                            binding.buttonMagnetometro.setEnabled(true);
                        }
                    }
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                    }
                });

            }else{
                servicio.random().enqueue(new Callback<Result>() {
                    @Override
                    public void onResponse(Call<Result> call, Response<Result> response) {
                        if (response.isSuccessful()){
                            ArrayList<User> listaUsuariosMagnetómetro = personasMagneViewModel.getListaPersonasMagne().getValue();
                            User persona = response.body().getResults().get(0);
                            listaUsuariosMagnetómetro.add(persona);
                            personasMagneViewModel.getListaPersonasMagne().postValue(listaUsuariosMagnetómetro);
                            binding.buttonAnadir.setEnabled(true);
                            binding.buttonMagnetometro.setEnabled(true);
                        }
                    }
                    @Override
                    public void onFailure(Call<Result> call, Throwable t) {
                    }
                });


            }
        });

    }
    public void mostrarAlertaMagnetometro(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Detalles - Magnetómetro");
        alertDialog.setMessage("Haga CLICK en 'Añadir' para agregar contactos a su lista."+
                " Esta aplicación está utilizando el MAGNETÓMETRO de su dispositivo.\n\n"+
                "De esta forma, la lista se mostrará el 100% cuando se apunte al NORTE. "+
                "Caso contrario, se desvanecerá...");
        alertDialog.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("msgAlerta", "Positivo");
                    }
                });
        alertDialog.show();
    }
    public void mostrarAlertaAcelerometro(){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("Detalles - Acelerómetro");
        alertDialog.setMessage("Haga CLICK en 'Añadir' para agregar contactos a su lista."+
                " Esta aplicación está utilizando el ACELERÓMETRO de su dispositivo.\n\n"+
                "De esta forma, la lista hará scroll hacia abajo, cuando agite su dispositivo.");
        alertDialog.setPositiveButton("Aceptar",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("msgAlerta", "Positivo");
                    }
                });
        alertDialog.show();
    }
}
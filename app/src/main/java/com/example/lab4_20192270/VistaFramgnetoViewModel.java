package com.example.lab4_20192270;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


public class VistaFramgnetoViewModel extends ViewModel {
    private final MutableLiveData<String> vistaActual = new MutableLiveData<>();

    public MutableLiveData<String> getVistaActual(){
        return vistaActual;
    }
}

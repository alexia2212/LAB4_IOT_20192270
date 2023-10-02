package com.example.lab4_20192270;

import android.view.View;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab4_20192270.DTO.User;

import java.util.ArrayList;

public class VistaFramgnetoViewModel extends ViewModel {
    private MutableLiveData<String> fragmentoActual = new MutableLiveData<>();

    public MutableLiveData<String> getFragmentoActual() {
        return fragmentoActual;
    }

    public void setFragmentoActual(MutableLiveData<String> fragmentoActual) {
        this.fragmentoActual = fragmentoActual;
    }
}

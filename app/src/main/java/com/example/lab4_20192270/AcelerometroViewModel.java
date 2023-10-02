package com.example.lab4_20192270;

import android.os.Bundle;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab4_20192270.DTO.User;

import java.util.ArrayList;

public class AcelerometroViewModel extends ViewModel {
    private MutableLiveData<ArrayList<User>> listaPersona = new MutableLiveData<>();

    public MutableLiveData<ArrayList<User>> getListaPersona() {
        return listaPersona;
    }

    public void setListaPersona(MutableLiveData<ArrayList<User>> listaPersona) {
        this.listaPersona = listaPersona;
    }
}

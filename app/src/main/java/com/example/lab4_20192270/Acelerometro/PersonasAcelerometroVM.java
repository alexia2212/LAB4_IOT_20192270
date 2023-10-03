package com.example.lab4_20192270.Acelerometro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab4_20192270.DTO.User;

import java.util.ArrayList;

public class PersonasAcelerometroVM extends ViewModel {
    private final MutableLiveData<ArrayList<User>> listaPersonasAcelero = new MutableLiveData<ArrayList<User>>();

    public MutableLiveData<ArrayList<User>> getListaPersonasAcelero(){
        return listaPersonasAcelero;
    }
}

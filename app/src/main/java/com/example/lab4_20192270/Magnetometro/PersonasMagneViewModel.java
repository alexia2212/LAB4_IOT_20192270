package com.example.lab4_20192270.Magnetometro;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.lab4_20192270.DTO.User;

import java.util.ArrayList;

public class PersonasMagneViewModel extends ViewModel {
    private final MutableLiveData<ArrayList<User>> listaPersonasMagne=new MutableLiveData<ArrayList<User>>();
    public MutableLiveData<ArrayList<User>> getListaPersonasMagne() {
        return listaPersonasMagne;
    }
}

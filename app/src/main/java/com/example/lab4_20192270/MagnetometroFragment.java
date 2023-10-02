package com.example.lab4_20192270;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lab4_20192270.databinding.FragmentMagnetometroBinding;
import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MagnetometroFragment extends Fragment {

    private FragmentMagnetometroBinding binding;
    Service service = new Retrofit.Builder()
            .baseUrl("https://randomuser.me").
            addConverterFactory(GsonConverterFactory.create()).build().create(Service.class);

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMagnetometroBinding.inflate(getLayoutInflater());

        return binding.getRoot();
    }

}
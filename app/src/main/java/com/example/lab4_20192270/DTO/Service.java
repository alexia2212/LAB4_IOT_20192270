package com.example.lab4_20192270.DTO;

import com.example.lab4_20192270.DTO.Result;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET("/api/")
    Call<Result> random();
}

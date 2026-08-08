package com.gustavo.characterlist.network;

import java.util.logging.Logger;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static final Retrofit retrofit = new Retrofit.Builder().baseUrl("https://rickandmortyapi.com").addConverterFactory(GsonConverterFactory.create()).build();

    public static Retrofit getInstance() {
        return retrofit;
    }
}

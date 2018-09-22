package com.daffaalam.biomaker.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ClientAPI {

    private static final String BASE_URL = "https://duntekom.tech/user/00/biomaker/";

    public RestAPI getEndPoint() {
        return new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build().create(RestAPI.class);
    }
}

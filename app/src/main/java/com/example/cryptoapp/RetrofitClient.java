package com.example.cryptoapp;

import  retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL="https://api.coinpaprika.com/";
//    private static final String BASE_URL="https://api.coinpaprika.com/v1/coins/";
    private static Retrofit retrofit=null;

    public static ApiInterface getRetrofitClient(){

        if (retrofit==null){
            retrofit=new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit.create(ApiInterface.class);
    }

}

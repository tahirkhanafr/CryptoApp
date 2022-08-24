package com.example.cryptoapp;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface
{
    @GET("/v1/coins/")
//    @GET("/btc-bitcoin")
    Call<List<Coins>> getCoins();

}

package com.example.cryptoapp;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity implements CoinAdapter.OnNoteListener {
    Button showjson;
    TextView tvshow;
    EditText edCoin;
    RecyclerView recyclerView;
    List<Coins> coinsLis;
    CoinAdapter adapter;
    LinearLayoutManager linearLayoutManager;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycle);
        progressBar=findViewById(R.id.progressBar);
        coinsLis=new ArrayList<>();
        linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter=new CoinAdapter(coinsLis, this);
        recyclerView.setAdapter(adapter);

        requestToRetrofit();

//        extractcoin();
//        showjson.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                callVolley();
//            }
//        });



    }

    private void requestToRetrofit() {
        progressBar.setVisibility(View.VISIBLE);
        RetrofitClient.getRetrofitClient().getCoins().enqueue(new Callback<List<Coins>>() {
            @Override
            public void onResponse(@NonNull Call<List<Coins>> call, @NonNull retrofit2.Response<List<Coins>> response) {
//                Toast.makeText(MainActivity.this, "Response"+ response, Toast.LENGTH_SHORT).show();
//                System.out.println("Response.."+ response);
                if (response.isSuccessful() && response.body()!=null){

                    coinsLis.addAll(response.body());
                    adapter.notifyDataSetChanged();
                    progressBar.setVisibility(View.GONE);

                }
            }

            @Override
            public void onFailure(Call<List<Coins>> call, Throwable t) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(MainActivity.this, "Error"+ t.getMessage(), Toast.LENGTH_LONG).show();
                System.out.println("Error..."+ t);
            }
        });

    }


    private void callVolley() {
        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        String url = "https://api.coinpaprika.com/v1/coins/btc-bitcoin";
//        String url="https://api.coinpaprika.com/v1/coins";
        String coinName=edCoin.getText().toString();
        String url="https://api.coinpaprika.com/v1/coins/"+coinName ;

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        tvshow.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(),Toast.LENGTH_SHORT).show();
                        System.out.println("Error Response"+ error);

                    }
                }
        );
        requestQueue.add(stringRequest);
    }

//    private void extractcoin() {
//        RequestQueue requestQueue= Volley.newRequestQueue(this);
////        String url="https://api.coinpaprika.com/v1/coins/";
//        String url="https://api.coinpaprika.com/v1/coins/btc-bitcoin";
//
//        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(Request.Method.GET,
//                url,null,
//                new Response.Listener<JSONArray>() {
//                    @Override
//                    public void onResponse(JSONArray response) {
//                        System.out.println("Responce is positive "+ response);
//                        for (int i=0; i<response.length(); i++){
//                            try {
//                                JSONObject coinid=response.getJSONObject(i);
//
//                                Coins cons=new Coins();
//                                 cons.setId(coinid.getString("id"));
//
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                            }
//                        }
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                        adapter=new Adapter(getApplicationContext(),coins);
//                        recyclerView.setAdapter(adapter);
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        System.out.println("OnErrorResponse.."+ error);
//                    }
//                });
//        requestQueue.add(jsonArrayRequest);
//    }

    @Override
    public void onNoteCLick(int position) {
        coinsLis.get(position);
        Intent intent=new Intent(MainActivity.this,SingleCoinDetail.class);
        intent.putExtra("id",coinsLis.get(position).getId());
        intent.putExtra("rank",coinsLis.get(position).getRank());
        intent.putExtra("name",coinsLis.get(position).getName());
        intent.putExtra("symbol",coinsLis.get(position).getSymbol());
        startActivity(intent);
    }
}
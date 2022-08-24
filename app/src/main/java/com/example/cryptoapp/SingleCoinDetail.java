package com.example.cryptoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SingleCoinDetail extends AppCompatActivity {

    TextView coinName, tvDesc;
    TextView tvRANK, tvSYMBOL,tvNAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_coin_detail);

        String coinID=getIntent().getStringExtra("id");

        coinName=findViewById(R.id.TvName);
        tvDesc=findViewById(R.id.Desc);
        tvRANK=findViewById(R.id.TVRANK);
        tvSYMBOL=findViewById(R.id.TVSYMBOL);
        tvNAME=findViewById(R.id.TVNAME);


        coinName.setText(coinID);


        RequestQueue requestQueue= Volley.newRequestQueue(this);
//        String url = "https://api.coinpaprika.com/v1/coins/btc-bitcoin";
        String getcoinID=coinName.getText().toString();
        String url="https://api.coinpaprika.com/v1/coins/"+getcoinID ;

        StringRequest stringRequest=new StringRequest(Request.Method.GET,
                url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject=new JSONObject(response);
//                            JSONObject des=jsonObject.getJSONObject("description");
                            String dess=jsonObject.getString("description");
                            String name=jsonObject.getString("name");
                            String rank=jsonObject.getString("rank");
                            String symbol=jsonObject.getString("symbol");


                            coinName.setText(name);
                            tvDesc.setText(dess);
                            tvRANK.setText(rank);
                            tvSYMBOL.setText(symbol);
                            tvNAME.setText(name);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(SingleCoinDetail.this, error.toString(),Toast.LENGTH_SHORT).show();
                        System.out.println("Error Response"+ error);

                    }
                }
        );
        requestQueue.add(stringRequest);
    }

}
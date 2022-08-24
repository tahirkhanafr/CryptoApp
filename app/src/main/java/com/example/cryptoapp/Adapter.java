package com.example.cryptoapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

//public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder > {
//
//
//LayoutInflater layoutInflater;
//List<Coins> coins;
//
//public Adapter (Context ctx, List<Coins> coins){
//    this.layoutInflater= LayoutInflater.from(ctx);
//    this.coins=coins;
//}
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//    View view=layoutInflater.inflate(R.layout.item_list,parent,false);
//
//        return new ViewHolder(view);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//    holder.coinid.setText(coins.get(position).getId());
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return coins.size();
//    }
//
//    public static class ViewHolder extends RecyclerView.ViewHolder{
//    TextView coinid;
//
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            coinid=itemView.findViewById(R.id.textView);
//
//        }
//    }
//}

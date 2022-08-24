package com.example.cryptoapp;

import android.content.pm.LabeledIntent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CoinAdapter extends RecyclerView.Adapter<CoinAdapter.ViewHolder> {

    private List<Coins> coinsList;
    private OnNoteListener mOnNoteListener;

    public CoinAdapter(List<Coins> coinsList, OnNoteListener onNoteListener) {
        this.coinsList = coinsList;
        this.mOnNoteListener=onNoteListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list,parent,false);

        return new ViewHolder(view,mOnNoteListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvid.setText(coinsList.get(position).getId());
//        holder.tvname.setText(coinsList.get(position).getName());
        holder.tvsymbol.setText(coinsList.get(position).getSymbol());
        holder.tvrank.setText(String.valueOf(coinsList.get(position).getRank()));

    }

    @Override
    public int getItemCount() {
        return coinsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView tvid, tvname, tvsymbol;
        TextView tvrank;
        OnNoteListener onNoteListener;
        public ViewHolder(@NonNull View itemView, OnNoteListener onNoteListener) {
            super(itemView);
            tvid=itemView.findViewById(R.id.tvid);
//            tvname=itemView.findViewById(R.id.tvname);
            tvsymbol=itemView.findViewById(R.id.tvsymbol);
            tvrank=itemView.findViewById(R.id.tvrank);

            this.onNoteListener=onNoteListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            onNoteListener.onNoteCLick(getAdapterPosition());

        }
    }
    public interface  OnNoteListener{
        void onNoteCLick(int position);
    }
}

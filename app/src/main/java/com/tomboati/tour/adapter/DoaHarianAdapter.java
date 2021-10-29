package com.tomboati.tour.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.model.DoaModel;
import com.tomboati.tour.view.activity.doa_dzikir.DetailDoaSehariHariActivity;

import java.util.List;

public class DoaHarianAdapter extends RecyclerView.Adapter<DoaHarianAdapter.ViewHolder>{


    private final List<DoaModel> response;

    public DoaHarianAdapter(List<DoaModel> response) {
        this.response = response;
    }

    @NonNull
    @Override
    public DoaHarianAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoaHarianAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doa_doa, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoaHarianAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nama_doa.setText(response.get(position).getJudul().toUpperCase());
        holder.cardViewListDoaHarian.setOnClickListener(v -> {
            Intent myIntent = new Intent(v.getContext(), DetailDoaSehariHariActivity.class);
            myIntent.putExtra("OBJECT", response.get(position));
            v.getContext().startActivity(myIntent);
        });
    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final CardView cardViewListDoaHarian;
        private final TextView nama_doa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewListDoaHarian = itemView.findViewById(R.id.cardViewListDoaHarian);
            nama_doa = itemView.findViewById(R.id.nama_doa);
        }
    }
}

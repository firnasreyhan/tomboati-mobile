package com.tomboati.tour.adapter;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.model.DoaHajiUmrahModel;
import com.tomboati.tour.model.DoaModel;
import com.tomboati.tour.view.activity.doa_dzikir.DetailAnekaSholawatActivity;
import com.tomboati.tour.view.activity.doa_dzikir.DetailDoaSehariHariActivity;

import java.util.List;

public class DoaHajiUmrahAdapter extends RecyclerView.Adapter<DoaHajiUmrahAdapter.ViewHolder>{


    private final List<DoaModel> response;
    private boolean is_sholawat = false;

    public DoaHajiUmrahAdapter(List<DoaModel> response) {
        this.response = response;
    }

    public DoaHajiUmrahAdapter(List<DoaModel> response, boolean is_sholawat) {
        this.response = response;
        this.is_sholawat = is_sholawat;
    }

    @NonNull
    @Override
    public DoaHajiUmrahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doa_doa, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoaHajiUmrahAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.nama_doa.setText(response.get(position).getJudul());
        if (is_sholawat) {
            holder.img_items.setImageResource(R.drawable.ic_mosque);
        }
        holder.cardViewListDoaHarian.setOnClickListener(v -> {
            Intent myIntent = new Intent(v.getContext(), is_sholawat ? DetailAnekaSholawatActivity.class : DetailDoaSehariHariActivity.class);
            myIntent.putExtra("OBJECT", response.get(position));
            v.getContext().startActivity(myIntent);
        });
    }

    @Override
    public int getItemCount() {
        return response.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final CardView cardViewListDoaHarian;
        private final TextView nama_doa;
        private final ImageView img_items;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewListDoaHarian = itemView.findViewById(R.id.cardViewListDoaHarian);
            nama_doa = itemView.findViewById(R.id.nama_doa);
            img_items = itemView.findViewById(R.id.img_items);
        }
    }
}

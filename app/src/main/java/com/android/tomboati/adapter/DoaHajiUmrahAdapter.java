package com.android.tomboati.adapter;
import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.DoaHajiUmrahModel;
import com.android.tomboati.view.activity.DetailAnekaSholawatActivity;
import com.android.tomboati.view.activity.DetailDoaSehariHariActivity;

import java.util.List;

public class DoaHajiUmrahAdapter extends RecyclerView.Adapter<DoaHajiUmrahAdapter.ViewHolder>{


    private final List<DoaHajiUmrahModel> response;
    private boolean is_sholawat = false;

    public DoaHajiUmrahAdapter(List<DoaHajiUmrahModel> response) {
        this.response = response;
    }

    public DoaHajiUmrahAdapter(List<DoaHajiUmrahModel> response, boolean is_sholawat) {
        this.response = response;
        this.is_sholawat = is_sholawat;
    }



    @NonNull
    @Override
    public DoaHajiUmrahAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doa_sehari_hari, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DoaHajiUmrahAdapter.ViewHolder holder, int position) {
//        holder.tv_no.setText("" + (position + 1));
        holder.nama_doa.setText(response.get(position).getTitle());
        if (is_sholawat) {
            holder.img_items.setImageResource(R.drawable.ic_mosque);
        }
        holder.cardViewListDoaHarian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), is_sholawat ? DetailAnekaSholawatActivity.class : DetailDoaSehariHariActivity.class);
                myIntent.putExtra("TITLE", response.get(position).getTitle());
                myIntent.putExtra("ARABIC", response.get(position).getArabic());
                myIntent.putExtra("TRANSLATE", response.get(position).getLatin());
                myIntent.putExtra("ARTI", response.get(position).getTranslation());
                if(!response.get(position).getKeterangan().equals("")) {
                    myIntent.putExtra("IS_KETERANGAN_ACTIVE", true);
                    myIntent.putExtra("KETERANGAN", response.get(position).getKeterangan());
                }
                v.getContext().startActivity(myIntent);
            }
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
//        private final TextView tv_no;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewListDoaHarian = itemView.findViewById(R.id.cardViewListDoaHarian);
            nama_doa = itemView.findViewById(R.id.nama_doa);
            img_items = itemView.findViewById(R.id.img_items);
//            tv_no = itemView.findViewById(R.id.tv_no);
        }
    }
}

package com.tomboati.tour.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.model.TempatMustajabModel;

import java.util.List;

public class WaktuMustajabAdapter extends RecyclerView.Adapter<WaktuMustajabAdapter.ViewHolder>{

    private final List<TempatMustajabModel> data;
    private final String prefix;

    public WaktuMustajabAdapter(List<TempatMustajabModel> data, String prefix) {
        this.data = data;
        this.prefix = prefix;
    }

    @NonNull
    @Override
    public WaktuMustajabAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WaktuMustajabAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_waktu_mustajab, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WaktuMustajabAdapter.ViewHolder holder, int position) {
        holder.textJudul.setText(data.get(position).getJudul());
        holder.textCount.setText(prefix + " Ke - " + (position + 1));
        holder.textKeterangan.setText(data.get(position).getKeterangan());
        holder.cardViewItemWaktuMustajab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.textKeterangan.getVisibility() == View.VISIBLE) {
                    holder.textKeterangan.setVisibility(View.GONE);
                    holder.imgArrow.setImageResource(R.drawable.ic_expand);
                } else {
                    holder.textKeterangan.setVisibility(View.VISIBLE);
                    holder.imgArrow.setImageResource(R.drawable.ic_less);
                }
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textJudul;
        private final TextView textCount;
        private final TextView textKeterangan;
        private final ImageView imgArrow;
        private final CardView cardViewItemWaktuMustajab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textJudul = itemView.findViewById(R.id.text_judul);
            textCount = itemView.findViewById(R.id.text_count);
            textKeterangan = itemView.findViewById(R.id.text_keterangan);
            imgArrow = itemView.findViewById(R.id.img_arrow);
            cardViewItemWaktuMustajab = itemView.findViewById(R.id.cardViewItemWaktuMustajab);
        }
    }
}

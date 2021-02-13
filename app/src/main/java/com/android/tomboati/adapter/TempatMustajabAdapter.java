package com.android.tomboati.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.TahlilResponse;
import com.android.tomboati.model.TempatMustajabModel;

import java.util.List;

public class TempatMustajabAdapter extends RecyclerView.Adapter<TempatMustajabAdapter.ViewHolder>{


    private final List<TempatMustajabModel> data;

    public TempatMustajabAdapter(List<TempatMustajabModel> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TempatMustajabAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TempatMustajabAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tempat_mustajab, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TempatMustajabAdapter.ViewHolder holder, int position) {
        holder.textJudul.setText(data.get(position).getJudul());
        holder.textCount.setText("Tempat Mustajab Ke - " + (position + 1));
        holder.textKeterangan.setText(data.get(position).getKeterangan());
        holder.imgMustajab.setImageResource(data.get(position).getDrawable());
        holder.btnDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(data.get(position).getLink())));
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
        private final Button btnDetail;
        private final ImageView imgMustajab;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textJudul = itemView.findViewById(R.id.text_judul);
            textCount = itemView.findViewById(R.id.text_count);
            textKeterangan = itemView.findViewById(R.id.text_keterangan);
            btnDetail = itemView.findViewById(R.id.btn_detail);
            imgMustajab = itemView.findViewById(R.id.img_mustajab);
        }
    }
    
}

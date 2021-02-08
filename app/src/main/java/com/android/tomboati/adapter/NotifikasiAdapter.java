package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.NotifikasiModel;

import java.util.List;

public class NotifikasiAdapter extends RecyclerView.Adapter<NotifikasiAdapter.ViewHolder> {
    private List<NotifikasiModel> list;

    public NotifikasiAdapter(List<NotifikasiModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifikasiAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewTanggalNotifikasi.setText(list.get(position).getTanggal());
        holder.textViewJudulNotifikasi.setText(list.get(position).getJudul());
        holder.textViewIsiNotifikasi.setText(list.get(position).getIsi());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewTanggalNotifikasi, textViewJudulNotifikasi, textViewIsiNotifikasi;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTanggalNotifikasi = itemView.findViewById(R.id.textViewTanggalNotifikasi);
            textViewJudulNotifikasi = itemView.findViewById(R.id.textViewJudulNotifikasi);
            textViewIsiNotifikasi = itemView.findViewById(R.id.textViewIsiNotifikasi);
        }
    }
}

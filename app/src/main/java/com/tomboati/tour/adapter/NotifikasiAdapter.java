package com.tomboati.tour.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.model.NotifikasiModel;

import java.util.ArrayList;
import java.util.List;

public class NotifikasiAdapter extends RecyclerView.Adapter<NotifikasiAdapter.ViewHolder> {
    private List<NotifikasiModel.DataItem> list;

    public NotifikasiAdapter(NotifikasiModel model) {
        if (model != null) {
            this.list = model.getData();
        } else {
            this.list = new ArrayList<>();
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NotifikasiAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notification, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewTanggalNotifikasi.setText(list.get(position).getIsi());
        holder.textViewJudulNotifikasi.setText(list.get(position).getTanggal());
        holder.textViewIsiNotifikasi.setText(list.get(position).getJudul());
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

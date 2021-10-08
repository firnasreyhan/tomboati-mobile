package com.tomboati.tour.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.model.DoaSesudahSholatModel;

import java.util.List;

public class DoaSesudahSholatAdapter extends RecyclerView.Adapter<DoaSesudahSholatAdapter.ViewHolder> {
    private List<DoaSesudahSholatModel> list;

    public DoaSesudahSholatAdapter(List<DoaSesudahSholatModel> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DoaSesudahSholatAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_doa_sesudah_sholat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNomor.setText((position + 1) + ". " + list.get(position).getNomor());
        holder.textViewArab.setText(list.get(position).getArab());
        holder.textViewKeterangan.setText(list.get(position).getKeterangan());

        if (list.get(position).getNomor().isEmpty()) {
            holder.textViewNomor.setVisibility(View.GONE);
        }

        if (list.get(position).getArab().isEmpty()) {
            holder.textViewArab.setVisibility(View.GONE);
        }

        if (list.get(position).getKeterangan().isEmpty()) {
            holder.textViewKeterangan.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNomor, textViewArab, textViewKeterangan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomor = itemView.findViewById(R.id.textViewNomor);
            textViewArab = itemView.findViewById(R.id.textViewArab);
            textViewKeterangan = itemView.findViewById(R.id.textViewKeterangan);
        }
    }
}

package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;

import java.util.List;

public class BiayaAdapter extends RecyclerView.Adapter<BiayaAdapter.ViewHolder> {
    private List<String> list;

    public BiayaAdapter(List<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_biaya, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewBiaya.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewBiaya;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewBiaya = itemView.findViewById(R.id.textViewBiaya);
        }
    }
}

package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.AsmaulHusnaModel;

import java.util.List;

public class AsmaulHusnaAdapter extends RecyclerView.Adapter<AsmaulHusnaAdapter.ViewHolder> {
    private List<AsmaulHusnaModel> list;

    public AsmaulHusnaAdapter(List<AsmaulHusnaModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AsmaulHusnaAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_asmaul_husna, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.no.setText(list.get(position).getNomor());
        holder.ayat.setText(list.get(position).getArab());
        holder.bacaan.setText(list.get(position).getLatin());
        holder.arti.setText(list.get(position).getTerjemahan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView ayat, bacaan, arti, no;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ayat = itemView.findViewById(R.id.tv_ayat);
            bacaan = itemView.findViewById(R.id.tv_bacaan);
            arti = itemView.findViewById(R.id.tv_arti);
            no = itemView.findViewById(R.id.tv_no);
        }
    }
}

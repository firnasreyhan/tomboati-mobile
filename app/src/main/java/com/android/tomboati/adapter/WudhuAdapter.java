package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.WudhuModel;

import java.util.List;

public class WudhuAdapter extends RecyclerView.Adapter<WudhuAdapter.ViewHolder> {
    private List<WudhuModel> list;

    public WudhuAdapter(List<WudhuModel> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WudhuAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wudhu, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNomor.setText(list.get(position).getNomor());
        holder.textViewKeterangan.setText(list.get(position).getKeterangan());
        holder.textViewArab.setText(list.get(position).getArab());
        holder.textViewLatin.setText(list.get(position).getLatin());
        holder.textViewTerjemahan.setText(list.get(position).getTerjemahan());

        if (list.get(position).getNomor().isEmpty()) {
            holder.textViewNomor.setVisibility(View.GONE);
        }
        if (list.get(position).getKeterangan().isEmpty()) {
            holder.textViewKeterangan.setVisibility(View.GONE);
        }
        if (list.get(position).getArab().isEmpty()) {
            holder.textViewArab.setVisibility(View.GONE);
        }
        if (list.get(position).getLatin().isEmpty()) {
            holder.textViewLatin.setVisibility(View.GONE);
        }
        if (list.get(position).getTerjemahan().isEmpty()) {
            holder.textViewTerjemahan.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNomor, textViewKeterangan, textViewArab, textViewLatin, textViewTerjemahan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomor = itemView.findViewById(R.id.textViewNomor);
            textViewKeterangan = itemView.findViewById(R.id.textViewKeterangan);
            textViewArab = itemView.findViewById(R.id.textViewArab);
            textViewLatin = itemView.findViewById(R.id.textViewLatin);
            textViewTerjemahan = itemView.findViewById(R.id.textViewTerjemahan);
        }
    }
}

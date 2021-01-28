package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.PaketModel;

import java.util.ArrayList;

public class PaketAdapter extends RecyclerView.Adapter<PaketAdapter.ViewHolder> {
    private ArrayList<PaketModel> list;

    public PaketAdapter(ArrayList<PaketModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new PaketAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paket, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

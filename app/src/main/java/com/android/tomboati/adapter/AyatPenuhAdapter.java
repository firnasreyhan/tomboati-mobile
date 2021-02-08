package com.android.tomboati.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.AyatResponse;

import java.util.List;

public class AyatPenuhAdapter extends RecyclerView.Adapter<AyatPenuhAdapter.ViewHolder> {
    private List<AyatResponse.AyatModel> list;

    public AyatPenuhAdapter(List<AyatResponse.AyatModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatPenuhAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ayat_penuh, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewAr.setText(list.get(position).getAr());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewAr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAr = itemView.findViewById(R.id.textViewAr);
        }
    }
}

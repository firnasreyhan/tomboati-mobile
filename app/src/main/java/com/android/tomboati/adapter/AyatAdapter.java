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

public class AyatAdapter extends RecyclerView.Adapter<AyatAdapter.ViewHolder> {
    private List<AyatResponse.AyatModel> list;

    public AyatAdapter(List<AyatResponse.AyatModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ayat, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewAr.setText(list.get(position).getAr());
        holder.textViewTr.setText(list.get(position).getNomor() + ". " + Html.fromHtml(list.get(position).getTr()).toString().substring(0, 1).toUpperCase() + Html.fromHtml(list.get(position).getTr()).toString().substring(1));
        holder.textViewId.setText(list.get(position).getNomor() + ". " + list.get(position).getIdn());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewAr, textViewId, textViewTr;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewAr = itemView.findViewById(R.id.textViewAr);
            textViewId = itemView.findViewById(R.id.textViewId);
            textViewTr = itemView.findViewById(R.id.textViewTr);
        }
    }
}

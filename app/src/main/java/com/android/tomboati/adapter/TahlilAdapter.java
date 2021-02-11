package com.android.tomboati.adapter;

import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.TahlilResponse;

import java.util.List;

public class TahlilAdapter extends RecyclerView.Adapter<TahlilAdapter.ViewHolder>{

    private final List<TahlilResponse.Datum> data;

    public TahlilAdapter(List<TahlilResponse.Datum> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public TahlilAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TahlilAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_istighosah, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TahlilAdapter.ViewHolder holder, int position) {
        holder.textArab.setText(data.get(position).getArabic());
        holder.textTranslate.setText((position + 1) + ". " + data.get(position).getTranslation());
        holder.textBaca.setText(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textArab;
        private final TextView textTranslate;
        private final TextView textBaca;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textArab = itemView.findViewById(R.id.textArab);
            textTranslate = itemView.findViewById(R.id.textTranslate);
            textBaca = itemView.findViewById(R.id.textBaca);
        }
    }
}

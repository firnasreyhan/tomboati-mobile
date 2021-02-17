package com.android.tomboati.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.TasbihModel;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.view.activity.HitungTasbihActivity;

import java.util.ArrayList;
import java.util.List;


public class TasbihAdapter extends RecyclerView.Adapter<TasbihAdapter.ViewHolder>{

    private List<TasbihModel> models;

    public TasbihAdapter() {
        Utility.addValue();
        this.models = new ArrayList<>();
        this.models.addAll(Utility.getTasbihModel());
        this.models.add(new TasbihModel("Hitung Tasbih Lainnya", "", 0));
    }

    @NonNull
    @Override
    public TasbihAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TasbihAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tasbih, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TasbihAdapter.ViewHolder holder, int position) {
        holder.nama_tasbih.setText(models.get(position).getJudul());
        holder.cardViewTasbih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == models.size() - 1) {
                    Toast.makeText(v.getContext(), "Coming Soon", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(v.getContext(), HitungTasbihActivity.class);
                    intent.putExtra("POSITION", position);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final CardView cardViewTasbih;
        private final TextView nama_tasbih;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewTasbih = itemView.findViewById(R.id.cardViewTasbih);
            nama_tasbih = itemView.findViewById(R.id.nama_tasbih);
        }
    }
}

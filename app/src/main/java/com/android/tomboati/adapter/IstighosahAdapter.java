package com.android.tomboati.adapter;

import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.IstighosahModel;
import com.android.tomboati.view.activity.quran.DetailAlQuranActivity;

import java.util.List;

public class IstighosahAdapter extends RecyclerView.Adapter<IstighosahAdapter.ViewHolder>{

    private final List<IstighosahModel> models;
    private ViewGroup view;

    public IstighosahAdapter(List<IstighosahModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.view = parent;
        return new IstighosahAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_istighosah, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            holder.textTranslate.setTextColor(view.getContext().getColor(R.color.black));
        }
        holder.tvNo.setText("" + (position + 1));
        holder.textArab.setText(models.get(position).getTextArab());
        holder.textTranslate.setText(models.get(position).getTextTranslate());
        holder.textBaca.setText("Dibaca sebanyak " + models.get(position).getCountBacaan() + " " +"kali");

        if(models.get(position).getTextTranslate().equals("Yasin")) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                holder.textTranslate.setTextColor(view.getContext().getColor(R.color.light_green));
            }
            holder.textTranslate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailAlQuranActivity.class);
                    intent.putExtra("ID_SURAH", "36");
                    intent.putExtra("NAMA_SURAH", "Surah Yasin");
                    v.getContext().startActivity(intent);
                }
            });
            holder.textTranslate.setText("Baca surah yasin disini!");
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textArab;
        private final TextView textTranslate;
        private final TextView textBaca;
        private final TextView tvNo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textArab = itemView.findViewById(R.id.textArab);
            textTranslate = itemView.findViewById(R.id.textTranslate);
            textBaca = itemView.findViewById(R.id.textBaca);
            tvNo = itemView.findViewById(R.id.tv_no);
        }
    }
}

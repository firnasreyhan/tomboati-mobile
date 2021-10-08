package com.tomboati.tour.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.model.JuzModelNew;
import com.tomboati.tour.view.activity.quran.DetailJuzNewActivity;

import java.util.List;

public class JuzNewAdapter extends RecyclerView.Adapter<JuzNewAdapter.ViewHolder> {
    private final List<JuzModelNew> list;

    public JuzNewAdapter(List<JuzModelNew> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JuzNewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_surah_new, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNomor.setText("" + (position + 1));
        holder.textViewNamaSurah.setText("JUZ " + (position + 1));
        holder.textViewKeterangan.setText(list.get(position).getKeterangan());
        holder.textViewAsma.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailJuzNewActivity.class);
                intent.putExtra("POSITION", position);
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNomor, textViewNamaSurah, textViewKeterangan, textViewAsma;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomor = itemView.findViewById(R.id.textNo);
            textViewNamaSurah = itemView.findViewById(R.id.textNamaSurah);
            textViewKeterangan = itemView.findViewById(R.id.textKeterangan);
            textViewAsma = itemView.findViewById(R.id.textArabic);
        }
    }
}

package com.android.tomboati.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.JuzModel;
import com.android.tomboati.view.activity.quran.DetailJuzActivity;

import java.util.List;

public class JuzAdapter extends RecyclerView.Adapter<JuzAdapter.ViewHolder> {
    private List<JuzModel> list;

    public JuzAdapter(List<JuzModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new JuzAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_juz, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewUrutanJuz.setText("Juz " + (position + 1));
        holder.textViewNamaSurahAyat.setText(list.get(position).getNamaSurah() + " - " + list.get(position).getAyatPertama());
        holder.textViewAsma.setText(list.get(position).getAsma());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailJuzActivity.class);
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
        private TextView textViewUrutanJuz, textViewNamaSurahAyat, textViewAsma;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewUrutanJuz = itemView.findViewById(R.id.textViewUrutanJuz);
            textViewNamaSurahAyat = itemView.findViewById(R.id.textViewNamaSurahAyat);
            textViewAsma = itemView.findViewById(R.id.textViewAsma);
        }
    }
}

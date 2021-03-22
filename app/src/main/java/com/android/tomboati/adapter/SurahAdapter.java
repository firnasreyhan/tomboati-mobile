package com.android.tomboati.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.SurahResponse;
import com.android.tomboati.view.activity.quran.DetailAlQuranActivity;

import java.util.List;

public class SurahAdapter extends RecyclerView.Adapter<SurahAdapter.ViewHolder> {
    private List<SurahResponse> list;

    public SurahAdapter(List<SurahResponse> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SurahAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_surah, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNomor.setText(list.get(position).getNomor() + ".");
        holder.textViewNamaSurah.setText(list.get(position).getNamaLatin());
        holder.textViewArtiSurahJumlahAyat.setText(list.get(position).getArti() + " (" + list.get(position).getJumlahAyat() + ")");
        holder.textViewAsma.setText(list.get(position).getNama());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailAlQuranActivity.class);
                intent.putExtra("ID_SURAH", list.get(position).getNomor());
                intent.putExtra("NAMA_SURAH", list.get(position).getNamaLatin());
                v.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNomor, textViewNamaSurah, textViewArtiSurahJumlahAyat, textViewAsma;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomor = itemView.findViewById(R.id.textViewNomor);
            textViewNamaSurah = itemView.findViewById(R.id.textViewNamaSurah);
            textViewArtiSurahJumlahAyat = itemView.findViewById(R.id.textViewArtiSurahJumlahAyat);
            textViewAsma = itemView.findViewById(R.id.textViewAsma);
        }
    }
}

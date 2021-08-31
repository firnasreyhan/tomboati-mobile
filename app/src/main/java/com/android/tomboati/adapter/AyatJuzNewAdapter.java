package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.QuranSurahResponse;
import com.android.tomboati.model.JuzModelNew;
import com.codesgood.views.JustifiedTextView;

import java.util.List;

public class AyatJuzNewAdapter extends RecyclerView.Adapter<AyatJuzNewAdapter.ViewHolder> {
    private List<QuranSurahResponse.Data.Verse> list;
    private List<JuzModelNew.AyatModel> listAyatModels;

    public AyatJuzNewAdapter(List<QuranSurahResponse.Data.Verse> list, List<JuzModelNew.AyatModel> listAyatModels) {
        this.list = list;
        this.listAyatModels = listAyatModels;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatJuzNewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ayat_juz_new, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNomor.setText("" + (position + 1));
        int count = 0;
        for(JuzModelNew.AyatModel model : listAyatModels) {
            count += model.getBanyakAyat();
            if(position < count) {
                holder.textKeterangan.setText(model.getNamaAyat() + " Ayat " + list.get(position).getNumber().getInSurah());
                break;
            }
        }
        holder.textArabic.setText(list.get(position).getText().getArab());
        holder.textLatin.setText(list.get(position).getText().getTransliteration().getEn());
        holder.textArti.setText(list.get(position).getTranslation().getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNomor, textKeterangan;
        private JustifiedTextView textArabic, textLatin, textArti;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomor = itemView.findViewById(R.id.textNo);
            textKeterangan = itemView.findViewById(R.id.textKeterangan);
            textArabic = itemView.findViewById(R.id.textArabic);
            textLatin = itemView.findViewById(R.id.textLatin);
            textArti = itemView.findViewById(R.id.textArti);
        }
    }
}

package com.android.tomboati.adapter;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.QuranSurahResponse;
import com.codesgood.views.JustifiedTextView;

import java.util.List;

public class AyatNewAdapter extends RecyclerView.Adapter<AyatNewAdapter.ViewHolder> {
    private List<QuranSurahResponse.Data.Verse> list;

    public AyatNewAdapter(List<QuranSurahResponse.Data.Verse> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AyatNewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ayat_new, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNomor.setText("" + (position));
        holder.textArabic.setText(list.get(position).getText().getArab());
        holder.textLatin.setText(list.get(position).getText().getTransliteration().getEn());
        holder.textArti.setText(list.get(position).getTranslation().getId());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNomor;
        private JustifiedTextView textArabic, textLatin, textArti;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNomor = itemView.findViewById(R.id.textNo);
            textArabic = itemView.findViewById(R.id.textArabic);
            textLatin = itemView.findViewById(R.id.textLatin);
            textArti = itemView.findViewById(R.id.textArti);
        }
    }
}

package com.tomboati.tour.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.QuranListResponse;
import com.tomboati.tour.view.activity.quran.DetailAlQuranNewActivity;

import java.util.List;

public class SurahNewAdapter extends RecyclerView.Adapter<SurahNewAdapter.ViewHolder> {
    private final List<QuranListResponse.Datum> list;

    public SurahNewAdapter(List<QuranListResponse.Datum> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SurahNewAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_surah_new, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewNomor.setText("" + (position + 1));
        holder.textViewNamaSurah.setText(list.get(position).getName().getTransliteration().getId());
        holder.textViewKeterangan.setText(list.get(position).getRevelation().getId()
                .concat(" | ").concat("" + list.get(position).getNumberOfVerses()).concat(" Ayat"));
        holder.textViewAsma.setText(list.get(position).getName().getShort());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailAlQuranNewActivity.class);
                intent.putExtra("ID_SURAH", (position + 1));
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

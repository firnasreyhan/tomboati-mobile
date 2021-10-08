package com.tomboati.tour.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.BacaanSholatResponse;
import com.tomboati.tour.view.activity.doa_dzikir.DetailDoaSehariHariActivity;

import java.util.List;

public class SholatAdapter extends RecyclerView.Adapter<SholatAdapter.ViewHolder> {

    private final List<BacaanSholatResponse> data;
    private int layout_item = 0;

    public SholatAdapter(List<BacaanSholatResponse> data, int layout_item) {
        this.data = data;
        this.layout_item = layout_item;
    }

    @NonNull
    @Override
    public SholatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SholatAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(layout_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SholatAdapter.ViewHolder holder, int position) {
        if(layout_item == R.layout.item_istighosah) {
            holder.tvNo.setText("" + (position + 1));
            holder.textArab.setText(data.get(position).getArabic());
            holder.textTranslate.setText(data.get(position).getLatin());
            holder.textBaca.setText(data.get(position).getName());
        } else {
            holder.nama_doa.setText(data.get(position).getName());
            holder.cardViewListDoaHarian.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent myIntent = new Intent(v.getContext(), DetailDoaSehariHariActivity.class);
                    myIntent.putExtra("TITLE", data.get(position).getName());
                    myIntent.putExtra("ARABIC", data.get(position).getArabic());
                    myIntent.putExtra("TRANSLATE", data.get(position).getLatin());
                    myIntent.putExtra("ARTI", data.get(position).getTerjemahan());
                    v.getContext().startActivity(myIntent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView textArab;
        private final TextView textTranslate;
        private final TextView textBaca;
        private final TextView tvNo;
        private final CardView cardViewListDoaHarian;
        private final TextView nama_doa;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textArab = itemView.findViewById(R.id.textArab);
            textTranslate = itemView.findViewById(R.id.textTranslate);
            textBaca = itemView.findViewById(R.id.textBaca);
            tvNo = itemView.findViewById(R.id.tv_no);
            cardViewListDoaHarian = itemView.findViewById(R.id.cardViewListDoaHarian);
            nama_doa = itemView.findViewById(R.id.nama_doa);
        }
    }
}

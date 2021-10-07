package com.android.tomboati.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.view.activity.pendaftaran.SyaratActivity;
import com.squareup.picasso.Picasso;
import java.util.List;

public class PaketHajiAdapter extends RecyclerView.Adapter<PaketHajiAdapter.ViewHolder> {

    private List<PaketResponse.PaketModel> list;
    private final PaketHajiAdapter.onSelectedData selectedData;

    public interface onSelectedData{
        void onSelected(String idPaket);
    }

    public PaketHajiAdapter(List<PaketResponse.PaketModel> list, PaketHajiAdapter.onSelectedData selectedData) {
        this.list = list;
        this.selectedData = selectedData;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PaketHajiAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paket_haji, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        if (list.get(position).getImagePaket() != null) {
            picassoLoad(list.get(position).getImagePaket(), holder.imageViewPaket);
        }

        if (list.get(position).getNamaPaket() != null) {
            holder.textViewNamaPaket.setText(list.get(position).getNamaPaket());
        }

        holder.cardViewItemPaketHaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedData.onSelected(list.get(position).getIdPaket());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewPaket;
        private TextView textViewNamaPaket;
        private CardView cardViewItemPaketHaji;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPaket = itemView.findViewById(R.id.imageViewPaket);
            textViewNamaPaket = itemView.findViewById(R.id.textViewNamaPaket);
            cardViewItemPaketHaji = itemView.findViewById(R.id.cardViewItemPaketHaji);
        }
    }
}
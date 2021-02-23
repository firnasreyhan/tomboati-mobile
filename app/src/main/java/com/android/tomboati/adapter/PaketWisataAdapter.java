package com.android.tomboati.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.api.response.PaketWisataResponse;
import com.android.tomboati.view.activity.DetailPaketActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;

public class PaketWisataAdapter extends RecyclerView.Adapter<PaketWisataAdapter.ViewHolder> {
    private List<PaketWisataResponse.PaketModel> list;

    public PaketWisataAdapter(List<PaketWisataResponse.PaketModel> list) {
        this.list = list;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_paket, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.get(position).getImageWisata() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(list.get(position).getImageWisata())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .dontAnimate()
                    .dontTransform()
                    .priority(Priority.IMMEDIATE)
                    .encodeFormat(Bitmap.CompressFormat.PNG)
                    .format(DecodeFormat.DEFAULT)
                    .placeholder(R.drawable.ic_logo)
                    .into(holder.imageViewPaket);
        }

        if (list.get(position).getImageMaskapai() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(list.get(position).getImageMaskapai())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .skipMemoryCache(true)
                    .dontAnimate()
                    .dontTransform()
                    .priority(Priority.IMMEDIATE)
                    .encodeFormat(Bitmap.CompressFormat.PNG)
                    .format(DecodeFormat.DEFAULT)
                    .placeholder(R.drawable.ic_logo)
                    .into(holder.imageViewMaskapai);
        }

        if (list.get(position).getNamaWisata() != null) {
            holder.textViewNamaPaket.setText(list.get(position).getNamaWisata());
        }

        if (list.get(position).getQuadSheet() != 0 && list.get(position).getDurasiWisata() != null) {
            DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
            decimalFormatSymbols.setDecimalSeparator(',');
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", decimalFormatSymbols);

            holder.textViewHargaPaketDurasi.setText("Rp. " + decimalFormat.format(list.get(position).getQuadSheet()) + " (" + list.get(position).getDurasiWisata() + " Hari)");
        }

        if (list.get(position).getPenerbangan() != null) {
            holder.textViewPenerbangan.setText(list.get(position).getPenerbangan());
        }

        if (list.get(position).getTempatHotelA() != null) {
            holder.textViewTempatHotelA.setText(list.get(position).getTempatHotelA());
        }

        if (list.get(position).getNamaHotelA() != null) {
            holder.textViewNamaHotelA.setText(list.get(position).getNamaHotelA());
        }

        if (list.get(position).getTempatHotelB() != null) {
            holder.textViewTempatHotelB.setText(list.get(position).getTempatHotelB());
        }

        if (list.get(position).getNamaHotelB() != null) {
            holder.textViewNamaHotelB.setText(list.get(position).getNamaHotelB());
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageViewPaket, imageViewMaskapai;
        private TextView textViewNamaPaket, textViewHargaPaketDurasi, textViewPenerbangan, textViewTempatHotelA, textViewNamaHotelA, textViewTempatHotelB, textViewNamaHotelB;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewPaket = itemView.findViewById(R.id.imageViewPaket);
            imageViewMaskapai = itemView.findViewById(R.id.imageViewMaskapai);
            textViewNamaPaket = itemView.findViewById(R.id.textViewNamaPaket);
            textViewHargaPaketDurasi = itemView.findViewById(R.id.textViewHargaPaketDurasi);
            textViewPenerbangan = itemView.findViewById(R.id.textViewPenerbangan);
            textViewTempatHotelA = itemView.findViewById(R.id.textViewTempatHotelA);
            textViewNamaHotelA = itemView.findViewById(R.id.textViewNamaHotelA);
            textViewTempatHotelB = itemView.findViewById(R.id.textViewTempatHotelB);
            textViewNamaHotelB = itemView.findViewById(R.id.textViewNamaHotelB);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), DetailPaketActivity.class);
                    intent.putExtra("ID_PAKET_WISATA", list.get(getAdapterPosition()).getIdWisataHalal());
                    v.getContext().startActivity(intent);
                }
            });
        }
    }
}
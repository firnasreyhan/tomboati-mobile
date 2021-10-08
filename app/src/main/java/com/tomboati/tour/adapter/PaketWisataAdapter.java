package com.tomboati.tour.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.PaketWisataResponse;
import com.tomboati.tour.view.activity.pendaftaran.DetailPaketActivity;
import com.squareup.picasso.Picasso;

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
            picassoLoad(list.get(position).getImageWisata(), holder.imageViewPaket);
        }

        if (list.get(position).getImageMaskapai() != null) {
            picassoLoad(list.get(position).getImageMaskapai(), holder.imageViewMaskapai);
        }

        if (list.get(position).getNamaWisata() != null) {
            holder.textViewNamaPaket.setText(list.get(position).getNamaWisata());
        }

        if (list.get(position).getQuadSheet() != 0 && list.get(position).getDurasiWisata() != null) {
            String amount = String.format("%,.0f%3s", list.get(position).getQuadSheet(), ",- (") + list.get(position).getDurasiWisata() + " Hari)";
            holder.textViewHargaPaketDurasi.setText("Rp. ".concat(amount));
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

    private void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
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
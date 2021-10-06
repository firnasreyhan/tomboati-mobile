package com.android.tomboati.adapter;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.DetailPembayaranResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DetailPembayaranAdapter extends RecyclerView.Adapter<DetailPembayaranAdapter.ViewHolder>{

    private final List<DetailPembayaranResponse.DataItem> list;
    private final String[] month = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    public DetailPembayaranAdapter(List<DetailPembayaranResponse.DataItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailPembayaranAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_pembayaran, parent, false));
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final boolean isValidation = list.get(position).getSTATUSPEMBAYARAN().equals("1");
        holder.textStatus.setText(isValidation ? "Terverifikasi" : "Belum Terverifikasi");
        holder.textStatus.setBackgroundResource(isValidation ? R.drawable.round_bg : R.drawable.round_bg_red);

        final String[] split = list.get(position).getTANGGALPEMBAYARAN().substring(0, 10).split("-");
        holder.textTanggal.setText(split[2].concat(" ").concat(month[Integer.parseInt(split[1]) - 1].concat(" ").concat(split[0])));

        holder.textHargaPembayaran.setText(String.format("BAYAR : Rp. %,.02f", Double.parseDouble(list.get(position).getJUMLAHPEMBAYARAN())));

        picassoLoad(list.get(position).getBUKTIPEMBAYARAN(), holder.imageViewBuktiPembayaran);

        holder.constraintLayoutExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.imageViewBuktiPembayaran.getVisibility() == View.VISIBLE) {
                    holder.imageViewBuktiPembayaran.setVisibility(View.GONE);
                    holder.imgArrow.setImageResource(R.drawable.ic_expand);
                } else {
                    holder.imageViewBuktiPembayaran.setVisibility(View.VISIBLE);
                    holder.imgArrow.setImageResource(R.drawable.ic_less);
                }
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

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView textTanggal;
        private final TextView textStatus;
        private final TextView textHargaPembayaran;
        private final ImageView imgArrow, imageViewBuktiPembayaran;
        private final ConstraintLayout constraintLayoutExpand;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textTanggal = itemView.findViewById(R.id.textTanggal);
            textStatus = itemView.findViewById(R.id.textStatus);
            textHargaPembayaran = itemView.findViewById(R.id.textHargaPembayaran);
            imgArrow = itemView.findViewById(R.id.imgArrow);
            imageViewBuktiPembayaran = itemView.findViewById(R.id.imageViewBuktiPembayaran);
            constraintLayoutExpand = itemView.findViewById(R.id.constraintLayoutExpand);
        }
    }
}

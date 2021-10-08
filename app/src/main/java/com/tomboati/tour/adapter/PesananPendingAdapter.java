package com.tomboati.tour.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.ListPaketVerifyRespone;
import com.tomboati.tour.view.activity.pembayaran.InformasiPembayaranActivity;

import java.util.List;

public class PesananPendingAdapter  extends RecyclerView.Adapter<PesananPendingAdapter.ViewHolder> {

    private List<ListPaketVerifyRespone.DataItem> list;
    private final String[] month = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    public PesananPendingAdapter(List<ListPaketVerifyRespone.DataItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new PesananPendingAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pesanan, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String namaPaket = (list.get(position).getNAMAPAKET() == null) ? list.get(position).getNAMAWISATA() : list.get(position).getNAMAPAKET();
        holder.textNamaPaket.setText(namaPaket);

        String tanggal = list.get(position).getTANGGALKEBERANGKAT();
        if(tanggal != null) {
            String[] prefix = tanggal.substring(0, 10).split("-");
            tanggal = prefix[2].concat(" ").concat(this.month[Integer.parseInt(prefix[1]) - 1]).concat(" ").concat(prefix[0]);
        }
        holder.textTanggalBerangkat.setText("Berangkat pada : ".concat((tanggal != null) ? tanggal : ""));

        holder.textHargaSheet.setText("Rp. ".concat(formatCurrency(list.get(position).getSHEETHARGA())));

        if(list.get(position).getIDPAKET() == null) {
            holder.textHeader.setText("Wisata Halal");
        } else {
            holder.textHeader.setText("Haji / Umrah");
        }

        String idPaket = (list.get(position).getIDPAKET() != null) ? list.get(position).getIDPAKET() : list.get(position).getIDWISATAHALAL();
        String idTransaksi = list.get(position).getIDTRANSAKSI();
        String kodePendaftaran = list.get(position).getKODEPENDAFTARAN();
        int sheet = list.get(position).getSHEET();

        holder.textKodeTransaksi.setText(idTransaksi + "/" + sheet + "/" + idPaket + "/" + kodePendaftaran);

    }

    @SuppressLint("DefaultLocale")
    private String formatCurrency(double amount) {
        return String.format("%,.0f%2s", amount, ",-");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textNamaPaket, textTanggalBerangkat, textHargaSheet, textHeader, textKodeTransaksi;

        public ViewHolder(View itemView) {
            super(itemView);
            textNamaPaket = itemView.findViewById(R.id.textNamaPaket);
            textTanggalBerangkat = itemView.findViewById(R.id.textTanggalBerangkat);
            textHargaSheet = itemView.findViewById(R.id.textHargaSheet);
            textHeader = itemView.findViewById(R.id.textHeader);
            textKodeTransaksi = itemView.findViewById(R.id.textKodeTransaksi);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Intent intent = new Intent(v.getContext(), InformasiPembayaranActivity.class);
                    intent.putExtra("ID_TRANSAKSI", list.get(getAdapterPosition()).getIDTRANSAKSI());
                    v.getContext().startActivity(intent);
                }
            });
        }

    }
}

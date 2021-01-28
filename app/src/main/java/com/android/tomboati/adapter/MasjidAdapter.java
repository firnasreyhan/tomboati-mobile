package com.android.tomboati.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.MasjidResponse;
import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MasjidAdapter extends RecyclerView.Adapter<MasjidAdapter.ViewHolder> {
    private final List<MasjidResponse.Feature> list;
    private final double latitude;
    private final double longitude;

    public MasjidAdapter(List<MasjidResponse.Feature> list, double latitude, double longitude) {
        this.list = list;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return  new MasjidAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_masjid, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nama_masjid.setText(list.get(position).getText());

        List<MasjidResponse.Feature.Context> contexts = list.get(position).getContext();
        String lokasi = contexts.get(0).getText().concat(", ")
                .concat(contexts.get((contexts.size() < 4) ? 1 : 2).getText())
                .concat(", ").concat(contexts.get((contexts.size() < 4) ? 2 : 3).getText());
        holder.lokasi_masjid.setText(lokasi);

        holder.jarak.setText(calculateDistance(latitude, longitude,
                list.get(position).getCenter().get(1), list.get(position).getCenter().get(0)) + " KM");

        holder.btn_buka_lokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String URI = "https://www.google.com/maps/search/?api=1&query=" +
                        list.get(position).getCenter().get(1) + "," + list.get(position).getCenter().get(0);
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(URI)));
            }
        });

        holder.btn_buka_rute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String URI = "https://www.google.com/maps/dir/?api=1&origin="
                        + latitude + "," + longitude + "&destination=" + list.get(position).getCenter().get(1)
                        + "," + list.get(position).getCenter().get(0) + "&travelmode=driving";
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse(URI)));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nama_masjid;
        private final TextView lokasi_masjid;
        private final TextView jarak;
        private final MaterialButton btn_buka_lokasi;
        private final MaterialButton btn_buka_rute;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nama_masjid = itemView.findViewById(R.id.nama_masjid);
            lokasi_masjid = itemView.findViewById(R.id.lokasi_masjid);
            jarak = itemView.findViewById(R.id.jarak);
            btn_buka_lokasi = itemView.findViewById(R.id.btn_buka_lokasi);
            btn_buka_rute = itemView.findViewById(R.id.btn_buka_rute);
        }
    }

    private int  calculateDistance(double oriLat, double oriLng, double disLat, double disLng) {
        try{
            final double AVERAGE_RADIUS_OF_EARTH_KM = 6371;
            double latDistance = Math.toRadians(oriLat - disLat);
            double lngDistance = Math.toRadians(oriLng - disLng);

            double average = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                    + Math.cos(Math.toRadians(oriLat)) * Math.cos(Math.toRadians(disLat))
                    * Math.sin(lngDistance / 2) * Math.sin(lngDistance / 2);

            double degrees = 2 * Math.atan2(Math.sqrt(average), Math.sqrt(1 - average));
            return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH_KM * degrees));

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }
}

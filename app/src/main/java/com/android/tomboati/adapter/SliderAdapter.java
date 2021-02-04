package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.model.SliderModel;
import com.android.tomboati.utils.Utility;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;
import java.util.Calendar;


public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {

    private ArrayList<JadwalSholatResponse> list;

    public SliderAdapter(ArrayList<JadwalSholatResponse> list) {
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        return  new SliderAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_slider, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (position == 1) {
            viewHolder.imageViewSlider.setImageResource(R.drawable.ic_slider_mekah);
            viewHolder.textViewKota.setText("Mekah");
        }
        else if (position == 2) {
            viewHolder.imageViewSlider.setImageResource(R.drawable.ic_slider_madinah);
            viewHolder.textViewKota.setText("Madinah");
        } else {
            viewHolder.textViewKota.setText(Utility.getKota());
        }
        viewHolder.textViewDateMasehi.setText(list.get(position).getData().getDetailData().getDate().getText().getM());
        viewHolder.textViewDateHijriah.setText(list.get(position).getData().getDetailData().getDate().getText().getH());

        if (getTimeInMillis(Utility.getHour(), Utility.getMinute()) <= getTimeInMillis(Integer.parseInt(list.get(position).getData().getData().getData().getShortShubuh().substring(0,2)), Integer.parseInt(list.get(position).getData().getData().getData().getShortShubuh().substring(3,5)))) {
            viewHolder.textViewWaktuSholat.setText("Shubuh");
            viewHolder.textViewJamSholat.setText(list.get(position).getData().getData().getData().getShortShubuh());
        } else if (getTimeInMillis(Utility.getHour(), Utility.getMinute()) <= getTimeInMillis(Integer.parseInt(list.get(position).getData().getData().getData().getShortDhuhur().substring(0,2)), Integer.parseInt(list.get(position).getData().getData().getData().getShortDhuhur().substring(3,5)))) {
            viewHolder.textViewWaktuSholat.setText("Dhuhur");
            viewHolder.textViewJamSholat.setText(list.get(position).getData().getData().getData().getShortDhuhur());
        } else if (getTimeInMillis(Utility.getHour(), Utility.getMinute()) <= getTimeInMillis(Integer.parseInt(list.get(position).getData().getData().getData().getShortAshar().substring(0,2)), Integer.parseInt(list.get(position).getData().getData().getData().getShortAshar().substring(3,5)))) {
            viewHolder.textViewWaktuSholat.setText("Ashar");
            viewHolder.textViewJamSholat.setText(list.get(position).getData().getData().getData().getShortAshar());
        } else if (getTimeInMillis(Utility.getHour(), Utility.getMinute()) <= getTimeInMillis(Integer.parseInt(list.get(position).getData().getData().getData().getShortMaghrib().substring(0,2)), Integer.parseInt(list.get(position).getData().getData().getData().getShortMaghrib().substring(3,5)))) {
            viewHolder.textViewWaktuSholat.setText("Maghrib");
            viewHolder.textViewJamSholat.setText(list.get(position).getData().getData().getData().getShortMaghrib());
        } else if (getTimeInMillis(Utility.getHour(), Utility.getMinute()) <= getTimeInMillis(Integer.parseInt(list.get(position).getData().getData().getData().getShortIsya().substring(0,2)), Integer.parseInt(list.get(position).getData().getData().getData().getShortIsya().substring(3,5)))) {
            viewHolder.textViewWaktuSholat.setText("Isya");
            viewHolder.textViewJamSholat.setText(list.get(position).getData().getData().getData().getShortIsya());
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public long getTimeInMillis(int hour, int minute) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTimeInMillis();
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView imageViewSlider;
        private TextView textViewKota, textViewDateMasehi, textViewDateHijriah, textViewWaktuSholat, textViewJamSholat;
        public ViewHolder(View itemView) {
            super(itemView);
            imageViewSlider = itemView.findViewById(R.id.imageViewSlider);
            textViewKota = itemView.findViewById(R.id.textViewKota);
            textViewDateMasehi = itemView.findViewById(R.id.textViewDateMasehi);
            textViewDateHijriah = itemView.findViewById(R.id.textViewDateHijriah);
            textViewWaktuSholat = itemView.findViewById(R.id.textViewWaktuSholat);
            textViewJamSholat = itemView.findViewById(R.id.textViewJamSholat);
        }
    }
}

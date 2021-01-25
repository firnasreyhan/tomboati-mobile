package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.android.tomboati.R;
import com.android.tomboati.model.SliderModel;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.ArrayList;


public class SliderAdapter extends SliderViewAdapter<SliderAdapter.ViewHolder> {

    private ArrayList<SliderModel> list;

    public SliderAdapter(ArrayList<SliderModel> list) {
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
        }
        else if (position == 2) {
            viewHolder.imageViewSlider.setImageResource(R.drawable.ic_slider_madinah);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    public class ViewHolder extends SliderViewAdapter.ViewHolder {
        private ImageView imageViewSlider;
        public ViewHolder(View itemView) {
            super(itemView);
            imageViewSlider = itemView.findViewById(R.id.imageViewSlider);
        }
    }
}

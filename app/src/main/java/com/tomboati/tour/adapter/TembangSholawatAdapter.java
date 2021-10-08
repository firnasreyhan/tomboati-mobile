package com.tomboati.tour.adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.model.TembangSholawatModel;

import java.util.List;

public class TembangSholawatAdapter extends RecyclerView.Adapter<TembangSholawatAdapter.ViewHolder>{

    private boolean is_selected = false;
    private int selected_position = 0;
    private ViewGroup view;
    private final TembangSholawatAdapter.onSelectedData onSelectedData;
    private final List<TembangSholawatModel> tembangModel;

    public interface onSelectedData{
        void onSelected(int position);
    }

    public TembangSholawatAdapter(List<TembangSholawatModel> tembangModel, TembangSholawatAdapter.onSelectedData onSelectedData) {
        this.tembangModel = tembangModel;
        this.onSelectedData = onSelectedData;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.view = parent;
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tembang_sholawat_mp3_play, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.duration.setText(tembangModel.get(position).getDuration());
        holder.text_judul.setText(tembangModel.get(position).getJudulTembang());
        holder.text_count.setText("Tembang Sholawat Ke - " + (position + 1));
        holder.parent_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selected_position = position;
                is_selected = true;
                onSelectedData.onSelected(position);
                notifyDataSetChanged();
            }
        });

        if(is_selected) {
            if(selected_position == position) {
                holder.parent_item.setBackgroundColor(Color.parseColor("#F2F2F2"));
            } else {
                holder.parent_item.setBackgroundColor(Color.WHITE);
            }
        }
    }

    @Override
    public int getItemCount() {
        return tembangModel.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView text_judul;
        private final TextView text_count;
        private final TextView duration;
        private final ConstraintLayout parent_item;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            text_judul = itemView.findViewById(R.id.text_judul);
            text_count = itemView.findViewById(R.id.text_count);
            duration = itemView.findViewById(R.id.duration);
            parent_item = itemView.findViewById(R.id.parent_item);
        }
    }

}

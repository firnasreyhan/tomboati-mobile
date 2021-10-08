package com.tomboati.tour.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.ItteneraryResponse;

import java.util.List;

public class ItteneraryAdapter extends RecyclerView.Adapter<ItteneraryAdapter.ViewHolder> {
    private List<ItteneraryResponse.ItteneraryModel> list;

    public ItteneraryAdapter(List<ItteneraryResponse.ItteneraryModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ittenerary, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textViewHariKe.setText("Hari ke-" + list.get(position).getHariKe());
        holder.textViewTempat.setText(list.get(position).getTempat());
        holder.textViewDetailKegiatan.setText(list.get(position).getDetailKegiatan());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewHariKe, textViewTempat, textViewDetailKegiatan;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewHariKe = itemView.findViewById(R.id.textViewHariKe);
            textViewTempat = itemView.findViewById(R.id.textViewTempat);
            textViewDetailKegiatan = itemView.findViewById(R.id.textViewDetailKegiatan);
        }
    }
}

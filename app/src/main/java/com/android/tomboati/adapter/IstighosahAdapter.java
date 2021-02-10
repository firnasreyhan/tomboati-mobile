package com.android.tomboati.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.IstighosahModel;

import java.util.List;

public class IstighosahAdapter extends RecyclerView.Adapter<IstighosahAdapter.ViewHolder>{

    private final List<IstighosahModel> models;

    public IstighosahAdapter(List<IstighosahModel> models) {
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IstighosahAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_istighosah, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textArab.setText(models.get(position).getTextArab());
        holder.textTranslate.setText(models.get(position).getTextTranslate());
        holder.textBaca.setText("Dibaca sebanyak " + models.get(position).getCountBacaan() + " " +"kali");
        holder.count.setText(String.valueOf(position + 1));

        if(position == 17) {
//            holder.btn_yasin.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView count;
        private final TextView textArab;
        private final TextView textTranslate;
        private final TextView textBaca;
//        private final Button btn_yasin;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            count = itemView.findViewById(R.id.textCount);
            textArab = itemView.findViewById(R.id.textArab);
            textTranslate = itemView.findViewById(R.id.textTranslate);
            textBaca = itemView.findViewById(R.id.textBaca);
//            btn_yasin = itemView.findViewById(R.id.btn_yasin);
        }
    }
}

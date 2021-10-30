package com.tomboati.tour.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.KomunitasResponse;
import com.tomboati.tour.helper.Common;
import com.tomboati.tour.model.NewsModel;
import com.tomboati.tour.view.activity.komunitas.DetailKomunitasActivity;
import com.codesgood.views.JustifiedTextView;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.List;

public class KomunitasAdapter extends RecyclerView.Adapter<KomunitasAdapter.ViewHolder>{

    private List<KomunitasResponse.Datum> data;

    public KomunitasAdapter(List<KomunitasResponse.Datum> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new KomunitasAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_komunitas, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Picasso.get().load(data.get(position).getFOTO()).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(holder.img_list_komunitas);

        holder.text_judul_komunitas.setText(data.get(position).getJUDULNEWS());

        String newsAll = Common.splitTextToString(data.get(position).getCONTENTNEWS());
        String[] listParagraph = newsAll.split("\\.");
        StringBuilder news = new StringBuilder();
        final int MAX_STRING = Math.min(listParagraph.length, 3);
        for(int i = 0; i < MAX_STRING; i++) {
            news.append(listParagraph[i]);
        }
        holder.text_sort_komunitas.setText(news.toString());

        String tagar = data.get(position).getTANGGALNEWS().concat(data.get(position).getDESKRIPSINEWS() == null ? "" : " / ".concat(data.get(position).getDESKRIPSINEWS()));
        holder.text_tagar.setText(tagar);

        holder.button_details.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailKomunitasActivity.class);
            intent.putExtra("OBJECT", new NewsModel(
                    data.get(position).getJUDULNEWS(),
                    tagar, newsAll,
                    data.get(position).getFOTO()
            ));
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView img_list_komunitas;
        private final JustifiedTextView text_judul_komunitas;
        private final JustifiedTextView text_sort_komunitas;
        private final MaterialButton button_details;
        private final TextView text_tagar;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_list_komunitas = itemView.findViewById(R.id.img_list_komunitas);
            text_judul_komunitas = itemView.findViewById(R.id.text_judul_komunitas);
            text_sort_komunitas = itemView.findViewById(R.id.text_sort_komunitas);
            button_details = itemView.findViewById(R.id.button_details);
            text_tagar = itemView.findViewById(R.id.text_tagar);
        }
    }

}

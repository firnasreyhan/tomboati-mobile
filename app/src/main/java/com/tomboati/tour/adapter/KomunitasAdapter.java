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
import com.tomboati.tour.api.response.KomunitasResponse;
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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Picasso.get().load(data.get(position).getFOTO()).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(holder.img_list_komunitas);

        holder.text_judul_komunitas.setText(data.get(position).getJUDULNEWS());

        String s = data.get(position).getCONTENTNEWS().replaceAll("\\<.*?\\>", "").replaceAll("&.*?;", " ");
        String[] senteces = s.split("\\. ");
        StringBuilder shortNews = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            shortNews.append(senteces[i]).append(". ");
        }
        holder.text_sort_komunitas.setText(shortNews);

        String tagar = data.get(position).getTANGGALNEWS()
            .concat(data.get(position).getDESKRIPSINEWS() == null ? "" :
                " / ".concat(data.get(position).getDESKRIPSINEWS()));
        holder.text_tagar.setText(tagar);

        holder.button_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), DetailKomunitasActivity.class);
                intent.putExtra("IMAGE", data.get(position).getFOTO());
                intent.putExtra("TEXT_TAGAR", tagar);
                intent.putExtra("TEXT_JUDUL", data.get(position).getJUDULNEWS());
                intent.putExtra("CONTENT", data.get(position).getCONTENTNEWS());

                view.getContext().startActivity(intent);
            }
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

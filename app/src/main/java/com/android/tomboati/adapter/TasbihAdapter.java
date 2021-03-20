package com.android.tomboati.adapter;

import android.app.AlertDialog;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.tomboati.R;
import com.android.tomboati.model.TasbihModel;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.view.activity.doa_dzikir.HitungTasbihActivity;
import com.android.tomboati.view.activity.doa_dzikir.HitungTasbihLainnyaActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;


public class TasbihAdapter extends RecyclerView.Adapter<TasbihAdapter.ViewHolder>{

    private List<TasbihModel> models;

    public TasbihAdapter() {
        Utility.addValue();
        this.models = new ArrayList<>();
        this.models.addAll(Utility.getTasbihModel());
        this.models.add(new TasbihModel("Atur Hitungan Sendiri"));
    }

    @NonNull
    @Override
    public TasbihAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TasbihAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tasbih, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TasbihAdapter.ViewHolder holder, int position) {
        holder.nama_tasbih.setText(models.get(position).getJudul());
        holder.cardViewTasbih.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == models.size() - 1) {

                    final AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                    final View view = LayoutInflater.from(v.getContext()).inflate(R.layout.view_custom_dialog, null);

                    dialog.setView(view);
                    dialog.setCancelable(false);

                    AlertDialog alert = dialog.create();
                    alert.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

                    TextInputEditText inpAyat = view.findViewById(R.id.inpNamaBacaan);
                    TextInputEditText inpMax = view.findViewById(R.id.inpMaxHitungan);
                    AppCompatButton btnHitung = view.findViewById(R.id.btnHitung);
                    AppCompatButton btnBatal = view.findViewById(R.id.btnBatal);

                    btnBatal.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            alert.dismiss();
                        }
                    });

                    btnHitung.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            String ayat = inpAyat.getText().toString();
                            String max = inpMax.getText().toString();
                            if(ayat.equals("") || max.equals("")) {
                                Toast.makeText(v.getContext(), "Mohon isi field terlebih dahulu!" , Toast.LENGTH_SHORT).show();
                            } else if(Integer.parseInt(max) < 1) {
                                Toast.makeText(v.getContext(), "Maksimal hitungan harus lebih dari 0" , Toast.LENGTH_SHORT).show();
                            } else {
                                Intent intent = new Intent(v.getContext(), HitungTasbihLainnyaActivity.class);
                                intent.putExtra("inpAyat", inpAyat.getText().toString());
                                intent.putExtra("inpMax", Integer.parseInt(inpMax.getText().toString()));
                                alert.dismiss();
                                v.getContext().startActivity(intent);
                            }
                        }
                    });

                    alert.show();
                } else {
                    Intent intent = new Intent(v.getContext(), HitungTasbihActivity.class);
                    intent.putExtra("POSITION", position);
                    v.getContext().startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final CardView cardViewTasbih;
        private final TextView nama_tasbih;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cardViewTasbih = itemView.findViewById(R.id.cardViewTasbih);
            nama_tasbih = itemView.findViewById(R.id.nama_tasbih);
        }
    }
}

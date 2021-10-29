package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.appcompat.app.AlertDialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Toast;

import com.tomboati.tour.databinding.ActivityHitungTasbihBinding;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.activity.base.BaseNonToolbarActivity;

public class HitungTasbihActivity extends BaseNonToolbarActivity {

    private ActivityHitungTasbihBinding bind;
    private int countTasbeeh = 0;
    private final int max = 33;
    private int timeVibrate = 40;
    private int index = 0;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityHitungTasbihBinding.inflate(getLayoutInflater());

        index = intent.getIntExtra("POSITION", 0);
        countTasbeeh = Utility.getTasbihModel().get(index).getCount_tasbeeh();

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        if(index == 0) {
            bind.imgPrev.setVisibility(View.GONE);
        }

        if(index == Utility.getTasbihModel().size() - 1) {
            bind.imgNext.setVisibility(View.GONE);
        }

        bind.imgPrev.setOnClickListener(v -> {
            Utility.getTasbihModel().get(index).setCount_tasbeeh(countTasbeeh);

            index--;

            countTasbeeh = Utility.getTasbihModel().get(index).getCount_tasbeeh();

            timeVibrate = 40;
            onStart();

            if(index == 0) {
                bind.imgPrev.setVisibility(View.GONE);
            }else if(index < Utility.getTasbihModel().size() - 1) {
                bind.imgNext.setVisibility(View.VISIBLE);
            }
        });

        bind.imgNext.setOnClickListener(v -> {
            Utility.getTasbihModel().get(index).setCount_tasbeeh(countTasbeeh);

            index++;
            timeVibrate = 40;

            countTasbeeh = Utility.getTasbihModel().get(index).getCount_tasbeeh();
            onStart();

            if(index == Utility.getTasbihModel().size() - 1) {
                bind.imgNext.setVisibility(View.GONE);
            }

            if(index > 0) {
                bind.imgPrev.setVisibility(View.VISIBLE);
            }
        });

        bind.imgAdd.setOnClickListener(v -> {
            if(countTasbeeh < max){
                countTasbeeh++;
            } else {
                timeVibrate = 1000;
                Toast.makeText(v.getContext(), "Hitungan tasbih anda sudah maksimal", Toast.LENGTH_SHORT).show();
            }


            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                final VibrationEffect effect = VibrationEffect.createOneShot(timeVibrate, VibrationEffect.EFFECT_TICK);
                vibrator.cancel();
                vibrator.vibrate(effect);
            }

            bind.txtCount.setText(String.valueOf(countTasbeeh));
            bind.bar.setProgress((countTasbeeh / (float) max) * 100, true);
        });

        bind.imgMinus.setOnClickListener(v -> {
            if(countTasbeeh > 0) {
                countTasbeeh--;
                timeVibrate = 40;


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    final VibrationEffect effect = VibrationEffect.createOneShot(timeVibrate,
                            VibrationEffect.EFFECT_TICK);
                    vibrator.cancel();
                    vibrator.vibrate(effect);
                }

                bind.txtCount.setText(String.valueOf(countTasbeeh));
                bind.bar.setProgress((countTasbeeh / (float) max) * 100, true);
            }
        });

        bind.imgReload.setOnClickListener(v -> {
            if(countTasbeeh > 0) {
                AlertDialog.Builder alert = new AlertDialog.Builder(v.getContext());
                alert.setTitle("Konfirmasi!");
                alert.setMessage("Apakah anda yakin ingin mengatur ulang?");
                alert.setCancelable(false);
                alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        countTasbeeh = 0;
                        timeVibrate = 40;
                        bind.txtCount.setText(String.valueOf(countTasbeeh));
                        bind.bar.setProgress((countTasbeeh / (float) max) * 100, true);
                    }
                });
                alert.show();
            }
        });

        bind.back.setOnClickListener(v -> {
            Utility.getTasbihModel().get(index).setCount_tasbeeh(countTasbeeh);
            finish();
        });
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

    @Override
    protected void onStart() {
        super.onStart();
        bind.txtCount.setText("" + Utility.getTasbihModel().get(index).getCount_tasbeeh());
        bind.textJudul.setText(Utility.getTasbihModel().get(index).getJudul());
        bind.textArabic.setText(Utility.getTasbihModel().get(index).getArabic());
        bind.textTranslate.setText(Utility.getTasbihModel().get(index).getTranslate());
        bind.bar.setProgress((countTasbeeh / (float) max) * 100, true);
    }
}
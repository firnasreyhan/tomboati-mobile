package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.ramijemli.percentagechartview.PercentageChartView;
import com.tomboati.tour.databinding.ActivityHitungTasbihLainnyaBinding;
import com.tomboati.tour.view.activity.base.BaseNonToolbarActivity;

public class HitungTasbihLainnyaActivity extends BaseNonToolbarActivity {

    private ActivityHitungTasbihLainnyaBinding bind;
    private int countTasbeeh = 0;
    private int max = 33;
    private int timeVibrate =40;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);

        bind = ActivityHitungTasbihLainnyaBinding.inflate(getLayoutInflater());

        max = this.getIntent().getIntExtra("inpMax", max);

        bind.txtCount.setText(String.valueOf(countTasbeeh));
        bind.textMax.setText("Dibaca ".concat(String.valueOf(this.getIntent().getIntExtra("inpMax", max))).concat(" kali"));
        bind.textAyat.setText("\"".concat(this.getIntent().getStringExtra("inpAyat")).concat("\""));
        bind.bar.setProgress((countTasbeeh / (float) max) * 100, true);

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

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
                    final VibrationEffect effect = VibrationEffect.createOneShot(timeVibrate, VibrationEffect.EFFECT_TICK);
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
                        bind.bar.setProgress(0, true);
                    }
                });
                alert.show();
            }
        });

        bind.back.setOnClickListener(v -> onBackPressed());
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }
}
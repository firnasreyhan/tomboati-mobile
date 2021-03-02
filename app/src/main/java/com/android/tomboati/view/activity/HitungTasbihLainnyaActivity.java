package com.android.tomboati.view.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.utils.Utility;
import com.ramijemli.percentagechartview.PercentageChartView;

public class HitungTasbihLainnyaActivity extends AppCompatActivity {

    private int count_tasbeeh = 0;
    private int max = 33;
    private int time_vibrate =40;

    private TextView text_count, text_max, text_ayat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_hitung_tasbih_lainnya);

        PercentageChartView chartView = findViewById(R.id.bar);
        chartView.setProgress(count_tasbeeh, true);

        text_count = findViewById(R.id.txt_count);
        text_max = findViewById(R.id.text_max);
        text_ayat = findViewById(R.id.text_ayat);

        max = this.getIntent().getIntExtra("inpMax", max);

        text_count.setText("" + count_tasbeeh);
        text_max.setText("Dibaca " + this.getIntent().getIntExtra("inpMax", max) + " kali");
        text_ayat.setText("\"" + this.getIntent().getStringExtra("inpAyat") + "\"");

        final Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        findViewById(R.id.img_add).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_tasbeeh < max){
                    count_tasbeeh++;
                }

                if(count_tasbeeh == max) {
                    time_vibrate = 1000;
                    Toast.makeText(v.getContext(), "Hitungan tasbih anda sudah maksimal", Toast.LENGTH_SHORT).show();
                }


                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    final VibrationEffect effect = VibrationEffect.createOneShot(time_vibrate,
                            VibrationEffect.EFFECT_TICK);
                    vibrator.cancel();
                    vibrator.vibrate(effect);
                }

                text_count.setText("" + count_tasbeeh);
                chartView.setProgress((count_tasbeeh / (float) max) * 100, true);
            }
        });

        findViewById(R.id.img_minus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_tasbeeh > 0) {
                    count_tasbeeh--;
                    time_vibrate = 40;


                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        final VibrationEffect effect = VibrationEffect.createOneShot(time_vibrate,
                                VibrationEffect.EFFECT_TICK);
                        vibrator.cancel();
                        vibrator.vibrate(effect);
                    }

                    text_count.setText("" + count_tasbeeh);
                    chartView.setProgress((count_tasbeeh / (float) max) * 100, true);
                }
            }
        });

        findViewById(R.id.img_reload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(count_tasbeeh > 0) {
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
                            count_tasbeeh = 0;
                            time_vibrate = 40;
                            text_count.setText("" + count_tasbeeh);
                            chartView.setProgress(0, true);
                        }
                    });
                    alert.show();
                }
            }
        });

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
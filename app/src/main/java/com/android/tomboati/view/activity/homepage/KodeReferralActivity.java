package com.android.tomboati.view.activity.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.model.AkunModel;
import com.android.tomboati.preference.PreferenceAkun;
import com.google.android.material.button.MaterialButton;

public class KodeReferralActivity extends AppCompatActivity {

    private TextView textViewReferralFrom, textViewKodeReferral;
    private MaterialButton materialButtonCopy, materialButtonShare;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_kode_referral);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Kode Referral");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        textViewReferralFrom = findViewById(R.id. textViewReferralFrom);
        textViewKodeReferral = findViewById(R.id. textViewKodeReferral);
        materialButtonCopy = findViewById(R.id. materialButtonCopy);
        materialButtonShare = findViewById(R.id. materialButtonShare);

        AkunModel data = PreferenceAkun.getAkun(this);

        textViewReferralFrom.setText(data.getReferral());

        if(data.getPaket().equals("MITRA")) {
            textViewKodeReferral.setText(data.getUserId());
        }

        materialButtonCopy.setOnClickListener(v -> {
            if(data.getPaket().equals("MITRA")) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Referral", data.getUserId());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(v.getContext(), "Berhasil dicopy!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(v.getContext(), "Silahkan mendaftar sebagai mitra!", Toast.LENGTH_SHORT).show();
            }
        });

        materialButtonShare.setOnClickListener(v -> {
            if(data.getPaket().equals("MITRA")) {
                final String URI_APK = "";
                final String PREFIX = "Tombo Ati Tour membuka peluang secara terbuka kepada masyarakat " +
                        "umum untuk bersama - sama menjadi Freelance Inspirasi Baitullah dekat " +
                        "di Hati.\n\nBentuk peluang yang diberikan berupa kesempatan untuk " +
                        "menjadi Mitra atau sebagai Kantor Cabang di daerah. \n\n" +
                        "Silahkan ikuti langkah berikut ini : " +
                        "1. Download aplikasi kami di playstore melalui link berikut : \n" + URI_APK +
                        "2. Silahkan lakukan pendaftaran menggunakan nomor hp anda, dan jangan " +
                        "lupa untuk memasukkan kode referral \"" + data.getUserId() + "\" ya. \n\n" +
                        "Terima kasih dan salam hangat";
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, PREFIX);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            } else {
                Toast.makeText(v.getContext(), "Silahkan mendaftar sebagai mitra!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
package com.tomboati.tour.view.activity.homepage;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.preference.PreferenceAkun;
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
                final String REFERRAL_ME = data.getUserId();
                final String[] NAME = data.getName().split(" ");
                String PHONE = data.getHphone();
                if (PHONE.charAt(0) == '0') {
                    PHONE = PHONE.replaceFirst("0", "62");
                } else if (PHONE.charAt(0) == '+') {
                    PHONE = PHONE.replaceFirst("\\+", "");
                }
                final String URI_APK = "https://tomboatitour.biz/apps";
                final String WA_ME = "https://api.whatsapp.com/send?phone=" + PHONE;
                final String TEXT =
                        "SPECIAL UNTUK ANDA\n\n" +
                        "Unduh Aplikasinya - Banyak Manfaatnya\n" +
                        "GRATIS\n\n" +
                        "- Al Qur'an\n" +
                        "- Panduan Sholat\n" +
                        "- Panduan Doa dan Dzikir\n" +
                        "- Doa Umroh &  Haji\n" +
                        "- Live Mekkah dll\n\n" +
                        "klik \uD83D\uDC47:\n" +
                        URI_APK + "\n\n" +
                        "Cukup masukkan\n" +
                        "- no HP Anda\n" +
                        "- referral : " + REFERRAL_ME + "\n\n" +
                        "Dapatkan kesempatan UMROH GRATIS dengan cukup mereferensikan Aplikasi ini.\n\n" +
                        "Info : (" + NAME[0]  + (NAME.length > 1 ? " " + NAME[1] : "") + ")\n" +
                        WA_ME;
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, TEXT);
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
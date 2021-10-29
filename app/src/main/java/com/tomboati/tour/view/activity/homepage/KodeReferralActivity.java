package com.tomboati.tour.view.activity.homepage;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.databinding.ActivityKodeReferralBinding;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;

public class KodeReferralActivity extends BaseToolbarActivity {

    private ActivityKodeReferralBinding bind;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityKodeReferralBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Kode Referral");
        AkunModel data = PreferenceAkun.getAkun(this);

        bind.setReferralFrom(data.getReferral());

        if(data.getPaket().equals("MITRA")) {
            bind.setReferralMe(data.getUserId());
        }

        bind.materialButtonCopy.setOnClickListener(v -> {
            if(data.getPaket().equals("MITRA")) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Referral", data.getUserId());
                clipboardManager.setPrimaryClip(clipData);
                showToast("Referral berhasil dicopy!");
            } else {
                showToast("Silahkan mendaftar sebagai mitra!");
            }
        });

        bind.materialButtonShare.setOnClickListener(v -> {
            if(data.getPaket().equals("MITRA")) {
                final String REFERRAL_ME = data.getUserId();
                String phone = data.getHphone();
                if (phone.charAt(0) == '0') {
                    phone = phone.replaceFirst("0", "62");
                } else if (phone.charAt(0) == '+') {
                    phone = phone.replaceFirst("\\+", "");
                }
                final String URI_APK = "https://tomboatitour.biz/apps";
                final String URI_WA_ME = "https://wa.me/" + phone;
                final String TEXT =
                        "_Assalamualaikum Wr. Wb._\n\n" +
                                "*SPECIAL UNTUK ANDA*\n\n" +
                                "Unduh Aplikasinya - Banyak Manfaatnya\n" +
                                "*GRATIS*\n\n" +
                                "- Al Qur'an\n" +
                                "- Panduan Sholat\n" +
                                "- Panduan Doa dan Dzikir\n" +
                                "- Doa Umroh &  Haji\n" +
                                "- Live Mekkah dll\n\n" +
                                "klik \uD83D\uDC47:\n" +
                                URI_APK + "\n\n" +
                                "Cukup masukkan\n" +
                                "- no HP Anda\n" +
                                "- referral : *" + REFERRAL_ME + "*\n\n" +
                                "Dapatkan kesempatan *UMROH GRATIS* 10 Free 1, Wisata dan Poin Hadiah menarik dengan mereferensikan Aplikasi ini.\n\n" +
                                "Info : " + URI_WA_ME;
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, TEXT);
                sendIntent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(sendIntent, null);
                startActivity(shareIntent);
            } else {
                showToast("Silahkan mendaftar sebagai mitra!");
            }
        });
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

}
package com.tomboati.tour.view.activity.homepage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.databinding.ActivityUmrohHajiBinding;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;
import com.tomboati.tour.view.activity.pendaftaran.ListPaketActivity;
import com.tomboati.tour.view.activity.pendaftaran.ListPaketHajiActivity;

public class UmrohHajiActivity extends BaseToolbarActivity {

    private ActivityUmrohHajiBinding bind;
    private final String PAKET = "PAKET";
    private final String PAKET_HAJI = "PAKET_HAJI";
    private final Activity CLASS_PAKET = new ListPaketActivity();
    private final Activity CLASS_PAKET_HAJI = new ListPaketHajiActivity();

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityUmrohHajiBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Umroh & Haji");

        bind.cardViewUmrohPromo.setOnClickListener(v -> setIntentPaket(PAKET, "Umroh Promo", "Promo", CLASS_PAKET));
        bind.cardViewUmrohHemat.setOnClickListener(v -> setIntentPaket(PAKET, "Umroh Hemat", "Hemat", CLASS_PAKET));
        bind.cardViewUmrohBisnis.setOnClickListener(v -> setIntentPaket(PAKET, "Umroh Bisnis", "Bisnis", CLASS_PAKET));
        bind.cardViewUmrohVIP.setOnClickListener(v -> setIntentPaket(PAKET, "Umroh VIP", "VIP", CLASS_PAKET));
        bind.cardViewUmrohPlus.setOnClickListener(v -> setIntentPaket(PAKET, "Umroh Plus", "Plus", CLASS_PAKET));

        bind.cardViewHajiReguler.setOnClickListener(v -> setIntentPaket(PAKET_HAJI, "Haji Reguler", "Reguler", CLASS_PAKET_HAJI));
        bind.cardViewHajiPlus.setOnClickListener(v -> setIntentPaket(PAKET_HAJI, "Haji Plus", "Plus", CLASS_PAKET));
        bind.cardViewHajiTanpaAntri.setOnClickListener(v -> setIntentPaket(PAKET_HAJI, "Haji Tanpa Antri", "TanpaAntri", CLASS_PAKET));
        bind.cardViewHajiTalangan.setOnClickListener(v -> setIntentPaket(PAKET_HAJI, "Haji Talangan", "Talangan", CLASS_PAKET_HAJI));
        bind.cardViewHajiBadal.setOnClickListener(v -> setIntentPaket(PAKET_HAJI, "Haji Badal", "Badal", CLASS_PAKET_HAJI));

    }

    private void setIntentPaket(String keyPaket, String title, String paket, Activity activityCompat) {
        Intent intent = new Intent(this, activityCompat.getClass());
        intent.putExtra(keyPaket, paket);
        intent.putExtra("TITLE", title);
        startActivity(intent);
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

}
package com.tomboati.tour.view.activity.homepage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.databinding.ActivityWisataReligiBinding;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;
import com.tomboati.tour.view.activity.pendaftaran.ListPaketActivity;

public class WisataReligiActivity extends BaseToolbarActivity {

    private ActivityWisataReligiBinding bind;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityWisataReligiBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Wisata Halal & Tour Religi");
        bind.materialCardViewWisataInternasional.setOnClickListener(v -> setIntentExtra("Internasional", "Wisata Internasional"));
        bind.materialCardViewWisataNasional.setOnClickListener(v -> setIntentExtra("Nasional", "Wisata Nasional"));
        bind.materialCardViewZiarahWali.setOnClickListener(v -> setIntentExtra("ZiarahWali", "Wisata Ziarah Wali"));
    }

    private void setIntentExtra(String paketWisata, String title) {
        Intent intentPaket = new Intent(this, ListPaketActivity.class);
        intentPaket.putExtra("PAKET_WISATA", paketWisata);
        intentPaket.putExtra("TITLE", title);
        startActivity(intentPaket);
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

}
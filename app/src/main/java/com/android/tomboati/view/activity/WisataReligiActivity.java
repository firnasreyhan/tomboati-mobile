package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.view.activity.pendaftaran.ListPaketActivity;
import com.google.android.material.card.MaterialCardView;

public class WisataReligiActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private MaterialCardView materialCardViewWisataInternasional, materialCardViewWisataNasional, materialCardViewZiarahWali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_wisata_religi);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Wisata Halal & Tour Religi");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        materialCardViewWisataInternasional = findViewById(R.id.materialCardViewWisataInternasional);
        materialCardViewWisataNasional = findViewById(R.id.materialCardViewWisataNasional);
        materialCardViewZiarahWali = findViewById(R.id.materialCardViewZiarahWali);

        materialCardViewWisataInternasional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPaketActivity.class);
                intent.putExtra("PAKET_WISATA", "Internasional");
                intent.putExtra("TITLE", "Wisata Internasional");
                startActivity(intent);
            }
        });

        materialCardViewWisataNasional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPaketActivity.class);
                intent.putExtra("PAKET_WISATA", "Nasional");
                intent.putExtra("TITLE", "Wisata Nasional");
                startActivity(intent);
            }
        });

        materialCardViewZiarahWali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ListPaketActivity.class);
                intent.putExtra("PAKET_WISATA", "ZiarahWali");
                intent.putExtra("TITLE", "Wisata Ziarah Wali");
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
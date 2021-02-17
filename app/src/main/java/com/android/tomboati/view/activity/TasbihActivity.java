package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.android.tomboati.R;
import com.android.tomboati.adapter.DoaHajiUmrahAdapter;
import com.android.tomboati.adapter.TasbihAdapter;
import com.android.tomboati.model.DoaHajiUmrahModel;
import com.android.tomboati.utils.Utility;

import java.util.List;

import okhttp3.internal.Util;

public class TasbihActivity extends AppCompatActivity {

    private RecyclerView recyclerViewTasbih;
    private TasbihAdapter tasbihAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_tasbih);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Tasbih");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewTasbih = findViewById(R.id.recyclerViewTasbih);
        recyclerViewTasbih.setHasFixedSize(true);
        recyclerViewTasbih.setLayoutManager(new LinearLayoutManager(this));
        tasbihAdapter = new TasbihAdapter();
        recyclerViewTasbih.setAdapter(tasbihAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
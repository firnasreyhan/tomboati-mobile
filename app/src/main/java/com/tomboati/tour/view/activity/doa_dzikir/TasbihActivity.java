package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.tomboati.tour.R;
import com.tomboati.tour.adapter.TasbihAdapter;

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
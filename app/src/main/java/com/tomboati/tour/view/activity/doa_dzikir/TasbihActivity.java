package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import com.tomboati.tour.R;
import com.tomboati.tour.adapter.TasbihAdapter;
import com.tomboati.tour.databinding.ActivityTasbihBinding;

public class TasbihActivity extends AppCompatActivity {

    private ActivityTasbihBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        bind = ActivityTasbihBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setSupportActionBar(bind.toolbar);
        setTitle("Tasbih");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        bind.recyclerViewTasbih.setHasFixedSize(true);
        bind.recyclerViewTasbih.setLayoutManager(new LinearLayoutManager(this));
        bind.recyclerViewTasbih.setAdapter(new TasbihAdapter());
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.adapter.PaketAdapter;
import com.android.tomboati.model.PaketModel;

import java.util.ArrayList;

public class ListPaketActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerViewPaket;
    private PaketAdapter paketAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_list_paket);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Paket");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewPaket = findViewById(R.id.recyclerViewPaket);

        ArrayList<PaketModel> list = new ArrayList<>();
        list.add(new PaketModel("1"));
        list.add(new PaketModel("2"));
        list.add(new PaketModel("3"));
        list.add(new PaketModel("4"));
        list.add(new PaketModel("5"));

        paketAdapter = new PaketAdapter(list);
        recyclerViewPaket.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPaket.setAdapter(paketAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
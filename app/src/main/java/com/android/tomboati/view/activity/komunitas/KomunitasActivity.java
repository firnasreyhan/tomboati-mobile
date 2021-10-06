package com.android.tomboati.view.activity.komunitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.android.tomboati.R;
import com.android.tomboati.adapter.KomunitasAdapter;
import com.android.tomboati.api.response.KomunitasResponse;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.viewmodel.tomboati.homepage.KomunitasViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

public class KomunitasActivity extends AppCompatActivity {

    private KomunitasViewModel komunitasViewModel;
    private RecyclerView recyclerViewKomunitas;
    private ShimmerFrameLayout shimmerFrameLayoutKomunitas;
    private KomunitasAdapter komunitasAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_komunitas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Komunitas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        recyclerViewKomunitas = findViewById(R.id.recyclerViewKomunitas);
        shimmerFrameLayoutKomunitas = findViewById(R.id.shimmerFrameLayoutKomunitas);

        if(Utility.isConnecting(this)) {

            komunitasViewModel = ViewModelProviders.of(this).get(KomunitasViewModel.class);

            recyclerViewKomunitas.setHasFixedSize(true);
            recyclerViewKomunitas.setLayoutManager(new LinearLayoutManager(this));

            komunitasViewModel.getKomunitas().observe(this, new Observer<List<KomunitasResponse.Datum>>() {
                @Override
                public void onChanged(List<KomunitasResponse.Datum> data) {
//                    Log.d("DATA SIZE : ", "" + data.size());
                    komunitasAdapter = new KomunitasAdapter(data);
                    recyclerViewKomunitas.setAdapter(komunitasAdapter);

                    recyclerViewKomunitas.setVisibility(View.VISIBLE);
                    shimmerFrameLayoutKomunitas.setVisibility(View.GONE);
                    shimmerFrameLayoutKomunitas.stopShimmer();
                }
            });


        }

    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutKomunitas.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutKomunitas.stopShimmer();
        super.onPause();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.adapter.PaketAdapter;
import com.android.tomboati.api.response.PaketMonthResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.model.BulanModel;
import com.android.tomboati.model.PaketModel;
import com.android.tomboati.viewmodel.ListPaketViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;

public class ListPaketActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerViewPaket;
    private PaketAdapter paketAdapter;
    private ListPaketViewModel listPaketViewModel;
    private ShimmerFrameLayout shimmerFrameLayoutPaket;
    private LinearLayout linearLayoutPaket, linearLayoutNoPaket;
    private Spinner spinnerBulan;

    private ArrayList<BulanModel> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        listPaketViewModel = ViewModelProviders.of(this).get(ListPaketViewModel.class);
        setContentView(R.layout.activity_list_paket);

        String paket = getIntent().getStringExtra("PAKET");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Paket");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewPaket = findViewById(R.id.recyclerViewPaket);
        shimmerFrameLayoutPaket = findViewById(R.id.shimmerFrameLayoutPaket);
        linearLayoutPaket = findViewById(R.id.linearLayoutPaket);
        linearLayoutNoPaket = findViewById(R.id.linearLayoutNoPaket);
        spinnerBulan = findViewById(R.id.spinnerBulan);
        recyclerViewPaket.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPaket.setHasFixedSize(true);

        listPaketViewModel.getPaketMonth(
                paket
        ).observe(this, new Observer<PaketMonthResponse>() {
            @Override
            public void onChanged(PaketMonthResponse paketMonthResponse) {
                if (!paketMonthResponse.isError()) {
                    if (!paketMonthResponse.getData().getBulan().isEmpty()) {
                        list.add(new BulanModel("99","Semaunya"));
                        for (String nomor: paketMonthResponse.getData().getBulan()) {
                            String bulan = "";
                            if (nomor.equalsIgnoreCase("01")) {
                                bulan = "Januari";
                            } else if (nomor.equalsIgnoreCase("02")) {
                                bulan = "Februari";
                            } else if (nomor.equalsIgnoreCase("03")) {
                                bulan = "Maret";
                            } else if (nomor.equalsIgnoreCase("04")) {
                                bulan = "April";
                            } else if (nomor.equalsIgnoreCase("05")) {
                                bulan = "Mei";
                            } else if (nomor.equalsIgnoreCase("06")) {
                                bulan = "Juni";
                            } else if (nomor.equalsIgnoreCase("07")) {
                                bulan = "Juli";
                            } else if (nomor.equalsIgnoreCase("08")) {
                                bulan = "Agustus";
                            } else if (nomor.equalsIgnoreCase("09")) {
                                bulan = "September";
                            } else if (nomor.equalsIgnoreCase("10")) {
                                bulan = "Oktober";
                            } else if (nomor.equalsIgnoreCase("11")) {
                                bulan = "November";
                            } else if (nomor.equalsIgnoreCase("12")) {
                                bulan = "Desember";
                            }
                            list.add(new BulanModel(nomor, bulan));
                        }

                        ArrayAdapter<BulanModel> adapter = new ArrayAdapter<BulanModel>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, list);
                        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);

                        spinnerBulan.setAdapter(adapter);
                    }
                }
            }
        });

        listPaketViewModel.getPaket(
                paket
        ).observe(this, new Observer<PaketResponse>() {
            @Override
            public void onChanged(PaketResponse paketResponse) {
                if (!paketResponse.isError()) {
                    if (!paketResponse.getData().isEmpty()) {
                        paketAdapter = new PaketAdapter(paketResponse.getData());
                        recyclerViewPaket.setAdapter(paketAdapter);

                        linearLayoutPaket.setVisibility(View.VISIBLE);
                        linearLayoutNoPaket.setVisibility(View.GONE);
                    } else {
                        linearLayoutPaket.setVisibility(View.GONE);
                        linearLayoutNoPaket.setVisibility(View.VISIBLE);
                    }
                } else {
                    linearLayoutPaket.setVisibility(View.GONE);
                    linearLayoutNoPaket.setVisibility(View.VISIBLE);
                }
                shimmerFrameLayoutPaket.setVisibility(View.GONE);
                shimmerFrameLayoutPaket.stopShimmer();
            }
        });

        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutPaket.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutPaket.stopShimmer();
        super.onPause();
    }
}
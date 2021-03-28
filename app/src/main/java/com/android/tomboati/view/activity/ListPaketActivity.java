package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.android.tomboati.R;
import com.android.tomboati.adapter.PaketAdapter;
import com.android.tomboati.adapter.PaketWisataAdapter;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.PaketMonthResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.api.response.PaketWisataResponse;
import com.android.tomboati.model.BulanModel;
import com.android.tomboati.viewmodel.ListPaketViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class ListPaketActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPaket;
    private PaketAdapter paketAdapter;
    private PaketWisataAdapter paketWisataAdapter;
    private ListPaketViewModel listPaketViewModel;
    private ShimmerFrameLayout shimmerFrameLayoutPaket;
    private LinearLayout linearLayoutPaket, linearLayoutNoPaket;
    private Spinner spinnerBulan;

    private ArrayList<BulanModel> list;

    private final String[] month = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        listPaketViewModel = ViewModelProviders.of(this).get(ListPaketViewModel.class);
        setContentView(R.layout.activity_list_paket);

        list = new ArrayList<>();

        String paket = getIntent().getStringExtra("PAKET");
        String paketHaji = getIntent().getStringExtra("PAKET_HAJI");
        String paketWisata = getIntent().getStringExtra("PAKET_WISATA");
        String title = getIntent().getStringExtra("TITLE");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewPaket = findViewById(R.id.recyclerViewPaket);
        shimmerFrameLayoutPaket = findViewById(R.id.shimmerFrameLayoutPaket);
        linearLayoutPaket = findViewById(R.id.linearLayoutPaket);
        linearLayoutNoPaket = findViewById(R.id.linearLayoutNoPaket);
        spinnerBulan = findViewById(R.id.spinnerBulan);
        recyclerViewPaket.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewPaket.setHasFixedSize(true);

        list.add(new BulanModel("99","Semuanya"));
        setSpinnerAdapter(list);

        MutableLiveData<PaketMonthResponse> paketMonthResponse = null;

        if(paket != null) {
            paketMonthResponse = listPaketViewModel.getPaketMonth(paket);
        } else if(paketHaji != null) {
            paketMonthResponse = listPaketViewModel.getPaketHajiMonth(paketHaji);
        } else {
            paketMonthResponse = listPaketViewModel.getPaketWisataMonth(paketWisata);
        }

        paketMonthResponse.observe(this, new Observer<PaketMonthResponse>() {
            @Override
            public void onChanged(PaketMonthResponse paketMonthResponse) {
                if(!paketMonthResponse.isError()) {
                    if(!paketMonthResponse.isError()) {
                        for (String no: paketMonthResponse.getData().getBulan()) {
                            list.add(new BulanModel(no, month[Integer.parseInt(no) - 1]));
                        }
                        setSpinnerAdapter(list);
                    }
                }
            }
        });

        spinnerBulan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                shimmerFrameLayoutPaket.startShimmer();
                shimmerFrameLayoutPaket.setVisibility(View.VISIBLE);
                linearLayoutNoPaket.setVisibility(View.GONE);

                if (paket != null) {
                    getPaket(paket, list.get(position).getUrutan(), true);
                } else if (paketHaji != null) {
                    getPaket(paketHaji, list.get(position).getUrutan(), false);
                } else {
                    getPaketWisata(paketWisata, list.get(position).getUrutan());
                }
            }

            @Override public void onNothingSelected(AdapterView<?> parent) { }
        });
    }

    private void getPaket(String paket, String urutan, boolean isPaketUmrah) {
        MutableLiveData<PaketResponse> data = isPaketUmrah ?
            listPaketViewModel.getPaket(paket, urutan)
                :
            listPaketViewModel.getPaketHaji(paket, urutan);
        data.observe(this, new Observer<PaketResponse>() {
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
    }

    private void getPaketWisata(String paket, String urutan) {
        listPaketViewModel.getPaketWisata(paket, urutan).observe(this, new Observer<PaketWisataResponse>() {
            @Override
            public void onChanged(PaketWisataResponse paketWisataResponse) {
                if (!paketWisataResponse.isError()) {
                    if (!paketWisataResponse.getData().isEmpty()) {
                        paketWisataAdapter = new PaketWisataAdapter(paketWisataResponse.getData());
                        recyclerViewPaket.setAdapter(paketWisataAdapter);

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

    }


    private void setSpinnerAdapter(List<BulanModel> list) {
        ArrayAdapter<BulanModel> adapter = new ArrayAdapter<BulanModel>(getApplicationContext(),  android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item);
        spinnerBulan.setAdapter(adapter);
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
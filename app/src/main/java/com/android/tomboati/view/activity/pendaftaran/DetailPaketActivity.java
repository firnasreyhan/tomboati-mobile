package com.android.tomboati.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.adapter.BiayaAdapter;
import com.android.tomboati.adapter.ItteneraryAdapter;
import com.android.tomboati.api.response.ItteneraryResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.api.response.PaketWisataResponse;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.view.activity.pendaftaran.SyaratActivity;
import com.android.tomboati.viewmodel.DetailPaketViewModel;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class DetailPaketActivity extends AppCompatActivity {
    private DetailPaketViewModel detailPaketViewModel;

    private Toolbar toolbar;
    private ImageView imageViewPaket, imageViewMaskapai;
    private TextView textViewNamaPaket, textViewQuad, textViewTriple, textViewDouble, textViewPenerbangan, textViewTempatHotelA, textViewNamaHotelA, textViewTempatHotelB, textViewNamaHotelB;
    private CardView cardViewQuad, cardViewTriple, cardViewDouble;
    private RecyclerView recyclerViewIttenerary, recyclerViewBiayaBelumTermasuk, recyclerViewBiayaSudahTermasuk;
    private String idPaket, idPaketWisata;
    private MaterialButton materialButtonPesan;

    private int sheet = 0;
    private double sheetHarga = 0;
    private double hargaQuad, hargaTriple, hargaDouble;
    private String tanggalKeberangkatan;
    private boolean isSheetSelected = false;

    private PesananaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_paket);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Detail Paket");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        idPaket = getIntent().getStringExtra("ID_PAKET");
        idPaketWisata = getIntent().getStringExtra("ID_PAKET_WISATA");

        detailPaketViewModel = ViewModelProviders.of(this).get(DetailPaketViewModel.class);

        imageViewPaket = findViewById(R.id.imageViewPaket);
        imageViewMaskapai = findViewById(R.id.imageViewMaskapai);
        textViewNamaPaket = findViewById(R.id.textViewNamaPaket);
        textViewQuad = findViewById(R.id.textViewQuad);
        textViewTriple = findViewById(R.id.textViewTriple);
        textViewDouble = findViewById(R.id.textViewDouble);
        textViewPenerbangan = findViewById(R.id.textViewPenerbangan);
        textViewTempatHotelA = findViewById(R.id.textViewTempatHotelA);
        textViewNamaHotelA = findViewById(R.id.textViewNamaHotelA);
        textViewTempatHotelB = findViewById(R.id.textViewTempatHotelB);
        textViewNamaHotelB = findViewById(R.id.textViewNamaHotelB);
        recyclerViewIttenerary = findViewById(R.id.recyclerViewIttenerary);
        recyclerViewBiayaBelumTermasuk = findViewById(R.id.recyclerViewBiayaBelumTermasuk);
        recyclerViewBiayaSudahTermasuk = findViewById(R.id.recyclerViewBiayaSudahTermasuk);
        cardViewDouble = findViewById(R.id.cardViewDouble);
        cardViewTriple = findViewById(R.id.cardViewTriple);
        cardViewQuad = findViewById(R.id.cardViewQuad);
        materialButtonPesan = findViewById(R.id.materialButtonPesan);

        recyclerViewIttenerary.setHasFixedSize(true);
        recyclerViewIttenerary.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewBiayaBelumTermasuk.setHasFixedSize(true);
        recyclerViewBiayaBelumTermasuk.setLayoutManager(new LinearLayoutManager(this));

        recyclerViewBiayaSudahTermasuk.setHasFixedSize(true);
        recyclerViewBiayaSudahTermasuk.setLayoutManager(new LinearLayoutManager(this));

        if (idPaket != null) {
            detailPaketViewModel.getPaket(idPaket).observe(this, new Observer<PaketResponse>() {
                @Override
                public void onChanged(PaketResponse paketResponse) {
                    if (!paketResponse.isError()) {
                        if (!paketResponse.getData().isEmpty()) {
                            textViewNamaPaket.setText(paketResponse.getData().get(0).getNamaPaket());

                            picassoLoad(paketResponse.getData().get(0).getImagePaket(), imageViewPaket);

                            textViewQuad.setText("Rp. ".concat(formatCurrency(paketResponse.getData().get(0).getQuadSheet())));
                            hargaQuad = paketResponse.getData().get(0).getQuadSheet();

                            textViewTriple.setText("Rp. ".concat(formatCurrency(paketResponse.getData().get(0).getTripleSheet())));
                            hargaTriple = paketResponse.getData().get(0).getTripleSheet();

                            textViewDouble.setText("Rp. ".concat(formatCurrency(paketResponse.getData().get(0).getDoubleSheet())));
                            hargaDouble = paketResponse.getData().get(0).getDoubleSheet();

                            tanggalKeberangkatan = paketResponse.getData().get(0).getTanggalKeberangkatan();

                            picassoLoad(paketResponse.getData().get(0).getImageMaskapai(), imageViewMaskapai);

                            textViewPenerbangan.setText(paketResponse.getData().get(0).getPenerbangan());
                            textViewTempatHotelA.setText(paketResponse.getData().get(0).getTempatHotelA());
                            textViewNamaHotelA.setText(paketResponse.getData().get(0).getNamaHotelA());
                            textViewTempatHotelB.setText(paketResponse.getData().get(0).getTempatHotelB());
                            textViewNamaHotelB.setText(paketResponse.getData().get(0).getNamaHotelB());

                            String biayaSudahTermasuk = paketResponse.getData().get(0).getBiayaSudahTermasuk();
                            recyclerViewBiayaSudahTermasuk.setAdapter(new BiayaAdapter(splitText(biayaSudahTermasuk)));

                            String biayaBelumTermasuk = paketResponse.getData().get(0).getBiayaBelumTermasuk();
                            recyclerViewBiayaBelumTermasuk.setAdapter(new BiayaAdapter(splitText(biayaBelumTermasuk)));
                        }
                    }
                }
            });
        } else {
            detailPaketViewModel.getPaketWisata(idPaketWisata).observe(this, new Observer<PaketWisataResponse>() {
                @Override
                public void onChanged(PaketWisataResponse paketWisataResponse) {
                    if (!paketWisataResponse.isError()) {
                        textViewNamaPaket.setText(paketWisataResponse.getData().get(0).getNamaWisata());

                        picassoLoad(paketWisataResponse.getData().get(0).getImageWisata(), imageViewPaket);

                        textViewQuad.setText("Rp. ".concat(formatCurrency(paketWisataResponse.getData().get(0).getQuadSheet())));
                        hargaQuad = paketWisataResponse.getData().get(0).getQuadSheet();

                        textViewTriple.setText("Rp. ".concat(formatCurrency(paketWisataResponse.getData().get(0).getTripleSheet())));
                        hargaTriple = paketWisataResponse.getData().get(0).getTripleSheet();

                        textViewDouble.setText("Rp. ".concat(formatCurrency(paketWisataResponse.getData().get(0).getDoubleSheet())));
                        hargaDouble = paketWisataResponse.getData().get(0).getDoubleSheet();

                        tanggalKeberangkatan = paketWisataResponse.getData().get(0).getTanggalKeberangkatan();

                        picassoLoad(paketWisataResponse.getData().get(0).getImageMaskapai(), imageViewMaskapai);

                        textViewPenerbangan.setText(paketWisataResponse.getData().get(0).getPenerbangan());
                        textViewTempatHotelA.setText(paketWisataResponse.getData().get(0).getTempatHotelA());
                        textViewNamaHotelA.setText(paketWisataResponse.getData().get(0).getNamaHotelA());
                        textViewTempatHotelB.setText(paketWisataResponse.getData().get(0).getTempatHotelB());
                        textViewNamaHotelB.setText(paketWisataResponse.getData().get(0).getNamaHotelB());

                        String biayaSudahTermasuk = paketWisataResponse.getData().get(0).getBiayaSudahTermasuk();
                        recyclerViewBiayaSudahTermasuk.setAdapter(new BiayaAdapter(splitText(biayaSudahTermasuk)));

                        String biayaBelumTermasuk = paketWisataResponse.getData().get(0).getBiayaBelumTermasuk();
                        recyclerViewBiayaBelumTermasuk.setAdapter(new BiayaAdapter(splitText(biayaBelumTermasuk)));
                    }
                }
            });
        }

        MutableLiveData<ItteneraryResponse> itteneraryResponse = (idPaket != null) ?
            detailPaketViewModel.getIttenerary(idPaket)
                :
            detailPaketViewModel.getItteneraryWisata(idPaketWisata)
        ;

        itteneraryResponse.observe(this, new Observer<ItteneraryResponse>() {
            @Override
            public void onChanged(ItteneraryResponse itteneraryResponse) {
                if (!itteneraryResponse.isError()) {
                    if (!itteneraryResponse.getData().isEmpty()) {
                        recyclerViewIttenerary.setAdapter(new ItteneraryAdapter(itteneraryResponse.getData()));
                    }
                }
            }
        });

        final CardView[] arrCardView = {cardViewDouble, cardViewTriple, cardViewQuad};
        final double[] arrSheetHarga = {hargaDouble, hargaTriple, hargaQuad};

        for (int i = 0; i < arrCardView.length; i++) {
            int j = i;
            arrCardView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    hrefButton(j + 2);
                    sheet = j + 2;
                    sheetHarga = arrSheetHarga[j];
                    isSheetSelected = true;
                }
            });
        }

        materialButtonPesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (AppPreference.getUser(v.getContext()) == null) {
                    Toast.makeText(v.getContext(), "Mohon mendaftar terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else if (!isSheetSelected) {
                    Toast.makeText(v.getContext(), "Mohon pilih paket terlebih dahulu", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(v.getContext(), SyaratActivity.class);
                    intent.putExtra("ID_PAKET", (idPaket == null) ? idPaketWisata : idPaket);
                    intent.putExtra("TANGGAL_BERANGKAT", tanggalKeberangkatan);
                    intent.putExtra("SHEET", String.valueOf(sheet));
                    intent.putExtra("SHEET_HARGA", String.valueOf(sheetHarga));
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @SuppressLint("DefaultLocale")
    private String formatCurrency(double amount) {
        return String.format("%,.0f%2s", amount, ",-");
    }

    private List<String> splitText(String text) {
        List<String> list = new ArrayList<>();
        String[] texts = text.split(">");
        for (String s : texts) {
            String sz = s + ">";
            sz = sz.replaceAll("<.*?>", "");
            sz = sz.replaceAll("&.*?;", "dan");
            if (!sz.isEmpty()) {
                list.add(sz);
            }
        }
        return list;
    }

    private void hrefButton(int index) {
        final CardView[] arrBtn = {cardViewDouble, cardViewTriple, cardViewQuad};
        for (int i = 2; i < arrBtn.length + 2; i++) {
            if (i == index) {
                arrBtn[i - 2].setCardBackgroundColor(getResources().getColor(R.color.light_green));
                continue;
            }
            arrBtn[i - 2].setCardBackgroundColor(getResources().getColor(R.color.dark_green));
        }
    }

    private void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }
}
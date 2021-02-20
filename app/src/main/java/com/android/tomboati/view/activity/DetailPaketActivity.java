package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.adapter.BiayaAdapter;
import com.android.tomboati.adapter.ItteneraryAdapter;
import com.android.tomboati.api.response.ItteneraryResponse;
import com.android.tomboati.api.response.PaketResponse;
import com.android.tomboati.viewmodel.DetailPaketViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;

public class DetailPaketActivity extends AppCompatActivity {
    private DetailPaketViewModel detailPaketViewModel;
    private Toolbar toolbar;
    private ImageView imageViewPaket, imageViewMaskapai;
    private TextView textViewNamaPaket, textViewQuad, textViewTriple, textViewDouble, textViewPenerbangan, textViewTempatHotelA, textViewNamaHotelA, textViewTempatHotelB, textViewNamaHotelB;
    private RecyclerView recyclerViewIttenerary, recyclerViewBiayaBelumTermasuk, recyclerViewBiayaSudahTermasuk;
    private String idPaket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_paket);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        idPaket = getIntent().getStringExtra("ID_PAKET");
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
        recyclerViewIttenerary.setHasFixedSize(true);
        recyclerViewIttenerary.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBiayaBelumTermasuk.setHasFixedSize(true);
        recyclerViewBiayaBelumTermasuk.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewBiayaSudahTermasuk.setHasFixedSize(true);
        recyclerViewBiayaSudahTermasuk.setLayoutManager(new LinearLayoutManager(this));

        detailPaketViewModel.getPaket(
                idPaket
        ).observe(this, new Observer<PaketResponse>() {
            @Override
            public void onChanged(PaketResponse paketResponse) {
                if (!paketResponse.isError()) {
                    if (!paketResponse.getData().isEmpty()) {
                        setTitle(paketResponse.getData().get(0).getNamaPaket());

                        Glide.with(DetailPaketActivity.this)
                                .load(paketResponse.getData().get(0).getImagePaket())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(true)
                                .dontAnimate()
                                .dontTransform()
                                .priority(Priority.IMMEDIATE)
                                .encodeFormat(Bitmap.CompressFormat.PNG)
                                .format(DecodeFormat.DEFAULT)
                                .placeholder(R.drawable.ic_logo)
                                .into(imageViewPaket);

                        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
                        decimalFormatSymbols.setDecimalSeparator(',');
                        DecimalFormat decimalFormat = new DecimalFormat("###,###,###,###", decimalFormatSymbols);

                        if (paketResponse.getData().get(0).getQuadSheet() != 0) {
                            textViewQuad.setText("Rp. " + decimalFormat.format(paketResponse.getData().get(0).getQuadSheet()));
                        }
                        if (paketResponse.getData().get(0).getTripleSheet() != 0) {
                            textViewTriple.setText("Rp. " + decimalFormat.format(paketResponse.getData().get(0).getTripleSheet()));
                        }
                        if (paketResponse.getData().get(0).getDoubleSheet() != 0) {
                            textViewDouble.setText("Rp. " + decimalFormat.format(paketResponse.getData().get(0).getDoubleSheet()));
                        }

                        Glide.with(DetailPaketActivity.this)
                                .load(paketResponse.getData().get(0).getImageMaskapai())
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(true)
                                .dontAnimate()
                                .dontTransform()
                                .priority(Priority.IMMEDIATE)
                                .encodeFormat(Bitmap.CompressFormat.PNG)
                                .format(DecodeFormat.DEFAULT)
                                .placeholder(R.drawable.ic_logo)
                                .into(imageViewMaskapai);

                        textViewPenerbangan.setText(paketResponse.getData().get(0).getPenerbangan());
                        textViewTempatHotelA.setText(paketResponse.getData().get(0).getTempatHotelA());
                        textViewNamaHotelA.setText(paketResponse.getData().get(0).getNamaHotelA());
                        textViewTempatHotelB.setText(paketResponse.getData().get(0).getTempatHotelB());
                        textViewNamaHotelB.setText(paketResponse.getData().get(0).getNamaHotelB());

                        List<String> listBST = new ArrayList<>();
                        String biayaSudahTermasuk = paketResponse.getData().get(0).getBiayaSudahTermasuk();
                        String[] sentecesBiayaSudahTermasuk = biayaSudahTermasuk.split(">");
                        for (int i = 0; i < sentecesBiayaSudahTermasuk.length; i++) {
                            String sz = sentecesBiayaSudahTermasuk[i] + ">";
                            sz = sz.replaceAll("\\<.*?\\>", "");
                            if (!sz.isEmpty()) {
                                listBST.add(sz);
                            }
                        }
                        recyclerViewBiayaSudahTermasuk.setAdapter(new BiayaAdapter(listBST));

                        List<String> listBBT = new ArrayList<>();
                        String biayaBelumTermasuk = paketResponse.getData().get(0).getBiayaBelumTermasuk();
                        String[] sentecesBiayaBelumTermasuk = biayaBelumTermasuk.split(">");
                        for (int i = 0; i < sentecesBiayaBelumTermasuk.length; i++) {
                            String sz = sentecesBiayaBelumTermasuk[i] + ">";
                            sz = sz.replaceAll("\\<.*?\\>", "");
                            if (!sz.isEmpty()) {
                                listBBT.add(sz);
                            }
                        }
                        recyclerViewBiayaBelumTermasuk.setAdapter(new BiayaAdapter(listBBT));
                    }
                }
            }
        });

        detailPaketViewModel.getIttenerary(
                idPaket
        ).observe(this, new Observer<ItteneraryResponse>() {
            @Override
            public void onChanged(ItteneraryResponse itteneraryResponse) {
                if (!itteneraryResponse.isError()) {
                    if (!itteneraryResponse.getData().isEmpty()) {
                        recyclerViewIttenerary.setAdapter(new ItteneraryAdapter(itteneraryResponse.getData()));
                    }
                }
            }
        });

        String s = "<ol><li><strong>testing</strong></li><li><i>testing</i></li></ol>";
        String[] senteces = s.split(">");
        Log.e("sizeSenteces", String.valueOf(senteces.length));
        for (int i = 0; i < senteces.length; i++) {
            String sz = senteces[i] + ">";
            Log.e("sz" + String.valueOf(i), sz.replaceAll("\\<.*?\\>", ""));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
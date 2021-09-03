package com.android.tomboati.view.activity.pembayaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.PembayaranResponse;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.view.activity.pendaftaran.Syarat2Activity;
import com.android.tomboati.viewmodel.PembayaranViewModel;
import com.google.android.material.button.MaterialButton;

public class InformasiPembayaranActivity extends AppCompatActivity {

    private PembayaranViewModel viewModel;
    private TextView textViewStatus, textViewIdPembayaran, textViewTotalPembayaran,
            textViewSisaPembayaran, textViewTanggalPembayaran, textViewJatuhTempo;
    private ConstraintLayout constraintLayoutNoSignIn, constraintLayoutSignIn;
    private MaterialButton materialButtonBayarSekarang1, materialButtonBayarSekarang, materialButtonLihatDetailPembayaran;
    private final String[] month = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"
    };

    private String idPembayaran = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_informasi_pembayaran);
        viewModel = ViewModelProviders.of(this).get(PembayaranViewModel.class);

        textViewStatus = findViewById(R.id.textViewStatus);
        textViewIdPembayaran = findViewById(R.id.textViewIdPembayaran);
        textViewTotalPembayaran = findViewById(R.id.textViewTotalPembayaran);
        textViewSisaPembayaran = findViewById(R.id.textViewSisaPembayaran);
        textViewTanggalPembayaran = findViewById(R.id.textViewTanggalPembayaran);
        textViewJatuhTempo = findViewById(R.id.textViewJatuhTempo);
        constraintLayoutNoSignIn = findViewById(R.id.constraintLayoutNoSignIn);
        constraintLayoutSignIn = findViewById(R.id.constraintLayoutSignIn);
        materialButtonBayarSekarang = findViewById(R.id.materialButtonBayarSekarang);
        materialButtonBayarSekarang1 = findViewById(R.id.materialButtonBayarSekarang1);
        materialButtonLihatDetailPembayaran = findViewById(R.id.materialButtonLihatDetailPembayaran);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Informasi Pembayaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        final String ID_TRANSAKSI = getIntent().getStringExtra("ID_TRANSAKSI");

        final AlertProgress progress = new AlertProgress(this, "Tunggu sebentar");
        progress.showDialog();
        viewModel.getPembayaran(ID_TRANSAKSI).observe(this, new Observer<PembayaranResponse>() {
            @SuppressLint("DefaultLocale")
            @Override
            public void onChanged(PembayaranResponse pembayaranResponse) {
                progress.dismissDialog();
                if(pembayaranResponse != null) {
                    if (!pembayaranResponse.isError()) {
                        constraintLayoutSignIn.setVisibility(View.VISIBLE);
                        constraintLayoutNoSignIn.setVisibility(View.GONE);
                        PembayaranResponse.DataItem data = pembayaranResponse.getData().get(0);
                        idPembayaran = data.getIDPEMBAYARAN();
                        final boolean isValidation = data.getSTATUSPEMBAYARAN().equals("1");
                        textViewStatus.setText(isValidation ? "Terverifikasi" : "Belum Terverifikasi");
                        textViewStatus.setBackgroundResource(isValidation ? R.drawable.round_bg : R.drawable.round_bg_red);
                        textViewIdPembayaran.setText(data.getIDPEMBAYARAN());
                        textViewTotalPembayaran.setText(String.format("Rp. %,.02f", Double.parseDouble(data.getTOTALPEMBAYARAN())));
                        textViewSisaPembayaran.setText(String.format("Rp. %,.02f", Double.parseDouble(data.getSISAPEMBAYARAN())));
                        Log.d("===", "onChanged: " + data.getTANGGALPEMBAYARAN());
//                        String[] split = data.getTANGGALPEMBAYARAN().split("-");
//                        textViewTanggalPembayaran.setText(split[2].concat(" ").concat(month[Integer.parseInt(split[1]) + 1].concat(" ").concat(split[0])));
//                        split = data.getTANGGALJATUHTEMPO().split("-");
//                        textViewJatuhTempo.setText(split[2].concat(" ").concat(month[Integer.parseInt(split[1]) + 1].concat(" ").concat(split[0])));
                    }
                }
            }
        });

        materialButtonLihatDetailPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), DetailPembayaranActivity.class);
                intent.putExtra("ID_PEMBAYARAN", idPembayaran);
                startActivity(intent);
            }
        });

        materialButtonBayarSekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Syarat2Activity.class);
                intent.putExtra("ID_TRANSAKSI", ID_TRANSAKSI);
                startActivity(intent);
            }
        });

        materialButtonBayarSekarang1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), Syarat2Activity.class);
                intent.putExtra("ID_TRANSAKSI", ID_TRANSAKSI);
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
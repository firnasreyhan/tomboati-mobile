package com.android.tomboati.view.activity.pendaftaran;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.preference.AppPreference;
import com.google.android.material.button.MaterialButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.Serializable;

public class PendaftaranDokumenDataDiriActivity extends AppCompatActivity {

    private CardView cardViewFotoKTP, cardViewFotoAkteKelahiran, cardViewFotoKartuKeluarga, cardViewFotoPaspor, cardViewFotoBukuNikah;
    private ImageView imageViewKTP, imageViewAkteKelahiran, imageViewKartuKeluarga, imageViewPaspor, imageViewBukuNikah;
    private MaterialButton materialButtonLanjutkan;

    private Uri uriKTP, uriAkteKelahiran, uriKartuKeluarga, uriFotoPaspor, uriFotoBukuNikah;
    private int uriNumber;
    private PesananaModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_pendaftaran_dokumen_data_diri);

        model = (PesananaModel) getIntent().getSerializableExtra("OBJECT");

        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Pendaftaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        cardViewFotoKTP = findViewById(R.id.cardViewFotoKTP);
        cardViewFotoAkteKelahiran = findViewById(R.id.cardViewFotoAkteKelahiran);
        cardViewFotoKartuKeluarga = findViewById(R.id.cardViewFotoKartuKeluarga);
        cardViewFotoPaspor = findViewById(R.id.cardViewFotoPaspor);
        cardViewFotoBukuNikah = findViewById(R.id.cardViewFotoBukuNikah);

        imageViewKTP = findViewById(R.id.imageViewKTP);
        imageViewAkteKelahiran = findViewById(R.id.imageViewAkteKelahiran);
        imageViewKartuKeluarga = findViewById(R.id.imageViewKartuKeluarga);
        imageViewPaspor = findViewById(R.id.imageViewPaspor);
        imageViewBukuNikah = findViewById(R.id.imageViewBukuNikah);

        materialButtonLanjutkan = findViewById(R.id.materialButtonLanjutkan);

        final CardView[] arrCardView = {
            cardViewFotoKTP, cardViewFotoAkteKelahiran, cardViewFotoKartuKeluarga, cardViewFotoPaspor, cardViewFotoBukuNikah
        };

        for(int i = 0; i < arrCardView.length; i++) {
            int j = i;
            arrCardView[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(PendaftaranDokumenDataDiriActivity.this);
                    uriNumber = j + 1;
                }
            });
        }

        materialButtonLanjutkan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkData()) {
                    model.setFileKTP(uriKTP.toString());
                    model.setFileAkteKelahiran(uriAkteKelahiran.toString());
                    model.setFileKK(uriKartuKeluarga.toString());
                    model.setFilePaspor(uriFotoPaspor.toString());
                    model.setFileBukuNikah((uriFotoBukuNikah == null) ? "" : uriFotoBukuNikah.toString());

                    Intent intent = new Intent(v.getContext(), PendaftaranDataKeluargaActivity.class);
                    intent.putExtra("OBJECT", (Serializable) model);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                switch (uriNumber) {
                    case 1:
                        uriKTP = result.getUri();
                        imageViewKTP.setImageURI(uriKTP);
                        break;
                    case 2:
                        uriAkteKelahiran = result.getUri();
                        imageViewAkteKelahiran.setImageURI(uriAkteKelahiran);
                        break;
                    case 3:
                        uriKartuKeluarga = result.getUri();
                        imageViewKartuKeluarga.setImageURI(uriKartuKeluarga);
                        break;
                    case 4:
                        uriFotoPaspor = result.getUri();
                        imageViewPaspor.setImageURI(uriFotoPaspor);
                        break;
                    case 5:
                        uriFotoBukuNikah = result.getUri();
                        imageViewBukuNikah.setImageURI(uriFotoBukuNikah);
                        break;
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                String error = result.getError().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkData() {

        int countError = 0;

        final Uri[] uriImg = {
                uriKTP, uriAkteKelahiran, uriKartuKeluarga, uriFotoPaspor
        };

        final String[] prefix = {
                "KTP", "akte kelahiran", "kartu keluarga", "paspor"
        };


            for(int i = 0; i < uriImg.length; i++) {
                if(uriImg[i] == null) {
                    Toast.makeText(this, "Harap upload foto " + prefix[i] + " anda", Toast.LENGTH_SHORT).show();
                    countError++;
                }
            }


        return (countError == 0);
    }










}
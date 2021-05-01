package com.android.tomboati.view.activity.pembayaran;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.view.activity.MainActivity;
import com.android.tomboati.view.activity.pendaftaran.PendaftaranDataDiriActivity;
import com.android.tomboati.viewmodel.PembayaranViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class FormPembayaranActivity extends AppCompatActivity {

    private Uri uriBuktiPembayaran = null;
    private ImageView imageViewBuktiPembayaran;
    private TextInputEditText textInputEditTextJumlahPembayaran, textInputEditTextTanggalPembayaran, textInputEditTextDeskripsi;
    private CardView cardViewFotoBuktiPembayaran;
    private MaterialButton materialButtonSimpanPembayaran;

    private PembayaranViewModel viewModel;
    private LifecycleOwner OWNER = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_form_pembayaran);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Form Pembayaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(PembayaranViewModel.class);

        final String ID_TRANSAKSI = getIntent().getStringExtra("ID_TRANSAKSI");

        imageViewBuktiPembayaran = findViewById(R.id.imageViewBuktiPembayaran);
        textInputEditTextJumlahPembayaran = findViewById(R.id.textInputEditTextJumlahPembayaran);
        textInputEditTextTanggalPembayaran = findViewById(R.id.textInputEditTextTanggalPembayaran);
        textInputEditTextDeskripsi = findViewById(R.id.textInputEditTextDeskripsi);
        cardViewFotoBuktiPembayaran = findViewById(R.id.cardViewFotoBuktiPembayaran);
        materialButtonSimpanPembayaran = findViewById(R.id.materialButtonSimpanPembayaran);

        cardViewFotoBuktiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(FormPembayaranActivity.this);
            }
        });

        materialButtonSimpanPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkData()) {
                    final AlertProgress progress = new AlertProgress(v.getContext(), "Sedang mengirimkan data");
                    progress.showDialog();
                    viewModel.postPembayaran(
                        ID_TRANSAKSI,
                        textInputEditTextJumlahPembayaran.getText().toString(),
                        textInputEditTextTanggalPembayaran.getText().toString(),
                        textInputEditTextDeskripsi.getText().toString(),
                        uriBuktiPembayaran.toString()
                    ).observe(OWNER, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            progress.dismissDialog();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            final AlertInfo info = new AlertInfo(v.getContext(), "Data berhasil tersimpan", intent);
                            info.showDialog();
                        }
                    });
                }
            }
        });



    }

    private boolean checkData() {
        int count = 0;

        if(uriBuktiPembayaran == null) {
            Toast.makeText(getApplicationContext(), "Pilih Foto Bukti Pembayaran", Toast.LENGTH_SHORT).show();
            count++;
        }

        if(textInputEditTextJumlahPembayaran.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Jumlah Pembayaran Masih Kosong", Toast.LENGTH_SHORT).show();
            count++;
        }

        if(textInputEditTextJumlahPembayaran.getText().toString().equals("0")) {
            Toast.makeText(getApplicationContext(), "Jumlah Pembayaran tidak boleh nol Kosong", Toast.LENGTH_SHORT).show();
            count++;
        }

        if(textInputEditTextTanggalPembayaran.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Tanggal Pembayaran Masih Kosong", Toast.LENGTH_SHORT).show();
            count++;
        }

        if(textInputEditTextDeskripsi.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Deskripsi Pembayaran Masih Kosong", Toast.LENGTH_SHORT).show();
            count++;
        }

        return (count == 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                uriBuktiPembayaran = result.getUri();
                imageViewBuktiPembayaran.setImageURI(uriBuktiPembayaran);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                String error = result.getError().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
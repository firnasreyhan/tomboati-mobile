package com.tomboati.tour.view.activity.pembayaran;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.utils.AlertProgress;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.homepage.PembayaranViewModel;
import com.google.android.material.button.MaterialButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class FormPembayaranActivity extends AppCompatActivity {

    private Uri uriBuktiPembayaran = null;
    private ImageView imageViewBuktiPembayaran;
    private TextView editTextJumlahPembayaran, editTextTanggalPembayaran, editTextDeskripsi;
    private CardView cardViewFotoBuktiPembayaran;
    private MaterialButton materialButtonSimpanPembayaran;

    private String tanggal = "";

    private PembayaranViewModel viewModel;
    private LifecycleOwner OWNER = this;

    private String[] bulan = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September","Oktober", "November", "Desember"
    };

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
        editTextJumlahPembayaran = findViewById(R.id.editTextJumlahPembayaran);
        editTextTanggalPembayaran = findViewById(R.id.editTextTanggalPembayaran);
        editTextDeskripsi = findViewById(R.id.editTextDeskripsi);

        cardViewFotoBuktiPembayaran = findViewById(R.id.cardViewFotoBuktiPembayaran);
        materialButtonSimpanPembayaran = findViewById(R.id.materialButtonSimpanPembayaran);

        cardViewFotoBuktiPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(FormPembayaranActivity.this);
            }
        });

        editTextTanggalPembayaran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggal = "" + year + "-" + (month + 1) + "-" + dayOfMonth;
                        editTextTanggalPembayaran.setText(
                                String.format("%02d ", dayOfMonth) + bulan[month] + " " + year
                        );
                    }
                }, Utility.getYear(), Utility.getMonth(), Utility.getDay()).show();
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
                        editTextJumlahPembayaran.getText().toString(),
                        tanggal,
                        editTextDeskripsi.getText().toString(),
                        uriBuktiPembayaran.toString()
                    ).observe(OWNER, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            progress.dismissDialog();
                            final AlertInfo info;
                            if(!baseResponse.isError()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                info = new AlertInfo(FormPembayaranActivity.this, "Data berhasil tersimpan", intent);
                            } else {
                                info = new AlertInfo(v, "Data berhasil tersimpan");
                                info.setDialogError();
                            }

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

        if(editTextJumlahPembayaran.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Jumlah Pembayaran Masih Kosong", Toast.LENGTH_SHORT).show();
            count++;
        }

        if(editTextJumlahPembayaran.getText().toString().equals("0")) {
            Toast.makeText(getApplicationContext(), "Jumlah Pembayaran tidak boleh nol", Toast.LENGTH_SHORT).show();
            count++;
        }

        if(editTextJumlahPembayaran.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Deskripsi Pembayaran Masih Kosong", Toast.LENGTH_SHORT).show();
            count++;
        }

        if(tanggal.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Mohon pilih tanggal pembayaran", Toast.LENGTH_SHORT).show();
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
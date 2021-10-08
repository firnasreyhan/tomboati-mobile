package com.tomboati.tour.view.activity.pendaftaran;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.utils.AlertProgress;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.homepage.ImageChatViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class PendaftaranHajiRegulerActivity extends AppCompatActivity {

    private EditText textInputEditTextNomorKTP, textInputEditTextNamaLengkap, textInputEditTextTempatLahir;
    private TextView textInputEditTextTanggalLahir;
    private ImageChatViewModel viewModel;
    private Spinner spinnerJenisKelamin;
    private ImageView imageViewKTP;
    private Uri uriFotoKtp = null;

    private String[] bulan = {
            "Januari", "Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September","Oktober", "November", "Desember"
    };

    private String tanggalLahir = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_pendaftaran_haji_reguler);

        String nama_paket = getIntent().getStringExtra("NAMA_PAKET");

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(nama_paket);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(ImageChatViewModel.class);
        spinnerJenisKelamin = findViewById(R.id.spinnerJenisKelamin);
        textInputEditTextNomorKTP = findViewById(R.id.textInputEditTextNomorKTP);
        textInputEditTextNamaLengkap = findViewById(R.id.textInputEditTextNamaLengkap);
        textInputEditTextTempatLahir = findViewById(R.id.textInputEditTextTempatLahir);
        textInputEditTextTanggalLahir = findViewById(R.id.textInputEditTextTanggalLahir);
        imageViewKTP = findViewById(R.id.imageViewKTP);

        String[] jenisKelamin = new String[] {"Laki-laki", "Perempuan"};
        ArrayAdapter<String> jenisKelaminAdapter = new ArrayAdapter<>(this, R.layout.item_spinner, jenisKelamin);
        spinnerJenisKelamin.setAdapter(jenisKelaminAdapter);

        textInputEditTextTanggalLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggalLahir = String.format("%02d-%02d-%4d", dayOfMonth, (month + 1), year);
                        textInputEditTextTanggalLahir.setText(String.format("%02d ", dayOfMonth) + bulan[month] + " " + year);
                    }
                }, Utility.getYear(), Utility.getMonth(), Utility.getDay()).show();
            }
        });

        findViewById(R.id.cardViewFotoKTP).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(PendaftaranHajiRegulerActivity.this);
            }
        });

        findViewById(R.id.materialButtonDaftarSekarang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkData()) {

                    AlertProgress progress = new AlertProgress(v, "Sedang mengirim data");
                    progress.showDialog();

                    String message = "Saya, </br>" +
                            "Nama : " + textInputEditTextNamaLengkap.getText().toString() + ", </br>" +
                            "No KTP : " + textInputEditTextNomorKTP.getText().toString() + ", </br>" +
                            "Jenis Kelamin : " + spinnerJenisKelamin.getSelectedItem().toString() + ", </br>" +
                            "Tempat, Tanggal Lahir : " + textInputEditTextTempatLahir.getText().toString() +
                            ", " + tanggalLahir + ", </br>" +
                            "Berminat untuk mendaftar paket " + nama_paket;

                    viewModel.sendChat(message, uriFotoKtp).observe(PendaftaranHajiRegulerActivity.this,new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            if(progress.isDialogShowing()) {
                                progress.dismissDialog();
                            }

                            AlertInfo info;

                            if (!baseResponse.isError()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                info = new AlertInfo(PendaftaranHajiRegulerActivity.this,"Pendaftaran berhasil", intent);
                            } else {
                                info = new AlertInfo(v, baseResponse.getMessage());
                                info.setDialogError();
                            }
                            info.showDialog();
                        }
                    });

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
                this.uriFotoKtp = result.getUri();
                imageViewKTP.setImageURI(result.getUri());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                String error = result.getError().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean checkData() {

        int countError = 0;

        final EditText[] editText = {
                textInputEditTextNomorKTP, textInputEditTextNamaLengkap, textInputEditTextTempatLahir
        };

        for (EditText textInputEditText : editText) {
            if (textInputEditText.getText().toString().isEmpty()) {
                textInputEditText.setError("Mohon isi data berikut");
                countError++;
            }
        }

        if(tanggalLahir.isEmpty()) {
            Toast.makeText(this, "Harap pilih tanggal lahir", Toast.LENGTH_SHORT).show();
            countError++;
        }

        if(uriFotoKtp == null) {
            Toast.makeText(this, "Harap upload foto ktp anda", Toast.LENGTH_SHORT).show();
            countError++;
        }

        return (countError == 0);
    }
}
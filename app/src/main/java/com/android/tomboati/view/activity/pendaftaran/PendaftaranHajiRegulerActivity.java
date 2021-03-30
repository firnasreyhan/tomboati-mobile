package com.android.tomboati.view.activity.pendaftaran;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.view.activity.MainActivity;
import com.android.tomboati.viewmodel.ImageChatViewModel;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class PendaftaranHajiRegulerActivity extends AppCompatActivity {

    private TextInputEditText textInputEditTextNomorKTP, textInputEditTextNamaLengkap, textInputEditTextTempatLahir, textInputEditTextTanggalLahir;
    private ImageChatViewModel viewModel;
    private Spinner spinnerJenisKelamin;
    private ImageView imageViewKTP;
    private Uri uriFotoKtp = null;

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
                            ", " + textInputEditTextTanggalLahir.getText().toString() + ", </br>" +
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
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                info = new AlertInfo(v.getContext(),"Pendaftaran berhasil", intent);
                            } else {
                                info = new AlertInfo(v, "Gagal mengirim data");
                            }
                            info.showDialog();
                        }
                    });

                }
            }
        });








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

        final TextInputEditText[] editText = {
                textInputEditTextNomorKTP, textInputEditTextNamaLengkap,
                textInputEditTextTanggalLahir, textInputEditTextTempatLahir
        };

        for (TextInputEditText textInputEditText : editText) {
            if (textInputEditText.getText().toString().isEmpty()) {
                textInputEditText.setError("Mohon isi data berikut");
                countError++;
            }
        }

        if(uriFotoKtp == null) {
            Toast.makeText(this, "Harap upload foto ktp anda", Toast.LENGTH_SHORT).show();
            countError++;
        }

        return (countError == 0);
    }
}
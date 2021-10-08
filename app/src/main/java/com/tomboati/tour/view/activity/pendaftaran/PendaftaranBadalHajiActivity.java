package com.tomboati.tour.view.activity.pendaftaran;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.model.DataHajiBadalModel;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.utils.AlertProgress;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.pendaftaran.PendaftaranHajiViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class PendaftaranBadalHajiActivity extends AppCompatActivity {

    private final int[] arrIdCardView = {
        R.id.cardViewFCKtpAlmarhum, R.id.cardViewFCKkAlmarhum, R.id.cardViewFCDiriAlmarhum
    };
    private ImageView imageViewFCKtpAlmarhum, imageViewFCKkAlmarhum, imageViewFCFotoAlmarhum;
    private Uri[] arrUriImages = new Uri[3];
    private DataHajiBadalModel model;
    private int uriNumber;

    private PendaftaranHajiViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_pendaftaran_badal_haji);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Formulir Data Haji Badal");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(PendaftaranHajiViewModel.class);
        model = (DataHajiBadalModel) getIntent().getSerializableExtra("OBJECT");

        imageViewFCKtpAlmarhum = findViewById(R.id.imageViewFCKtpAlmarhum);
        imageViewFCKkAlmarhum = findViewById(R.id.imageViewFCKkAlmarhum);
        imageViewFCFotoAlmarhum = findViewById(R.id.imageViewFCDiriAlmarhum);

        for(int i = 0; i < arrIdCardView.length; i++) {
            int j = i;
            findViewById(arrIdCardView[i]).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(PendaftaranBadalHajiActivity.this);
                    uriNumber = j + 1;
                }
            });
        }

        findViewById(R.id.materialButtonPesanSekarang).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isImageSelected()) {
                    AlertProgress progress = new AlertProgress(v, "Sedang mengirim data");
                    progress.showDialog();

                    model.setIdUserRegister(PreferenceAkun.getAkun(v.getContext()).getUserId());
                    viewModel.pendaftaranBadalHaji(model).observe(PendaftaranBadalHajiActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            if (progress.isDialogShowing()) {
                                progress.dismissDialog();
                            }

                            AlertInfo info;

                            if (!baseResponse.isError()) {
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                info = new AlertInfo(PendaftaranBadalHajiActivity.this, "Pendaftaran berhasil", intent);
                            } else {
                                info = new AlertInfo(v, "Gagal mengirim data");
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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                switch (uriNumber) {
                    case 1:
                        arrUriImages[0] = result.getUri();
                        model.setFcKTPAlmarhum(arrUriImages[0].toString());
                        imageViewFCKtpAlmarhum.setImageURI(arrUriImages[0]);
                        break;
                    case 2:
                        arrUriImages[1] = result.getUri();
                        model.setFcKKAlmarhum(arrUriImages[1].toString());
                        imageViewFCKkAlmarhum.setImageURI(arrUriImages[1]);
                        break;
                    case 3:
                        arrUriImages[2] = result.getUri();
                        model.setFcFotoAlmarhum(arrUriImages[2].toString());
                        imageViewFCFotoAlmarhum.setImageURI(arrUriImages[2]);
                        break;
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                String error = result.getError().getMessage();
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private boolean isImageSelected() {
        int countError = 0;
        final String[] prefix = {"ktp", "kk", "diri"};
        for (int i = 0; i < arrUriImages.length; i++) {
            if(arrUriImages[i] == null) {
                Toast.makeText(this, "Harap upload foto " + prefix[i] + " almarhum",
                        Toast.LENGTH_SHORT).show();
                countError++;
            }
        }
        return (countError == 0);
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
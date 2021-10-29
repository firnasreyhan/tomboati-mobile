package com.tomboati.tour.view.activity.mitra.pendaftaran;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.preference.PreferenceAkun;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class RegistrasiDataPembayaranMitraActivity extends AppCompatActivity {

    private EditText editTextRegistrasiNomorKTP, editTextRegistrasiUsername,
            editTextRegistrasiEmail, editTextRegistrasiNomorRekening, editTextRegistrasiNamaBank,
            editTextRegistrasiCabangBank, editTextRegistrasiAtasNama;
    private ImageView imageViewRegistrasiKTP, imageViewRegistrasiBuktiPembayaran;
    private CircleImageView imageViewRegistrasiFotoProfile;
    private MaterialCardView cardViewRegisrasiBuktiPembayaran, cardViewRegistrasiKTP;
    private FloatingActionButton floatingActionButtonFotoProfile;

    private MaterialButton materialButtonLanjutkan;

    private final Uri[] URI = new Uri[3];
    private int uriNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_registrasi_data_pembayaran_mitra);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Registrasi Data Lanjutan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        editTextRegistrasiNomorKTP = findViewById(R.id.editTextRegistrasiNomorKTP);
        editTextRegistrasiUsername = findViewById(R.id.editTextRegistrasiUsername);
        editTextRegistrasiEmail = findViewById(R.id.editTextRegistrasiEmail);
        editTextRegistrasiNomorRekening = findViewById(R.id.editTextRegistrasiNomorRekening);
        editTextRegistrasiNamaBank = findViewById(R.id.editTextRegistrasiNamaBank);
        editTextRegistrasiCabangBank = findViewById(R.id.editTextRegistrasiCabangBank);
        editTextRegistrasiAtasNama = findViewById(R.id.editTextRegistrasiAtasNama);
        imageViewRegistrasiFotoProfile = findViewById(R.id.imageViewRegistrasiFotoProfile);
        imageViewRegistrasiKTP = findViewById(R.id.imageViewRegistrasiKTP);
        imageViewRegistrasiBuktiPembayaran = findViewById(R.id.imageViewRegistrasiBuktiPembayaran);
        cardViewRegisrasiBuktiPembayaran = findViewById(R.id.cardViewRegisrasiBuktiPembayaran);
        cardViewRegistrasiKTP = findViewById(R.id.cardViewRegistrasiKTP);
        floatingActionButtonFotoProfile = findViewById(R.id.floatingActionButtonFotoProfile);
        materialButtonLanjutkan = findViewById(R.id.materialButtonLanjutkan);

        floatingActionButtonFotoProfile.setOnClickListener(v ->{
            openCropImage();
            uriNumber = 1;
        });

        cardViewRegistrasiKTP.setOnClickListener(v ->{
            openCropImage();
            uriNumber = 2;
        });

        cardViewRegisrasiBuktiPembayaran.setOnClickListener(v ->{
            openCropImage();
            uriNumber = 3;
        });

        materialButtonLanjutkan.setOnClickListener(v -> {
            final String[] USERNAME = editTextRegistrasiUsername.getText().toString().split("\\s+");

            if(!checkData()) {
                Toast.makeText(v.getContext(), "Data anda masih kurang lengkap", Toast.LENGTH_SHORT).show();
            }else if(USERNAME.length > 1) {
                Toast.makeText(v.getContext(), "Username tidak boleh mengandung spasi", Toast.LENGTH_SHORT).show();
            } else {
                final AkunModel akunModel = PreferenceAkun.getAkun(this);
                akunModel.setPhoto(URI[0].toString());
                akunModel.setKtp(editTextRegistrasiNomorKTP.getText().toString());
                akunModel.setUserId(editTextRegistrasiUsername.getText().toString().toLowerCase());
                akunModel.setEmail(editTextRegistrasiEmail.getText().toString());
                akunModel.setFotoKTP(URI[1].toString());
                akunModel.setRekening(editTextRegistrasiNomorRekening.getText().toString());
                akunModel.setBank(editTextRegistrasiNamaBank.getText().toString());
                akunModel.setCabang(editTextRegistrasiCabangBank.getText().toString());
                akunModel.setAtasNama(editTextRegistrasiAtasNama.getText().toString());
                akunModel.setBuktiBayar(URI[2].toString());

                PreferenceAkun.removeAkun(v.getContext());
                PreferenceAkun.setAkun(v.getContext(), akunModel);

                startActivity(new Intent(v.getContext(), SyaratPembatalanMitraActivity.class));
            }

        });
    }

    private boolean checkData() {
        int count = 0;

        for (Uri uri : URI) {
            if(uri == null) {
                count++;
            }
        }

        final EditText[] EDITTEXT = {
                editTextRegistrasiNomorKTP, editTextRegistrasiUsername, editTextRegistrasiEmail,
                editTextRegistrasiNamaBank, editTextRegistrasiCabangBank,
                editTextRegistrasiNomorRekening, editTextRegistrasiAtasNama
        };

        for(EditText editText : EDITTEXT) {
            if(editText.getText().toString().isEmpty()) {
                count++;
            }
        }

        return (count == 0);
    }

    private void openCropImage() {
        CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(RegistrasiDataPembayaranMitraActivity.this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                URI[uriNumber - 1] = result.getUri();
                switch (uriNumber) {
                    case 1:
                        imageViewRegistrasiFotoProfile.setImageURI(result.getUri());
                        break;
                    case 2:
                        imageViewRegistrasiKTP.setImageURI(result.getUri());
                        break;
                    default:
                        imageViewRegistrasiBuktiPembayaran.setImageURI(result.getUri());
                        break;
                }
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
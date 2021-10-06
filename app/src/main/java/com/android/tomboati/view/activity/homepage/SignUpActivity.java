package com.android.tomboati.view.activity.homepage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.viewmodel.tomboati.auth.SignUpViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class SignUpActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private SignUpViewModel signUpViewModel;
    private Toolbar toolbar;
    private ShapeableImageView shapeableImageViewFoto;
    private FloatingActionButton floatingActionButtonFoto;
    private CardView cardViewKTP;
    private ImageView imageViewKTP;
    private LinearLayout linearLayoutEmptyKTP;
    private TextView textViewSignIn;
    private TextInputEditText textInputEditTextNomorKTP, textInputEditTextNamaLengkap, textInputEditTextNomorHP, textInputEditTextEmail, textInputEditTextPassword, textInputEditTextKonfirmasiPassword;
    private MaterialButton materialButtonSignUp;

    private Uri uriFoto, uriKTP;
    private int uriNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        signUpViewModel = ViewModelProviders.of(this).get(SignUpViewModel.class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Daftar");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog = new ProgressDialog(this);
        shapeableImageViewFoto = findViewById(R.id.shapeableImageViewFoto);
        floatingActionButtonFoto = findViewById(R.id.floatingActionButtonFoto);
        cardViewKTP = findViewById(R.id.cardViewKTP);
        imageViewKTP = findViewById(R.id.imageViewKTP);
        linearLayoutEmptyKTP = findViewById(R.id.linearLayoutEmptyKTP);
        textViewSignIn = findViewById(R.id.textViewSignIn);
        textInputEditTextNomorKTP = findViewById(R.id.textInputEditTextNomorKTP);
        textInputEditTextNamaLengkap = findViewById(R.id.textInputEditTextNamaLengkap);
        textInputEditTextNomorHP = findViewById(R.id.textInputEditTextNomorHP);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = findViewById(R.id.textInputEditTextPassword);
        textInputEditTextKonfirmasiPassword = findViewById(R.id.textInputEditTextKonfirmasiPassword);
        materialButtonSignUp = findViewById(R.id.materialButtonSignUp);

        floatingActionButtonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        .start(SignUpActivity.this);
                uriNumber = 1;
            }
        });

        cardViewKTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.OFF)
                        .start(SignUpActivity.this);
                uriNumber = 2;
            }
        });

        textViewSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        materialButtonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cek1 = true;
                boolean cek2 = true;
                boolean cek3 = true;
                boolean cek4 = true;
                boolean cek5 = true;
                boolean cek6 = true;
                boolean cek7 = true;
                boolean cek8 = true;
                boolean cek9 = true;
                boolean cek10 = true;

                if (textInputEditTextNomorKTP.getText().toString().isEmpty()){
                    textInputEditTextNomorKTP.setError("Mohon isi data berikut!");
                    cek1 = false;
                }
                if (textInputEditTextNamaLengkap.getText().toString().isEmpty()){
                    textInputEditTextNamaLengkap.setError("Mohon isi data berikut!");
                    cek2 = false;
                }
                if (textInputEditTextNomorHP.getText().toString().isEmpty()){
                    textInputEditTextNomorHP.setError("Mohon isi data berikut!");
                    cek3 = false;
                }
                if (textInputEditTextEmail.getText().toString().isEmpty()){
                    textInputEditTextEmail.setError("Mohon isi data berikut!");
                    cek4 = false;
                }
                if (textInputEditTextPassword.getText().toString().isEmpty()){
                    textInputEditTextPassword.setError("Mohon isi data berikut!");
                    cek5 = false;
                }
                if (textInputEditTextKonfirmasiPassword.getText().toString().isEmpty()){
                    textInputEditTextKonfirmasiPassword.setError("Mohon isi data berikut!");
                    cek6 = false;
                }
                if (cek5 && cek6) {
                    if (!textInputEditTextPassword.getText().toString().equals(textInputEditTextKonfirmasiPassword.getText().toString())) {
                        textInputEditTextKonfirmasiPassword.setError("Kombinasi password tidak cocok!");
                        cek7 = false;
                    }
                }

                if (uriFoto == null) {
                    Toast.makeText(SignUpActivity.this, "Silahkan upload gambar profil anda.", Toast.LENGTH_SHORT).show();
                    cek9 = false;
                }

                if (uriKTP == null) {
                    Toast.makeText(SignUpActivity.this, "Silahkan upload gambar ktp anda.", Toast.LENGTH_SHORT).show();
                    cek10 = false;
                }

                if (cek1 && cek2 && cek3 && cek4 && cek5 && cek6 && cek7 && cek8 && cek9 && cek10) {
                    progressDialog.setMessage("Mohon tunggu sebentar...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    int loadingTime = 3000;
                    new Handler().postDelayed(() -> {
                        signUpViewModel.signUp(
                            textInputEditTextNomorKTP.getText().toString(),
                            textInputEditTextEmail.getText().toString(),
                            textInputEditTextPassword.getText().toString(),
                            textInputEditTextNamaLengkap.getText().toString(),
                            textInputEditTextNomorHP.getText().toString(),
                            uriKTP,
                            uriFoto
                        ).observe(SignUpActivity.this, new Observer<BaseResponse>() {
                            @Override
                            public void onChanged(BaseResponse baseResponse) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                new AlertDialog.Builder(v.getContext())
                                        .setTitle("Pesan")
                                        .setMessage(baseResponse.getMessage())
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            @Override
                                            public void onClick(DialogInterface dialog, int which) {
                                                if (!baseResponse.isError()) {
                                                    progressDialog.setMessage("Mohon tunggu sebentar...");
                                                    progressDialog.setCancelable(false);
                                                    progressDialog.show();

                                                    int loadingTime = 3000;
                                                    new Handler().postDelayed(() -> {
                                                        signUpViewModel.signIn(
                                                                textInputEditTextEmail.getText().toString(),
                                                                textInputEditTextPassword.getText().toString()
                                                        ).observe(SignUpActivity.this, new Observer<SignInResponse>() {
                                                            @Override
                                                            public void onChanged(SignInResponse signInResponse) {
                                                                if (progressDialog.isShowing()) {
                                                                    progressDialog.dismiss();
                                                                }
                                                                Toast.makeText(v.getContext(), signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                                                if (!signInResponse.isError()) {
                                                                    if (!signInResponse.getData().isEmpty()) {
                                                                        AppPreference.saveUser(v.getContext(), signInResponse.getData().get(0));
                                                                        Intent intent = new Intent(v.getContext(), MainActivity.class);
                                                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                                        startActivity(intent);
                                                                    }
                                                                }
                                                            }
                                                        });
                                                    }, loadingTime);
                                                } else {
                                                    dialog.dismiss();
                                                }
                                            }
                                        })
                                        .show();
                            }
                        });
                    }, loadingTime);
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
                        uriFoto = result.getUri();
                        shapeableImageViewFoto.setImageURI(uriFoto);
                        break;
                    case 2:
                        uriKTP = result.getUri();
                        imageViewKTP.setImageURI(uriKTP);
                        imageViewKTP.setVisibility(View.VISIBLE);
                        linearLayoutEmptyKTP.setVisibility(View.GONE);
                        break;
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
            }
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
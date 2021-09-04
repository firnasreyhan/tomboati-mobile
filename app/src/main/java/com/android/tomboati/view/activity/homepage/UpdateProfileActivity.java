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
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.viewmodel.tomboati.auth.UpdateProfileViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class UpdateProfileActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private UpdateProfileViewModel updateProfileViewModel;
    private Toolbar toolbar;
    private ShapeableImageView shapeableImageViewFoto;
    private FloatingActionButton floatingActionButtonFoto;
    private CardView cardViewKTP;
    private ImageView imageViewKTP;
    private LinearLayout linearLayoutEmptyKTP;
    private TextInputEditText textInputEditTextNomorKTP, textInputEditTextNamaLengkap, textInputEditTextNomorHP, textInputEditTextEmail;
    private MaterialButton materialButtonSimpan;

    private Uri uriFoto, uriKTP;
    private int uriNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        updateProfileViewModel = ViewModelProviders.of(this).get(UpdateProfileViewModel.class);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Ubah Profile");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progressDialog = new ProgressDialog(this);
        shapeableImageViewFoto = findViewById(R.id.shapeableImageViewFoto);
        floatingActionButtonFoto = findViewById(R.id.floatingActionButtonFoto);
        cardViewKTP = findViewById(R.id.cardViewKTP);
        imageViewKTP = findViewById(R.id.imageViewKTP);
        linearLayoutEmptyKTP = findViewById(R.id.linearLayoutEmptyKTP);;
        textInputEditTextNomorKTP = findViewById(R.id.textInputEditTextNomorKTP);
        textInputEditTextNamaLengkap = findViewById(R.id.textInputEditTextNamaLengkap);
        textInputEditTextNomorHP = findViewById(R.id.textInputEditTextNomorHP);
        textInputEditTextEmail = findViewById(R.id.textInputEditTextEmail);
        materialButtonSimpan = findViewById(R.id.materialButtonSimpan);

        setAkun();

        floatingActionButtonFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(UpdateProfileActivity.this);
                uriNumber = 1;
            }
        });

        cardViewKTP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(UpdateProfileActivity.this);
                uriNumber = 2;
            }
        });

        materialButtonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean cek1 = true;
                boolean cek2 = true;
                boolean cek3 = true;
                boolean cek4 = true;

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

                if (cek1 && cek2 && cek3 && cek4) {
                    progressDialog.setMessage("Mohon tunggu sebentar...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    int loadingTime = 3000;
                    new Handler().postDelayed(() -> {
                        updateProfileViewModel.updateProfile(
                                AppPreference.getUser(UpdateProfileActivity.this).getIdUserRegister(),
                                textInputEditTextNomorKTP.getText().toString(),
                                textInputEditTextEmail.getText().toString(),
                                textInputEditTextNamaLengkap.getText().toString(),
                                textInputEditTextNomorHP.getText().toString()
                        ).observe(UpdateProfileActivity.this, new Observer<BaseResponse>() {
                            @Override
                            public void onChanged(BaseResponse baseResponse) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }

                                if (uriKTP != null) {
                                    updateProfileViewModel.updateFileKTP(
                                            AppPreference.getUser(UpdateProfileActivity.this).getIdUserRegister(),
                                            uriKTP
                                    ).observe(UpdateProfileActivity.this, new Observer<BaseResponse>() {
                                        @Override
                                        public void onChanged(BaseResponse baseResponse) {
                                            Log.e("updateFileKTP", baseResponse.getMessage());
                                        }
                                    });
                                }

                                if (uriFoto != null) {
                                    updateProfileViewModel.updateFoto(
                                            AppPreference.getUser(UpdateProfileActivity.this).getIdUserRegister(),
                                            uriFoto
                                    ).observe(UpdateProfileActivity.this, new Observer<BaseResponse>() {
                                        @Override
                                        public void onChanged(BaseResponse baseResponse) {
                                            Log.e("updateFoto", baseResponse.getMessage());
                                        }
                                    });
                                }

                                if (!baseResponse.isError()) {
                                    updateProfileViewModel.signIn(
                                            AppPreference.getUser(UpdateProfileActivity.this).getEmail(),
                                            AppPreference.getUser(UpdateProfileActivity.this).getPassword()
                                    ).observe(UpdateProfileActivity.this, new Observer<SignInResponse>() {
                                        @Override
                                        public void onChanged(SignInResponse signInResponse) {
                                            AppPreference.removeUser(UpdateProfileActivity.this);
                                            AppPreference.saveUser(UpdateProfileActivity.this, signInResponse.getData().get(0));

                                            new AlertDialog.Builder(v.getContext())
                                                    .setTitle("Pesan")
                                                    .setMessage(baseResponse.getMessage())
                                                    .setCancelable(false)
                                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                        @Override
                                                        public void onClick(DialogInterface dialog, int which) {
                                                            dialog.dismiss();
                                                            finish();
                                                        }
                                                    })
                                                    .show();
                                        }
                                    });
                                } else {
                                    new AlertDialog.Builder(v.getContext())
                                            .setTitle("Pesan")
                                            .setMessage(baseResponse.getMessage())
                                            .setCancelable(false)
                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    dialog.dismiss();
                                                }
                                            })
                                            .show();
                                }
                            }
                        });

//                        updateProfileViewModel.updateProfile(
//                                AppPreference.getUser(UpdateProfileActivity.this).getIdUserRegister(),
//                                textInputEditTextNomorKTP.getText().toString(),
//                                textInputEditTextEmail.getText().toString(),
//                                AppPreference.getUser(UpdateProfileActivity.this).getPassword(),
//                                textInputEditTextNamaLengkap.getText().toString(),
//                                textInputEditTextNomorHP.getText().toString(),
//                                uriKTP,
//                                uriFoto
//                        ).observe(UpdateProfileActivity.this, new Observer<SignInResponse>() {
//                            @Override
//                            public void onChanged(SignInResponse signInResponse) {
//                                if (progressDialog.isShowing()) {
//                                    progressDialog.dismiss();
//                                }
//                                if (!signInResponse.isError()) {
//                                    new AlertDialog.Builder(v.getContext())
//                                            .setTitle("Pesan")
//                                            .setMessage(signInResponse.getMessage())
//                                            .setCancelable(false)
//                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    AppPreference.removeUser(UpdateProfileActivity.this);
//                                                    AppPreference.saveUser(UpdateProfileActivity.this, signInResponse.getData().get(0));
//                                                    dialog.dismiss();
//                                                    finish();
//                                                }
//                                            })
//                                            .show();
//                                } else {
//                                    new AlertDialog.Builder(v.getContext())
//                                            .setTitle("Pesan")
//                                            .setMessage(signInResponse.getMessage())
//                                            .setCancelable(false)
//                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                                                @Override
//                                                public void onClick(DialogInterface dialog, int which) {
//                                                    dialog.dismiss();
//                                                }
//                                            })
//                                            .show();
//                                }
//                            }
//                        });
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
                        //shapeableImageViewFoto.setImageURI(uriFoto);

                        Glide.with(this)
                                .load(uriFoto)
                                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                                .skipMemoryCache(true)
                                .dontAnimate()
                                .dontTransform()
                                .priority(Priority.IMMEDIATE)
                                .encodeFormat(Bitmap.CompressFormat.PNG)
                                .format(DecodeFormat.DEFAULT)
                                .placeholder(R.drawable.ic_logo)
                                .into(shapeableImageViewFoto);

                        break;
                    case 2:
                        uriKTP = result.getUri();
                        //imageViewKTP.setImageURI(uriKTP);

                        Glide.with(this)
                                .load(uriKTP)
                                .diskCacheStrategy(DiskCacheStrategy.ALL)
                                .skipMemoryCache(true)
                                .dontAnimate()
                                .dontTransform()
                                .priority(Priority.IMMEDIATE)
                                .encodeFormat(Bitmap.CompressFormat.PNG)
                                .format(DecodeFormat.DEFAULT)
                                .placeholder(R.drawable.ic_logo)
                                .into(imageViewKTP);
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

    private void setAkun() {
        imageViewKTP.setVisibility(View.VISIBLE);
        linearLayoutEmptyKTP.setVisibility(View.GONE);

        textInputEditTextNamaLengkap.setText(AppPreference.getUser(this).getNamaLengkap());
        textInputEditTextEmail.setText(AppPreference.getUser(this).getEmail());
        textInputEditTextNomorKTP.setText(AppPreference.getUser(this).getNomorKTP());
        textInputEditTextNomorHP.setText(AppPreference.getUser(this).getNomorHP());

        Glide.with(this)
                .load(AppPreference.getUser(this).getFoto())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .dontAnimate()
                .dontTransform()
                .priority(Priority.IMMEDIATE)
                .encodeFormat(Bitmap.CompressFormat.PNG)
                .format(DecodeFormat.DEFAULT)
                .placeholder(R.drawable.ic_logo)
                .into(shapeableImageViewFoto);


        Glide.with(this)
                .load(AppPreference.getUser(this).getFileKTP())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .dontAnimate()
                .dontTransform()
                .priority(Priority.IMMEDIATE)
                .encodeFormat(Bitmap.CompressFormat.PNG)
                .format(DecodeFormat.DEFAULT)
                .placeholder(R.drawable.ic_logo)
                .into(imageViewKTP);
    }
}
package com.tomboati.tour.view.activity.homepage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.tomboati.tour.databinding.ActivityImageChatBinding;
import com.tomboati.tour.viewmodel.tomboati.homepage.ImageChatViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class ImageChatActivity extends AppCompatActivity {

    private ActivityImageChatBinding bind;
    private ProgressDialog progressDialog;
    private ImageChatViewModel imageChatViewModel;
    private final LifecycleOwner OWNER = this;
    private final int LOADING_TIME = 3000;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityImageChatBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        String chat = getIntent().getStringExtra("CHAT");
        imageChatViewModel = ViewModelProviders.of(this).get(ImageChatViewModel.class);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Mohon tunggu sebentar...");
        progressDialog.setCancelable(false);

        bind.textInputEditTextChat.setText(chat);

        CropImage.activity().setGuidelines(CropImageView.Guidelines.ON).start(ImageChatActivity.this);

        bind.floatingActionButtonSend.setOnClickListener(v -> {
            progressDialog.show();
            new Handler().postDelayed(() -> {
                imageChatViewModel.sendChat(
                        bind.textInputEditTextChat.getText().toString(), uri
                ).observe(OWNER, baseResponse -> {
                    progressDialog.dismiss();
                    if (!baseResponse.isError()) {
                        finish();
                    } else {
                        Toast.makeText(v.getContext(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }, LOADING_TIME);
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                this.uri = result.getUri();
                bind.imageViewChat.setImageURI(this.uri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                String error = result.getError().toString();
                Log.d("ERROR", "onActivityResult: " + error);
                finish();
            } else {
                finish();
            }
        }
    }
}
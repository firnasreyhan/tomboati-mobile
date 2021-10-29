package com.tomboati.tour.view.activity.homepage;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.tomboati.tour.databinding.ActivityImageChatBinding;
import com.tomboati.tour.view.activity.base.BaseNonToolbarActivity;
import com.tomboati.tour.viewmodel.tomboati.homepage.ImageChatViewModel;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class ImageChatActivity extends BaseNonToolbarActivity {

    private ActivityImageChatBinding bind;
    private final int LOADING_TIME = 3000;
    private Uri uri;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityImageChatBinding.inflate(getLayoutInflater());
        ImageChatViewModel imageChatViewModel = ViewModelProviders.of(this).get(ImageChatViewModel.class);
        bind.setPesan(intent.getStringExtra("CHAT"));
        cropImages();
        bind.floatingActionButtonSend.setOnClickListener(v -> {
            showProgressDialog("Sedang mengirimkan pesan");
            new Handler().postDelayed(() -> {
                imageChatViewModel.sendChat(bind.getPesan(), uri).observe(getOwner(), baseResponse -> {
                    dismissProgressDialog();
                    if (!baseResponse.isError()) {
                        finish();
                    }
                    showToast(baseResponse.getMessage());
                });
            }, LOADING_TIME);
        });
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
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
                showToast(error);
                finish();
            } else {
                finish();
            }
        }
    }
}
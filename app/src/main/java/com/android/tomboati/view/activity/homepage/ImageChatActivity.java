package com.android.tomboati.view.activity.homepage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.viewmodel.tomboati.homepage.ImageChatViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class ImageChatActivity extends AppCompatActivity {
    private ProgressDialog progressDialog;
    private ImageChatViewModel imageChatViewModel;
    private ImageView imageViewChat;
    private TextInputEditText textInputEditTextChat;
    private FloatingActionButton floatingActionButtonSend;
    private Uri uri;
    private String chat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_chat);

        chat = getIntent().getStringExtra("CHAT");

        progressDialog = new ProgressDialog(this);
        imageChatViewModel = ViewModelProviders.of(this).get(ImageChatViewModel.class);
        textInputEditTextChat = findViewById(R.id.textInputEditTextChat);
        floatingActionButtonSend = findViewById(R.id.floatingActionButtonSend);
        imageViewChat = findViewById(R.id.imageViewChat);

        textInputEditTextChat.setText(chat);

        CropImage.activity()
                .setGuidelines(CropImageView.Guidelines.ON)
                .start(ImageChatActivity.this);

        floatingActionButtonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Mohon tunggu sebentar...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                int loadingTime = 3000;
                new Handler().postDelayed(() -> {
                    imageChatViewModel.sendChat(
                            textInputEditTextChat.getText().toString(),
                            uri
                    ).observe(ImageChatActivity.this, new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            if (!baseResponse.isError()) {
                                finish();
                            }
                        }
                    });
                }, loadingTime);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                Uri uri = result.getUri();
                this.uri = uri;
                imageViewChat.setImageURI(uri);
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                finish();
            } else {
                finish();
            }
        }
    }
}
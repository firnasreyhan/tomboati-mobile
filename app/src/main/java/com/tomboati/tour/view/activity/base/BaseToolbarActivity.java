package com.tomboati.tour.view.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;
import com.tomboati.tour.R;
import com.tomboati.tour.utils.AlertProgress;

public abstract class BaseToolbarActivity extends AppCompatActivity {

    protected abstract View getContentView();
    private AlertProgress alertProgress;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        onViewReady(savedInstanceState, getIntent());
        setContentView(getContentView());
        showBackArrow();
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

    }

    protected void showBackArrow() {
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            supportActionBar.setDisplayHomeAsUpEnabled(true);
            supportActionBar.setDisplayShowHomeEnabled(true);
        }
    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void startsActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    protected void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }

    protected void setToolbar(Toolbar toolbar, String title){
        setSupportActionBar(toolbar);
        setTitle(title);
    }

    protected void setRecyclerView(RecyclerView recyclerView, RecyclerView.Adapter<?> adapter) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    protected void showProgressDialog(String message) {
        this.alertProgress = new AlertProgress(this, message);
        this.alertProgress.showDialog();
    }

    protected void dismissProgressDialog() {
        if(this.alertProgress.isDialogShowing()) {
            this.alertProgress.dismissDialog();
        }
    }

    protected void cropImages() {
        CropImage.activity().setGuidelines(CropImageView.Guidelines.OFF).start(this);
    }

    protected LifecycleOwner getOwner() {
        return this;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}

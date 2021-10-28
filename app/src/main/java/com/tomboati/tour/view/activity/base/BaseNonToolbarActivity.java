package com.tomboati.tour.view.activity.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.squareup.picasso.Picasso;
import com.tomboati.tour.R;

public abstract class BaseNonToolbarActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        onViewReady(savedInstanceState, getIntent());
        setContentView(getContentView());
    }

    @CallSuper
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {

    }

    protected void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    protected void startsActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }

    protected void startsActivity(Intent intent) {
        startActivity(intent);
    }

    protected void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }

    protected void setToolbar(Toolbar toolbar, String title){
        setSupportActionBar(toolbar);
        setTitle(title);
    }

    protected abstract View getContentView();
}

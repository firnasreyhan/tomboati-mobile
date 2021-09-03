package com.android.tomboati.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.preference.AppPreference;
import com.intentfilter.androidpermissions.PermissionManager;
import com.intentfilter.androidpermissions.models.DeniedPermissions;

import java.util.Arrays;

public class SplashScreenActivity extends AppCompatActivity {

    private PermissionManager permissionManager;

    private final String[] PERMISSIONS = {
            Manifest.permission.INTERNET,
            Manifest.permission.CAMERA,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeSplashScreenTomboAti);
        setContentView(R.layout.activity_splash_screen);

        permissionManager = PermissionManager.getInstance(this);
        permissionManager.checkPermissions(Arrays.asList(PERMISSIONS), new PermissionManager.PermissionRequestListener() {
            @Override
            public void onPermissionGranted() {
                toMainActivity();
            }

            @Override
            public void onPermissionDenied(DeniedPermissions deniedPermissions) {
                Toast.makeText(getApplicationContext(), "Izin diperlukan untuk menggunakan aplikasi", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private void toMainActivity() {
        int loadingTime = 2000;
        new Handler().postDelayed(() -> {
            startActivity(new Intent(SplashScreenActivity.this, MainActivity.class));
            finish();
        }, loadingTime);
    }
}
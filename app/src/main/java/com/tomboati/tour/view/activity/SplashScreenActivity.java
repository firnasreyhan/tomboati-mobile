package com.tomboati.tour.view.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.view.activity.mitra.auth.AuthRegisterUserActivity;
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
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

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
        int loadingTime = 1000;
        new Handler().postDelayed(() -> {
            final boolean isLogged = PreferenceAkun.getAkun(SplashScreenActivity.this) != null;

            final Class<?> activity = isLogged ?
                    MainActivity.class
                    :
                    AuthRegisterUserActivity.class;

            final Uri uri = getIntent().getData();
            Intent intent = new Intent(SplashScreenActivity.this, activity);
            if (uri != null) {
                String[] segment = uri.getPath().split("/");
                if (isLogged) {
                    final boolean isLoginUser = PreferenceAkun.getAkun(SplashScreenActivity.this).getPaket().equals("USER");
                    if (!isLoginUser) {
                        Toast.makeText(getApplicationContext(), "Silahkan logout untuk membuat akun baru dengan kode referral tersebut!", Toast.LENGTH_SHORT).show();
                    } else {
                        intent = new Intent(SplashScreenActivity.this, AuthRegisterUserActivity.class);
                    }
                }
                intent.putExtra("REFERRAL", segment[segment.length - 1]);
            }

            startActivity(intent);
            finish();
        }, loadingTime);
    }
}
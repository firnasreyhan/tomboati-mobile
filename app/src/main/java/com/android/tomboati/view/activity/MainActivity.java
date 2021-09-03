package com.android.tomboati.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.view.fragment.homepage.AkunFragment;
import com.android.tomboati.view.fragment.homepage.BerandaFragment;
import com.android.tomboati.view.fragment.homepage.InboxFragment;
import com.android.tomboati.view.fragment.homepage.PesananFragment;
import com.android.tomboati.view.fragment.homepage.RiwayatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private boolean doubleBackToExit;
    private Fragment fragmentActive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setSupportActionBar(toolbar);
        setTitle("");

        if(Utility.isConnecting(this)) {
            loadFragment(new BerandaFragment());
            fragmentActive = new BerandaFragment();
            bottomNavigationView.setOnNavigationItemSelectedListener(this);
        }

    }

    @Override
    public void onBackPressed() {
        if(Utility.isConnecting(this)) {
            if (doubleBackToExit) {
                super.onBackPressed();
            }
            this.doubleBackToExit = true;
            Toast.makeText(this, "Tekan sekali lagi untuk keluar.", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    doubleBackToExit = false;
                }
            }, 8000);
        } else {
            finish();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment fragment = null;
        switch (item.getItemId()){
            case R.id.menu_beranda:
                fragment = new BerandaFragment();
                break;
            case R.id.menu_riwayat:
                fragment = new RiwayatFragment();
                break;
            case R.id.menu_pesanan:
                fragment = new PesananFragment();
                break;
            case R.id.menu_inbox:
                fragment = new InboxFragment();
                break;
            case R.id.menu_akun:
                fragment = new AkunFragment();
                break;
        }

        if (!fragment.getClass().getName().equalsIgnoreCase(fragmentActive.getClass().getName())) {
            return loadFragment(fragment);
        } else {
            return false;
        }
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            fragmentActive = fragment;
            getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutFragment, fragment).commit();
            return true;
        }
        return false;
    }
}
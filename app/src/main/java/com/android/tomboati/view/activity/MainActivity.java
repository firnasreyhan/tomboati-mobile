package com.android.tomboati.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.view.fragment.AkunFragment;
import com.android.tomboati.view.fragment.BerandaFragment;
import com.android.tomboati.view.fragment.InboxFragment;
import com.android.tomboati.view.fragment.PesananFragment;
import com.android.tomboati.view.fragment.RiwayatFragment;
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

        loadFragment(new BerandaFragment());
        fragmentActive = new BerandaFragment();
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExit) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExit = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar.", Toast.LENGTH_SHORT).show();
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
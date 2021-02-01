package com.android.tomboati.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.view.fragment.AkunFragment;
import com.android.tomboati.view.fragment.BerandaFragment;
import com.android.tomboati.view.fragment.InboxFragment;
import com.android.tomboati.view.fragment.PesananFragment;
import com.android.tomboati.view.fragment.RiwayatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragmentManager;
    private Fragment fragmentActive, fragmentBeranda, fragmentRiwayat, fragmentPesanan, fragmentInbox, fragmentAkun;
    private boolean doubleBackToExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        setSupportActionBar(toolbar);
        setTitle("");

        fragmentManager = getSupportFragmentManager();
        fragmentBeranda = new BerandaFragment();
        fragmentRiwayat = new RiwayatFragment();
        fragmentPesanan = new PesananFragment();
        fragmentInbox = new InboxFragment();
        fragmentAkun = new AkunFragment();
        fragmentActive = fragmentBeranda;

        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        fragmentManager.beginTransaction().add(R.id.frameLayoutFragment, fragmentAkun, "Akun").hide(fragmentAkun).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayoutFragment, fragmentInbox, "Inbox").hide(fragmentInbox).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayoutFragment, fragmentPesanan, "Pesanan").hide(fragmentPesanan).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayoutFragment, fragmentRiwayat, "Riwayat").hide(fragmentRiwayat).commit();
        fragmentManager.beginTransaction().add(R.id.frameLayoutFragment, fragmentBeranda, "Beranda").commit();
    }

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.menu_beranda:
                    fragmentManager.beginTransaction().hide(fragmentActive).show(fragmentBeranda).commit();
                    fragmentActive = fragmentBeranda;
                    doubleBackToExit = true;
                    return true;
                case R.id.menu_simpan:
                    fragmentManager.beginTransaction().hide(fragmentActive).show(fragmentRiwayat).commit();
                    fragmentActive = fragmentRiwayat;
                    doubleBackToExit = false;
                    return true;
                case R.id.menu_pesanan:
                    //((VideoFragment) fragmentVideo).pauseVideo();
                    fragmentManager.beginTransaction().hide(fragmentActive).show(fragmentPesanan).commit();
                    fragmentActive = fragmentPesanan;
                    doubleBackToExit = false;
                    return true;
                case R.id.menu_inbox:
                    fragmentManager.beginTransaction().hide(fragmentActive).show(fragmentInbox).commit();
                    fragmentActive = fragmentInbox;
                    doubleBackToExit = false;
                    return true;
                case R.id.menu_akun:
                    fragmentManager.beginTransaction().hide(fragmentActive).show(fragmentAkun).commit();
                    fragmentActive = fragmentAkun;
                    doubleBackToExit = false;
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onBackPressed() {
        if (doubleBackToExit) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExit = true;
        Toast.makeText(this, "Tekan sekali lagi untuk keluar.", Toast.LENGTH_SHORT).show();
    }

    public void updateStatusBarColor(String color){// Color must be in hexadecimal fromat
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor(color));
        }
    }
}
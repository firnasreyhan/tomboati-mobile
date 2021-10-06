package com.android.tomboati.view.activity.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.view.fragment.homepage.akun.AkunMitraFragment;
import com.android.tomboati.view.fragment.homepage.akun.AkunNonMitraFragment;
import com.android.tomboati.view.fragment.homepage.BerandaFragment;
import com.android.tomboati.view.fragment.homepage.InboxFragment;
import com.android.tomboati.view.fragment.homepage.PesananFragment;
import com.android.tomboati.view.fragment.homepage.RiwayatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private boolean doubleBackToExit;
    private boolean loginUserFix = false;

    private Fragment berandaFragment, riwayatFragment, pesananFragment, akunFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_main);

        if(PreferenceAkun.getAkun(this).isFieldFilled()) {
            loginUserFix = true;
        }

        berandaFragment = new BerandaFragment();
        riwayatFragment = new RiwayatFragment();
        pesananFragment = new PesananFragment();
        akunFragment =
            PreferenceAkun.getAkun(this).getPaket().equals("MITRA") ?
                new AkunMitraFragment()
                        :
                new AkunNonMitraFragment()
        ;

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);

        if(Utility.isConnecting(this)) {
            setFragment(berandaFragment);
            bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
                @SuppressLint("NonConstantResourceId")
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragmentActive = berandaFragment;
                    switch (item.getItemId()){
                        case R.id.menu_beranda:
                            fragmentActive = berandaFragment;
                            break;
                        case R.id.menu_riwayat:
                            fragmentActive = loginUserFix ? riwayatFragment : null;
                            break;
                        case R.id.menu_pesanan:
                            fragmentActive = loginUserFix ? pesananFragment : null;
                            break;
                        case R.id.menu_inbox:
                            fragmentActive = loginUserFix ? new InboxFragment() : null;
                            break;
                        case R.id.menu_akun:
                            fragmentActive = akunFragment;
                            break;
                    }
//                    return false;
                    if(fragmentActive != null) {
                        if(!fragmentActive.isInLayout()) {
                            setFragment(fragmentActive);
                        }
                        return true;
                    } else {
                        Toast.makeText(getApplicationContext(), "Mohon lengkapi data diri anda untuk menggunakan fitur ini", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }
            });
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

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutFragment, fragment).commit();
    }

}
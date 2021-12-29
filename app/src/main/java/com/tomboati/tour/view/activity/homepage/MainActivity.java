package com.tomboati.tour.view.activity.homepage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.databinding.ActivityMainBinding;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.activity.base.BaseNonToolbarActivity;
import com.tomboati.tour.view.fragment.homepage.akun.AkunMitraFragment;
import com.tomboati.tour.view.fragment.homepage.akun.AkunNonMitraFragment;
import com.tomboati.tour.view.fragment.homepage.BerandaFragment;
import com.tomboati.tour.view.fragment.homepage.InboxFragment;
import com.tomboati.tour.view.fragment.homepage.PesananFragment;
import com.tomboati.tour.view.fragment.homepage.RiwayatFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends BaseNonToolbarActivity {

    private boolean doubleBackToExit;
    private boolean loginUserFix = false;
    private ActivityMainBinding bind;
    private Fragment berandaFragment, riwayatFragment, pesananFragment, akunFragment;

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityMainBinding.inflate(getLayoutInflater());
        if(PreferenceAkun.getAkun(this).isFieldFilled()) {
            loginUserFix = true;
        }

        berandaFragment = new BerandaFragment();
        riwayatFragment = new RiwayatFragment();
        pesananFragment = new PesananFragment();
        final String PAKET = PreferenceAkun.getAkun(this).getPaket();
        akunFragment = (PAKET.equals("MITRA") || PAKET.equals("RESELLER")) ?
                        new AkunMitraFragment()
                        :
                        new AkunNonMitraFragment()
        ;

        if(Utility.isConnecting(this)) {
            setFragment(berandaFragment);
            bind.bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
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

                if(fragmentActive != null) {
                    if(!fragmentActive.isInLayout()) {
                        setFragment(fragmentActive);
                    }
                    return true;
                } else {
                    showToast("Mohon lengkapi data diri anda untuk menggunakan fitur ini");
                    return false;
                }
            });
        }
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

    @Override
    public void onBackPressed() {
        if(Utility.isConnecting(this)) {
            if (doubleBackToExit) {
                super.onBackPressed();
            }
            this.doubleBackToExit = true;
            showToast("Tekan sekali lagi untuk keluar.");
            new Handler().postDelayed(() -> doubleBackToExit = false, 4000);
        } else {
            this.finish();
        }
    }

    public void setFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frameLayoutFragment, fragment).commit();
    }

}
package com.android.tomboati.view.fragment.homepage.akun;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.android.tomboati.R;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.view.activity.homepage.KodeReferralActivity;
import com.android.tomboati.view.activity.mitra.auth.AuthLoginMitraActivity;
import com.android.tomboati.view.activity.mitra.pendaftaran.RegistrasiDataDiriMitraActivity;
import com.android.tomboati.view.activity.mitra.pendaftaran.SyaratPendaftaranMitraActivity;
import com.google.android.material.button.MaterialButton;

public class AkunNonMitraFragment extends Fragment {

    private ConstraintLayout menuLengkapiBiodata, menuDaftarSebagaiMitra, menuMasukSebagaiMitra, menuKodeReferral, menuKebijakanPrivasi;
    private TextView textViewNamaPengguna;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_akun_non_mitra, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menuLengkapiBiodata = view.findViewById(R.id.menuLengkapiBiodata);
        menuDaftarSebagaiMitra = view.findViewById(R.id.menuDaftarSebagaiMitra);
        menuMasukSebagaiMitra = view.findViewById(R.id.menuMasukSebagaiMitra);
        menuKodeReferral = view.findViewById(R.id.menuKodeReferral);
        menuKebijakanPrivasi = view.findViewById(R.id.menuKebijakanPrivasi);
        textViewNamaPengguna = view.findViewById(R.id.textViewNamaPengguna);

        Log.d("TAG", "onViewCreated: " + PreferenceAkun.getAkun(view.getContext()).isFieldFilled());

        if(PreferenceAkun.getAkun(view.getContext()).isFieldFilled()) {
            menuLengkapiBiodata.setVisibility(View.GONE);
            textViewNamaPengguna.setText(PreferenceAkun.getAkun(getContext()).getName());
        }

        menuLengkapiBiodata.setOnClickListener(v -> {
            final Intent INTENT = new Intent(v.getContext(), RegistrasiDataDiriMitraActivity.class);
            INTENT.putExtra("IS_DAFTAR_MITRA", false);
            startActivity(INTENT);
        });

        menuMasukSebagaiMitra.setOnClickListener(v -> startActivity(new Intent(v.getContext(), AuthLoginMitraActivity.class)));

        menuKodeReferral.setOnClickListener(v -> startActivity(new Intent(v.getContext(), KodeReferralActivity.class)));

        menuDaftarSebagaiMitra.setOnClickListener(v -> startActivity(new Intent(v.getContext(), SyaratPendaftaranMitraActivity.class)));
    }
}

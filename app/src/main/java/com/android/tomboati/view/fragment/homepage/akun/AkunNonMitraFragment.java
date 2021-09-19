package com.android.tomboati.view.fragment.homepage.akun;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.android.tomboati.R;
import com.android.tomboati.model.AkunModel;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.view.activity.homepage.KodeReferralActivity;
import com.android.tomboati.view.activity.mitra.auth.AuthLoginMitraActivity;
import com.android.tomboati.view.activity.mitra.pendaftaran.RegistrasiDataDiriMitraActivity;
import com.android.tomboati.view.activity.mitra.pendaftaran.SyaratPendaftaranMitraActivity;
import com.google.android.material.button.MaterialButton;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AkunNonMitraFragment extends Fragment {

    private ConstraintLayout menuLengkapiBiodata, menuDaftarSebagaiMitra, menuMasukSebagaiMitra, menuKodeReferral, menuKebijakanPrivasi;
    private TextView textViewNamaPengguna, textLengkapiBiodata, textDeskripsiBiodata;
    private CircleImageView imageViewProfil;
    private AkunModel MODEL;

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
        textLengkapiBiodata = view.findViewById(R.id.textLengkapiBiodata);
        textDeskripsiBiodata = view.findViewById(R.id.textDeskripsiBiodata);
        imageViewProfil = view.findViewById(R.id.imageVIewProfile);

        MODEL = PreferenceAkun.getAkun(view.getContext());

        if(MODEL.isFieldFilled()) {
            textViewNamaPengguna.setText(PreferenceAkun.getAkun(view.getContext()).getName());
            textLengkapiBiodata.setText("Ubah Data Diri");
            textDeskripsiBiodata.setText("Ubah data diri anda disini");
            picassoLoad(MODEL.getFotoKTP(), imageViewProfil);
        }

        if(PreferenceAkun.getAkun(view.getContext()).isSuksesDaftarMitra()) {
            menuDaftarSebagaiMitra.setVisibility(View.GONE);
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

    private void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }
}
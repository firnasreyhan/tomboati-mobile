package com.android.tomboati.view.fragment.homepage.akun;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.preference.PreferenceAkun;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.view.activity.homepage.HubungiKamiActivity;
import com.android.tomboati.view.activity.homepage.KodeReferralActivity;
import com.android.tomboati.view.activity.homepage.MainActivity;
import com.android.tomboati.view.activity.mitra.auth.AuthRegisterUserActivity;
import com.android.tomboati.view.activity.mitra.auth.UbahPasswordActivity;
import com.android.tomboati.viewmodel.tomboati.mitra.LoginAkunMitraViewModel;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AkunMitraFragment extends Fragment {

    private TextView textViewNamaAkunMitra;
    private CircleImageView imageViewFotoAkunMitra;
    private ConstraintLayout constrainLayoutKodeReferral, consraintLayoutLogout,
            constraintLayoutIbahPassword, menuHubungiKami, constraintLayoutUbahProfil;

    private LoginAkunMitraViewModel viewModel;
    private final LifecycleOwner OWNER = this;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_akun_mitra, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(LoginAkunMitraViewModel.class);

        textViewNamaAkunMitra = view.findViewById(R.id.textViewNamaAkunMitra);
        imageViewFotoAkunMitra = view.findViewById(R.id.imageViewFotoAkunMitra);
        constrainLayoutKodeReferral = view.findViewById(R.id.constrainLayoutKodeReferral);
        consraintLayoutLogout = view.findViewById(R.id.consraintLayoutLogout);
        constraintLayoutIbahPassword = view.findViewById(R.id.constraintLayoutIbahPassword);
        constraintLayoutUbahProfil = view.findViewById(R.id.constraintLayoutUbahProfil);
        menuHubungiKami = view.findViewById(R.id.menuHubungiKami);

        textViewNamaAkunMitra.setText(PreferenceAkun.getAkun(getContext()).getName());
        picassoLoad(PreferenceAkun.getAkun(getContext()).getPhoto(), imageViewFotoAkunMitra);


        constrainLayoutKodeReferral.setOnClickListener(v ->
                v.getContext().startActivity(new Intent(v.getContext(), KodeReferralActivity.class))
        );

        constraintLayoutIbahPassword.setOnClickListener(v ->
                v.getContext().startActivity(new Intent(v.getContext(), UbahPasswordActivity.class))
        );

        menuHubungiKami.setOnClickListener(v ->
                v.getContext().startActivity(new Intent(v.getContext(), HubungiKamiActivity.class))
        );


        constraintLayoutUbahProfil.setOnClickListener(v ->
                Toast.makeText(v.getContext(), "Coming Soon", Toast.LENGTH_SHORT).show()
        );

        consraintLayoutLogout.setOnClickListener(v -> {
            final AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
            final View views = LayoutInflater.from(getContext()).inflate(R.layout.view_custom_dialog_keluar, null);

            dialog.setView(views);
            dialog.setCancelable(false);

            AlertDialog alert = dialog.create();
            alert.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

            AppCompatButton btnBatal = views.findViewById(R.id.btnBatal);
            AppCompatButton btnKeluar = views.findViewById(R.id.btnKeluar);

            btnBatal.setOnClickListener(v1 -> alert.dismiss());

            btnKeluar.setOnClickListener(v1 -> {
                alert.dismiss();
                AlertProgress progress = new AlertProgress(v, "Sedang mengeluarkan data..");
                progress.showDialog();
                viewModel.logout().observe(OWNER, baseResponse -> {
                    progress.dismissDialog();
                    if (baseResponse.isError()) {
                        AlertInfo info = new AlertInfo(v, baseResponse.getMessage());
                        info.setDialogError();
                        info.showDialog();
                    } else {
                        PreferenceAkun.removeAkun(v.getContext());
                        Intent intent = new Intent(v.getContext(), AuthRegisterUserActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);

                        Toast.makeText(v.getContext(), "Berhasil keluar!", Toast.LENGTH_SHORT).show();
                    }
                });
            });


            alert.show();
        });
    }

    private void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }
}

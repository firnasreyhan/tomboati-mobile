package com.tomboati.tour.view.fragment.homepage.akun;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
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

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.PoinResponse;
import com.tomboati.tour.model.AkunModel;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.utils.AlertProgress;
import com.tomboati.tour.view.activity.homepage.HubungiKamiActivity;
import com.tomboati.tour.view.activity.homepage.KodeReferralActivity;
import com.tomboati.tour.view.activity.homepage.UbahProfilActivity;
import com.tomboati.tour.view.activity.homepage.WebKemitraanActivity;
import com.tomboati.tour.view.activity.mitra.auth.AuthRegisterUserActivity;
import com.tomboati.tour.view.activity.mitra.auth.UbahPasswordActivity;
import com.tomboati.tour.viewmodel.tomboati.mitra.AkunMitraViewModel;
import com.tomboati.tour.viewmodel.tomboati.mitra.LoginAkunMitraViewModel;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AkunMitraFragment extends Fragment {

    private TextView textViewNamaAkunMitra, textViewPoint;
    private CircleImageView imageViewFotoAkunMitra;
    private ConstraintLayout constrainLayoutKodeReferral, consraintLayoutLogout,
            constraintLayoutIbahPassword, menuHubungiKami, constraintLayoutUbahProfil, constrainLayoutKemitraan;

    private AkunMitraViewModel viewModel;
    private final LifecycleOwner OWNER = this;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_akun_mitra, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(requireActivity()).get(AkunMitraViewModel.class);

        textViewNamaAkunMitra = view.findViewById(R.id.textViewNamaAkunMitra);
        imageViewFotoAkunMitra = view.findViewById(R.id.imageViewFotoAkunMitra);
        constrainLayoutKodeReferral = view.findViewById(R.id.constrainLayoutKodeReferral);
        consraintLayoutLogout = view.findViewById(R.id.consraintLayoutLogout);
        constraintLayoutIbahPassword = view.findViewById(R.id.constraintLayoutIbahPassword);
        constraintLayoutUbahProfil = view.findViewById(R.id.constraintLayoutUbahProfil);
        menuHubungiKami = view.findViewById(R.id.menuHubungiKami);
        textViewPoint = view.findViewById(R.id.textViewPoint);
        constrainLayoutKemitraan = view.findViewById(R.id.constrainLayoutKemitraan);

        viewModel.getPoin().observe(OWNER, poinResponse -> {
            if(!poinResponse.isError()) {
                textViewPoint.setText(poinResponse.getPoin());
            }
        });

        final String NAME = PreferenceAkun.getAkun(getContext()).getName();
        final String[] NAME_ARRAY = NAME.split(" ");
        final StringBuilder NAME_FIX = new StringBuilder();
        for (int i = 0; i < NAME_ARRAY.length; i++) {
            if(i < 3) {
                NAME_FIX.append(NAME_ARRAY[i]).append(" ");
            }
        }

        textViewNamaAkunMitra.setText(NAME_FIX);


        constrainLayoutKodeReferral.setOnClickListener(v -> v.getContext().startActivity(new Intent(v.getContext(), KodeReferralActivity.class)));
        constraintLayoutIbahPassword.setOnClickListener(v -> v.getContext().startActivity(new Intent(v.getContext(), UbahPasswordActivity.class)));
        menuHubungiKami.setOnClickListener(v -> v.getContext().startActivity(new Intent(v.getContext(), HubungiKamiActivity.class)));
        constraintLayoutUbahProfil.setOnClickListener(v -> v.getContext().startActivity(new Intent(v.getContext(), UbahProfilActivity.class)));
        constrainLayoutKemitraan.setOnClickListener(v -> v.getContext().startActivity(new Intent(v.getContext(), WebKemitraanActivity.class)));

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

                        Toast.makeText(v.getContext(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            });
            alert.show();
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        AkunModel model = PreferenceAkun.getAkun(getContext());
        picassoLoad(model.getPhoto(), imageViewFotoAkunMitra);
        if(model.getPoin() != null) {
            textViewPoint.setText(model.getPoin());
        }
    }

    private void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }
}

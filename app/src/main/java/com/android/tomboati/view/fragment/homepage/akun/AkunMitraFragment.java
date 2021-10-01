package com.android.tomboati.view.fragment.homepage.akun;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
import com.android.tomboati.view.activity.homepage.KodeReferralActivity;
import com.android.tomboati.view.activity.homepage.MainActivity;
import com.android.tomboati.view.activity.mitra.auth.AuthRegisterUserActivity;
import com.android.tomboati.view.activity.mitra.auth.UbahPasswordActivity;
import com.android.tomboati.viewmodel.tomboati.mitra.LoginAkunMitraViewModel;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class AkunMitraFragment  extends Fragment {

    private TextView textViewNamaAkunMitra;
    private CircleImageView imageViewFotoAkunMitra;
    private ConstraintLayout constrainLayoutKodeReferral, consraintLayoutLogout, constraintLayoutIbahPassword;

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

            textViewNamaAkunMitra.setText(PreferenceAkun.getAkun(getContext()).getName());
            picassoLoad(PreferenceAkun.getAkun(getContext()).getPhoto(), imageViewFotoAkunMitra);


        constrainLayoutKodeReferral.setOnClickListener(v ->
            v.getContext().startActivity(new Intent(v.getContext(), KodeReferralActivity.class))
        );

        constraintLayoutIbahPassword.setOnClickListener(v ->
            v.getContext().startActivity(new Intent(v.getContext(), UbahPasswordActivity.class))
        );

        consraintLayoutLogout.setOnClickListener(v -> {

            AlertProgress progress = new AlertProgress(v, "Sedang mengeluarkan data..");
            progress.showDialog();
            viewModel.logout().observe(OWNER, baseResponse -> {
                progress.dismissDialog();
                if(baseResponse.isError()) {
                    AlertInfo info =  new AlertInfo(v, baseResponse.getMessage());
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

    }

    private void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }
}

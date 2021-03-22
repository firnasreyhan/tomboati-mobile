package com.android.tomboati.view.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.view.activity.KodeReferralActivity;
import com.android.tomboati.view.activity.MainActivity;
import com.android.tomboati.view.activity.SignUpActivity;
import com.android.tomboati.view.activity.UpdateProfileActivity;
import com.android.tomboati.view.activity.doa_dzikir.HitungTasbihLainnyaActivity;
import com.android.tomboati.viewmodel.AkunViewModel;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.squareup.picasso.Picasso;

public class AkunFragment extends Fragment {

    private ProgressDialog progressDialog;
    private TextView textViewSignUp;
    private AkunViewModel akunViewModel;
    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private MaterialButton materialButtonSignIn, materialButtonSignOut, materialButtonKodeReferral, materialButtonProfileEdit, materialButtonPasswordEdit;
    private NestedScrollView nestedScrollView;
    private LinearLayout linearLayoutAkun;
    private ShapeableImageView shapeableImageViewFoto;
    private TextView textViewNamaLengkap, textViewNomorHP;

    private final int loadingTime = 3000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        akunViewModel = ViewModelProviders.of(this).get(AkunViewModel.class);
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        // Initiate component
        progressDialog = new ProgressDialog(getContext());
        textViewSignUp = view.findViewById(R.id.textViewSignUp);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = view.findViewById(R.id.textInputEditTextPassword);
        materialButtonSignIn = view.findViewById(R.id.materialButtonSignIn);
        nestedScrollView = view.findViewById(R.id.nestedScrollView);
        linearLayoutAkun = view.findViewById(R.id.linearLayoutAkun);
        shapeableImageViewFoto = view.findViewById(R.id.shapeableImageViewFoto);
        textViewNamaLengkap = view.findViewById(R.id.textViewNamaLengkap);
        textViewNomorHP = view.findViewById(R.id.textViewNomorHP);
        materialButtonSignOut = view.findViewById(R.id.materialButtonSignOut);
        materialButtonKodeReferral = view.findViewById(R.id.materialButtonKodeReferral);
        materialButtonProfileEdit = view.findViewById(R.id.materialButtonProfileEdit);
        materialButtonPasswordEdit = view.findViewById(R.id.materialButtonPasswordEdit);

        if (AppPreference.getUser(getContext()) != null) {
            nestedScrollView.setVisibility(View.GONE);
            linearLayoutAkun.setVisibility(View.VISIBLE);
//            setAkun();
        } else {
            nestedScrollView.setVisibility(View.VISIBLE);
            linearLayoutAkun.setVisibility(View.GONE);
        }

        textViewSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SignUpActivity.class));
            }
        });

        materialButtonKodeReferral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), KodeReferralActivity.class));
            }
        });

        materialButtonProfileEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), UpdateProfileActivity.class));
            }
        });

        materialButtonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final TextInputEditText[] arrEditText = {textInputEditTextEmail, textInputEditTextPassword};
                final String[] arrValue = {arrEditText[0].getText().toString(), arrEditText[1].getText().toString()};
                int countError = 0;

                for (int i = 0; i < arrEditText.length; i++) {
                    if (arrValue[i].isEmpty()) {
                        arrEditText[i].setError("Mohon isi data berikut!");
                        countError++;
                    }
                }

                if (countError == 0) {
                    AlertProgress progress = new AlertProgress(v, "Sedang memperiksa data");
                    progress.showDialog();

                    new Handler().postDelayed(() -> {
                        akunViewModel.signIn(arrValue[0], arrValue[1]).observe(getViewLifecycleOwner(),
                                new Observer<SignInResponse>() {
                                    @Override
                                    public void onChanged(SignInResponse signInResponse) {
                                        if (!signInResponse.isError()) {
                                            if (!signInResponse.getData().isEmpty()) {
                                                if (progress.isDialogShowing()) {
                                                    progress.dismissDialog();
                                                }

                                                AppPreference.saveUser(v.getContext(), signInResponse.getData().get(0));
                                                Intent intent = new Intent(getContext(), MainActivity.class);
                                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                                startActivity(intent);

                                                Toast.makeText(v.getContext(), "Berhasil masuk!", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                    }
                                });
                    }, loadingTime);
                }
            }
        });

        materialButtonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                final View views = LayoutInflater.from(v.getContext()).inflate(R.layout.view_custom_dialog_keluar, null);

                dialog.setView(views).setCancelable(false);

                AlertDialog alert = dialog.create();
                alert.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

                AppCompatButton btnKeluar = views.findViewById(R.id.btnKeluar);
                AppCompatButton btnBatal = views.findViewById(R.id.btnBatal);

                btnBatal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                    }
                });

                btnKeluar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alert.dismiss();
                        AlertProgress progress = new AlertProgress(v, "Sedang mengeluarkan data");
                        progress.showDialog();

                        new Handler().postDelayed(() -> {
                            akunViewModel.signOut().observe(getActivity(), new Observer<BaseResponse>() {
                                @Override
                                public void onChanged(BaseResponse baseResponse) {
                                    if (!baseResponse.isError()) {
                                        if (progress.isDialogShowing()) {
                                            progress.dismissDialog();
                                        }

                                        Toast.makeText(getContext(), "Berhasil keluar!", Toast.LENGTH_SHORT).show();

                                        AppPreference.removeUser(getContext());
                                        Intent intent = new Intent(getContext(), MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        startActivity(intent);
                                    }
                                }
                            });
                        }, loadingTime);
                    }
                });
                alert.show();
            }
        });

        materialButtonPasswordEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertProgress progress = new AlertProgress(v, "Sedang mengirimkan data");
                progress.showDialog();

                akunViewModel.resetPassword(AppPreference.getUser(v.getContext()).getIdUserRegister())
                    .observe(getActivity(), new Observer<BaseResponse>() {
                        @Override
                        public void onChanged(BaseResponse baseResponse) {

                            new Handler().postDelayed(() -> {
                                if (progress.isDialogShowing()) {
                                    progress.dismissDialog();
                                }

                                AlertInfo info = new AlertInfo(v, baseResponse.getMessage());
                                info.showDialog();
                            }, loadingTime);
                        }
                    });
            }
        });




        return view;
    }

    // Set account to preferences  ===================
    private void setAkun() {
        textViewNamaLengkap.setText(AppPreference.getUser(getContext()).getNamaLengkap());
        textViewNomorHP.setText("+" + AppPreference.getUser(getContext()).getNomorHP());

        Picasso.get()
                .load(AppPreference.getUser(getContext()).getFoto())
                .priority(Picasso.Priority.HIGH)
                .placeholder(R.drawable.ic_logo)
                .into(shapeableImageViewFoto);
    }

    @Override
    public void onResume() {
        super.onResume();
        // If user is logged then set account to active  ===================
        if (AppPreference.getUser(getContext()) != null) {
            setAkun();
        }
    }
}
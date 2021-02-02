package com.android.tomboati.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.api.response.SignInResponse;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.utils.notif.Token;
import com.android.tomboati.view.activity.MainActivity;
import com.android.tomboati.view.activity.SignUpActivity;
import com.android.tomboati.viewmodel.AkunViewModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class AkunFragment extends Fragment {

    private ProgressDialog progressDialog;
    private TextView textViewSignUp;
    private AkunViewModel akunViewModel;
    private TextInputEditText textInputEditTextEmail, textInputEditTextPassword;
    private MaterialButton materialButtonSignIn, materialButtonSignOut;
    private ConstraintLayout constraintLayoutSignIn;
    private LinearLayout linearLayoutAkun;
    private ShapeableImageView shapeableImageViewFoto;
    private TextView textViewNamaLengkap, textViewNomorHP;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        akunViewModel = ViewModelProviders.of(getActivity()).get(AkunViewModel.class);
        View view = inflater.inflate(R.layout.fragment_akun, container, false);

        progressDialog = new ProgressDialog(getContext());
        textViewSignUp = view.findViewById(R.id.textViewSignUp);
        textInputEditTextEmail = view.findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = view.findViewById(R.id.textInputEditTextPassword);
        materialButtonSignIn = view.findViewById(R.id.materialButtonSignIn);
        constraintLayoutSignIn = view.findViewById(R.id.constraintLayoutSignIn);
        linearLayoutAkun = view.findViewById(R.id.linearLayoutAkun);
        shapeableImageViewFoto = view.findViewById(R.id.shapeableImageViewFoto);
        textViewNamaLengkap = view.findViewById(R.id.textViewNamaLengkap);
        textViewNomorHP = view.findViewById(R.id.textViewNomorHP);
        materialButtonSignOut = view.findViewById(R.id.materialButtonSignOut);

        if (AppPreference.getUser(getContext()) != null) {
//            ((MainActivity)getActivity()).updateStatusBarColor("#00441F");
            constraintLayoutSignIn.setVisibility(View.GONE);
            linearLayoutAkun.setVisibility(View.VISIBLE);

            setAkun();

            materialButtonSignOut.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    progressDialog.setMessage("Mohon tunggu sebentar...");
                    progressDialog.setCancelable(false);
                    progressDialog.show();

                    int loadingTime = 3000;
                    new Handler().postDelayed(() -> {
                        akunViewModel.signOut().observe(getActivity(), new Observer<BaseResponse>() {
                            @Override
                            public void onChanged(BaseResponse baseResponse) {
                                if (progressDialog.isShowing()) {
                                    progressDialog.dismiss();
                                }
                                Toast.makeText(getContext(), baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                if (!baseResponse.isError()) {
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
        } else {
            constraintLayoutSignIn.setVisibility(View.VISIBLE);
            linearLayoutAkun.setVisibility(View.GONE);
            textViewSignUp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(v.getContext(), SignUpActivity.class));
                }
            });

            materialButtonSignIn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean cek1 = true;
                    boolean cek2 = true;

                    if (textInputEditTextEmail.getText().toString().isEmpty()){
                        textInputEditTextEmail.setError("Mohon isi data berikut!");
                        cek1 = false;
                    }

                    if (textInputEditTextPassword.getText().toString().isEmpty()){
                        textInputEditTextPassword.setError("Mohon isi data berikut!");
                        cek2 = false;
                    }

                    if (cek1 && cek2) {
                        progressDialog.setMessage("Mohon tunggu sebentar...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        int loadingTime = 3000;
                        new Handler().postDelayed(() -> {
                            akunViewModel.signIn(
                                    textInputEditTextEmail.getText().toString(),
                                    textInputEditTextPassword.getText().toString()
                            ).observe(getActivity(), new Observer<SignInResponse>() {
                                @Override
                                public void onChanged(SignInResponse signInResponse) {
                                    if (progressDialog.isShowing()) {
                                        progressDialog.dismiss();
                                    }
                                    Toast.makeText(getContext(), signInResponse.getMessage(), Toast.LENGTH_SHORT).show();
                                    if (!signInResponse.isError()) {
                                        if (!signInResponse.getData().isEmpty()) {
                                            AppPreference.saveUser(v.getContext(), signInResponse.getData().get(0));
                                            Intent intent = new Intent(getContext(), MainActivity.class);
                                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                        }
                                    }
                                }
                            });
                        }, loadingTime);
                    }
                }
            });
        }

        return view;
    }

    private void setAkun() {
        textViewNamaLengkap.setText(AppPreference.getUser(getContext()).getNamaLengkap());
        textViewNomorHP.setText(AppPreference.getUser(getContext()).getNomorHP());

        Glide.with(getContext())
                .load(AppPreference.getUser(getContext()).getFoto())
                .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
                .skipMemoryCache(true)
                .dontAnimate()
                .dontTransform()
                .priority(Priority.IMMEDIATE)
                .encodeFormat(Bitmap.CompressFormat.PNG)
                .format(DecodeFormat.DEFAULT)
                .placeholder(R.drawable.ic_logo)
                .into(shapeableImageViewFoto);
    }
}
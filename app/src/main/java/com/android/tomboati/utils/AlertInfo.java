package com.android.tomboati.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.android.tomboati.R;

public class AlertInfo {

    private final AlertDialog alert;
    private final View views;

    public AlertInfo(View v) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
        views = LayoutInflater.from(v.getContext()).inflate(R.layout.view_custom_dialog_info, null);

        dialog.setView(views).setCancelable(false);

        this.alert = dialog.create();
        this.alert.getWindow().requestFeature(Window.FEATURE_NO_TITLE);


        Button dismiss = this.views.findViewById(R.id.btnDismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissDialog();
            }
        });
    }

    public AlertInfo(Activity c, String textCustom, Intent intent) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(c);
        views = LayoutInflater.from(c).inflate(R.layout.view_custom_dialog_info, null);

        dialog.setView(views).setCancelable(false);

        this.alert = dialog.create();
        this.alert.getWindow().requestFeature(Window.FEATURE_NO_TITLE);

        TextView tv = this.views.findViewById(R.id.textCustom);
        tv.setText(textCustom);

        Button dismiss = this.views.findViewById(R.id.btnDismiss);
        dismiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                c.startActivity(intent);
                c.finish();
            }
        });
    }

    public AlertInfo(View v, String textCustom) {
        this(v);
        TextView tv = this.views.findViewById(R.id.textCustom);
        tv.setText(textCustom);
    }

    public void showDialog() {
        this.alert.show();
    }

    private void dismissDialog() {
        this.alert.dismiss();
    }



}

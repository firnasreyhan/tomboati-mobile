package com.android.tomboati.view.activity.sholat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;

import com.android.tomboati.R;
import com.android.tomboati.adapter.MasjidAdapter;
import com.android.tomboati.api.response.MasjidResponse;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.viewmodel.MasjidTerdekatViewModel;
import com.intentfilter.androidpermissions.PermissionManager;
import com.intentfilter.androidpermissions.models.DeniedPermissions;

import java.util.Arrays;
import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationParams;

public class MasjidTerdekatActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private MasjidTerdekatViewModel masjidTerdekatViewModel;
    private final int LIMIT = 10;
    private final String QUERY = "Mosque";
    private RecyclerView recyclerViewMasjidTerdekat;
    private AlertDialog.Builder alert;
    private PermissionManager permissionManager;
    private AlertProgress progress;

    private boolean isLoaded = false;

    // Check gps provider is enabled
    private boolean isProviderEnable() {
        return SmartLocation.with(this).location().state().isAnyProviderAvailable();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_masjid_terdekat);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Masjid Terdekat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        progress = new AlertProgress(this, "Sedang mengambil data");
        recyclerViewMasjidTerdekat = findViewById(R.id.recyclerViewMasjidTerdekat);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        // Check if network provider is not enabled
        if(Utility.isConnecting(this)) {
            masjidTerdekatViewModel = ViewModelProviders.of(this).get(MasjidTerdekatViewModel.class);
            // Check if gps provider is not enabled
            if (!isLoaded) {
                if (!isProviderEnable()) {
                    // If is not enabled showing alert dialog
                    alert = new AlertDialog.Builder(this);
                    alert.setTitle("GPS settings");
                    alert.setMessage("GPS tidak diaktifkan. Apakah Anda ingin pergi ke menu pengaturan?");
                    alert.setCancelable(false);
                    alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Goto setting page for gps activated
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                        }
                    });
                    alert.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            finish();
                        }
                    });
                    alert.show();
                } else {
                    cekPermission();
                }
            }
        }
    }

    // Request permission function
    private void cekPermission() {
        // Show permission dialog using library androidPermission
        permissionManager = PermissionManager.getInstance(this);
        permissionManager.checkPermissions(Arrays.asList(Utility.PERMISSION),
                new PermissionManager.PermissionRequestListener() {
                    @Override
                    public void onPermissionGranted() {
                        // Write command here where permission is granted
                        showLocation();
                    }

                    @Override
                    public void onPermissionDenied(DeniedPermissions deniedPermissions) {
                        // Write command here where permission is denied
                        showDialogSetting();
                    }
                });
    }

    private void showLocation() {
        // Start ProgressDialog
        progress.showDialog();

        // Get location using library SmartLocation
        SmartLocation.with(this).location().config(LocationParams.BEST_EFFORT).oneFix().start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {
                showLokasiMasjid( location.getLatitude(),location.getLongitude());
            }
        });
    }

    private void showDialogSetting() {
        // Showing alert dialog where permission is denied
        alert = new AlertDialog.Builder(this);
        alert.setTitle("Diperlukan akses lokasi!");
        alert.setMessage("Aplikasi ini membutuhkan akses lokasi, Apakah anda setuju?");
        alert.setCancelable(false);
        alert.setPositiveButton("Pengaturan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Showing setting page where setting button is pressed
                openSetting();
                finish();
            }
        });
        alert.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Closing activity where back button is pressed
                dialog.dismiss();
                finish();
            }
        });
        alert.show();
    }

    private void openSetting() {
        // Open setting application page detail for allow permission
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, permissionManager.getResultCode());
    }

    private void showLokasiMasjid(double latitude, double longitude) {
        final String proximity = "" + longitude + "," + latitude;
        masjidTerdekatViewModel.masjid(
                QUERY,
                proximity,
                LIMIT
        ).observe(this, new Observer<List<MasjidResponse.Feature>>() {
            @Override
            public void onChanged(List<MasjidResponse.Feature> features) {
                if (!features.isEmpty()) {
                    MasjidAdapter masjidAdapter = new MasjidAdapter(features, latitude, longitude);
                    recyclerViewMasjidTerdekat.setLayoutManager(new LinearLayoutManager(MasjidTerdekatActivity.this));
                    recyclerViewMasjidTerdekat.setAdapter(masjidAdapter);
                }
                if (progress.isDialogShowing()) {
                    progress.dismissDialog();
                }
            }
        });
    }
}
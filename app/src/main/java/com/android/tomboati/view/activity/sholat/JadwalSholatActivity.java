package com.android.tomboati.view.activity.sholat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.viewmodel.sholat.JadwalSholatViewModel;
import com.google.android.material.button.MaterialButton;
import com.intentfilter.androidpermissions.PermissionManager;
import com.intentfilter.androidpermissions.models.DeniedPermissions;

import java.util.Arrays;
import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationParams;

public class JadwalSholatActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private JadwalSholatViewModel jadwalSholatViewModel;
    private TextView hijriah, masehi, kota, imsak, subuh, terbit, dhuha, dzuhur, ashar, maghrib, isya;
    private PermissionManager permissionManager;
    private AlertProgress progress;
    private AlertDialog.Builder alert;

    private boolean isLoaded = false;

    // Check gps provider is enabled
    private boolean isProviderEnable() {
        return SmartLocation.with(this).location().state().isAnyProviderAvailable();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_jadwal_sholat);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Jadwal Sholat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        hijriah = findViewById(R.id.hijriah);
        masehi = findViewById(R.id.masehi);
        kota = findViewById(R.id.kota);
        imsak = findViewById(R.id.imsak);
        subuh = findViewById(R.id.subuh);
        terbit = findViewById(R.id.terbit);
        dhuha = findViewById(R.id.dhuha);
        dzuhur = findViewById(R.id.dzuhur);
        ashar = findViewById(R.id.ashar);
        maghrib = findViewById(R.id.maghrib);
        isya = findViewById(R.id.isya);

        MaterialButton change_date = findViewById(R.id.ubah_tanggal);
        progress = new AlertProgress(this, "Sedang mengambil data");

        // Where button change date is on clicking
        change_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Check if gps provider is enabled
                if (isProviderEnable()) {
                    // Where gps provider is enabled than show date picker dialog
                    new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                            progress.showDialog();
                            // Show jadwal sholat using date obtained from date picker
                            // and using latitude, longitude from Utility temporary for to
                            // avoid location repeatedly
                            showJadwalSholat(year, month, dayOfMonth, Utility.getLatitude(), Utility.getLongitude(), Utility.getGMT());
                        }
                    }, Utility.getYear(), Utility.getMonth(), Utility.getDay()).show();
                } else {
                    // Where gps provider is not enabled, than show toast information
                    Toast.makeText(getApplicationContext(), "Gps anda belum aktif", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
            jadwalSholatViewModel = ViewModelProviders.of(this).get(JadwalSholatViewModel.class);
            // Check if gps provider is not enabled
            if (!isLoaded) {
                if (!isProviderEnable()) {
                    alert = new AlertDialog.Builder(getApplicationContext());
                    alert.setTitle("GPS settings");
                    alert.setMessage("GPS tidak diaktifkan. Apakah Anda ingin pergi ke menu pengaturan?");
                    alert.setCancelable(false);
                    alert.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
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

                // Get location using reverse geocode
                SmartLocation.with(JadwalSholatActivity.this).geocoding().reverse(location, new OnReverseGeocodingListener() {
                    @Override
                    public void onAddressResolved(Location location, List<Address> list) {
                        String text_kota;
                        if (list.size() > 0) {
                            String kab = list.get(0).getSubAdminArea();
                            String kecamatan = list.get(0).getLocality();
                            String negara = list.get(0).getCountryName();

                            // Setting text kota with kecamatan, kab - negara
                            text_kota = kecamatan.concat(", ").concat(kab).concat(" - ").concat(negara);
                        } else {
                            text_kota = "Location Not Found!";
                        }
                        kota.setText(text_kota);

                    }
                });

                // Save latitude and longitude into Utility as temporary
                Utility.setLatitude(location.getLatitude());
                Utility.setLongitude(location.getLongitude());

                // Show jadwal sholat using response API
                showJadwalSholat(
                        Utility.getYear(), Utility.getMonth(), Utility.getDay(),
                        location.getLatitude(),
                        location.getLongitude(), Utility.getGMT()
                );
            }
        });
    }

    private void showJadwalSholat(int year, int month, int day, double latitude, double longitude, int timezone) {
        jadwalSholatViewModel.jadwalSholat( year, month, day, latitude, longitude, timezone
        ).observe(this, new Observer<JadwalSholatResponse>() {
            @Override
            public void onChanged(JadwalSholatResponse jadwalSholatResponse) {
                if (jadwalSholatResponse != null) {
                    JadwalSholatResponse.Data.DetailData.DetailDetailData jadwal = jadwalSholatResponse.getData().getData().getData();
                    imsak.setText(jadwal.getShortImsak());
                    subuh.setText(jadwal.getShortShubuh());
                    terbit.setText(jadwal.getShortSyuruq());
                    dhuha.setText(jadwal.getShortDhuha());
                    dzuhur.setText(jadwal.getShortDhuhur());
                    ashar.setText(jadwal.getShortAshar());
                    maghrib.setText(jadwal.getShortMaghrib());
                    isya.setText(jadwal.getShortIsya());

                    JadwalSholatResponse.Data.DetailData.DetailDate.DateText dates = jadwalSholatResponse.getData().getData().getDate().getText();
                    hijriah.setText(dates.getH());
                    masehi.setText(dates.getM());

                    isLoaded = true;
                }
                if(progress.isDialogShowing()) {
                    progress.dismissDialog();
                }
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
}
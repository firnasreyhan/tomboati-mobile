package com.android.tomboati.view.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.utils.Compass;
import com.android.tomboati.utils.GPSTracker;
import com.google.android.material.button.MaterialButton;

import java.util.Locale;

public class ArahKiblatActivity extends AppCompatActivity {
    private static final String TAG = ArahKiblatActivity.class.getSimpleName();
    private Compass compass;
    private ProgressDialog progressDialog;
    private MaterialButton materialButtonDapatkanLokasi;
    private ImageView qiblatIndicator, imageDial;
    private TextView tvAngle;
    private float currentAzimuth;
    SharedPreferences prefs;
    GPSTracker gps;
    private final int RC_Permission = 1221;
    private boolean isSuccessGetLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_arah_kiblat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        /////////////////////////////////////////////////
        prefs = getSharedPreferences("", MODE_PRIVATE);
        gps = new GPSTracker(this);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //////////////////////////////////////////
        progressDialog = new ProgressDialog(this);
        qiblatIndicator = findViewById(R.id.qibla_indicator);
        imageDial = findViewById(R.id.dial);
        tvAngle = findViewById(R.id.angle);
        materialButtonDapatkanLokasi = findViewById(R.id.materialButtonDapatkanLokasi);

        //////////////////////////////////////////
        qiblatIndicator.setVisibility(View.VISIBLE);
        qiblatIndicator.setVisibility(View.GONE);

        fetch_GPS();
        materialButtonDapatkanLokasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Mohon tunggu sebentar...");
                progressDialog.setCancelable(false);
                progressDialog.show();

                int loadingTime = 5000;
                new Handler().postDelayed(() -> {
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    fetch_GPS();
                }, loadingTime);
            }
        });
        setupCompass();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "start compass");
        if (compass != null) {
            compass.start(this);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        if (compass != null) {
            compass.stop();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (GPSTracker.isFromSetting == true){
            fetch_GPS();
            finish();
            startActivity(getIntent());
            GPSTracker.isFromSetting = false;
        }

        if (compass != null) {
            compass.start(this);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "stop compass");
        if (compass != null) {
            compass.stop();
        }
        if (gps != null) {
            gps.stopUsingGPS();
            gps = null;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        if (requestCode == RC_Permission) {
            // If request is cancelled, the result arrays are empty.
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // permission was granted, yay! Do the
                SaveBoolean("permission_granted", true);
                tvAngle.setText("Akses Lokasi Diberikan");
                qiblatIndicator.setVisibility(View.INVISIBLE);
                qiblatIndicator.setVisibility(View.GONE);

//                fetch_GPS();
            } else {
                Toast.makeText(getApplicationContext(), "Aplikasi ini membutuhkan Akses Lokasi", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private void setupCompass() {
        Boolean permission_granted = GetBoolean("permission_granted");
        if (permission_granted) {
            getBearing();
        } else {
            tvAngle.setText("Akses lokasi belum tersedia");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION},
                        RC_Permission);
            } else {
//                fetch_GPS();
            }
        }


        compass = new Compass(this);
        Compass.CompassListener cl = new Compass.CompassListener() {

            @Override
            public void onNewAzimuth(float azimuth) {
                // adjustArrow(azimuth);
                adjustGambarDial(azimuth);
                adjustArrowQiblat(azimuth);
            }
        };
        compass.setListener(cl);

        ////////////// ADDED CODE ///////////////
        //fetch_GPS();
    }

    public void adjustGambarDial(float azimuth) {
        // Log.d(TAG, "will set rotation from " + currentAzimuth + " to "                + azimuth);

        Animation an = new RotateAnimation(-currentAzimuth, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = (azimuth);
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        imageDial.startAnimation(an);
    }

    public void adjustArrowQiblat(float azimuth) {
        //Log.d(TAG, "will set rotation from " + currentAzimuth + " to "                + azimuth);

        float kiblat_derajat = GetFloat("kiblat_derajat");
        Animation an = new RotateAnimation(-(currentAzimuth) + kiblat_derajat, -azimuth,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);
        currentAzimuth = (azimuth);
        an.setDuration(500);
        an.setRepeatCount(0);
        an.setFillAfter(true);
        qiblatIndicator.startAnimation(an);
        if (kiblat_derajat > 0) {
            qiblatIndicator.setVisibility(View.VISIBLE);
        } else {
            qiblatIndicator.setVisibility(View.INVISIBLE);
            qiblatIndicator.setVisibility(View.GONE);
        }
    }

    @SuppressLint("MissingPermission")
    public void getBearing() {
        // Get the location manager

        float kaabaDegs = GetFloat("kiblat_derajat");
        if (kaabaDegs > 0.0001) {
            String strYourLocation;
            if(gps.getLocation() != null)
                strYourLocation = "Lokasi Anda:  " + gps.getLocation().getLatitude() + ", " + gps.getLocation().getLongitude();
            else
                strYourLocation = "Gagal mendapatkan lokasi anda";
            String strKaabaDirection = String.format(Locale.ENGLISH, "%.0f", kaabaDegs)
                    + " ° " + getDirectionString(kaabaDegs);
            tvAngle.setText(strKaabaDirection);
            // MenuItem item = menu.findItem(R.id.gps);
            //if (item != null) {
            //item.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gps_off));
            //}
            qiblatIndicator.setVisibility(View.VISIBLE);
        } else {
            //fetch_GPS();
        }
    }

    private String getDirectionString(float azimuthDegrees) {
        String where = "Barat Laut";

        if (azimuthDegrees >= 350 || azimuthDegrees <= 10)
            where = "Utara";
        if (azimuthDegrees < 350 && azimuthDegrees > 280)
            where = "Barat Laut";
        if (azimuthDegrees <= 280 && azimuthDegrees > 260)
            where = "Barat";
        if (azimuthDegrees <= 260 && azimuthDegrees > 190)
            where = "Barat Daya";
        if (azimuthDegrees <= 190 && azimuthDegrees > 170)
            where = "Selatan";
        if (azimuthDegrees <= 170 && azimuthDegrees > 100)
            where = "Tenggara";
        if (azimuthDegrees <= 100 && azimuthDegrees > 80)
            where = "Timur";
        if (azimuthDegrees <= 80 && azimuthDegrees > 10)
            where = "Timur Laut";

        return where;
    }

    public void SaveBoolean(String Judul, Boolean bbb) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putBoolean(Judul, bbb);
        edit.apply();
    }

    public Boolean GetBoolean(String Judul) {
        return prefs.getBoolean(Judul, false);
    }

    public void SaveFloat(String Judul, Float bbb) {
        SharedPreferences.Editor edit = prefs.edit();
        edit.putFloat(Judul, bbb);
        edit.apply();
    }

    public Float GetFloat(String Judul) {
        return prefs.getFloat(Judul, 0);
    }

    public void fetch_GPS() {
        double result;
        gps = new GPSTracker(this);
        if (gps.canGetLocation()) {
            double myLat = gps.getLatitude();
            double myLng = gps.getLongitude();
            // \n is for new line
            //Toast.makeText(getApplicationContext(), "Lokasi anda: - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            Log.e("TAG", "GPS is on");
            if (myLat < 0.001 && myLng < 0.001) {
                // qiblatIndicator.isShown(false);
                qiblatIndicator.setVisibility(View.INVISIBLE);
                qiblatIndicator.setVisibility(View.GONE);
                tvAngle.setText("Lokasi belum siap.");
                /*if (item != null) {
                    item.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gps_off));
                }*/
                // Toast.makeText(getApplicationContext(), "Location not ready, Please Restart Application", Toast.LENGTH_LONG).show();
            } else {
                /*if (item != null) {
                    item.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gps_on));
                }*/
                double kaabaLng = 39.826206; // ka'bah Position https://www.latlong.net/place/kaaba-mecca-saudi-arabia-12639.html
                double kaabaLat = Math.toRadians(21.422487); // ka'bah Position https://www.latlong.net/place/kaaba-mecca-saudi-arabia-12639.html
                double myLatRad = Math.toRadians(myLat);
                double longDiff = Math.toRadians(kaabaLng - myLng);
                double y = Math.sin(longDiff) * Math.cos(kaabaLat);
                double x = Math.cos(myLatRad) * Math.sin(kaabaLat) - Math.sin(myLatRad) * Math.cos(kaabaLat) * Math.cos(longDiff);
                result = (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
                SaveFloat("kiblat_derajat", (float) result);
                String strKaabaDirection = String.format(Locale.ENGLISH, "%.0f", (float) result)
                        + " ° " + getDirectionString((float) result);
                tvAngle.setText(strKaabaDirection);
                qiblatIndicator.setVisibility(View.VISIBLE);

                /*Location kaaba = new Location("Kaaba");
                kaaba.setLatitude(39.826206);
                kaaba.setLongitude(21.422487);
                Location currentLocation = gps.getLocation();
                if(currentLocation != null) {
                    float bearTo = currentLocation.bearingTo(kaaba);
                    if(bearTo < 0)
                        bearTo = bearTo + 360;
                }*/
                setupCompass();
            }
            //  Toast.makeText(getApplicationContext(), "lat_saya: "+lat_saya + "\nlon_saya: "+lon_saya, Toast.LENGTH_LONG).show();
        } else {
            // can't get location
            // GPS or Network is not enabled
            // Ask user to enable GPS/network in settings
            gps.showSettingsAlert();

            // qiblatIndicator.isShown(false);
            qiblatIndicator.setVisibility(View.INVISIBLE);
            qiblatIndicator.setVisibility(View.GONE);
            tvAngle.setText("Harap aktifkan Lokasi");
            /*if (item != null) {
                item.setIcon(ContextCompat.getDrawable(getApplicationContext(), R.drawable.gps_off));
            }*/
            // Toast.makeText(getApplicationContext(), "Please enable Location first and Restart Application", Toast.LENGTH_LONG).show();
        }
    }
}
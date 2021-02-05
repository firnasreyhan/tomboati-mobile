package com.android.tomboati.view.fragment;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.adapter.SliderAdapter;
import com.android.tomboati.api.response.JadwalSholatResponse;
import com.android.tomboati.model.SliderModel;
import com.android.tomboati.utils.Utility;
import com.android.tomboati.view.activity.DoaDzikirActivity;
import com.android.tomboati.view.activity.JadwalSholatActivity;
import com.android.tomboati.view.activity.SholatActivity;
import com.android.tomboati.view.activity.UmrohHajiActivity;
import com.android.tomboati.view.activity.WisataReligiActivity;
import com.android.tomboati.viewmodel.BerandaViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.intentfilter.androidpermissions.PermissionManager;
import com.intentfilter.androidpermissions.models.DeniedPermissions;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.nlopez.smartlocation.OnLocationUpdatedListener;
import io.nlopez.smartlocation.OnReverseGeocodingListener;
import io.nlopez.smartlocation.SmartLocation;
import io.nlopez.smartlocation.location.config.LocationParams;

public class BerandaFragment extends Fragment {
    private BerandaViewModel berandaViewModel;
    private SliderView sliderView;
    private SliderAdapter sliderAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ShimmerFrameLayout shimmerFrameLayoutSlider;
    private CardView cardViewUmrohHaji, cardViewWisataReligi, cardViewDoaDzikir, cardViewSholat, cardViewAlQuran, cardViewKalenderHijriah, cardViewQurbanAqiqah, cardViewKomunitas, cardViewTomboatiChannel, cardViewLiveMekkah;

    private PermissionManager permissionManager;
    private ProgressDialog dialog;
    private AlertDialog.Builder alert;

    private ArrayList<JadwalSholatResponse> list = new ArrayList<>();

    // Check gps provider is enabled
    private boolean isProviderEnable() {
        return SmartLocation.with(getContext()).location().state().isAnyProviderAvailable();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        berandaViewModel = ViewModelProviders.of(getActivity()).get(BerandaViewModel.class);
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        sliderView = view.findViewById(R.id.sliderView);
        cardViewUmrohHaji = view.findViewById(R.id.cardViewUmrohHaji);
        cardViewSholat = view.findViewById(R.id.cardViewSholat);
        cardViewWisataReligi = view.findViewById(R.id.cardViewWisataReligi);
        cardViewDoaDzikir = view.findViewById(R.id.cardViewDoaDzikir);
        cardViewTomboatiChannel = view.findViewById(R.id.cardViewTomboatiChannel);
        cardViewLiveMekkah = view.findViewById(R.id.cardViewLiveMekkah);
        cardViewAlQuran = view.findViewById(R.id.cardViewAlQuran);
        cardViewKalenderHijriah = view.findViewById(R.id.cardViewKalenderHijriah);
        cardViewQurbanAqiqah = view.findViewById(R.id.cardViewQurbanAqiqah);
        cardViewKomunitas = view.findViewById(R.id.cardViewKomunitas);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        shimmerFrameLayoutSlider = view.findViewById(R.id.shimmerFrameLayoutSlider);

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3); //set scroll delay in seconds :
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();


//        sliderAdapter = new SliderAdapter(list);
//        sliderView.setSliderAdapter(sliderAdapter);

        cardViewUmrohHaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), UmrohHajiActivity.class));
            }
        });

        cardViewSholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SholatActivity.class));
            }
        });

        cardViewDoaDzikir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DoaDzikirActivity.class));
            }
        });

        cardViewWisataReligi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), WisataReligiActivity.class));
            }
        });

        cardViewTomboatiChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCwDEM2yv71YDtaoxAjrswLA")));
            }
        });

        cardViewLiveMekkah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=k2gOsvK8XNM")));
            }
        });

        cardViewAlQuran.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        cardViewKalenderHijriah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        cardViewQurbanAqiqah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        cardViewKomunitas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                shimmerFrameLayoutSlider.startShimmer();
                shimmerFrameLayoutSlider.setVisibility(View.VISIBLE);
                sliderView.setVisibility(View.GONE);
                onStart();
                new Handler().postDelayed(new Runnable() {
                    @Override public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000);
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if gps provider is not enabled
        if (!isProviderEnable()) {
            // If is not enabled showing alert dialog
            alert = new AlertDialog.Builder(getContext());
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
                }
            });
            alert.show();
        } else {
            cekPermission();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutSlider.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutSlider.stopShimmer();
        super.onPause();
    }

    // Request permission function
    private void cekPermission() {
        // Show permission dialog using library androidPermission
        permissionManager = PermissionManager.getInstance(getActivity());
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
        showProgressDialog();

        // Get location using library SmartLocation
        SmartLocation.with(getContext()).location().config(LocationParams.BEST_EFFORT).oneFix().start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {

                // Get location using reverse geocode
                SmartLocation.with(getActivity()).geocoding().reverse(location, new OnReverseGeocodingListener() {
                    @Override
                    public void onAddressResolved(Location location, List<Address> list) {
                        String text_kota = null;
                        if (list.size() > 0) {
                            String kab = list.get(0).getSubAdminArea();
                            String kecamatan = list.get(0).getLocality();
                            String negara = list.get(0).getCountryName();

                            // Setting text kota with kecamatan, kab - negara
//                            text_kota = kecamatan.concat(", ").concat(kab).concat(" - ").concat(negara);
                            text_kota = kab;
                        } else {
                            text_kota = "Location Not Found!";
                        }
                        //kota.setText(text_kota);
                        Utility.setKota(text_kota);
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
        berandaViewModel.jadwalSholat(
                year,
                month,
                day,
                latitude,
                longitude,
                timezone
        ).observe(getActivity(), new Observer<JadwalSholatResponse>() {
            @Override
            public void onChanged(JadwalSholatResponse jadwalSholatResponse) {
                if (jadwalSholatResponse != null) {
                    list.add(jadwalSholatResponse);
                    Log.e("showJadwalSholat", jadwalSholatResponse.getData().getName());
                    showJadwalSholatMecca(year,
                            month,
                            day,
                            21.422487,
                            39.826206,
                            timezone);
                }
            }
        });
    }

    public void showJadwalSholatMecca(int year, int month, int day, double latitude, double longitude, int timezone) {
        berandaViewModel.jadwalSholat(
                year,
                month,
                day,
                latitude,
                longitude,
                timezone
        ).observe(getActivity(), new Observer<JadwalSholatResponse>() {
            @Override
            public void onChanged(JadwalSholatResponse jadwalSholatResponse) {
                if (jadwalSholatResponse != null) {
                    list.add(jadwalSholatResponse);
                    Log.e("showJadwalSholatMecca", jadwalSholatResponse.getData().getName());
                    Log.e("size", String.valueOf(list.size()));
                    showJadwalSholatMedina(year,
                            month,
                            day,
                            24.470901,
                            39.612236,
                            timezone);
                }
            }
        });
    }

    public void showJadwalSholatMedina(int year, int month, int day, double latitude, double longitude, int timezone) {
        berandaViewModel.jadwalSholat(
                year,
                month,
                day,
                latitude,
                longitude,
                timezone
        ).observe(getActivity(), new Observer<JadwalSholatResponse>() {
            @Override
            public void onChanged(JadwalSholatResponse jadwalSholatResponse) {
                if (jadwalSholatResponse != null) {
                    list.add(jadwalSholatResponse);

                    Log.e("showJadwalSholatMedina", jadwalSholatResponse.getData().getName());
                    Log.e("size", String.valueOf(list.size()));

                    sliderAdapter = new SliderAdapter(list);
                    sliderView.setSliderAdapter(sliderAdapter);

                    shimmerFrameLayoutSlider.stopShimmer();
                    shimmerFrameLayoutSlider.setVisibility(View.GONE);
                    sliderView.setVisibility(View.VISIBLE);

                    dialog.dismiss();
                }
            }
        });
    }

    private void showDialogSetting() {
        // Showing alert dialog where permission is denied
        alert = new AlertDialog.Builder(getContext());
        alert.setTitle("Diperlukan akses lokasi!");
        alert.setMessage("Aplikasi ini membutuhkan akses lokasi, Apakah anda setuju?");
        alert.setCancelable(false);
        alert.setPositiveButton("Pengaturan", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Showing setting page where setting button is pressed
                openSetting();
                dialog.dismiss();
            }
        });
        alert.setNegativeButton("Kembali", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Closing activity where back button is pressed
                dialog.dismiss();
            }
        });
        alert.show();
    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Tunggu Sebentar...");
        dialog.setCancelable(true);
        dialog.show();
    }

    private void openSetting() {
        // Open setting application page detail for allow permission
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getContext().getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, permissionManager.getResultCode());
    }
}
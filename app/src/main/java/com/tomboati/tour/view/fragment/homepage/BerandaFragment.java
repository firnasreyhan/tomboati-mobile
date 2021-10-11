package com.tomboati.tour.view.fragment.homepage;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.location.Address;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.SliderAdapter;
import com.tomboati.tour.api.response.JadwalSholatResponse;
import com.tomboati.tour.api.response.KataMutiaraResponse;
import com.tomboati.tour.api.response.NewsResponse;
import com.tomboati.tour.api.response.PaketResponse;
import com.tomboati.tour.api.response.PaketWisataResponse;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.utils.AlertProgress;
import com.tomboati.tour.utils.Utility;
import com.tomboati.tour.view.activity.pendaftaran.DetailPaketActivity;
import com.tomboati.tour.view.activity.homepage.DetailNewsActivity;
import com.tomboati.tour.view.activity.doa_dzikir.DoaDzikirActivity;
import com.tomboati.tour.view.activity.komunitas.KomunitasActivity;
import com.tomboati.tour.view.activity.quran.AlQuranNewActivity;
import com.tomboati.tour.view.activity.sholat.SholatActivity;
import com.tomboati.tour.view.activity.homepage.UmrohHajiActivity;
import com.tomboati.tour.view.activity.homepage.WisataReligiActivity;
import com.tomboati.tour.viewmodel.tomboati.homepage.BerandaViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
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
    private CardView cardViewUmrohHaji, cardViewWisataReligi, cardViewDoaDzikir, cardViewSholat, cardViewAlQuran, cardViewQurbanAqiqah, cardViewKomunitas, cardViewTomboatiChannel, cardViewLiveMekkah;

    private CircleImageView circleImageView;
    private TextView textViewNamaLengkap, textViewKataMutiara, textViewJudulNews, textViewStatusUser;
    private TextView textViewSortNews;
    private MaterialButton materialButtonDetailNews;

    private ImageView imageViewPromoHaji1, imageViewPromoHaji2, imageViewPromoHaji3, imageViewPromoTour1, imageViewPromoTour2, imageViewPromoTour3, imageViewNews;

    private AlertDialog.Builder alert;
    private String[] idPaket = new String[6];

    private AlertProgress progress;

    // Check gps provider is enabled
    private boolean isProviderEnable() {
        return SmartLocation.with(getActivity()).location().state().isAnyProviderAvailable();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inflate the layout for this fragment
        berandaViewModel = ViewModelProviders.of(requireActivity()).get(BerandaViewModel.class);

        sliderView = view.findViewById(R.id.sliderView);
        cardViewUmrohHaji = view.findViewById(R.id.cardViewUmrohHaji);
        cardViewSholat = view.findViewById(R.id.cardViewSholat);
        cardViewWisataReligi = view.findViewById(R.id.cardViewWisataReligi);
        cardViewDoaDzikir = view.findViewById(R.id.cardViewDoaDzikir);
        cardViewTomboatiChannel = view.findViewById(R.id.cardViewTomboatiChannel);
        cardViewLiveMekkah = view.findViewById(R.id.cardViewLiveMekkah);
        cardViewAlQuran = view.findViewById(R.id.cardViewAlQuran);
        cardViewQurbanAqiqah = view.findViewById(R.id.cardViewQurbanAqiqah);
        cardViewKomunitas = view.findViewById(R.id.cardViewKomunitas);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        shimmerFrameLayoutSlider = view.findViewById(R.id.shimmerFrameLayoutSlider);
        circleImageView = view.findViewById(R.id.circleImageView);
        textViewNamaLengkap = view.findViewById(R.id.textViewNamaLengkap);
        textViewSortNews = view.findViewById(R.id.textViewSortNews);
        materialButtonDetailNews = view.findViewById(R.id.materialButtonDetailNews);
        imageViewPromoHaji1 = view.findViewById(R.id.imageViewPromoHaji1);
        imageViewPromoHaji2 = view.findViewById(R.id.imageViewPromoHaji2);
        imageViewPromoHaji3 = view.findViewById(R.id.imageViewPromoHaji3);
        imageViewPromoTour1 = view.findViewById(R.id.imageViewPromoTour1);
        imageViewPromoTour2 = view.findViewById(R.id.imageViewPromoTour2);
        imageViewPromoTour3 = view.findViewById(R.id.imageViewPromoTour3);
        textViewKataMutiara = view.findViewById(R.id.textViewKataMutiara);
        textViewJudulNews = view.findViewById(R.id.textViewJudulNews);
        imageViewNews = view.findViewById(R.id.imageViewNews);
        textViewStatusUser = view.findViewById(R.id.textViewStatusUser);

        progress = new AlertProgress(view, "Sedang menyiapkan data");

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3); //set scroll delay in seconds :
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        if (!getActivity().isFinishing()) {

            if (PreferenceAkun.getAkun(getContext()).getName() != null) {
                textViewNamaLengkap.setText(PreferenceAkun.getAkun(getContext()).getName());
            }

            if (PreferenceAkun.getAkun(getContext()).getPhoto() != null) {
                picassoLoad(PreferenceAkun.getAkun(getContext()).getPhoto(),circleImageView);
            }

            textViewStatusUser.setText(PreferenceAkun.getAkun(getContext()).getPaket());

            initOnClickMenu();

            // ON Swipe Layout ===================
            swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    swipeRefreshLayout.setRefreshing(false);
                    shimmerFrameLayoutSlider.startShimmer();
                    shimmerFrameLayoutSlider.setVisibility(View.VISIBLE);
                    sliderView.setVisibility(View.GONE);
                    berandaViewModel.clearDataLocation();
                    showLocation();
                }
            });

            // Button detail news is clicked  ===================
            materialButtonDetailNews.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(v.getContext(), DetailNewsActivity.class));
                }
            });

            // Show paket umrah  ===================
            berandaViewModel.getPaket().observe(getActivity(), new Observer<PaketResponse>() {
                @Override
                public void onChanged(PaketResponse paketResponse) {
                    if (!paketResponse.isError()) {
                        if (!paketResponse.getData().isEmpty()) {
                            ImageView[] arrImage = {imageViewPromoHaji1, imageViewPromoHaji2, imageViewPromoHaji3};
                            for (int i = 0; i < paketResponse.getData().size(); i++) {
                                if (paketResponse.getData().get(i) != null) {
                                    idPaket[i] = paketResponse.getData().get(i).getIdPaket();
                                    picassoLoad(paketResponse.getData().get(i).getImagePaket(), arrImage[i]);
                                }
                            }
                        }
                    }
                }
            });

            // Show paket wisata halal  ===================
            berandaViewModel.getWisataHalal().observe(getActivity(), new Observer<PaketWisataResponse>() {
                @Override
                public void onChanged(PaketWisataResponse paketWisataResponse) {
                    if (!paketWisataResponse.isError()) {
                        if (!paketWisataResponse.getData().isEmpty()) {
                            ImageView[] arrImage = {imageViewPromoTour1, imageViewPromoTour2, imageViewPromoTour3};
                            for (int i = 0; i < paketWisataResponse.getData().size(); i++) {
                                if (paketWisataResponse.getData().get(i) != null) {
                                    idPaket[i + 3] = paketWisataResponse.getData().get(i).getIdWisataHalal();
                                    picassoLoad(paketWisataResponse.getData().get(i).getImageWisata(), arrImage[i]);
                                }
                            }
                        }
                    }
                }
            });

            // Show news  ===================
            berandaViewModel.getNews().observe(getActivity(), new Observer<NewsResponse>() {
                @Override
                public void onChanged(NewsResponse newsResponse) {
                    if (!newsResponse.isError()) {
                        if (!newsResponse.getData().isEmpty()) {
                            picassoLoad(newsResponse.getData().get(0).getFoto(), imageViewNews);

                            textViewJudulNews.setText(newsResponse.getData().get(0).getJudulNews());

                            List<String> s = splitText(newsResponse.getData().get(0).getContentNews());
                            StringBuilder news = new StringBuilder();
                            StringBuilder sortNews = new StringBuilder();
                            for(int i = 0; i < s.size(); i++) {
                                news.append(s.get(i));
                                int maxString = Math.min(s.size(), 3);
                                if(maxString > i) {
                                    sortNews.append(s.get(i));
                                }
                            }
                            Utility.setContentNews(news.toString());
                            Utility.setShortNews(sortNews.toString());
                            textViewSortNews.setText(Utility.getShortNews());
//                            textViewSortNews.setJustificationMode(LineBreaker.JUSTIFICATION_MODE_INTER_WORD);
                        }
                    }
                }
            });

            // Show kata mutiara  ===================
            berandaViewModel.getKataMutiara().observe(getActivity(), new Observer<KataMutiaraResponse>() {
                @Override
                public void onChanged(KataMutiaraResponse kataMutiaraResponse) {
                    if (!kataMutiaraResponse.isError()) {
                        if (!kataMutiaraResponse.getData().isEmpty()) {
                            String s = kataMutiaraResponse.getData().get(0).getTeksKataMutiara().replaceAll("\\<.*?\\>", "");
                            textViewKataMutiara.setText(s);
                        }
                    }
                }
            });
        }

        initOnClickPromo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_beranda, container, false);
    }

    private void initOnClickPromo() {

        ImageView[] imgArr = {
                imageViewPromoHaji1, imageViewPromoHaji2, imageViewPromoHaji3, imageViewPromoTour1, imageViewPromoTour2, imageViewPromoTour3
        };

        for (int i = 0; i < imgArr.length; i++) {
            int j = i;
            imgArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(idPaket[j] != null) {
                        Intent intent = new Intent(v.getContext(), DetailPaketActivity.class);
                        intent.putExtra((j > 2) ? "ID_PAKET_WISATA" : "ID_PAKET", idPaket[j]);
                        v.getContext().startActivity(intent);
                    } else {
                        Toast.makeText(v.getContext(), "Paket Kosong!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    private List<String> splitText(String text) {
        List<String> list = new ArrayList<>();
        String[] texts = text.split(">");
        for (String s : texts) {
            String sz = s + ">";
            sz = sz.replaceAll("<.*?>", "");
            sz = sz.replaceAll("&.*?;", " dan ");
            if (!sz.isEmpty()) {
                list.add(sz);
            }
        }
        return list;
    }

    private void initOnClickMenu() {

        CardView[] cardArr = {
                cardViewUmrohHaji, cardViewSholat, cardViewDoaDzikir, cardViewWisataReligi,
                cardViewAlQuran, cardViewKomunitas,
                cardViewTomboatiChannel, cardViewLiveMekkah
        };

        AppCompatActivity[] activityArr = {
                new UmrohHajiActivity(), new SholatActivity(), new DoaDzikirActivity(),
                new WisataReligiActivity(), new AlQuranNewActivity(),
                new KomunitasActivity()
        };

        String[] uriArr = {
                "https://www.youtube.com/channel/UCwDEM2yv71YDtaoxAjrswLA",
                "https://www.youtube.com/results?search_query=livemakkah"
        };

        for (int i = 0; i < cardArr.length; i++) {
            int j = i;
            cardArr[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(
                        (j > activityArr.length - 1) ?
                            new Intent(Intent.ACTION_VIEW, Uri.parse(uriArr[j - activityArr.length]))
                            :
                            new Intent(v.getContext(), activityArr[j].getClass())
                    );
                }
            });
        }

        cardViewQurbanAqiqah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Coming Soon...", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void checkLocation() {
        if (!isProviderEnable()) {
            alert = new AlertDialog.Builder(getContext());
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
                    getActivity().finish();
                }
            });
            alert.show();
        } else {
            showLocation();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (Utility.getList().isEmpty()) {
            checkLocation();
        } else {
            sliderAdapter = new SliderAdapter(Utility.getList());
            sliderView.setSliderAdapter(sliderAdapter);

            shimmerFrameLayoutSlider.stopShimmer();
            shimmerFrameLayoutSlider.setVisibility(View.GONE);
            sliderView.setVisibility(View.VISIBLE);
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


    private void showLocation() {
        progress.showDialog();

        SmartLocation.with(getContext()).location().config(LocationParams.BEST_EFFORT).oneFix().start(new OnLocationUpdatedListener() {
            @Override
            public void onLocationUpdated(Location location) {

                SmartLocation.with(getActivity()).geocoding().reverse(location, new OnReverseGeocodingListener() {
                    @Override
                    public void onAddressResolved(Location location, List<Address> list) {
                        String text_kota = null;
                        if (list.size() > 0) {
                            String kab = list.get(0).getSubAdminArea();
                            text_kota = kab;
                        } else {
                            text_kota = "Location Not Found!";
                        }
                        Utility.setKota(text_kota);
                    }
                });

                Utility.setLatitude(location.getLatitude());
                Utility.setLongitude(location.getLongitude());

                showJadwalSholat(
                        Utility.getYear(), Utility.getMonth(), Utility.getDay(),
                        location.getLatitude(), location.getLongitude(), Utility.getGMT()
                );
            }
        });
    }

    private void showJadwalSholat(int year, int month, int day, double latitude, double longitude, int timezone) {
        if (!Utility.getList().isEmpty()) {
            Utility.getList().clear();
            sliderAdapter.notifyDataSetChanged();
        }
        berandaViewModel.jadwalSholat(year, month, day, latitude, longitude, timezone ).observe(this
                , new Observer<JadwalSholatResponse>() {
            @Override
            public void onChanged(@Nullable JadwalSholatResponse jadwalSholatResponse) {
                if (jadwalSholatResponse != null) {
                    Utility.getList().add(jadwalSholatResponse);
                    showJadwalSholatMecca(year, month, day, 21.422487, 39.826206, timezone);
                }
            }
        });
    }

    public void showJadwalSholatMecca(int year, int month, int day, double latitude, double longitude, int timezone) {
        berandaViewModel.jadwalSholat( year, month, day, latitude, longitude, timezone ).observe(this
                , new Observer<JadwalSholatResponse>() {
            @Override
            public void onChanged(@Nullable JadwalSholatResponse jadwalSholatResponse) {
                if (jadwalSholatResponse != null) {
                    Utility.getList().add(jadwalSholatResponse);
                    showJadwalSholatMedina(year, month, day, 24.470901, 39.612236, timezone);
                }
            }
        });
    }

    public void showJadwalSholatMedina(int year, int month, int day, double latitude, double longitude, int timezone) {
        berandaViewModel.jadwalSholat( year, month, day, latitude, longitude, timezone  ).observe(this
                , new Observer<JadwalSholatResponse>() {
            @Override
            public void onChanged(@Nullable JadwalSholatResponse jadwalSholatResponse) {
                if (jadwalSholatResponse != null) {
                    Utility.getList().add(jadwalSholatResponse);

                    sliderAdapter = new SliderAdapter(Utility.getList());
                    sliderView.setSliderAdapter(sliderAdapter);

                    shimmerFrameLayoutSlider.stopShimmer();
                    shimmerFrameLayoutSlider.setVisibility(View.GONE);
                    sliderView.setVisibility(View.VISIBLE);

                    if (progress.isDialogShowing()) {
                        progress.dismissDialog();
                    }
                }
            }
        });
    }

    private void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }
}
package com.android.tomboati.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.tomboati.R;
import com.android.tomboati.adapter.SliderAdapter;
import com.android.tomboati.model.SliderModel;
import com.android.tomboati.view.activity.DoaDzikirActivity;
import com.android.tomboati.view.activity.SholatActivity;
import com.android.tomboati.view.activity.UmrohHajiActivity;
import com.android.tomboati.view.activity.WisataReligiActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class BerandaFragment extends Fragment {
    private SliderView sliderView;
    private SliderAdapter sliderAdapter;
    private CardView cardViewUmrohHaji, cardViewWisataReligi, cardViewDoaDzikir, cardViewSholat, cardViewAlQuran, cardViewKalenderHijriah, cardViewQurbanAqiqah, cardViewKomunitas, cardViewTomboatiChannel, cardViewLiveMekkah;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
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

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM); //set indicator animation by using IndicatorAnimationType. :WORM or THIN_WORM or COLOR or DROP or FILL or NONE or SCALE or SCALE_DOWN or SLIDE and SWAP!!
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorSelectedColor(Color.WHITE);
        sliderView.setIndicatorUnselectedColor(Color.GRAY);
        sliderView.setScrollTimeInSec(3); //set scroll delay in seconds :
        sliderView.setAutoCycle(true);
        sliderView.startAutoCycle();

        ArrayList<SliderModel> list = new ArrayList<>();
        list.add(new SliderModel("", "", "", "", "", "", ""));
        list.add(new SliderModel("", "", "Mekah", "", "", "", ""));
        list.add(new SliderModel("", "", "Madinah", "", "", "", ""));

        sliderAdapter = new SliderAdapter(list);

        sliderView.setSliderAdapter(sliderAdapter);

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

        return view;
    }
}
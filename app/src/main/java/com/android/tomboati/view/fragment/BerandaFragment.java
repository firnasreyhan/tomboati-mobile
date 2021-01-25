package com.android.tomboati.view.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.android.tomboati.R;
import com.android.tomboati.adapter.SliderAdapter;
import com.android.tomboati.model.SliderModel;
import com.android.tomboati.view.activity.DoaDzikirActivity;
import com.android.tomboati.view.activity.UmrohHajiActivity;
import com.android.tomboati.view.activity.SholatActivity;
import com.android.tomboati.view.activity.WisataReligiActivity;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class BerandaFragment extends Fragment {
    private SliderView sliderView;
    private SliderAdapter sliderAdapter;
    private LinearLayout linearLayoutHaji, linearLayoutSholat, linearLayoutWisataReligi, linearLayoutDoaDzikir, linearLayoutTomboatiChannel, linearLayoutLiveMekkah;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_beranda, container, false);
        sliderView = view.findViewById(R.id.sliderView);
        linearLayoutHaji = view.findViewById(R.id.linearLayoutHaji);
        linearLayoutSholat = view.findViewById(R.id.linearLayoutSholat);
        linearLayoutWisataReligi = view.findViewById(R.id.linearLayoutWisataReligi);
        linearLayoutDoaDzikir = view.findViewById(R.id.linearLayoutDoaDzikir);
        linearLayoutTomboatiChannel = view.findViewById(R.id.linearLayoutTomboatiChannel);
        linearLayoutLiveMekkah = view.findViewById(R.id.linearLayoutLiveMekkah);

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

        linearLayoutHaji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), UmrohHajiActivity.class));
            }
        });

        linearLayoutSholat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), SholatActivity.class));
            }
        });

        linearLayoutDoaDzikir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), DoaDzikirActivity.class));
            }
        });

        linearLayoutWisataReligi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(v.getContext(), WisataReligiActivity.class));
            }
        });

        linearLayoutTomboatiChannel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCwDEM2yv71YDtaoxAjrswLA")));
            }
        });

        linearLayoutLiveMekkah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=k2gOsvK8XNM")));
            }
        });

        return view;
    }
}
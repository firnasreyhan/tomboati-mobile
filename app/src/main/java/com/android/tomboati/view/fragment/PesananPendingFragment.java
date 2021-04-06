package com.android.tomboati.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.tomboati.R;
import com.android.tomboati.adapter.PesananPendingAdapter;
import com.android.tomboati.api.response.ListPaketVerifyRespone;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.viewmodel.PesananViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class PesananPendingFragment extends Fragment {

    private PesananViewModel viewModel;
    private final LifecycleOwner OWNER = this;
    private RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayoutPesanan;
    private PesananPendingAdapter adapter;
    private List<ListPaketVerifyRespone.DataItem> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pesanan_pending, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(PesananViewModel.class);

        list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerViewPesananPending);
        shimmerFrameLayoutPesanan = view.findViewById(R.id.shimmerFrameLayoutPesanan);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        String idUser = AppPreference.getUser(view.getContext()).getIdUserRegister();

        viewModel.getPaketHajiUmrahVerif(idUser).observe(OWNER, new Observer<ListPaketVerifyRespone>() {
            @Override
            public void onChanged(ListPaketVerifyRespone listPaketVerifyRespone) {
                if(!listPaketVerifyRespone.isError()) {
                    if(!listPaketVerifyRespone.getData().isEmpty()) {
                        list.addAll(listPaketVerifyRespone.getData());
                    }
                }
                viewModel.getPaketWisataHalalVerif(idUser).observe(OWNER, new Observer<ListPaketVerifyRespone>() {
                    @Override
                    public void onChanged(ListPaketVerifyRespone listPaketVerifyRespone) {
                        if(!listPaketVerifyRespone.isError()) {
                            if(!listPaketVerifyRespone.getData().isEmpty()) {
                                list.addAll(listPaketVerifyRespone.getData());
                                adapter = new PesananPendingAdapter(list);
                                recyclerView.setAdapter(adapter);

                                recyclerView.setVisibility(View.VISIBLE);
                                shimmerFrameLayoutPesanan.setVisibility(View.GONE);
                                shimmerFrameLayoutPesanan.stopShimmer();
                            }
                        }
                    }
                });
            }
        });

    }


    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutPesanan.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutPesanan.stopShimmer();
        super.onPause();
    }
}
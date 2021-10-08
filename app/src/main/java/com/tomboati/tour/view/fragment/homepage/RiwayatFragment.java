package com.tomboati.tour.view.fragment.homepage;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.PesananPendingAdapter;
import com.tomboati.tour.api.response.ListPaketVerifyRespone;
import com.tomboati.tour.preference.PreferenceAkun;
import com.tomboati.tour.viewmodel.tomboati.homepage.RiwayatViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class RiwayatFragment extends Fragment {

    private RiwayatViewModel viewModel;
    private final LifecycleOwner OWNER = this;
    private RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayoutPesanan;
    private LinearLayout linearLayoutNoSignIn;
    private PesananPendingAdapter adapter;
    private List<ListPaketVerifyRespone.DataItem> list;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_riwayat, container, false);
        final Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle("List Riwayat");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(RiwayatViewModel.class);

        list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerViewPesananPending);
        shimmerFrameLayoutPesanan = view.findViewById(R.id.shimmerFrameLayoutPesanan);
        linearLayoutNoSignIn = view.findViewById(R.id.linearLayoutNoSignIn);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        swipeRefreshLayout.setOnRefreshListener(() -> {
            recyclerView.setVisibility(View.GONE);
            linearLayoutNoSignIn.setVisibility(View.GONE);
            shimmerFrameLayoutPesanan.setVisibility(View.VISIBLE);
            list.clear();
            onResume();
        });


    }



    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutPesanan.startShimmer();

        String idUser = PreferenceAkun.getAkun(getContext()).getId();
        viewModel.getRiwayatPaketHajiUmrahVerif(idUser).observe(OWNER, new Observer<ListPaketVerifyRespone>() {
            @Override
            public void onChanged(ListPaketVerifyRespone listPaketVerifyRespone) {
                if (listPaketVerifyRespone != null) {
                    if (!listPaketVerifyRespone.isError()) {
                        if (!listPaketVerifyRespone.getData().isEmpty()) {
                            list.addAll(listPaketVerifyRespone.getData());
                        }
                    }
                }
                viewModel.getRiwayatPaketWisataHalalVerif(idUser).observe(OWNER, new Observer<ListPaketVerifyRespone>() {
                    @Override
                    public void onChanged(ListPaketVerifyRespone listPaketVerifyRespone1) {
                        shimmerFrameLayoutPesanan.setVisibility(View.GONE);
                        swipeRefreshLayout.setRefreshing(false);
                        shimmerFrameLayoutPesanan.stopShimmer();
                        if (listPaketVerifyRespone1 != null) {
                            if (!listPaketVerifyRespone1.isError()) {
                                if (!listPaketVerifyRespone1.getData().isEmpty()) {
                                    list.addAll(listPaketVerifyRespone1.getData());
                                }
                            }
                        }

                        if (listPaketVerifyRespone != null || listPaketVerifyRespone1 != null) {
                            adapter = new PesananPendingAdapter(list);
                            recyclerView.setAdapter(adapter);
//
                            recyclerView.setVisibility(View.VISIBLE);
                        } else {
                            linearLayoutNoSignIn.setVisibility(View.VISIBLE);
                            recyclerView.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutPesanan.stopShimmer();
        super.onPause();
    }
}
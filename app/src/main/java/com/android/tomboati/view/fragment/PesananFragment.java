package com.android.tomboati.view.fragment;

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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.adapter.PesananPendingAdapter;
import com.android.tomboati.api.response.ListPaketVerifyRespone;
import com.android.tomboati.preference.AppPreference;
import com.android.tomboati.viewmodel.PesananViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.List;

public class PesananFragment extends Fragment {

    private PesananViewModel viewModel;
    private final LifecycleOwner OWNER = this;
    private RecyclerView recyclerView;
    private ShimmerFrameLayout shimmerFrameLayoutPesanan;
    private LinearLayout linearLayoutNoSignIn;
    private PesananPendingAdapter adapter;
    private TextView textView;
    private List<ListPaketVerifyRespone.DataItem> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,  Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_pesanan, container, false);
        final Toolbar toolbar = v.findViewById(R.id.toolbar);
        toolbar.setTitle("List Pesanan");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(PesananViewModel.class);

        list = new ArrayList<>();

        recyclerView = view.findViewById(R.id.recyclerViewPesananPending);
        shimmerFrameLayoutPesanan = view.findViewById(R.id.shimmerFrameLayoutPesanan);
        linearLayoutNoSignIn = view.findViewById(R.id.linearLayoutNoSignIn);
        textView = view.findViewById(R.id.textViewPesananNoSignIn);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));


        if (AppPreference.getUser(view.getContext()) != null) {
            String idUser = AppPreference.getUser(view.getContext()).getIdUserRegister();
            viewModel.getPaketHajiUmrahVerif(idUser).observe(OWNER, new Observer<ListPaketVerifyRespone>() {
                @Override
                public void onChanged(ListPaketVerifyRespone listPaketVerifyRespone) {
                    if (listPaketVerifyRespone != null) {
                        if (!listPaketVerifyRespone.isError()) {
                            if (!listPaketVerifyRespone.getData().isEmpty()) {
                                list.addAll(listPaketVerifyRespone.getData());
                            }
                        }
                    }
                    viewModel.getPaketWisataHalalVerif(idUser).observe(OWNER, new Observer<ListPaketVerifyRespone>() {
                        @Override
                        public void onChanged(ListPaketVerifyRespone listPaketVerifyRespone) {
                            shimmerFrameLayoutPesanan.setVisibility(View.GONE);
                            shimmerFrameLayoutPesanan.stopShimmer();
                            if (listPaketVerifyRespone != null) {
                                if (!listPaketVerifyRespone.isError()) {
                                    if (!listPaketVerifyRespone.getData().isEmpty()) {
                                        list.addAll(listPaketVerifyRespone.getData());
                                        adapter = new PesananPendingAdapter(list);
                                        recyclerView.setAdapter(adapter);

                                        recyclerView.setVisibility(View.VISIBLE);
                                    }
                                }
                            } else {
                                linearLayoutNoSignIn.setVisibility(View.VISIBLE);
                                recyclerView.setVisibility(View.GONE);
                            }
                        }
                    });
                }
            });
        } else {
            shimmerFrameLayoutPesanan.setVisibility(View.GONE);
            shimmerFrameLayoutPesanan.stopShimmer();
            linearLayoutNoSignIn.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
            textView.setText("Masuk untuk melihat pesanan");
        }
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
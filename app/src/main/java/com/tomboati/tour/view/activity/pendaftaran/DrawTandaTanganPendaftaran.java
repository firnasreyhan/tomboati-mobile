package com.tomboati.tour.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.tomboati.tour.viewmodel.tomboati.pendaftaran.PendaftaranWisataHalalViewModel;
import com.rm.freedrawview.FreeDrawView;
import com.rm.freedrawview.PathRedoUndoCountChangeListener;

import com.tomboati.tour.R;
import com.tomboati.tour.api.response.BaseResponse;
import com.tomboati.tour.model.PesananaModel;
import com.tomboati.tour.utils.AlertInfo;
import com.tomboati.tour.utils.AlertProgress;
import com.tomboati.tour.view.activity.homepage.MainActivity;
import com.tomboati.tour.viewmodel.tomboati.pendaftaran.PendaftaranDataKeluargaViewModel;
import com.google.android.material.button.MaterialButton;

public class DrawTandaTanganPendaftaran extends AppCompatActivity {

    private PendaftaranDataKeluargaViewModel viewModel;
    private PendaftaranWisataHalalViewModel viewModelWisata;
    private PesananaModel model;
    private MaterialButton materialButtonPesanSekarang;
    private FreeDrawView freeDrawing;
    private final LifecycleOwner OWNER = this;
    private int undoCounts, redoCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_draw_tanda_tangan_pendaftaran);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Pendaftaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(PendaftaranDataKeluargaViewModel.class);
        viewModelWisata = ViewModelProviders.of(this).get(PendaftaranWisataHalalViewModel.class);
        model = (PesananaModel) getIntent().getSerializableExtra("OBJECT");

        freeDrawing = findViewById(R.id.freeDrawing);

        freeDrawing.setPaintWidthDp(10);

        freeDrawing.setPathRedoUndoCountChangeListener(new PathRedoUndoCountChangeListener() {
            @Override  public void onUndoCountChanged(int undoCount) {  undoCounts = undoCount;  }
            @Override  public void onRedoCountChanged(int redoCount) {  redoCounts = redoCount; }
        });

        findViewById(R.id.clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { freeDrawing.clearDraw(); }
        });

        findViewById(R.id.undo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (undoCounts > 0) { freeDrawing.undoLast(); }
            }
        });

        findViewById(R.id.redo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (redoCounts > 0) { freeDrawing.redoLast(); }
            }
        });

        materialButtonPesanSekarang = findViewById(R.id.materialButtonPesanSekarang);
        materialButtonPesanSekarang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(undoCounts == 0) {
                    Toast.makeText(v.getContext(), "Gambar masih kosong", Toast.LENGTH_SHORT).show();
                } else  {
                    AlertProgress progress = new AlertProgress(v, "Sedang mengirim data");
                    progress.showDialog();

                    freeDrawing.getDrawScreenshot(new FreeDrawView.DrawCreatorListener() {
                        @Override  public void onDrawCreated(Bitmap draw) {
                            model.setTtdPendaftar(draw);

                            MutableLiveData<BaseResponse> pendaftaran = (model.isPaketWisata()) ?
                                viewModelWisata.pendaftaranWisataHalal(model)
                                    :
                                viewModel.pendaftaran(model)
                            ;

                            pendaftaran.observe(OWNER, new Observer<BaseResponse>() {
                                @Override
                                public void onChanged(BaseResponse baseResponse) {
                                    if (progress.isDialogShowing()) {
                                        progress.dismissDialog();
                                    }

                                    if(baseResponse != null) {
                                        AlertInfo info;

                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                                        if (!baseResponse.isError()) {
                                            info = new AlertInfo(DrawTandaTanganPendaftaran.this, "Pendaftaran berhasil", intent);
                                        } else {
                                            info = new AlertInfo(DrawTandaTanganPendaftaran.this, baseResponse.getMessage(), intent);
                                            info.setDialogError();
                                        }
                                        info.showDialog();
                                    }
                                }
                            });
                        } @Override  public void onDrawCreationError() { }
                    });
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
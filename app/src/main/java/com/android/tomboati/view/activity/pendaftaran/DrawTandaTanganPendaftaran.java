package com.android.tomboati.view.activity.pendaftaran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.rm.freedrawview.FreeDrawView;
import com.rm.freedrawview.PathRedoUndoCountChangeListener;

import com.android.tomboati.R;
import com.android.tomboati.api.response.BaseResponse;
import com.android.tomboati.model.PesananaModel;
import com.android.tomboati.utils.AlertInfo;
import com.android.tomboati.utils.AlertProgress;
import com.android.tomboati.view.activity.MainActivity;
import com.android.tomboati.viewmodel.PendaftaranDataKeluargaViewModel;
import com.google.android.material.button.MaterialButton;

public class DrawTandaTanganPendaftaran extends AppCompatActivity {

    private PendaftaranDataKeluargaViewModel viewModel;
    private PesananaModel model;
    private MaterialButton materialButtonPesanSekarang;
    private FreeDrawView freeDrawing;
    private int undoCounts, redoCounts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_tanda_tangan_pendaftaran);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Pendaftaran");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        viewModel = ViewModelProviders.of(this).get(PendaftaranDataKeluargaViewModel.class);
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
                            viewModel.pendaftaran(model).observe(DrawTandaTanganPendaftaran.this, new Observer<BaseResponse>() {
                                @Override
                                public void onChanged(BaseResponse baseResponse) {
                                    if (progress.isDialogShowing()) {
                                        progress.dismissDialog();
                                    }


                                    AlertInfo info;

                                    if (!baseResponse.isError()) {
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                        info = new AlertInfo(v.getContext(),"Pendaftaran berhasil", intent);
//                                        info.showDialog();
                                    } else {
                                        info = new AlertInfo(v, "Gagal mengirim data");
                                    }
                                    info.showDialog();
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
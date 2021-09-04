package com.android.tomboati.view.activity.quran;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.adapter.AyatAdapter;
import com.android.tomboati.api.response.AyatResponse;
import com.android.tomboati.viewmodel.quran.DetailAlQuranViewModel;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.io.IOException;

public class DetailAlQuranActivity extends AppCompatActivity {
    private DetailAlQuranViewModel detailAlQuranViewModel;
    private Toolbar toolbar;
    private TextView textViewNamaSurah;
    private MaterialButton materialButtonPlayPause;
    private SeekBar seekBarAudio;
    private RecyclerView recyclerViewAyat;
    private LinearLayout linearLayoutContent;
    private AyatAdapter ayatAdapter;
    private ShimmerFrameLayout shimmerFrameLayoutAyat;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();
    private ProgressDialog progressDialog;
    private String audioURL, idSurah, namaSurah;
    private File file;
//    private boolean isDownloaded;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_al_quran);
        idSurah = getIntent().getStringExtra("ID_SURAH");
        namaSurah = getIntent().getStringExtra("NAMA_SURAH");

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle(namaSurah);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        detailAlQuranViewModel = ViewModelProviders.of(this).get(DetailAlQuranViewModel.class);
        file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), (namaSurah + ".mp3"));
        progressDialog = new ProgressDialog(this);
        recyclerViewAyat = findViewById(R.id.recyclerViewAyat);
        shimmerFrameLayoutAyat = findViewById(R.id.shimmerFrameLayoutAyat);
        linearLayoutContent = findViewById(R.id.linearLayoutContent);
        textViewNamaSurah = findViewById(R.id.textViewNamaSurah);
        materialButtonPlayPause = findViewById(R.id.materialButtonPlayPause);
        seekBarAudio = findViewById(R.id.seekBarAudio);

        mediaPlayer = new MediaPlayer();

        recyclerViewAyat.setHasFixedSize(true);
        recyclerViewAyat.setLayoutManager(new LinearLayoutManager(this));

        seekBarAudio.setMax(100);

        detailAlQuranViewModel.getAyat(
                idSurah
        ).observe(this, new Observer<AyatResponse>() {
            @Override
            public void onChanged(AyatResponse ayatResponses) {
                if (ayatResponses.isStatus()) {
                    textViewNamaSurah.setText(ayatResponses.getNamaLatin());
                    ayatAdapter = new AyatAdapter(ayatResponses.getAyat());
                    recyclerViewAyat.setAdapter(ayatAdapter);
                    audioURL = ayatResponses.getAudio();

                    linearLayoutContent.setVisibility(View.VISIBLE);
                    shimmerFrameLayoutAyat.setVisibility(View.GONE);
                    shimmerFrameLayoutAyat.stopShimmer();
                }
            }
        });

        if (file.exists()) {
            prepareMediaPlayer();
        }

        materialButtonPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (file.exists()) {
                    if (mediaPlayer.isPlaying()) {
                        handler.removeCallbacks(runnable);
                        mediaPlayer.pause();
                        materialButtonPlayPause.setIconResource(R.drawable.ic_play);
                    } else {
                        mediaPlayer.start();
                        materialButtonPlayPause.setIconResource(R.drawable.ic_pause);
                        updateSeekBar();
                    }
                } else {
                    Log.e("exist", "false");
                    if (isConnectingToInternet()) {
                        progressDialog.setMessage("Mohon Tunggu Sebentar...");
                        progressDialog.setCancelable(false);
                        progressDialog.show();

                        DownloadManager downloadManager = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
                        Uri uri = Uri.parse(audioURL);
                        DownloadManager.Request request = new DownloadManager.Request(uri);
                        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                        request.setDestinationInExternalFilesDir(v.getContext(), Environment.DIRECTORY_DOWNLOADS, (namaSurah + ".mp3"));
                        Long aLong = downloadManager.enqueue(request);
                        registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
                    }
                }
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        shimmerFrameLayoutAyat.startShimmer();
    }

    @Override
    public void onPause() {
        shimmerFrameLayoutAyat.stopShimmer();
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer.isPlaying()) {
            handler.removeCallbacks(runnable);
            mediaPlayer.pause();
            materialButtonPlayPause.setIconResource(R.drawable.ic_play);
        }
    }

    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
        }
    };

    public void prepareMediaPlayer() {
        try {
            File file = new File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS), (namaSurah + ".mp3"));
            mediaPlayer.setDataSource(this, Uri.parse(file.getAbsolutePath()));
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            seekBarAudio.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration())* 100));
            handler.postDelayed(runnable, 1000);
        }

        Log.e("progress", String.valueOf(seekBarAudio.getProgress()));

        if (seekBarAudio.getProgress() >= 95) {
//            handler.removeCallbacks(runnable);
//            mediaPlayer.stop();
            materialButtonPlayPause.setIconResource(R.drawable.ic_play);
        }
    }

    private boolean isConnectingToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    private BroadcastReceiver onComplete = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }

//            finish();
//            overridePendingTransition( 0, 0);
//            startActivity(getIntent());
//            overridePendingTransition( 0, 0);

            prepareMediaPlayer();
            mediaPlayer.start();
            materialButtonPlayPause.setIconResource(R.drawable.ic_pause);
            updateSeekBar();
//
//            isDownloaded = true;
        }
    };
}
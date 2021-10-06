package com.android.tomboati.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.android.tomboati.R;
import com.android.tomboati.adapter.TembangSholawatAdapter;
import com.android.tomboati.model.TembangSholawatModel;
import com.android.tomboati.utils.Utility;

import java.util.ArrayList;
import java.util.List;

public class TembangSholawatActivity extends AppCompatActivity implements TembangSholawatAdapter.onSelectedData {

    private List<TembangSholawatModel> tembangModel;
    private TextView text_judul;
    private TextView duration, current;
    private SeekBar progress;
    private ConstraintLayout view_control;
    private ImageView btnPlayPause;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    private int position = 0;
    private boolean is_playing = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_tembang_sholawat);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Tembang Sholawat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        text_judul = findViewById(R.id.text_judul_control);
        duration = findViewById(R.id.text_duration);
        current = findViewById(R.id.text_current);
        progress = findViewById(R.id.progress);
        view_control = findViewById(R.id.view_control);
        btnPlayPause = findViewById(R.id.btn_play);

        mediaPlayer = new MediaPlayer();
        if (Utility.isConnecting(this)) {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);

            tembangModel = new ArrayList<>();
            addDataTembangSholawat();

            RecyclerView recyclerView = findViewById(R.id.recyclerViewTembangItem);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
            recyclerView.setAdapter(new TembangSholawatAdapter(tembangModel, this));

            btnPlayPause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mediaPlayer.isPlaying()) {
                        handler.removeCallbacks(updater);
                        mediaPlayer.pause();
                        btnPlayPause.setImageResource(R.drawable.ic_play_msc);
                        is_playing = false;
                    } else {
                        mediaPlayer.start();
                        btnPlayPause.setImageResource(R.drawable.ic_pause_msc);
                        updateSeekBar();
                        is_playing = true;
                    }
                }
            });

            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    progress.setProgress(0);
                    btnPlayPause.setImageResource(R.drawable.ic_play_msc);
                    current.setText("00:00");
                    mediaPlayer.reset();
                    prepareMediaPlayer(tembangModel.get(position).getUrltembang());
                    is_playing = false;
                }
            });

            mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mp, int percent) {
                    progress.setSecondaryProgress(percent);
                }
            });

            progress.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    SeekBar seekBar = (SeekBar) v;
                    int playPosition = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                    mediaPlayer.seekTo(playPosition);
                    current.setText(millisecondToTimer(mediaPlayer.getCurrentPosition()));
                    updateSeekBar();
                    return false;
                }
            });

        }
    }

    @Override
    public void onSelected(int position) {
        this.position = position;
        progress.setProgress(0);
        text_judul.setText(tembangModel.get(position).getJudulTembang());
        duration.setText(tembangModel.get(position).getDuration());
        btnPlayPause.setImageResource(R.drawable.ic_pause_msc);
        current.setText("00:00");

        is_playing = true;

        if (view_control.getVisibility() == View.GONE) {
            view_control.setVisibility(View.VISIBLE);
        }

        mediaPlayer.reset();
        prepareMediaPlayer(tembangModel.get(position).getUrltembang());
        mediaPlayer.start();
        updateSeekBar();
    }

    private void prepareMediaPlayer(final String URI) {
        try {
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setDataSource(URI);
            mediaPlayer.prepare();
        } catch (Exception e) {
            Log.d("EXCEPTION : ", e.getMessage());
        }
    }

    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            progress.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) * 100));
            handler.postDelayed(updater, 1000);
        }
    }

    private final Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            int currentDuration = mediaPlayer.getCurrentPosition();
            current.setText(millisecondToTimer(currentDuration));
        }
    };

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    public void addDataTembangSholawat() {
        tembangModel.add(new TembangSholawatModel("Allahu Kahfi", "https://media1.vocaroo.com/mp3/1jnbs7ZpKB6S", "03.07"));
        tembangModel.add(new TembangSholawatModel("Keagungan Tuhan", "https://media1.vocaroo.com/mp3/1ehrijLMaiou", "02.53"));
        tembangModel.add(new TembangSholawatModel("Labbaik", "https://media1.vocaroo.com/mp3/11FfUvLmqkL4", "04.37"));
        tembangModel.add(new TembangSholawatModel("Labbaik 2", "https://media1.vocaroo.com/mp3/1f0l0XzBl5lF", "04.54"));
        tembangModel.add(new TembangSholawatModel("Lir Ilir", "https://media1.vocaroo.com/mp3/19RwyYGIbcKF", "07.00"));
        tembangModel.add(new TembangSholawatModel("Padang Bulan", "https://media1.vocaroo.com/mp3/1oRBw2oHJVvr", "11.16"));
        tembangModel.add(new TembangSholawatModel("Saben Malam Jumat", "https://media1.vocaroo.com/mp3/1i2aMZX6R7zn", "06.17"));
        tembangModel.add(new TembangSholawatModel("Sajadah Panjang", "https://media1.vocaroo.com/mp3/1lbeCysCvhrL", "03.34"));
        tembangModel.add(new TembangSholawatModel("Tiket Suwargo", "https://media1.vocaroo.com/mp3/1htsoD6xHl2H", "04.58"));
        tembangModel.add(new TembangSholawatModel("Tombo Ati", "https://media1.vocaroo.com/mp3/1mL7wBdpm84H", "04.59"));
        tembangModel.add(new TembangSholawatModel("Turi - Turi Putih", "https://media1.vocaroo.com/mp3/1mqFiWIeZt4I", "04.09"));
    }

    private String millisecondToTimer(int millisecond) {
        final int hours = millisecond / (1000 * 60 * 60);
        final int minutes = millisecond % (1000 * 60 * 60) / (1000 * 60);
        final int second = (millisecond % (1000 * 60 * 60) % (1000 * 60) / 1000);

        String timerTime = (hours > 0) ? hours + ":" : "";
        String minutesTime = (minutes < 10) ? "0" + minutes : "" + minutes;
        String secondTime = (second < 10) ? "0" + second : "" + second;

        return timerTime + minutesTime + ":" + secondTime;
    }

    @Override
    protected void onDestroy() {
        mediaPlayer.reset();
        super.onDestroy();
    }
}
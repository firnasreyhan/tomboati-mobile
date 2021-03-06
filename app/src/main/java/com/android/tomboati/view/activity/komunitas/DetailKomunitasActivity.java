package com.android.tomboati.view.activity.komunitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;

import com.android.tomboati.R;
import com.codesgood.views.JustifiedTextView;
import com.squareup.picasso.Picasso;

public class DetailKomunitasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_detail_komunitas);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Detail Komunitas");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView image_detail = findViewById(R.id.image_detail);
        JustifiedTextView text_tagar_detail = findViewById(R.id.text_tagar_detail);
        JustifiedTextView text_judul_detail = findViewById(R.id.text_judul_detail);
        JustifiedTextView textViewContentNews = findViewById(R.id.textViewContentNews);

        Intent intent = this.getIntent();
        Picasso.get().load(intent.getStringExtra("IMAGE")).priority(Picasso.Priority.HIGH)
                .placeholder(R.drawable.ic_logo).into(image_detail);

        text_tagar_detail.setText(intent.getStringExtra("TEXT_TAGAR"));
        text_judul_detail.setText(intent.getStringExtra("TEXT_JUDUL"));

        String content = intent.getStringExtra("CONTENT").replaceAll("\\<.*?\\>", "").replaceAll("&.*?;", " ");;;
        textViewContentNews.setText("\t".concat(content));

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
package com.android.tomboati.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.adapter.DoaHajiUmrahAdapter;
import com.android.tomboati.adapter.TempatMustajabAdapter;
import com.android.tomboati.model.DoaHajiUmrahModel;
import com.android.tomboati.model.TempatMustajabModel;

import java.util.ArrayList;
import java.util.List;

public class TampatMustajabActivity extends AppCompatActivity {


    private RecyclerView recyclerViewTempatMustajab;
    private TempatMustajabAdapter tempatMustajabAdapter;
    private List<TempatMustajabModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_tampat_mustajab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Tempat Mustajab");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        models = new ArrayList<>();

        recyclerViewTempatMustajab = findViewById(R.id.recyclerViewTempatMustajab);
        recyclerViewTempatMustajab.setHasFixedSize(true);
        recyclerViewTempatMustajab.setLayoutManager(new LinearLayoutManager(this));

        addItem("Multazam", "Multazam merupakan dinding yang terletak antara hajar Aswad dan pintu Kakbah. Tempat ini diyakini para ulama sebagai tempat mustajab untuk berdoa di sekitar Kabah.", "https://id.wikipedia.org/wiki/Multazam", R.drawable.msb_multazam);
        addItem("Hajar Aswad", "Barangkali setiap muslim tahu Hajar Aswad, yaitu batu yang diyakini oleh umat Islam berasal dari surga. Orang yang pertama kali menemukannya adalah Nabi Ismail dan yang meletakkannya adalah Nabi Ibrahim. Hajar Aswad dijadikan pondasi Kakbah saat Nabi Ibrahim dan Nabi Ismail mendapat perintah dari Allah.", "https://id.wikipedia.org/wiki/Hajar_Aswad", R.drawable.msb_hajar_aswad);
        addItem("Hijir Ismail", "Hijir Ismail adalah setengah lingkaran kecil di samping Ka'bah.Ditempat ini sering dipakai jamaah haji maupun umrah untuk melakukan salat sunnah karena diyakini sebagai salah satu tempat mustajab untuk berdoa.", "https://id.wikipedia.org/wiki/Hijir_Ismail", R.drawable.msb_hijr_ismail);
        addItem("Maqam Ibrahim", "Makam Ibrahim adalah tempat bekas berdirinya Nabi Ibrahim AS tatkala membangun Kakbah terbuat dari batu. Letak makam Ibrahim ini berhadapan dengan Pintu Kakbah. Kini batu tersebut disimpan dalam bangunan kristal berkrangka besi dan tertutup kaca tebal.", "https://id.wikipedia.org/wiki/Maqam_Ibrahim", R.drawable.msb_maqam_ibrahim);
        addItem("Shafa dan Marwah", "Bukit Shafa dan Marwah merupakan saksi sejarah bagi umat Islam, masih dalam sejarah Nabi Ibrahim AS dan Nabi Ismail AS. Demikian, bukit Shafa dan Marwah termasuk bagian dari bangunan Masjidil Haram dan menjadi bagian dari pelaksanaan ibadah haji dan umrah, yakni Sa'i.", "https://id.wikipedia.org/wiki/Shofa_dan_Marwah", R.drawable.msb_shafa_marwah);
        addItem("Raudhah", "Tempat mustajab untuk berdoa selanjutnya yakni di Masjid Nabawi, tepatnya di Raudhah (Taman Surga), yaitu tempat antara mimbar dan kediaman Rasulullah Muhammad SAW saat beliau hidup yang menjadi salah satu tempat istimewa bagi masyarakat muslim.", "https://news.detik.com/berita/d-4828661/keutamaan-raudhah-taman-surga-tempat-mustajabnya-doa", R.drawable.msb_raudhah);

        tempatMustajabAdapter = new TempatMustajabAdapter(models);
        recyclerViewTempatMustajab.setAdapter(tempatMustajabAdapter);
    }


    private void addItem(String judul, String keterangan, String link, int drawable) {
        models.add(new TempatMustajabModel(judul, keterangan, link, drawable));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
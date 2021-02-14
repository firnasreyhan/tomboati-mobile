package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.adapter.TempatMustajabAdapter;
import com.android.tomboati.adapter.WaktuMustajabAdapter;
import com.android.tomboati.model.TempatMustajabModel;

import java.util.ArrayList;
import java.util.List;

public class WaktuMustajabActivity extends AppCompatActivity {


    private RecyclerView recyclerViewWaktuMustajab;
    private WaktuMustajabAdapter waktuMustajabAdapter;
    private List<TempatMustajabModel> models;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_waktu_mustajab);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Waktu Mustajab");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        models = new ArrayList<>();

        recyclerViewWaktuMustajab = findViewById(R.id.recyclerViewWaktuMustajab);
        recyclerViewWaktuMustajab.setHasFixedSize(true);
        recyclerViewWaktuMustajab.setLayoutManager(new LinearLayoutManager(this));

        addItem("Sepertiga Malam Terakhir", "Di saat manusia lainnya sedang pulas tertidur, Anda justru dapat memanfaatkan waktu di sepertiga malam untuk berdoa kepada Allah SWT karena merupakan waktu yang penuh berkah.", "", 0);
        addItem("Selesai Sholat Wajib", "Setelah kita selesai sholat, hendaklah melanjutkan dengan berdzikir dan berdoa karena salah satu waktu paling mustajab untuk berdoa adalah setelah selesai sholat.", "", 0);
        addItem("Sahur dan Berbuka Puasa", "Orang yang berpuasa adalah orang yang didengar doanya oleh Allah SWT, terutama ketika ia berdoa di waktu sahur dan berbuka.", "", 0);
        addItem("Malam Lailatur Qadar", "Malam lailatul qadar merupakan malam yang sangat istimewa karena di saat itulah Al-Quran turun. Di malam itu, dianjurkan untuk memperbanyak ibadah dan doa, Saking istimewanya malam lailatul qadar, Allah menyembunyikannya diantara 10 malam terakhir di bulan Ramadhan. Dan setiap doa di malam lailatul qadar akan dikabulkan.", "", 0);
        addItem("Saat Adzan Berkumandang", "Rasulullah SAW bersabda: “Doa tidak tertolak pada dua waktu, atau minimal kecil kemungkinan tertolaknya. Yaitu ketika adzan berkumandang dan saat perang berkecamuk, ketika kedua kubu saling menyerang.” (H.R. Abu Daud)", "", 0);
        addItem("Diantara Adzan dan Iqamah", "Selain saat adzan berkumandang, ternyata waktu berdoa yang mustajab juga ada pada saat waktu jeda antara adzan dan iqamah. Sebagaimana sabda Rasul: “Doa diantara adzan dan iqamah tidak tertolak.” (H.R. Tirmidzi)", "", 0);
        addItem("Saat Turun Hujan", "Masih banyak yang tidak mengetahui bahwa salah satu waktu yang baik untuk berdoa adalah ketika hujan turun. Hal ini disebabkan karena hujan adalah salah satu bentuk rahmat dari Allah SWT.", "", 0);
        addItem("Dihari Jumat", "Jumat merupakan hari yang penuh berkah, dimana kita dianjurkan untuk banyak melakukan ibadah dan kebaikan di hari tersebut. Sebagaimana sabda Rasul: “Di dalamnya terdapat waktu. Jika seorang muslim berdoa ketika itu, pasti diberikan apa yang ia minta.”", "", 0);
        addItem("Saat Berperang Dijalan Allah", "Keistimewaan berjihad atau berperang di jalan Allah adalah dikabulkannya doa orang yang berjihad tersebut. Sebagaimana sabda Rasul: “Doa tidak tertolak pada dua waktu, atau minimal kecil kemungkinan tertolaknya. Yaitu ketika adzan berkumandang dan saat perang berkecamuk, ketika kedua kubu saling menyerang.” (H.R. Abu Daud)", "", 0);
        addItem("Saat Dihari Arafah", "Hari Arafah adalah hari saat wukuf di Arafah, tepatnya tanggal 9 Dzulhijjah sehingga dianjurkan bagi semua umat Muslim untuk berdoa di hari itu. Sebagaimana sabda Rasul: “Doa yang terbaik adalah doa ketika hari Arafah.” (H.R. At-Tirmidzi)", "", 0);
        addItem("Sujud Terakhir Sholat", "Rasululah SAW bersabda: “Seorang hamba yang berada paling dekat dengan Rabb-nya ialah ketika ia sedang bersujud. Maka perbanyaklah berdoa ketika itu.” (H.R. Muslim)", "", 0);
        addItem("Saat Didzolimi", "Sungguh doa orang yang dizholimi akan didengar langsung oleh Allah SWT. Sehingga berhati-hatilah jika akan mendzholimi orang. Sebagaimana sabda Rasul: “Hendaklah kamu waspada terhadap doa orang dizalimi. Sesungguhnya doa itu akan naik ke langit amat pantas seumpama api marak ke udara.” (Hadis Riwayat Hakim – sanad sahih).", "", 0);

        waktuMustajabAdapter = new WaktuMustajabAdapter(models);
        recyclerViewWaktuMustajab.setAdapter(waktuMustajabAdapter);
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
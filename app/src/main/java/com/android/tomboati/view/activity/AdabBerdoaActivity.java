package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.adapter.WaktuMustajabAdapter;
import com.android.tomboati.model.TempatMustajabModel;

import java.util.ArrayList;
import java.util.List;

public class AdabBerdoaActivity extends AppCompatActivity {

    private RecyclerView recyclerViewAdabBerdoa;
    private WaktuMustajabAdapter waktuMustajabAdapter;
    private List<TempatMustajabModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_adab_berdoa);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Adab Berdoa");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        models = new ArrayList<>();

        recyclerViewAdabBerdoa = findViewById(R.id.recyclerViewAdabBerdoa);
        recyclerViewAdabBerdoa.setHasFixedSize(true);
        recyclerViewAdabBerdoa.setLayoutManager(new LinearLayoutManager(this));

        addItem("Mencari Waktu Yang Mustajab", "Di antara waktu yang mustajab adalah hari Arafah, Ramadhan, sore hari Jumat, dan waktu sahur atau sepertiga malam terakhir.");
        addItem("Memanfaatkan Keadaan Mustajab", "Di antara keadaan yang mustajab untuk berdoa adalah: ketika perang, turun hujan, ketika sujud, antara adzan dan iqamah, atau ketika puasa menjelang berbuka.");
        addItem("Hadap Kiblat & Mengangkat Tangan", "Dari Jabir radhiallahu ‘anhu, bahwa Nabi shallallahu ‘alaihi wa sallam ketika berada di Padang Arafah, beliau menghadap kiblat, dan beliau terus berdoa sampai matahari terbenam. (HR. Muslim).");
        addItem("Suara Lirih & Tidak Dikeraskan", "Janganlah kalian mengeraskan doa kalian dan janganlah pula merendahkannya dan carilah jalan tengah di antara kedua itu. (QS. Al-Isra: 110).");
        addItem("Tidak Dibuat Bersajak", "Berdoalah kepada Tuhanmu dengan berendah diri dan suara yang lembut. Sesungguhnya Allah tidak menyukai orang-orang yang melampaui batas. (QS. Al-A’raf: 55). Ada yang mengatakan: maksudnya adalah berlebih-lebihan dalam membuat kalimat doa, dengan dipaksakan bersajak.");
        addItem("Khusyuk, & Penuh Harap", "Sesungguhnya mereka adalah orang-orang yang selalu bersegera dalam (mengerjakan) perbuatan-perbuatan yang baik dan mereka berdoakepada Kami dengan harap dan cemas. Dan mereka adalah orang-orang yang khusyu’ kepada Kami. (QS. Al-Anbiya’: 90).");
        addItem("Mantap Hati & Beryakin Dikabulkan", "Dari Abu Hurairah radhiallahu’anhu, Nabi shallallahu ‘alaihi wa sallam bersabda, “Apabila kalian berdoa, hendaknya dia mantapkan keinginannya. Karena Allah tidak keberatan dan kesulitan untuk mewujudkan sesuatu. (HR. Ibn Hibban dan dishahihkan Syua’ib Al-Arnauth).");
        addItem("Merengek & Mengulang Doa", "Misalnya, orang berdoa: Yaa Allah, ampunilah hambu-MU, ampunilah hambu-MU…, ampunilah hambu-MU yang penuh dosa ini. ampunilah ya Allah…. Dia ulang-ulang permohonannya. Semacam ini menunjukkan kesungguhhannya dalam berdoa.");
        addItem("Tidak Tergesa Gesa", "Akan dikabulkan (doa) kalian selama tidak tergesa-gesa. Dia mengatakan, ‘Saya telah berdoa, namun belum saja dikabulkan‘. (HR. Bukhari dan Muslim).");
        addItem("Memuji Allah SWT", "Bagian dari adab ketika memohon dan meminta adalah memuji Dzat yang diminta. Demikian pula ketika hendak berdoa kepada Allah. Hendaknya kita memuji Allah dengan menyebut nama-nama-Nya yang mulia (Asma-ul husna).");
        addItem("Perbanyak Taubat & Mohon Ampun", "Banyak mendekatkan diri kepada Allah merupakan sarana terbesar untuk mendapatkan cintanya Allah. Dengan dicintai Allah, doa seseorang akan mudah dikabulkan. Di antara amal yang sangat dicintai Allah adalah memperbanyak taubat dan istighfar.");
        addItem("Hindari Mendoakan Keburukan", "Kalau sekiranya Allah menyegerakan keburukan bagi manusia seperti permintaan mereka untuk menyegerakan kebaikan, pastilah diakhiri umur mereka (binasa). (QS. Yunus: 11).");
        addItem("Menghindari Makan Harta Haram", "Dan sesungguhnya Allah telah memerintahkan kepada orang-orang mukmin seperti yang diperintahkan-Nya kepada para Rasul. Firman-Nya, ‘Wahai para Rasul! Makanlah makanan yang baik-baik (halal) dan kerjakanlah amal shalih.");


        waktuMustajabAdapter = new WaktuMustajabAdapter(models, "Adab Berdoa");
        recyclerViewAdabBerdoa.setAdapter(waktuMustajabAdapter);
    }

    private void addItem(String judul, String keterangan) {
        models.add(new TempatMustajabModel(judul, keterangan, "", 0));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
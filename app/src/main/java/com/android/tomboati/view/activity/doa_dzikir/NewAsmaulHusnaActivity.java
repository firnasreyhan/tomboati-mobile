package com.android.tomboati.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.adapter.AsmaulHusnaAdapter;
import com.android.tomboati.model.AsmaulHusnaModel;

import java.util.ArrayList;
import java.util.List;

public class NewAsmaulHusnaActivity extends AppCompatActivity {
    private RecyclerView recyclerViewAsmaulHusna;
    private AsmaulHusnaAdapter asmaulHusnaAdapter;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_new_asmaul_husna);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Asmaul Husna");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewAsmaulHusna = findViewById(R.id.recyclerViewAsmaulHusna);
        recyclerViewAsmaulHusna.setHasFixedSize(true);
        recyclerViewAsmaulHusna.setLayoutManager(new LinearLayoutManager(this));

        List<AsmaulHusnaModel> list = new ArrayList<>();
        list.add(new AsmaulHusnaModel("","الله","Allah","Allah"));
        list.add(new AsmaulHusnaModel("1", "الرحمن", "Ar Rahman", "Yang Maha Pengasih"));
        list.add(new AsmaulHusnaModel("2", "الرحيم", "Ar Rahiim", "Yang Maha Penyayang"));
        list.add(new AsmaulHusnaModel("3", "الملك", "Al Malik", "Yang Maha Merajai / Memerintah"));
        list.add(new AsmaulHusnaModel("4", "القدوس", "Al Quddus", "Yang Maha Suci"));
        list.add(new AsmaulHusnaModel("5", "السلام", "As Salaam", "Yang Maha Memberi Kesejahteraan"));
        list.add(new AsmaulHusnaModel("6", "المؤمن", "Al Mu`min", "Yang Maha Memberi Keamanan"));
        list.add(new AsmaulHusnaModel("7", "المهيمن", "Al Muhaimin", "Yang Maha Pemelihara"));
        list.add(new AsmaulHusnaModel("8", "العزيز", "Al `Aziiz", "Yang Maha PerkasaYang Maha Perkasa"));
        list.add(new AsmaulHusnaModel("9", "الجبار", "Al Jabbar", "Yang Memiliki Mutlak Kegagahan"));
        list.add(new AsmaulHusnaModel("10", "المتكبر", "Al Mutakabbir", "Yang Maha Megah, Yang Memiliki Kebesaran"));
        list.add(new AsmaulHusnaModel("11", "الخالق", "Al Khaliq", "Yang Maha Pencipta"));
        list.add(new AsmaulHusnaModel("12", "البارئ", "Al Baari`", "Yang Maha Melepaskan (Membuat, Membentuk, Menyeimbangkan)"));
        list.add(new AsmaulHusnaModel("13", "المصور", "Al Mushawwir", "Yang Maha Membentuk Rupa (makhluknya)"));
        list.add(new AsmaulHusnaModel("14", "الغفار", "Al Ghaffaar", "Yang Maha Pengampun"));
        list.add(new AsmaulHusnaModel("15", "القهار", "Al Qahhaar", "Yang Maha Menundukan"));
        list.add(new AsmaulHusnaModel("16", "الوهاب", "Al Wahhaab", "Yang Maha Pemberi Karunia"));
        list.add(new AsmaulHusnaModel("17", "الرزاق", "Ar Razzaaq", "Yang Maha Pemberi Rezeki"));
        list.add(new AsmaulHusnaModel("18", "الفتاح", "Al Fattaah", "Yang Maha Pembuka Rahmat"));
        list.add(new AsmaulHusnaModel("19", "العليم", "Al `Aliim", "Yang Maha Mengetahui (Memiliki Ilmu)"));
        list.add(new AsmaulHusnaModel("20", "القابض", "Al Qaabidh", "Yang Maha Menyempitkan (makhluknya)"));
        list.add(new AsmaulHusnaModel("21", "الباسط", "Al Baasith", "Yang Maha Melapangkan (makhluknya)"));
        list.add(new AsmaulHusnaModel("22", "الخافض", "Al Khaafidh", "Yang Maha Merendahkan (makhluknya)"));
        list.add(new AsmaulHusnaModel("23", "الرافع", "Ar Raafi`", "Yang Maha Meninggikan (makhluknya)"));
        list.add(new AsmaulHusnaModel("24", "المعز", "Al Mu`izz", "Yang Maha Memuliakan (makhluknya)"));
        list.add(new AsmaulHusnaModel("25", "المذل", "Al Mudzil", "Yang Maha Menghinakan (makhluknya)"));
        list.add(new AsmaulHusnaModel("26", "السميع", "Al Samii`", "Yang Maha Mendengar"));
        list.add(new AsmaulHusnaModel("27", "البصير", "Al Bashiir", "Yang Maha Melihat"));
        list.add(new AsmaulHusnaModel("28", "الحكم,", "Al Hakam", "Yang Maha Menetapkan"));
        list.add(new AsmaulHusnaModel("29", "العدل", "Al `Adl", "Yang Maha Adil"));
        list.add(new AsmaulHusnaModel("30", "اللطيف", "Al Lathiif", "Yang Maha Lembut"));
        list.add(new AsmaulHusnaModel("31", "الخبير", "Al Khabiir", "Yang Maha Mengenal"));
        list.add(new AsmaulHusnaModel("32", "الحليم", "Al Haliim", "Yang Maha Penyantun"));
        list.add(new AsmaulHusnaModel("33", "العظيم", "Al `Azhiim", "Allah Yang Maha Agung"));
        list.add(new AsmaulHusnaModel("34", "الغفور", "Al Ghafuur", "Allah Yang Maha Memberi Pengampunan"));
        list.add(new AsmaulHusnaModel("35", "الشكور", "As Syakuur", "Allah Yang Maha Pembalas Budi (Menghargai)"));
        list.add(new AsmaulHusnaModel("36", "العلى", "Al `Aliy", "Allah Yang Maha Tinggi"));
        list.add(new AsmaulHusnaModel("37", "الكبير", "Al Kabiir", "Yang Maha Besar"));
        list.add(new AsmaulHusnaModel("38", "الحفيظ", "Al Hafizh", "Allah Yang Maha Memelihara"));
        list.add(new AsmaulHusnaModel("39", "المقيت", "Al Muqiit", "Allah Yang Maha Pemberi Kecukupan"));
        list.add(new AsmaulHusnaModel("40", "الحسيب", "Al Hasiib", "Allah Yang Maha Membuat Perhitungan"));
        list.add(new AsmaulHusnaModel("41", "الجليل", "Al Jaliil", "Allah Yang Maha Luhur"));
        list.add(new AsmaulHusnaModel("42", "الكريم", "Al Kariim", "Allah Yang Maha Pemurah"));
        list.add(new AsmaulHusnaModel("43", "الرقيب", "Ar Raqiib", "Allah Yang Maha Mengawasi"));
        list.add(new AsmaulHusnaModel("44", "المجيب", "Al Mujiib", "Allah Yang Maha Mengabulkan"));
        list.add(new AsmaulHusnaModel("45", "الواسع", "Al Waasi`", "Allah Yang Maha Luas"));
        list.add(new AsmaulHusnaModel("46", "الحكيم", "Al Hakim", "Allah Yang Maha Bijaksana"));
        list.add(new AsmaulHusnaModel("47", "الودود", "Al Waduud", "Allah Yang Maha Mengasihi"));
        list.add(new AsmaulHusnaModel("48", "المجيد", "Al Majiid", "Allah Yang Maha Mulia"));
        list.add(new AsmaulHusnaModel("49", "الباعث", "Al Baa`its", "Allah Yang Maha Membangkitkan"));
        list.add(new AsmaulHusnaModel("50", "الشهيد", "As Syahiid", "Allah Yang Maha Menyaksikan"));
        list.add(new AsmaulHusnaModel("51", "الحق", "Al Haqq", "Allah Yang Maha Benar"));
        list.add(new AsmaulHusnaModel("52", "الوكيل", "Al Wakiil", "Allah Yang Maha Memelihara"));
        list.add(new AsmaulHusnaModel("53", "القوى", "Al Qawiyyu", "Allah Yang Maha Kuat"));
        list.add(new AsmaulHusnaModel("54", "المتين", "Al Matiin", "Allah Yang Maha Kokoh"));
        list.add(new AsmaulHusnaModel("55", "الولى", "Al Waliyy", "Allah Yang Maha Melindungi"));
        list.add(new AsmaulHusnaModel("56", "الحميد", "Al Hamiid", "Allah Yang Maha Terpuji"));
        list.add(new AsmaulHusnaModel("57", "المحصى", "Al Muhshii", "Allah Yang Maha Mengalkulasi (Menghitung Segala Sesuatu)"));
        list.add(new AsmaulHusnaModel("58", "المبدئ", "Al Mubdi`", "Allah Yang Maha Memulai"));
        list.add(new AsmaulHusnaModel("59", "المعيد", "Al Mu`iid", "Allah Yang Maha Mengembalikan Kehidupan"));
        list.add(new AsmaulHusnaModel("60", "المحيى", "Al Muhyii", "Allah Yang Maha Menghidupkan"));
        list.add(new AsmaulHusnaModel("61", "المميت", "Al Mumiitu", "Allah Yang Maha Mematikan"));
        list.add(new AsmaulHusnaModel("62", "الحي", "Al Hayyu", "Allah Yang Maha Hidup"));
        list.add(new AsmaulHusnaModel("63", "القيوم", "Al Qayyuum", "Allah Yang Maha Mandiri"));
        list.add(new AsmaulHusnaModel("64", "الواجد", "Al Waajid", "Allah Yang Maha Penemu"));
        list.add(new AsmaulHusnaModel("65", "الماجد", "Al Maajid", "Allah Yang Maha Mulia"));
        list.add(new AsmaulHusnaModel("66", "الواحد", "Al Wahid", "Allah Yang Maha Tunggal"));
        list.add(new AsmaulHusnaModel("67", "الاحد", "Al Ahad", "Allah Yang Maha Esa"));
        list.add(new AsmaulHusnaModel("68", "الصمد", "As Shamad", "Allah Yang Maha Dibutuhkan, Tempat Meminta"));
        list.add(new AsmaulHusnaModel("69", "القادر", "Al Qaadir", "Allah Yang Maha Menentukan, Maha Menyeimbangkan"));
        list.add(new AsmaulHusnaModel("70", "المقتدر", "Al Muqtadir", "Allah Yang Maha Berkuasa"));
        list.add(new AsmaulHusnaModel("71", "المقدم", "Al Muqaddim", "Allah Yang Maha Mendahulukan"));
        list.add(new AsmaulHusnaModel("72", "المؤخر", "Al Mu`akkhir", "Allah Yang Maha Mengakhirkan"));
        list.add(new AsmaulHusnaModel("73", "الأول", "Al Awwal", "Allah Yang Maha Awal"));
        list.add(new AsmaulHusnaModel("74", "الأخر", "Al Aakhir", "Allah Yang Maha Akhir"));
        list.add(new AsmaulHusnaModel("75", "الظاهر", "Az Zhaahir", "Allah Yang Maha Nyata"));
        list.add(new AsmaulHusnaModel("76", "الباطن", "Al Baathin", "Allah Yang Maha Ghaib"));
        list.add(new AsmaulHusnaModel("77", "الوالي", "Al Waali", "Allah Yang Maha Memerintah"));
        list.add(new AsmaulHusnaModel("78", "المتعالي", "Al Muta`aalii", "Allah Yang Maha Tinggi"));
        list.add(new AsmaulHusnaModel("79", "البر", "Al Barru", "Allah Yang Maha Penderma (Maha Pemberi Kebajikan)"));
        list.add(new AsmaulHusnaModel("80", "التواب", "At Tawwaab", "Allah Yang Maha Penerima Tobat"));
        list.add(new AsmaulHusnaModel("81", "المنتقم", "Al Muntaqim", "Allah Yang Maha Pemberi Balasan"));
        list.add(new AsmaulHusnaModel("82", "العفو", "Al Afuww", "Allah Yang Maha Pemaaf"));
        list.add(new AsmaulHusnaModel("83", "الرؤوف", "Ar Ra`uuf", "Allah Yang Maha Pengasuh"));
        list.add(new AsmaulHusnaModel("84", "مالك الملك", "Malikul Mulk", "Allah Yang Maha Penguasa Kerajaan (Semesta)"));
        list.add(new AsmaulHusnaModel("85", "ذو الجلال و الإكرام", "Dzul Jalaali Wal Ikraam", "Allah Yang Maha Pemilik Kebesaran dan Kemuliaan"));
        list.add(new AsmaulHusnaModel("86", "المقسط", "Al Muqsith", "Allah Yang Maha Pemberi Keadilan"));
        list.add(new AsmaulHusnaModel("87", "الجامع", "Al Jamii`", "Allah Yang Maha Mengumpulkan"));
        list.add(new AsmaulHusnaModel("88", "الغنى", "Al Ghaniyy", "Allah Yang Maha Kaya"));
        list.add(new AsmaulHusnaModel("89", "المغنى", "Al Mughnii", "Allah Yang Maha Pemberi Kekayaan"));
        list.add(new AsmaulHusnaModel("90", "المانع", "Al Maani", "Allah Yang Maha Mencegah"));
        list.add(new AsmaulHusnaModel("91", "الضار", "Ad Dhaar", "Allah Yang Maha Penimpa Kemudharatan"));
        list.add(new AsmaulHusnaModel("92", "النافع", "An Nafii`", "Allah Yang Maha Memberi Manfaat"));
        list.add(new AsmaulHusnaModel("93", "النور", "An Nuur", "Allah Yang Maha Bercahaya (Menerangi, Memberi Cahaya)"));
        list.add(new AsmaulHusnaModel("94", "الهادئ", "Al Haadii", "Allah Yang Maha Pemberi Petunjuk"));
        list.add(new AsmaulHusnaModel("95", "البديع", "Al Badii’", "Allah Yang Maha Pencipta Yang Tiada Bandingannya"));
        list.add(new AsmaulHusnaModel("96", "الباقي", "Al Baaqii", "Allah Yang Maha Kekal"));
        list.add(new AsmaulHusnaModel("97", "الوارث", "Al Waarits", "Allah Yang Maha Pewaris"));
        list.add(new AsmaulHusnaModel("98", "الرشيد", "Ar Rasyiid", "Allah Yang Maha Pandai"));
        list.add(new AsmaulHusnaModel("99", "الصبور", "As Shabuur", "Allah Yang Maha Sabar"));

        asmaulHusnaAdapter = new AsmaulHusnaAdapter(list);
        recyclerViewAsmaulHusna.setAdapter(asmaulHusnaAdapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
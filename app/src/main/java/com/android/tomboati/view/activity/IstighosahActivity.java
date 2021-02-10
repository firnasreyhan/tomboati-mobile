package com.android.tomboati.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.adapter.IstighosahAdapter;
import com.android.tomboati.model.IstighosahModel;

import java.util.ArrayList;
import java.util.List;

public class IstighosahActivity extends AppCompatActivity {

    private List<IstighosahModel> listModel;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_istighosah);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Doa Istighosah");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        listModel = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleViewIstighosah);

    }

    @Override
    protected void onStart() {
        super.onStart();

        addModel("بِسْمِ اللهِ الرَّحْمَنِ الرَّحِيم", "Bismillahirrahmanirrahim", 1);
        addModel("بِسْمِ اللهِ الرَّ حْمَنِ الرَّ حِيْمِ . اَلْحَمْدُ لِلهِ رَبِّ الْعَا " + "لَمِيْنَ . اَلرَّحْمَنِ الرَّحِيْمِ . مَلِكِ يَوْمِ الدِّيْنِ . اِيَّا " +
                "كَنَعْبُدُ وَاِيَّا كَنَسْتَعِيْنُ . اِهْدِنَا الصِّرَاطَ الَّمُسْتَقِيْمَ . " + "صِرَاطَ الَّذِ يْنَ اَنْعَمْتَ عَلَيْهِمْ غَيْرِالْمَغْضُوْبِ عَلَيْهِمْ وَلَا " +
                "الضَّالِّيْنَ . اَمِينْ", "(Alfateha) Bismillaa hirrahmaanir rahiim " +
                "Alhamdulillaahi rabbill’aalamiin. Arrohmaanir rahiim. Maalikiyaumiddin. Iyyaakana’budu wa " +
                "iyyaakanasta’iin. Ihdinash shiraathal mustaqiim. Shiraathal ladziina an’amta " +
                "‘alaihim ghoiril maghdhuubi’alaihim waladhaalliin. Aamiinn", 1);
        addModel("أسْتَغْفِرُ اللهَ الْعَظِيْمَ", "Astaghfirullahal ‘Adhiim", 3);
        addModel("بلَاحَوْلَ وَلَا قُوَّةَ إلَّا بِا للهِ الْعَلِيِّ الْعَظِيْمِ", "Laa Haula Wa " +
                "Laa Quwwata Illaa Billahil ‘Aliyyil Adhiim", 3);
        addModel("أللَّهُمَّ صَلِّي عَلَى سَيِّدِنَا مُحَمَّدٍ وَعَلَى آلِ سَيِّدِنَا مُحَمَّدٍ",
                "Allahumma Shallii ‘Alaa Sayyidinaa Muhammadin Wa ‘Alaa Aali Sayyidinaa " +
                        "Muhammadin", 3);
        addModel("لَا إلهَ إلَّا أنْتَ سُبْحَانَكَ إنِّي كُنْتُ مِنَ الظَّالِمِيْنَ", "Laa Ilaaha" +
                " Illaa Anta Subhaanaka Innii Kuntu Minazhzhoolimiin", 40);
        addModel("بيَا اَللهُ يَا قَدِيْمُ", "Yaa Allahu Yaa Qadiim", 33);
        addModel("يَا سَمِيْعُ يَا بَصِيْرُ", "Yaa Samii’U Yaa Bashiir", 33);
        addModel("يَا مُبْدِعُ يَا خَالِقُ", "Yaa Mubdi’U Yaa Khaaliq", 33);
        addModel("يَا حَفِيْظُ يَا نَصِيْرُ يَا وَكِيْلُ ياَ اللهُ", "Yaa Hafiizhu Yaa Nashiiru " +
                "Yaa Wakiilu Yaa Allah", 33);
        addModel("يَا حَيُّ يَا قَيُّوْمُ بِرَحْمَتِكَ أسْتَغِيْثُ", "Yaa Hayyu Yaa Qayyuumu " +
                "Birahmatika Astaghiits", 33);
        addModel("يَا لَطِيْفُ", "Yaa Lathiif", 41);
        addModel("بأسْتَغْفِرُ اللهَ الْعَظِيْمَ إنَّهُ كَانَ غَفَّارًا", "Astaghfirullahal" +
                "’Azhiima Innahu Kaana Ghaffaraa", 33);
        addModel("أللَّهُمَّ صَلِّي عَلَى سَيِّدِنَا مُحَمَّدٍ قَدْ ضَاقَتْ حِيْلَتِي أدْرِكْنِي " +
                "يَا اَللهُ", "Allahumma Shallii ‘Alaa Sayyidinaa Muhammadin Qad Dhaaqat " +
                "Hiilatii Adriknii Yaa Allah", 3);
        addModel("أللّهُمَّ صَلِّي صَلَاةً كَامِلَةً وَسَلِّمْ سَلَامًا تَامًّا عَلَى سَيِّدِنَا " +
                "مُحَمَّدِ الّذِي تَنْحَلُّ بِهِ الْعُقَدُ وَتَنْفَرِجُ بِهِ الْكُرَبُ وَتُقْضَى بِهِ الْحَوَائِجُ وَتُنَالُ بِهِ الرَّغَائِبُ وَحُسْنُ الْخَوَاتِمِ وَيُسْتَسْقَى الْغَمَامُ بِوَجْهِهِ الْكَرِيْمِ " +
                "وَعَلَى آلِهِ وَصَحْبِهِ فِيْ كُلِّ لَمْحَةٍ وَنَفَسٍ بِعَدَدِ كُلِّ مَعْلُوْمٍ لَكَ", "Allahumma Shallii Shalaatan " +
                "Kaamilatan Wa Sallim Salaaman Taamman ‘Alaa Sayyidinaa Muhammadilladzii Tanhallu " +
                "Bihil’Uqadu Wa Tanfariju Bihilkurabu Wa Tuqdhaa Bihilhawaa-Iju Wa Tunaalu " +
                "Bihirraghaa-Ibu Wa Husnulkhawaatimi Wa Yustasqalghamaamu Bi Wajhihilkariimi Wa " +
                "‘Alaa Aalihi Wa Shahbihi Fii Kulli Lamhatin Wa Nafasin Bi’Adadi Kulli Ma’Luumin " +
                "Lak", 1);
        addModel("يَا بَدِيْعُ", "Yaa Badii’U", 41);
        addModel("بحَسْبُنَا اللهُ وَنِعْمَ الْوَكِيْلُ", "Hasbunallahu Wa Ni’Mal Wakiil", 41);
        addModel("يس", "Yasin", 1);
        addModel("اللهُ أكْبَرُ يَا رَبَّنَا وَإلَهَنَا وَسَيِّدَنَا أنْتَ مَوْلَانَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِرِيْنَ", "Allahu Akbaru Yaa " +
                "Rabbanaa Wa Ilaahanaa Wa Sayyidanaa Anta Maulaanaa Fanshurnaa ‘Alal Qaumil " +
                "Kaafiriin", 33);
        addModel("حَصَّنْتُكُمْ بِالْحَيِّ الْقَيُّوْمِ الَّذِيْ لَا يَمُوْتُ أبَدًا وَدَفَعْتُ " +
                "عَنْكُمُ السُّوْءَ بِألْفِ ألْفِ لَا حَوْلَ وَلَا قُوَّةَ إلَّا بِا للهِ " +
                "الْعَلِيِّ الْعَظِيْمِ", "Hashshantukum Bilhayyilqayyuumilladzii Laa Yamuutu " +
                "Abadan Wa Dafa’Tu ‘Ankumus Suu-A Bi-Alfi Alfi Alfi Laa Haula Wa Laa Quwwta Illaa " +
                "Billahil ‘Aliyyil ‘Azhiim",33);
        addModel("الْحَمْدُ للهِ الَّذِيْ أنْعَمَ عَلَيْنَا وَهَدَانَا عَلَى دَيْنِ الإسْلَامِ",
                "ALHAMDULILLALLADZII ‘AN’AMA ‘ALAINAA WA HADAANAA ‘ALAA DAINIL ISLAAM", 33);
        addModel("بِسْمِ اللهِ مَاشَاءَ اللهُ لَا يَسُوْقُ الْخَيْرَ إلَّا اللهِ بِسْمِ اللهِ مَاشَاءَ اللهُ لَا يَصْرِفُ السُّوْءَ إلَّا اللهُ بِسْمِ اللهِ مَاشَاءَ اللهُ مَا كَانَ مِنْ نِعْمَةٍ فَمِنَ اللهِ بِسْمِ" +
                " اللهِ مَاشَاءَ اللهُ لَا حَوْلَ وَلَا قُوَّةَ إلَّا بِا للهِ الْعَلِيِّ " +
                "الْعَظِيْمِ", "Bismillahi Maasyaa-Allahu Laa Yasuuqul Khaira Illallahi " +
                "Bismillahi Maasyaa-Allahu Laa Yashrifus Suu-A Illallahu Bismillahi Maasyaa-Allahu " +
                "Maa Kaana Min Ni’Matin Faminallhi Bismillahi Maasyaa-Allahu Laa Haula Wa Laa " +
                "Quwwata Illaa Billahil’Aliyyil ‘Azhimm", 1);
        addModel("بسَألْتُكَ يَا غَفَّارُ عَفْوًا وَتَوْبَةً وَبِالْقَهْرِ يَا قَهَّارُ خُذْ مَنْ" +
                " تَحَيَّلَا", "Sa-Altuka Yaa Ghaffaaru ‘Afwan Wa Taubata Wa Bilqahri Yaa " +
                "Qahhaaru Khudz Man Tahayyalaa", 33);
        addModel("بيَا جَبَّارُ يَا قَهَّارُ يَا ذَا الْبَطْشِ الشَّدِيْدِ خُذْ حَقَّنَا وَحَقَّ " +
                "الْمُسْلِمِيْنَ مِمَّنْ ظَلَمَنَا وَالْمُسْلِمِيْنَ وَتَعَدَّى عَلَيْنَا وَعَلَى" +
                " الْمُسْلِمِيْنَ", "Yaa Jabbaaru Yaa Qahhaaru Yaa Dzal Bath-Syisy Syadiidi Khudz " +
                "Haqqanaa Wa Haqqal Muslimiina Mimman Zhalamnaa Wal Muslimiina Wa Ta’Addaa ‘Alainaa " +
                "Wa ‘Alalmuslimiin", 33);
        addModel("بِسْمِ اللهِ الرَّ حْمَنِ الرَّ حِيْمِ . اَلْحَمْدُ لِلهِ رَبِّ الْعَا " + "لَمِيْنَ . اَلرَّحْمَنِ الرَّحِيْمِ . مَلِكِ يَوْمِ الدِّيْنِ . اِيَّا " +
                "كَنَعْبُدُ وَاِيَّا كَنَسْتَعِيْنُ . اِهْدِنَا الصِّرَاطَ الَّمُسْتَقِيْمَ . " + "صِرَاطَ الَّذِ يْنَ اَنْعَمْتَ عَلَيْهِمْ غَيْرِالْمَغْضُوْبِ عَلَيْهِمْ وَلَا " +
                "الضَّالِّيْنَ . اَمِينْ", "(Alfateha) Bismillaa hirrahmaanir rahiim " +
                "Alhamdulillaahi rabbill’aalamiin. Arrohmaanir rahiim. Maalikiyaumiddin. Iyyaakana’budu wa " +
                "iyyaakanasta’iin. Ihdinash shiraathal mustaqiim. Shiraathal ladziina an’amta " +
                "‘alaihim ghoiril maghdhuubi’alaihim waladhaalliin. Aamiinn", 1);

        IstighosahAdapter adapter = new IstighosahAdapter(listModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(IstighosahActivity.this));
        recyclerView.setAdapter(adapter);
    }

    private void addModel(String textArab, String textTranslate, int countBacaan) {
        listModel.add(new IstighosahModel(textArab, textTranslate, countBacaan));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.TahlilAdapter;
import com.tomboati.tour.api.response.TahlilResponse;

import java.util.ArrayList;
import java.util.List;

public class DoaZiarahActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDoaZiarah;
    private List<TahlilResponse.Datum> models;
    private TahlilAdapter tahlilAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_doa_ziarah);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Doa Ziarah");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        models = new ArrayList<>();

        recyclerViewDoaZiarah = findViewById(R.id.recyclerViewDoaZiarah);
        recyclerViewDoaZiarah.setHasFixedSize(true);
        recyclerViewDoaZiarah.setLayoutManager(new LinearLayoutManager(this));

        addItem("Mengucapkan Salam", "السَّلامُ عَلَيْكُمْ دَارَ قَوْمٍ مُؤْمِنينَ وَأتاكُمْ ما تُوعَدُونَ غَداً مُؤَجَّلُونَ وَإنَّا إنْ شاءَ اللَّهُ بِكُمْ لاحقُونَ", "Assalamu‘alaìkum dara qaumìn mu’mìnîn wa atakum ma tu‘adun ghadan mu’ajjalun, wa ìnna ìnsya-Allahu bìkum lahìqun");
        addItem("Membaca Istighfar", "أَسْتَغْفِرُ اللهَ العَظِيْمَ اَلَّذِي لآ إِلَهَ إِلَّا هُوَ اْلحَيُّ اْلقَيُّوْمُ وَأَتُوْبُ إِلَيْهِ", "Astaghfirullah Hal Adzim Alladzi La ilaha Illa Huwal Hayyul Qoyyumu Wa atubu Ilaihi");
        addItem("Membaca Surah Al-Fatihah", "بِسْمِ اللهِ الرَّحْمَنِ الرَّحِيْمِ. اَلْحَمْدُ لِلهِ رَبِّ الْعَالَمِيْنَ. اَلرَّحْمَنِ الرَّحِيْمِ. مَالِكِ يَوْمِ الدِّيْنِ. اِيَّاكَ نَعْبُدُ وَاِيَّاكَ نَسْتَعِيْنُ. اِهْدِنَا الصِّرَاطَ الَّمُسْتَقِيْمَ. صِرَاطَ الَّذِ يْنَ اَنْعَمْتَ عَلَيْهِمْ غَيْرِ الْمَغْضُوْبِ عَلَيْهِمْ وَلَا الضَّالِّيْنَ. اَمِينْ", "Bismillāhir-rahmānir-rahīm. Alhamdu lillahi rabbil ‘alamin. Ar Rahmaanirrahiim. Maaliki yaumiddiin. Iyyaaka na’budu wa iyyaaka nasta’iin. Ihdinash-shirraatal musthaqiim. Shiraathal ladziina an’amta ‘alaihim ghairil maghduubi ‘alaihim waladh-dhaalliin.");
        addItem("Membaca Surah Al-Ikhlas", "قُلْ هُوَ اللَّهُ أَحَدٌ . اللَّهُ الصَّمَدُ . لَمْ يَلِدْ وَلَمْ يُولَدْ . وَلَمْ يَكُن لَّهُ كُفُوًا أَحَدٌ", "Qul huwallahu ahad, allahu somad, lam yalid wa lam yụlad, wa lam yakul lahụ kufuwan ahad.");
        addItem("Membaca Surah Al-Alaq", "قُلْ أَعُوذُ بِرَبِّ الْفَلَقِ . مِنْ شَرِّ مَا خَلَقَ . وَمِنْ شَرِّ غَاسِقٍ إِذَا وَقَبَ . وَمِنْ شَرِّ النَّفَّاثَاتِ فِي الْعُقَدِ . وَمِنْ شَرِّ حَاسِدٍ إِذَا حَسَدَ", "Qul auudzu birobbil falaq. Min syarri maa kholaq. Wa min syarri ghoosiqin idzaa waqob. Wa min syarrin naffaatsaati fil ‘uqod. Wa min syarri haasidin idzaa hasad.");
        addItem("Membaca Surah An-Nas", "قُلْ أَعُوذُ بِرَبِّ النَّاسِ . مَلِكِ النَّاسِ . إِلَهِ النَّاسِ . مِنْ شَرِّ الْوَسْوَاسِ الْخَنَّاسِ . الَّذِي يُوَسْوِسُ فِي صُدُورِ النَّاسِ . مِنَ الْجِنَّةِ وَالنَّاسِ", "Qul auudzu birobbinnaas. Malikin naas. Ilaahin naas. Min syarril waswaasil khonnaas. Alladzii yuwaswisu fii shuduurin naas, minal jinnati wan naas.");
        addItem("Membaca Tahlil", "لَا إِلَهَ إِلَّا اللهَ", "Laailaaha Illallah.");
        addItem("Membaca Doa Jenazah", "اَللَّهُمَّ اغْفِرْ لَهُ وَارْحَمْهُ وَعَافِهِ وَاعْفُ عَنْهُ، وَأَكْرِمْ نُزُلَهُ، وَوَسِّعْ مَدْخَلَهُ، وَاغْسِلْهُ بِالْمَاءِ وَالثَّلْجِ وَالْبَرَدِ، وَنَقِّهِ مِنَ الذُّنُوبِ والْخَطَايَا كَمَا يُنَقَّى الثَّوْبُ الْأَبْيَضُ مِنَ الدَّنَسِ، وَأَبْدِلْهُ دَارًا خَيْرًا مِنْ دَارِهِ، وَزَوْجًا خَيْرًا مِنْ زَوْجِهِ، وَأَدْخِلْهُ الْجَنَّةَ، وَأَعِذْهُ مِنْ عَذَابِ الْقَبْرِ وَمِنْ عَذَابِ النَّار, وَافْسَحْ لَهُ فِي قَبْرِهِ، ونَوِّرْ لَهُ فِيهِ", "Allahummaghfìrlahu war hamhu wa ‘aafìhìì wa’fu anhu, wa akrìm nuzuulahu wawassì’ madholahu, waghsìlhu bìl maa’ì watssaljì walbaradì, wa naqqìhì, mìnaddzzunubì wal khathaya kamaa yunaqqatssaubul abyadhu mìnad danasì.");

        tahlilAdapter = new TahlilAdapter(models);
        recyclerViewDoaZiarah.setAdapter(tahlilAdapter);

    }

    private void addItem(String title, String arabic, String translate) {
        models.add(new TahlilResponse.Datum(title, arabic, translate));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
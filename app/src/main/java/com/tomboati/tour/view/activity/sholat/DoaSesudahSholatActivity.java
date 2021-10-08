package com.tomboati.tour.view.activity.sholat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.DoaSesudahSholatAdapter;
import com.tomboati.tour.model.DoaSesudahSholatModel;

import java.util.ArrayList;
import java.util.List;

public class DoaSesudahSholatActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private RecyclerView recyclerViewDoaSesudahSholat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_doa_sesudah_sholat);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Doa Sesudah Sholat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewDoaSesudahSholat = findViewById(R.id.recyclerViewDoaSesudahSholat);
        recyclerViewDoaSesudahSholat.setHasFixedSize(true);
        recyclerViewDoaSesudahSholat.setLayoutManager(new LinearLayoutManager(this));

        List<DoaSesudahSholatModel> list = new ArrayList<>();
        list.add(new DoaSesudahSholatModel(
                "Membaca istighfar di bawah ini sebanyak tiga kali:",
                "أَسْتَغْفِرُ اللهَ الْعَظِـيْمَ الَّذِيْ لَااِلَهَ اِلَّا هُوَ الْحَيُّ الْقَيُّوْمُ وَأَتُوْبُ إِلَيْهِ",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Memuji Allah dengan kalimat:",
                "اَللَّهُمَّ أَنْتَ السَّلاَمُ وَمِنْكَ السَّلَامُ تَبَارَكْتَ يَا ذَاالْـجَلَالِ وَاْلإِكْرَام",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Lalu membaca:",
                "اَللَّهُمَّ لَا مَانِعَ لِمَا أَعْطَيْتَ، وَلاَ مُعْطِيَ لِمَا مَنَعْتَ، وَلَا يَنْفَعُ ذَاالْجَدِّ مِنْكَ الْجَدُّ",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Berdoa agar diberi kemampuan untuk mengingat (dzikir), bersyukur, dan beribadah secara baik kepada Allah:",
                "اَللَّـهُمَّ اَعِنِّي عَلَى ذِكْرِكَ وَشُكْرِكَ وَحُسْنِ عِبَادَتِكَ",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Dilanjutkan dengan membaca:",
                "لَاإِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيْكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُ يُحْيِيْ وَيُمِيْتُ وَهُوَ عَلَى كُلِّ شَيْئٍ قَدِيْرٌ",
                "(dibaca tiga kali tiap selesai shalat fardhu, khusus setelah maghrib dan shubuh sepuluh kali)"));
        list.add(new DoaSesudahSholatModel(
                "Memohon perlindungan dari ganasnya neraka:",
                "اَللَّهُمَّ أَجِرْنِـى مِنَ النَّارِ",
                "(tujuh kali bakda maghrib dan shubuh)"));
        list.add(new DoaSesudahSholatModel(
                "Membaca Ayat Kursi:",
                "أَعُوذُ بِاللَّهِ مِنَ الشَّيْطَانِ الرَّجِيمِ. بِسْمِ اللهِ الرَّحْمَنِ الرَّحِيْمِ. اَللهُ لَا إِلَهَ إِلَّا هُوَ الْحَيُّ الْقَيُّومُ لَا تَأْخُذُهُ سِنَةٌ وَّلَانَوْمٌ، لَهُ مَافِي السَّمَاوَاتِ وَمَافِي اْلأَرْضِ مَن ذَا الَّذِيْ يَشْفَعُ عِنْدَهُ إِلَّا بِإِذْنِهِ يَعْلَمُ مَابَيْنَ أَيْدِيْهِمْ وَمَاخَلْفَهُمْ وَلَا يُحِيْطُونَ بِشَيْءٍ مِّنْ عِلْمِهِ إِلَّا بِمَا شَآءَ، وَسِعَ كُرْسِيُّهُ السَّمَاوَاتِ وَاْلأَرْضَ وَلَا يَـؤدُهُ حِفْظُهُمَا وَهُوَ الْعَلِيُّ الْعَظِيْمُ",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Membaca Surat al-Baqarah ayat 285-286",
                "آمَنَ الرَّسُولُ بِمَا أُنْزِلَ إِلَيْهِ مِنْ رَبِّهِ وَالْمُؤْمِنُونَ، كُلٌّ آمَنَ بِاللَّهِ وَمَلَائِكَتِهِ وَكُتُبِهِ وَرُسُلِهِ لَا نُفَرِّقُ بَيْنَ أَحَدٍ مِنْ رُسُلِهِ، وَقَالُوا سَمِعْنَا وَأَطَعْنَا غُفْرَانَكَ رَبَّنَا وَإِلَيْكَ الْمَصِيرُ. لَا يُكَلِّفُ اللَّهُ نَفْسًا إِلَّا وُسْعَهَا، لَهَا مَا كَسَبَتْ وَعَلَيْهَا مَا اكْتَسَبَتْ. رَبَّنَا لَا تُؤَاخِذْنَا إِنْ نَسِينَا أَوْ أَخْطَأْنَا، رَبَّنَا وَلَا تَحْمِلْ عَلَيْنَا إِصْرًا كَمَا حَمَلْتَهُ عَلَى الَّذِينَ مِنْ قَبْلِنَا، رَبَّنَا وَلَا تُحَمِّلْنَا مَا لَا طَاقَةَ لَنَا بِهِ، وَاعْفُ عَنَّا وَاغْفِرْ لَنَا وَارْحَمْنَا، أَنْتَ مَوْلَانَا فَانْصُرْنَا عَلَى الْقَوْمِ الْكَافِرِينَ",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Disambung dengan penggalan dari Surat Ali Imran:",
                "شَهِدَ اللَّهُ أَنَّهُ لَا إِلَٰهَ إِلَّا هُوَ وَالْمَلَائِكَةُ وَأُولُو الْعِلْمِ قَائِمًا بِالْقِسْطِ، لَا إِلَٰهَ إِلَّا هُوَ الْعَزِيزُ الْحَكِيمُ، إِنَّ الدِّينَ عِنْدَ اللَّهِ الْإِسْلَامُ، قُلِ اللَّهُمَّ مَالِكَ الْمُلْكِ تُؤْتِي الْمُلْكَ مَنْ تَشَاءُ وَتَنْزِعُ الْمُلْكَ مِمَّنْ تَشَاءُ وَتُعِزُّ مَنْ تَشَاءُ وَتُذِلُّ مَنْ تَشَاءُ، بِيَدِكَ الْخَيْرُ، إِنَّكَ عَلَىٰ كُلِّ شَيْءٍ قَدِيرٌ. تُولِجُ اللَّيْلَ فِي النَّهَارِ وَتُولِجُ النَّهَارَ فِي اللَّيْلِ، وَتُخْرِجُ الْحَيَّ مِنَ الْمَيِّتِ وَتُخْرِجُ الْمَيِّتَ مِنَ الْحَيِّ، وَتَرْزُقُ مَنْ تَشَاءُ بِغَيْرِ حِسَاب",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Membaca Surat al-Ikhlas, Surat al-Falaq, Surat an-Nas, lalu Surat al-Fatihah",
                "",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Membaca tasbih, hamdala, dan takbir masing-masing sebanyak 33 kali:",
                "سُبْحَانَ اللهِ ×٣٣" + "\n" + "اَلْحَمْدُلِلهِ ×٣٣" + "\n" + "اَللهُ اَكْبَرْ ×٣٣",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Kemudian dilanjutkan dengan:",
                "اَللهُ اَكْبَرْ كَبِيْرًا وَالْحَمْدُ لِلهِ كَثِيْرًا وَسُبْحَانَ اللهِ بُكْرَةً وَأَصِيْلًا، لَاإِلَهَ إِلَّا اللهُ وَحْدَهُ لَا شَرِيْكَ لَهُ، لَهُ الْمُلْكُ وَلَهُ الْحَمْدُيُحْيِيْ وَيُمِيْتُ وَهُوَ عَلَى كُلِّ شَيْئٍ قَدِيْرٌ، وَلَاحَوْلَ وَلَاقُوَّةَ إِلَّابِا للهِ الْعَلِـىِّ الْعَظِيْمِ. أَفْضَلُ ذِكْرِ فَاعْلَمْ أَنَّهُ",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Kemudian dilanjutkan dengan:",
                "لَاإِلَهَ إِلَّا اللهُ",
                "(Dibaca 300 kali bakda shubuh, 100 kali bakda isya, 50 kali bakda dhuhur, 50 kali bakda ashar, dan 100 kali bakda maghrib)"));
        list.add(new DoaSesudahSholatModel(
                "Kemudian dilanjutkan dengan:",
                "صَلَّى اللهُ عَلَى مُحَمَّدٍ",
                "(dibaca bakda shubuh 300 atau 100 kali)"));
        list.add(new DoaSesudahSholatModel(
                "Kemudian dilanjutkan dengan:",
                "لَاإِلَهَ إِلَّا اللهُ مُحَمَّدٌ رَسُوْلُ اللهِ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ",
                ""));
        list.add(new DoaSesudahSholatModel(
                "Wirid kemudian ditutup dengan doa sesuai dengan harapan masing-masing.",
                "",
                ""));

        DoaSesudahSholatAdapter adapter = new DoaSesudahSholatAdapter(list);
        recyclerViewDoaSesudahSholat.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
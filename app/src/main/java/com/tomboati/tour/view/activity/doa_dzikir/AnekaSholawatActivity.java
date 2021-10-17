package com.tomboati.tour.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.DoaHajiUmrahAdapter;
import com.tomboati.tour.databinding.ActivityAnekaSholawatBinding;
import com.tomboati.tour.model.DoaHajiUmrahModel;

import java.util.ArrayList;
import java.util.List;

public class AnekaSholawatActivity extends AppCompatActivity {

    private ActivityAnekaSholawatBinding bind;
    private DoaHajiUmrahAdapter doaHajiUmrahAdapter;
    private List<DoaHajiUmrahModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        bind = ActivityAnekaSholawatBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        setSupportActionBar(bind.toolbar);
        setTitle("Aneka Sholawat");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        models = new ArrayList<>();

        bind.recyclerViewAnekaSholawat.setHasFixedSize(true);
        bind.recyclerViewAnekaSholawat.setLayoutManager(new LinearLayoutManager(this));

        addItem("Sholawat Nariyah",
                "للّهُمَّ صَلِّ صَلَاةً كَامِلَةً وَسَلِّمْ سَلَامًا تَامًّا عَلَى سَيِّدِنَا مُحَمَّدِ الّذِي تَنْحَلُّ بِهِ الْعُقَدُ وَتَنْفَرِجُ بِهِ الْكُرَبُ وَتُقْضَى بِهِ الْحَوَائِجُ وَتُنَالُ بِهِ الرَّغَائِبُ وَحُسْنُ الْخَوَاتِمِ وَيُسْتَسْقَى الْغَمَامُ بِوَجْهِهِ الْكَرِيْمِ وَعَلَى آلِهِ وَصَحْبِهِ فِيْ كُلِّ لَمْحَةٍ وَنَفَسٍ بِعَدَدِ كُلِّ مَعْلُوْمٍ لَكَ",
                "Allahumma shollì sholaatan kaamìlatan Wa sallìm salaaman taaman 'ala sayyìdìnaa Muhammadìn Alladzì tanhallu bìhìl 'uqadu, wa tanfarìju bìhìl kurabu Wa tuqdhaa bìhìl hawaa'ìju Wa tunaalu bìhìr raghaa'ìbu wa husnul khawaatìmì wa yustasqal ghomaamu bì wajhìhìl karììmì, wa 'alaa aalìhì, wa shahbìhì 'adada kullì ma'luumìn laka.",
                "Ya Allah, berikanlah sholawat yang sempurna dan salam yang sempurna kepada junjunganku Baginda Nabi Muhammad yang dengannya terlepas dari ikatan (kesusahan) dan dibebaskan dari kesulitan. Dan dengannya juga ditunaikan hajat dan diperoleh segala keinginan dan kematian yang baik, serta memberi siraman (kebahagiaan) kepada orang yang sedih dengan wajahnya yang mulia, juga kepada keluarganya, para shahabatnya, dengan seluruh ilmu yang Engkau miliki.",
                "Dinamakan sholawat Nariyah karena dirumuskan oleh Syekh Nariyah."
        );

        addItem("Sholawat Ibrahimiyah",
                "اَللَّهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلٰى آلِ سَيِّدِنَا مُحَمَّدٍ كَمَا صَلَّيْتَ عَلٰى سَيِّدِنَا إِبْرَاهِيْمَ وَعَلٰى آلِ سَيِّدِنَا إِبْرَاهِيْمَ و بَارِكْ عَلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلٰى آلِ سَيِّدِنَا مُحَمَّدٍ كَمَا بَارَكْتَ عَلٰى سَيِّدِنَا إِبْرَاهِيْمَ وَعَلٰى آلِ سَيِّدِنَا إِبْرَاهِيْمَ فِيْ الْعَالَمِيْنَ إِنَّكَ حَمِيْدُ مَجِيْدٌ",
                "Allohumma solli 'alaa muhammad, wa 'alaa aali muhammad, kamaa sollaita 'alaa aali ibroohim, wa baarik 'alaa muhammad, wa 'alaa aali muhammad, kamaa baarokta 'alaa aali ibroohim, fil 'aalamiina innaka hamiidummajiid.",
                "Ya Allah, limpahkanlah rahmat dan keselamatan untuk Nabi Muhammad. Dan juga limpahkanlah rahmat dan keselamatan kepada keluarga Muhammad, sebagaimana telah Engkau limpahkan rahmat dan keselamatan kepada Ibrahim dan kepada keluarga Ibrahim. Limpahkanlah keberkahan kepada Muhammad dan kepada keluarga Muhammad, sebagaimana Engkau telah melimpahkan keberkahan kepada Ibrahim dan kepada keluarga Ibrahim. Di seluruh alam semesta, sesungguhnya Engkau adalah Maha Terpuji lagi Maha Agung.",
                "Sholawat Ibrahimiyah merupakan sholawat yang dibacakan saat sholat yaitu saat tasyahud akhir."
        );

        addItem("Sholawat Munjiyat",
                "اَللّٰهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ صَلاَةٌ تُنْجِيْنَا بِهَا مِنْ جَمِيْعِ الْاَهْوَالِ وَالْاٰفَاتِ وَتَقْضِيْ لَنَابِهَاجَمِيعَ الْحَاجَاتِ وَتُطَهِّرُنَابِهَا مِنْ جَمِيْعِ السَيِّئَاتِ وَتَرْفَعُنَابِهَا عِنْدَكَ اَعْلَى الدَّرَجَاتِ وَتُبَلِّغُنَابِهَا اَقْصَى الْغَايَاتِ مِنْ جَمِيْعِ الْخَيْرَاتِ فِى الْحَيَاةِ وَبَعْدَ الْمَمَاتِ",
                "Allahumma sholli 'alaa sayyidinaa Muhammadin sholaatan tunjiinaa bihaa min jamii'il-ahwaali wal-aafaati wa taqdhii lanaa bihaa jamii'al-haajaati wa tuthahirunaa bihaa min jamii'is-sayyi'aati wa tarfa'unaa bihaa 'indaka a'lad-darajaati wa tuballigunaa bihaa aqshal-gaayaati min jamii'il-khairaati fil-hayaati wa ba'dal-mamaati.",
                "Ya Allah, limpahkanlah rahmat kepada junjungan kami Nabi Muhammad SAW yang melaluinya Engkau akan menyelamatkan kami dari semua keadaan yang menakutkan dan membahayakan, dengan rahmat itu Engkau akan mendatangkan semua hajat kami dan membersihkan semua keburukan kami, mengangkat kami pada derajat tertinggi, menyampaikan kami pada puncak tujuan, dari semua kebaikan di waktu hidup dan sesudah mati.",
                ""
        );

        addItem("Sholawat Nuril Anwar",
                "اَللَّهُمَّ صَلِّ عَلَى نُوْرِ اْلاَنْوَارِ وَسِرِّ اْلاَسْرَارِ وَتِرْيَاقِ اْلاَغْيَارِ وَمِفتَاحِ بَابِ الْيَسَارِ سَيِّدِنَا وَمَوْلاَنَا مُحَمَّدِ نِالْمُخْتَارِ وَالِهِ اْلاَطْهَرِ وَاَصْحَابِهِ اْلاَخْيَارِ عَدَدَ نِعَمِ اللهِ وَأِفْضَالِهِ",
                "Allahumma Shalli 'Alaa Nuuril Anwaari Wasirril Asraari, Watiryaaqil Aghyaari Wamiftaahi Baabil Yasaari, Sayyidinaa Wamaulaana Muhammadinil Muhtaari Wa Aalihil Ath Haari Wa Ash Haabihil Ahyaari 'Adada Ni'amillaahi Wa Ifdhaalih.",
                "Ya Allah, limpahkanlah rahmat kepada cahaya dari segala cahaya, belakang layar dari segenap rahasia, penawar sedih dan kebingungan, pembuka pintu kemudahan, yakni junjungan kami, Nabi Muhammad saw. yang terpilih, keluarganya yang suci, dan para sahabatnya yang mulia sebanyak hitungan nikmat Yang Mahakuasa dan karunia-Nya.",
                ""
        );

        addItem("Sholawat Fatih",
                "اللَّهُمَّ صَلِّ عَلَى سَيِّدِنَا مُحَمَّدٍ. الفَاتِحِ لِمَا أُغْلِقَ. وَالخَاتِمِ لِمَا سَبَقَ. نَاصِرِ الحَقِّ بِالحَقِّ. وَالهَادِي إِلَى صِرَاطِكَ المُسْتَقِيمِ. وَعَلَى آلِهِ حَقَّ قَدْرِهِ وَمِقْدَارِهِ العَظِيمِ.",
                "Allahumma sholliaala sayyidina muhammad, Alfatihi lima ughliqo wal khotimi lima sabaqo, nashiril haqqi bil haqqi wal hadi ila shirotikal mustaqim wa ala alihi haqqo qodrihi wa miq darihil adzim.",
                "Ya Allah limpahkanlah rahmat dan keselamatan serta berkah kepada nabi Muhammad SAW, sebagai pemuka sesuatu yang terkunci, dan penutup sesuatu (para nabi) yang terdahulu, dialah penolong yang benar dengan membawa kebenaran serta petunjuk menuju jalan-Mu yang lurus. Semoga Allah melimpahkan rahmat-Nya kepada keluarga dan para sahabatnya dengan sebenar-benarnya dengan pangkat dan kedudukan yang agung.",
                ""
        );

        addItem("Sholawat Ma’tsuroh",
                "أللّٰـهُمَّ صَلِّ عَلٰى مُحّمَّدٍ نِالنَّـبِىِ اْلأُمِّىِّ وَعَلٰى اٰلِهِ وَسَلِّمْ",
                "Allaahumma Sholli'alaa Muhammadin Nabiyyil Ummiyyi Wa'alaa Aalihi Wasallim.",
                "Ya Allah, limpahkanlah sholawat kepada Muhammad yang tiada dapat membaca dan menulis (Ummy) dan semoga keselamatan tercurah kepada segenap keluarganya.",
                ""
        );

        addItem("Sholawat Quthbul Aqthab",
                "اَللّٰـهُمَّ صَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ فِى اْلأَوَّلِيْنَ. وَصَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ فِى اْلآخِرِيْنَ. وَصَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ فِى اْلـمُرْسَلِيْنَ. وَصَلِّ عَلٰى سَيِّدِنَا مُحَمَّدٍ فِى الْمَلَإِ اْلأَعْلٰى إِلٰى يَوْمِ الدِّيْنَ.",
                "Allahuma sholli 'ala sayyidina muhammad fil awwaliin, washolli 'ala sayyida muhammad fil akhirin, washolli 'ala sayyida muhammad fil mursaliin , washolli 'ala sayyida muhammad filmala'il 'a'la ila yauwmiddin.",
                "Ya Allah, berikanlah rahmat takdim kepada junjungan kita nabi muhammad dan kepada kepala keluarga junjungan kami muhammad pada (abad-abad)pertama dan berikan rahmat tadzim kepada junjungan kami muhammad dan kepada kepala keluarga muhammad pada (abad-abad) terahir dan berikanlah rahmat takzim kepada junjungan kami muhammad dan kepada kepala keluarga junjungan kami muhammad pada orang-orangyang tertinggi sampai hari kiamat.",
                ""
        );

        addItem("Sholawat Ummy",
                "اَللَّــهُمَّ صَلِّ عَـلـٰى سَـيِّـدِنَـا مُحَمَّدٍ عَبْدِكَ وَنَـبِـيِّكَ وَرَسُوْلِكَ نَبِى الْأُمِّـى وَعَــلـٰى أَلِـهِ وَصَحْبِهِ وَسِلِّـمْ",
                "Allahumma sholli’alaa sayyidinaa muhammadin abdika wanabiyyika wa Rosuulika Nabil Ummy wa a’laa alihi wa shohbihi wasillam.",
                "Ya Allah, limpahkanlah rahmat kepada junjungan kami Nabi Muhammad saw, sebagai hamba, Nabi, dan utusan-Mu yang Ummy (tidak bisa membaca dan menulis) beserta keluarga dan sahabatnya dengan salam yang sesungguhnya.",
                ""
        );

        addItem("Sholawat Hajat Dunia Akhirat",
                "اَللّـٰــهُمَّ صَلِّ عَـلـٰى سَيَّـدِنَـا مُحَمَّدٍ وَعَـلـٰى أَلِ سَيِّـدِنَا مُحَمَّدٍ وَعَلـٰى أَهْلِ بَــيْـتِه",
                "Allaahumma shalli ‘alaa sayyidinaa Muhammadin wa’alaa aaali sayyidinaa Muhammadin wa’alaa ahlihi baitihi.",
                "Ya Allah, limpahkanlah rahmat kepada junjungan kami Nabi Muhammad saw, dan kepada keluarga junjungan kami Nabi Muhammad saw, dan kepada ahli keluarganya.",
                ""
        );

        addItem("Sholawat Mukafa’ah",
                "اَللّٰـهُمَّ صَلِّ عِلٰى سَيِّدِنَا مُحَمَّدٍ وَعَلٰى أٰلِ سَيِّدِنَا مُحَمَّدٍ صَلَاةً دَائِمَةً مَقْبُوْلَةً تُـؤَدِّى بِهَا حَقَّهُ الْعَظِيْمِ .",
                "Allohumma sholli ‘ala sayidina Muhammadin wa ‘ala alihi sayidina Muhammad, Sholatan maqbulatan tu,addi biha ‘anna haqqohul ‘adzim.",
                "Ya Allah, limpahkanlah rahmat, salam dan berkah kepada junjungan kita Muhammad saw yang ummi (tidak bisa baca tulis) yang menjadi kekasih Allah swt, yang luhur pangkatnya dan yang agung kemuliaannya, dan limpahkanlah pula atas keluarganya dan para sahabatnya.",
                ""
        );

        addItem("Sholawat Ghozali",
                " اَللّٰـهُمَّ صَلِّ وَسَلِّمْ سَيِّدِنَا مُحَمَّدٍ وَعَلىَ اَلِ سَيِّدِنَا مُحَمَّدٍ صَلاَةً دَائِمَـــــةً مُسْتَمِرَّةٌ تَدُوْمُ بِدَوَامِكَ وَتَبْقَى بِبَقَـــائِكَ وَتَخْلُدُ بِخُلُوْدِكَ وَلاَغَايَــــة َلَهَا دُوْنَ مَرْضَـــاتِكَ وَلاَجَزَاءَ لِقَائِلِهَا وَمُصَلِّيْــــهَا غَيْرَ جَنَّتِكَ وَالنَّظْرِ اِلىَ وَجْهِكَ اْلكَرِيْمِ",
                "Allâhumma sholli wa sallim Sayyidinâ Muhammadin wa ‘alâ âli Sayyidinâ Muhammadin sholâtan dâ-imatan mustamirrotan tadûmu bidawâmika wa tabqô bi baqô-ika wa takhludu bikhulûdika Wa lâ ghôyata lahâ dûna mardlôtika walâ jazâ-a liqô-ilihâ wa mushollîhâ ghoiro jannatika wannadhori ilâ wajhikal karîmi.",
                "Ya Allah, limpahkanlah rahmat, salam dan berkah kepada junjungan kita Muhammad saw yang kekal dan terus menerus, ia kekal karena Kekekalan-Mu, ia tetap karena ketetapan-Mu, ia langgeng karena kelanggengan-Mu, tidak ada ujung bainya tanpa keridhoan-Mutidak ada balasan bagi pembacanya dan yang memintakan rahmat selain surga-Mu dan melihakt Wujud-Mu yang Mulia.",
                ""
        );

        addItem("Sholawat Syifa’",
                " أَللَّهُمَّ صَلِّ عَلَى سَيِّدِنَا مُحَمَّدٍ طِبِّ الْقُلُوْبِ وَدَوَائِهَا وَعَافِيَةِ الأَبْدَانِ وَشِفَائِهَا وَنُوْرِ الأَبْصَارِ وَضِيَائِهَا وَعَلَى آَلِهِ وَصَحْبِهِ وَسَلِّمْ",
                "Allohumma sholli 'ala sayyidina Muhammadin, tibbil quluubi wa dawaa-iha, wa 'aafiyatil abdaani wa syifa-iha, wa nuuril abshoori wa dliyaa-iha, wa 'ala aalihi wa shahbihi wa sallim.",
                "Ya Allah, limpahkanlah rahmat kepada baginda kami Nabi Muhammad yang menjadi obat dan penyembuhan hati, penyehat dan penyelamat badan, cahaya dan sinar penglihatan, dan limpahkan kepada keluarga dan sahabat-sahabatnya, dan berilah kesejahteraan.",
                ""
        );

        doaHajiUmrahAdapter = new DoaHajiUmrahAdapter(models, true);
        bind.recyclerViewAnekaSholawat.setAdapter(doaHajiUmrahAdapter);
    }

    private void addItem(String title, String arabic, String translate, String arti, String keterangan) {
        models.add(new DoaHajiUmrahModel(title, arabic, translate, arti, keterangan));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
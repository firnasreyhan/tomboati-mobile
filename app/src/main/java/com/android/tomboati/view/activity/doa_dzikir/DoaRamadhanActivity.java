package com.android.tomboati.view.activity.doa_dzikir;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.android.tomboati.R;
import com.android.tomboati.adapter.DoaHajiUmrahAdapter;
import com.android.tomboati.model.DoaHajiUmrahModel;

import java.util.ArrayList;
import java.util.List;

public class DoaRamadhanActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDoaRamadhan;
    private DoaHajiUmrahAdapter doaHajiUmrahAdapter;
    private List<DoaHajiUmrahModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_doa_ramadhan);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Doa Bulan Ramadhan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        models = new ArrayList<>();

        recyclerViewDoaRamadhan = findViewById(R.id.recyclerViewDoaRamadhan);
        recyclerViewDoaRamadhan.setHasFixedSize(true);
        recyclerViewDoaRamadhan.setLayoutManager(new LinearLayoutManager(this));

        addItem("Doa Menyambut Ramadhan", "اللَّهُمَّ بَارِكْ لَنَا فِى رَجَبٍ وَشَعْبَانَ وَبَلِّغْنَا رَمَضَانَ", "Allohuma baariklanaa fii Rojaba wa Sya’ban, wabalighnaa Romadhon.", "Ya Allah berahilah kami di bulan Rajab dan Sya’ban, serta pertemukanlah kami dengan bulan Ramadhan.");
        addItem("Niat Puasa Romadhon", "نَوَيْتُ صَوْمَ غَدٍ عَنْ أَدَاءِ فَرْضِ شَهْرِ رَمَضَانِ هذِهِ السَّنَةِ لِلهِ تَعَالَى", "Nawaitu Shauma Ghodin ‘an adaa’I fardhi syahri romadhoona haadzihis sanati lillahita’ala.", "Aku niat berpuasa esok hari untuk menunaikan kewajiban di bulan Ramadhan tahun ini karena Allah Ta’ala.");
        addItem("Doa Berbuka Puasa", "اَللّهُمَّ لَكَ صُمْتُ وَبِكَ آمَنْتُ وَعَلَى رِزْقِكَ أَفْطَرْتُ بِرَحْمَتِكَ يَا اَرْحَمَ الرَّحِمِيْنَ", "Allahumma laka shumtu wabika amantu wa ‘ala rizqika afthartu birahmatika yaa arhamar rahimin.", "Ya Allah karenaMu aku berpuasa, denganMu aku beriman, kepadaMu aku berserah, dan sengan rezekiMu aku berbuka (puasa), dengan rahmatMu, wahai Allah Tuhan Maha Pengasih.");
        addItem("Niat Sholat Tarawih Berjamaah", "اُصَلِّى سُنَّةَ التَّرَاوِيْحِ رَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ مَأْمُوْمًا ِللهِ تَعَالَى", "Ushalli sunnatat taraawiihi rak’ataini mustaqbilal qiblati ma’muman lillahi ta’aalaa.", "Aku niat Salat Tarawih dua rakaat menghadap kiblat sebagai makmum karena Allah Ta’ala.");
        addItem("Niat Sholat Tarawih Sendiri", "اُصَلِّى سُنَّةَ التَّرَاوِيْحِ رَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ ِللهِ تَعَالَى", "Usholli sunnatattarowihi rok’ataini mustaqbilal qiblati lillahi ta’ala.", "Aku niat Salat Tarawih dua rakaat menghadap kiblat karena Allah Ta’ala.");
        addItem("Niat Sholat Tarawih Sebagai Imam", "اُصَلِّى سُنَّةَ التَّرَاوِيْحِ رَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ إِمَامًا ِللهِ تَعَالَى", "Ushollii sunnatat-taraawiihi rok’ataini mustaqbilal qiblati imaaman lillaahi ta’alaa.", "Aku niat sholat sunnah tarawih dua raka’at menghadap kiblat sebagai imam karena Allah Ta’ala.");
        addItem("Doa Setelah Sholat Tarawih", "اَللهُمَّ اجْعَلْنَا بِالْإِيْمَانِ كَامِلِيْنَ. وَلِلْفَرَائِضِ مُؤَدِّيْنَ. وَلِلصَّلاَةِ حَافِظِيْنَ. وَلِلزَّكَاةِ فَاعِلِيْنَ. وَلِمَا عِنْدَكَ طَالِبِيْنَ. وَلِعَفْوِكَ رَاجِيْنَ. وَبِالْهُدَى مُتَمَسِّكِيْنَ. وَعَنِ الَّلغْوِ مُعْرِضِيْنَ. وَفِى الدُّنْيَا زَاهِدِيْنَ. وَفِى اْلآخِرَةِ رَاغِبِيْنَ. وَبَالْقَضَاءِ رَاضِيْنَ. وَلِلنَّعْمَاءِ شَاكِرِيْنَ. وَعَلَى الْبَلاَءِ صَابِرِيْنَ. وَتَحْتَ لِوَاءِ مُحَمَّدٍ صَلَّى اللهُ عَلَيْهِ وَسَلَّمَ يَوْمَ الْقِيَامَةِ سَائِرِيْنَ وَعَلَى الْحَوْضِ وَارِدِيْنَ. وَإِلَى الْجَنَّةِ دَاخِلِيْنَ. وَمِنَ النَّارِ نَاجِيْنَ. وَعَلى سَرِيْرِالْكَرَامَةِ قَاعِدِيْنَ. وَبِحُوْرٍعِيْنٍ مُتَزَوِّجِيْنَ. وَمِنْ سُنْدُسٍ وَاِسْتَبْرَقٍ وَدِيْبَاجٍ مُتَلَبِّسِيْنَ. وَمِنْ طَعَامِ الْجَنَّةِ آكِلِيْنَ. وَمِنْ لَبَنٍ وَعَسَلٍ مُصَفًّى شَارِبِيْنَ. بِأَكْوَابٍ وَّأَبَارِيْقَ وَكَأْسٍ مِّنْ مَعِيْن. مَعَ الَّذِيْنَ أَنْعَمْتَ عَلَيْهِمْ مِنَ النَّبِيِّيْنَ وَالصِّدِّيْقِيْنَ وَالشُّهَدَآءِ وَالصَّالِحِيْنَ وَحَسُنَ أُولئِكَ رَفِيْقًا. ذلِكَ الْفَضْلُ مِنَ اللهِ وَكَفَى بِاللهِ عَلِيْمًا. اَللهُمَّ اجْعَلْنَا فِى هذِهِ اللَّيْلَةِ الشَّهْرِالشَّرِيْفَةِ الْمُبَارَكَةِ مِنَ السُّعَدَاءِ الْمَقْبُوْلِيْنَ. وَلاَتَجْعَلْنَا مِنَ اْلأَشْقِيَاءِ الْمَرْدُوْدِيْنَ. وَصَلَّى اللهُ عَلَى سَيِّدِنَا مُحَمَّدٍ وَآلِه وَصَحْبِه أَجْمَعِيْنَ. بِرَحْمَتِكَ يَاأَرْحَمَ الرَّاحِمِيْنَ وَالْحَمْدُ لِلّهِ رَبِّ الْعَالَمِيْنَ", "Allaahummaj'alnaa bil-iimaani kaamiliin, wa lil-faraa 'idimu'addiin, wa lis-salaati haafiziin, wa liz-zakaatii faa'iliin, wa limaa 'indaka taalibiin, wa li'afwika raajiin, wa bil-hudaa mutamassikiin, wa 'anil-lagwi mu'ridiin, wa fid-dun-yaa zaahidiin, wa fil-aakhirati raagibiin, wa bil-qadaa'i raadiin, wa lin-na 'maa'i syaakiriin, wa 'alal-balaa'i saabiriin, wa tahta liwaa'i sayyidinaa Muhammadin sallallahu 'alaihi wa sallama yaumal-qiyaamati saa'iriin, wa ilal-haudi waaridiin, wa ilal-jannati daakhiliin, wa minan-naari naajin, wa 'alaa sariiril-karaamati qaa'idiin wa min huurin 'iinim mutazaw-wijiin, wa min sundusiw wa istabraqiw wa diibaajim mutalabbisiin, wa min ta'aamil-jannati aakiliin, wa mil labaniw wa 'asalim musaffan syaaribiin, bi akwaabiw wa abaariiqa wa ka'sim mim ma'iin. Ma'allaziina an'amta 'alaihim minan-nabiyyiina was-siddiiqiina wasy-syuhadaa'i was-saalihiinna wa hasuna ulaa'ika rafiiqaa, zaalikal-fadlu minallaahi wa kafaa billaahi 'aliimaa Allaahummaj'alnaa fii lailati haazasy-syahrisy-syariifatil-mubaarakati minas-su'adaa'il-maqbuuliina wa laa taj'alnaa minal-asyqiyaa'il-marduudiin. Wa sallallaahu 'alaa Muhammadiw wa 'alihii wa sahbihii ajmaa'in, bi rahmatika yaa arhamar-raahimiin, wal-hamdu lillaahi rabbil-'aalamiin.", "Yaa Allah, jadikanlah kami orang-orang yang sempurna imannya, yang memenuhi kewajiban-kewajiban, yang memelihara salat, yang mengeluarkan zakat, yang mencari apa yang ada di sisi-Mu, yang mengharapkan ampunan-Mu, yang berpegang pada petunjuk, yang berpaling dari kebatilan, yang zuhud di dunia, yang menyenangi akhirat, yang ridha dengan qadla-Mu (ketentuan-Mu), yang mensyukuri nikmat, yang sabar atas segala musibah, yang berada di bawah panji-panji junjungan kami Nabi Muhammad, pada hari kiamat, yang mengunjungi telaga (Nabi Muhammad), yang masuk ke dalam surga, yang selamat dari api neraka, yang duduk di atas ranjang kemuliaan, yang menikah dengan para bidadari, yang mengenakan berbagai sutra, yang makan makanan surga, yang minum susu dan madu murni dengan gelas, cangkir, dan cawan bersama orang-orang yang Engkau beri nikmat dari kalangan para nabi, shiddiqin, syuhada dan orang-orang salih. Mereka itulah teman yang terbaik. Itulah keutamaan (anugerah) dari Allah, dan cukuplah bahwa Allah Maha Mengetahui. Ya Allah, jadikanlah kami pada malam yang mulia dan diberkahi ini termasuk orang-orang yang bahagia dan diterima amalnya, dan janganlah Engkau jadikan kami tergolong orang-orang yang celaka dan ditolak amalnya. Semoga Allah mencurahkan rahmat-Nya atas junjungan kami Muhammad, serta seluruh keluarga dan sahabat beliau. Berkat rahmat-Mu, wahai Yang Paling Penyayang di antara yang penyayang. Segala puji bagi Allah Tuhan semesta alam.");
        addItem("Niat Sholat Witir", "(Rakaat 3)\nاُصَلِّى سُنًّةَ الْوِتْرِ ثَلاَثَ رَكَعَاتٍ مُسْتَقْبِلَ الْقِبْلَةِ اَدَاءً مَأْمُوْمًا ِللهِ تَعَالَى\n" +
                "(Rakaat 1)\nاُصَلِّى سُنًّةَ الْوِتْرِرَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ اَدَاءً مَأْمُوْمًاِللهِ تَعَالَى", "Ushallii sunnatal witri tsalaasa roka’aatin mustaqbilal qiblati adaa’an (ma’muman/imaman) lillaahi ta’alaa (Rakaat 3), Ushallii sunnatal witri rok ‘atan mustaqbilal qiblati adaa’an (ma’muman / imaman) lillaahi ta’alaa (Rakaat 1).", "Aku berniat shalat witir tiga rakaat menghadap kiblat menjadi (ma’muman/imaman) karena Allah ta’alaa (Rakaat 3), Saya niat sholat witir satu rakaat menghadap qiblat menjadi makmum karena Allah ta’alaa (Rakaat 1).");
        addItem("Doa Setelah Sholat Witir", "اَللّٰهُمَّ إِنَّا نَسْـأَلُكَ اِيْمَانًا دَائِمًا، وَنَسْأَلُكَ قَلْبًا خَاشِعًا، وَنَسْأَلُكَ عِلْمًا نَافِعًا،وَنَسْأَلُكَ يَقِيْنًا صَادِقًا، وَنَسْأَلُكَ عَمَلاً صَالِحًا، وَنَسْأَلُكَ دِيْنًاقَيِّمًا، وَنَسْأَلُكَ خَيْرًا كَثِيْرًا، وَنَسْأَلُكَ الْعَفْوَ وَالْعَافِيَةَ، وَنَسْأَلُكَ تَمَامَ الْعَافِيَةِ، وَنَسْأَلُكَ الشُّكْرَ عَلَى الْعَافِيَةِ، وَنَسْأَلُكَ الْغِنَاءَ عَنِ النَّاسِ اَللّٰهُمَّ رَبَّنَا تَقَبَّلْ مِنَّا صَلاَتَنَا وَصِيَامَنَا وَقِيَامَنَا وَتَخُشُّعَنَا وَتَضَرُّعَنَا وَتَعَبُّدَنَا وَتَمِّمْ تَقْصِيْرَنَا يَا اَللهُ يَااَللهُ يَااَللهُ يَااَرْحَمَ الرَّحِمِيْنَ. وَصَلَّى اللهُ عَلَى خَيْرِ خَلْقِهِ مُحَمَّدٍ وَعَلَى اَلِهِ وَصَحْبِهِ اَجْمَعِيْنَ، وَالْحَمْدُ ِللهِ رَبِّ الْعَالَمِيْنَ", "Allahumma innaa nas'aluka iimaanan daaimaan, wan'asaluka qalban khaasyi'an, wanas'aluka 'ilman naafi'an, wanas'aluka yaqiinan shaadiqon, wanas'aluka 'amalan shaalihan, wanas'aluka diinan qayyiman, wanas'aluka khairan katsiran, wanas'alukal 'afwa wal'aafiyata, wanas'aluka tamaamal 'aafiyati, wanas'alukasyukra 'alal 'aafiyati, anas'alukal ghinaa'a 'aninnaasi. Allahumma rabbanaa taqabbal minnaa shalaatanaa washiyaamanaa waqiyaamanaa watakhusy-syu'anaa watadhorru'anaa wata'abbudanaa watammim taqshiiranaa yaa allaahu yaa allaahu yaa allaahu yaa arhamar raahimiin. washallallaahu 'alaa khairi khalqihi muhammadin wa'alaa aalihi washahbihi ajma'iina, walhamdu lillaahi rabbil 'aalamiina.", "Wahai Allah. Sesungguhnya kami memohon kepada-Mu iman yang tetap, kami memohon kepada-Mu hati yang khusyu', kami memohon kepada-Mu ilmu yang bermanfaat, kami memohon kepada-Mu keyakinan yang benar, kami memohon kepada-Mu amal yang shaleh, kami memohon kepada-Mu agama yang lurus, kami memohon kepada-Mu kebaikan yang banyak, kami memohon kepada-Mu ampunan dan afiat, kami memohon kepada-Mu kesehatan yang sempurna, kami memohon kepada-Mu syukur atas kesehatan, dan kami memohon kepada-Mu terkaya dari semua manusia.\" \"Wahai Allah, Tuhan kami. Terimalah dari kami shalat kami, puasa kami, shalat malam kami, kekhusyu'an kami, kerendahan hati kami, ibadah kami. Sempurnakanlah kelalaian atau kekurangan kami, Wahai Allah Wahai Allah Wahai Allah Wahai Dzat yang Paling Penyayang diantara para penyayang. Semoga rahmat Allah tercurahkan kepada sebaik-baiknya makhluk-Nya, Muhammad, keluarga dan sahabatnya semua, dan segala puji milik Allah, Tuhan semesta alam.");
        addItem("Doa Malam Lailatul Qadar", "اللَّـهُـمَّ إنَّكَ عَفُوٌّ تُـحِبُّ العَفْوَ فَاعْفُ عَنِّي", "Allahumma innaka ‘afuwwun tuhibbul ‘afwa fa’fu ‘anni’.", "Ya Allah, Engkau Maha Pengampun, menyukai orang yang minta ampunan, ampunilah aku.");
        addItem("Istighfar Saat Sahur", "الصَّابِرِينَ وَالصَّادِقِينَ وَالْقَانِتِينَ وَالْمُنْفِقِينَ وَالْمُسْتَغْفِرِينَ بِالْأَسْحَارِ", "Aṣ-ṣābirīna waṣ-ṣādiqīna wal-qānitīna wal-munfiqīna wal-mustagfirīna bil-as-ḥār.", "Merekalah orang-orang yang penyabar, jujur, tunduk, rajin berinfak dan rajin istighfar di waktu sahur.");
        addItem("Doa Dicaci Maki Saat Puasa", "إِنِّي صَائِمٌ، إِنِّي صَائِمٌ", "Inni shaa-im, inni shaa-im", "Sesungguhnya aku berpuasa, sesungguhnya aku berpuasa");
        addItem("Doa Hari Pertama Puasa", "اللَّهُمَّ اجْعَلْ صِيَامِى فِيهِ صِيَامَ الصَّائِمِينَ وَ قِيَامِى فِيهِ قِيَامَ الْقَائِمِينَ وَ نَبِّهْنِى فِيهِ عَنْ نَوْمَةِ الْغَافِلِينَ وَ هَبْ لِى جُرْمِى فِيهِ يَا إِلَهَ الْعَالَمِينَ وَ اعْفُ عَنِّى يَا عَافِيا عَنِ الْمُجْرِمِينَ", "Allhumaj'al Shiyaami Fiihi Shiyaama al-Shaaimin wa Qiyaami Fiihi Qiyaama al-Qaaimina wa Nabbihni Fiihi an Naumatil Ghafiliin wa Habli Jurmi Fiihi Yaa Ilaaha al-‘Alamiina wa'fu'anni Yaa Afian anilmujrimin.", "Ya Allah! Jadikanlah puasaku, puasa orang benar-benar berpuasa. Dan ibadah malamku ibadah orang benar-benar mengerjakan ibadah. Dan jagalah aku dari tidurnya orang yang lalai. Hapuskanlah dosaku wahai Tuhan sekalian alam. Dan ampunilah aku wahai Tuhan Maha Pengampun dari pada segala dosa.");
        addItem("Doa Hari Ke - 30 Puasa", "اَللَّهُمَّ اجْعَلْ صِيَامِيْ فِيْهِ بِالشُّكْرِ وَ الْقَبُوْلِ عَلَى مَا تَرْضَاهُ وَ يَرْضَاهُ الرَّسُوْلُ مُحْكَمَةً فُرُوْعُهُ بِالأُصُوْلِ بِحَقِّ سَيِّدِنَا مُحَمَّدٍ وَ آلِهِ الطَّاهِرِيْنَ وَ الْحَمْدُ ِللهِ رَبِّ الْعَالَمِيْنَ", "Allâhummaj’al shiyâmî fîhi bisysyukri wal qabûli ‘alâ mâ tardhâhur Wayardlâhurrasûlu muhkamatan furû’uhu bil ushuli bihaqqi sayyidinâ muhammadin wa âlihit Al-Thâhirîn wal hamdu lillahi rabbil’âlamin.", "Ya Allah, terimalah puasaku di bulan ini dengan rasa syukur. Jadikanlah puasaku ini mendatangkan keridhaan-Mu dan keridhaan para Rasul-Mu. Engkau kuatkanlah furu (cabang-cabang)-nya dan ushul (pokok-pokok)-nya. Demi kebenaran junjungan kami Muhammad saw beserta keluarganya yang suci. Segala puji bagi-Mu ya Allah,Tuhan semesta alam.");
        addItem("Doa Melihat Hilal", "اللَّهُ أَكْبَرُ اللَّهُمَّ أَهِلَّهُ عَلَيْنَا بِالْأَمْنِ وَالْإِيْمَانِ وَالسَّلَامَةِ وَالْإِسْلَامِ وَالتَّوْفِيْقِ لِمَا تُحِبُّ وَتَرْضَى رَبُّنَا وَرَبُّكَ اللَّهُ", "Allahu Akbar Allaahumma A-Hillahu ‘Alainaa Bil Amni Wal Iimaan Was Salaamati Wal Islaam Wat Taufiiqi Limaa Tuhibbu Wa Tardhoo Robbunaa Wa Robbukallaah.", "Allahu Akbar. Ya Allah, jadikanlah hilal itu bagi kami, dengan membawa keamanan dan keimanan, keselamatan dan Islam, dan membawa taufiq yang membimbing kami menuju apa yang Engkau cintai dan Engkau ridhai. Rabb kami dan Rabb kamu (wahai bulan), adalah Allah.");

        doaHajiUmrahAdapter = new DoaHajiUmrahAdapter(models);
        recyclerViewDoaRamadhan.setAdapter(doaHajiUmrahAdapter);
    }

    private void addItem(String title, String arabic, String translate, String arti) {
        models.add(new DoaHajiUmrahModel(title, arabic, translate, arti, ""));
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
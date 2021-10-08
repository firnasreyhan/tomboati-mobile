package com.tomboati.tour.view.activity.sholat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.WudhuAdapter;
import com.tomboati.tour.model.WudhuModel;

import java.util.ArrayList;
import java.util.List;

public class WudhuActivity extends AppCompatActivity {

    private RecyclerView recyclerViewWudhu;

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_wudhu);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Wudhu");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        recyclerViewWudhu = findViewById(R.id.recyclerViewWudhu);
        recyclerViewWudhu.setHasFixedSize(true);
        recyclerViewWudhu.setLayoutManager(new LinearLayoutManager(this));

        List<WudhuModel> list = new ArrayList<>();

        list.add(new WudhuModel(
                "Doa sebelum wudu",
                "Sebelum membasuh semua anggota wudu, bacalah",
                "بِسْمِ اللهِ الرَّحْمَنِ الرَّحِيْمِ. رَّبِّ أَعُوذُ بِكَ مِنْ هَمَزَاتِ الشَّيَاطِينِ. وَأَعُوْذُ بِكَ رَبِّ أَنْ يَحْضُرُوْنَ",
                "Bismillahirrahmanirrahim. Rabbi a’udzubika min hamazatisy syayatin wa a’udzubika an yahdhurun",
                "Dengan nama Allah Yang Maha Pengasih lagi Maha Penyayang. Ya Tuhanku aku berlindung kepada-Mu dari bisikan-bisikan setan. Dan aku berlindung pada-Mu dari kedatangan mereka padaku."
        ));

        list.add(new WudhuModel(
                "Doa ketika menyentuh air",
                "",
                "الحَمْدُ لِلّهِ الَّذِي جَعَلَ المَاءَ طَهُوْرًا   ",
                "Alhamdulillahiladzi ja’alal maa a thahuura",
                "Segala puji bagi Allah yang menjadikan air ini suci."
        ));

        list.add(new WudhuModel(
                "Membasuh kedua tangan",
                "Sebelum membasuh anggota wudu, cucilah kedua tangan tiga kali sambil berdoa;",
                "اللَّهُمَّ إِنِّي أّسْأَلُكَ اليُمْنَ وَالبَرَكَةَ وَأَعُوْذُ بِكَ مِنَ الشُّؤْمِ  وَالهَلَكَةِ",
                "Allahumma inni as-alukal yumna wal barakata wa a’udzubika minas syu-mi walhalakah",
                "Ya Allah aku memohon kepada-Mu keberuntungan dan keberkahan, dan aku berlindung kepada-Mu dari keburukan dan kerusakan."
        ));

        list.add(new WudhuModel(
                "Niat wudu",
                "Berniat wudu guna menghilangkan hadas kecil untuk melaksanakan salat. Jangan hilangkan niat hingga membasuh wajah. Adapun lafaz niat wudu yaitu",
                "نَوَيْتُ اْلوُضُوْءَ لِرَفْعِ اْلحَدَثِ اْلأَصْغَرِ فَرْضًا لِلّهِ تَعَالَى",
                "Nawaitul wudhu’a li raf’il hadatsil ashghari fardhan lillahi ta’ala",
                "Aku niat berwudu untuk mengangkat hadas kecil fardu karena Allah Ta’ala."
        ));

        list.add(new WudhuModel(
                "Berkumur",
                "Tampung air di kedua telapak tangan kemudian berkumurlah dengannya tiga kali. Putarkan air ke seluruh bagian mulut hingga mencapai pangkal tenggorokan dan berdoa;",
                "اللَّهُمَّ أَعِنِّيْ عَلَى تِلاَوَةِ  كِتَابِكَ وَكَثْرَةِ الذِّكْرِ لَكَ",
                "Allahumma a’inni ‘alaa tilaawati kitaabika wa katsratidz dzikri laka",
                "Ya Allah bantulah aku untuk membaca kitab-Mu dan memperbanyak zikir kepada-Mu."
        ));

        list.add(new WudhuModel(
                "",
                "Boleh pula membaca doa dengan lafal lainnya sebagaimana disebutkan Imam An-Nawawi dalam Al-Adzkar",
                "اللّهُمَّ أَسْقِنِي مِنْ حَوْضِ نَبِيِّكَ كَأْساً لاَ أَظْمَأُ بَعْدَهُ أَبَدًا",
                "Allahumma asqini min haudhi nabiyyika ka’san laa adzhma’u ba’dahu abadan",
                ""
        ));

        list.add(new WudhuModel(
                "Membasuh hidung",
                "Memasukkan air ke hidung (Istinsyaq) Ambil satu cakupan air di tangan, kemudian isap atau masukkanlah air tersebut ke dalam hidung sebanyak tiga kali, bersihkanlah kotoran yang ada di dalam hidung, juga berdoa",
                "اللَّهُمَّ أَرِحْنِيْ رَائِحَةَالجَنَّةِ وَأَنْتَ عَنِّي رَاضٍ",
                "Allahumma arihni raaihatal jannati wa anta ‘anni rhadin",
                "Ya Allah Semoga Engkau menciumkan aku wangi surga dan semoga Engkau rida kepadaku."
        ));

        list.add(new WudhuModel(
                "Membasuh wajah",
                "Basuhlah air ke seluruh wajah, dari permukaan kening hingga pojok dagu. Dari depan telinga kanan hingga depan telinga kiri. Pastikan air sampai hingga ke seluruh bagian wajah, seperti sela-sela janggut yang tipis, juga pinggir wajah yang bersampingan dengan rambut. Saat membasuh wajah bacalah doa",
                "اللَّهُمَّ بَيِّضْ وَجْهِيْ بِنُورِكَ يَوْمَ تَبْيَضُّ وُجُوْهُ أَوْلِيَائِكَ، وَلاَ تُسَوِّدْ  وَجْهِي بِظُلُمَاتِكَ يَومَ تَسْوَدُّ وُجُوهُ أَعْدَائِكَ",
                "Allahumma bayyidh wajhii binuurika yauma tabyadhu wujuuhu auliyaaika, wa laa tusawwid wajhi bi dzhulumaatika yauma taswaddu wujuuhu a’daa-ika",
                "Ya Allah putihkanlah wajahku dengan cahaya-Mu di hari memutihnya wajah para walimu. Janganlah Engkau hitamkan wajahku dengan kegelapan-Mu di hari menghitamnya wajah musuh-musuh-Mu"
        ));

        list.add(new WudhuModel(
                "Membasuh kedua tangan (Kanan)",
                "Basuhlah kedua tangan kanan dari ujung jari hingga siku sebanyak tiga kali. Ketika membasuh tangan kanan bacalah doa",
                "اللَّهُمَّ أَعْطِني كِتَابِي بِيَمِيْنِيْ وَحَاسِبْنِي حِسَابًا يَسِيْرَا",
                "Allahumma a’thini kitaabi biyamiini wa haasibnii hisaaban yasiiran",
                "Ya Allah berikanlah catatan (amal) ku melalui tangan kananku dan hisablah aku dengan hisab yang mudah."
        ));

        list.add(new WudhuModel(
                "Membasuh kedua tangan (Kiri)",
                "Ketika membasuh tangan kiri bacalah doa",
                "اللَّهُمَّ إِنَّي أَعُوذُ بِكَ أَنَ تُعْطِيَنِي كِتَابِي بِشِمَالِي أَوْ مِنْ وَرَاءِ ظَهْرِيْ",
                "Allahumma inni a’udzubika an tu’tiyani kitaabi bi syimaali au min waraa-a dzahri",
                "Ya Allah aku berlindung kepadamu dari pemberian-Mu terhadap kitabku dengan tangan kiriku atau dari belakang punggungku."
        ));

        list.add(new WudhuModel(
                "Mengusap kepala",
                "Usaplah air ke kepala dengan cara membahasi kedua tangan dengan air, kemudian tempelkan jari-jari di depan kepala dan dorong hingga sampai tengkuk. Lalu tarik laki jari-jari tersebut hingga depan kepala. Lakukan sebanyak tiga kali dan berdoa",
                "اللَّهُمَّ غَشَّنِيْ  بِرَحْمَتِكَ، وَأَنْزِلْ  عَلَيَّ مِنْ بَرَكَا تِكَ، وَأَظِلَّنِيْ تَحْتَ ظِلِّ عَرْشِكَ، يَوْمَ لا ظِلَّ إِلا ظِلَّكَ. اللَّهُمَّ حَرِّمْ شَعْرِي وَبَشَريْ عَلَى النَّارِ",
                "Allahumma ghasyini birahmatika, wa anzil ‘alayya min barakaatika, wa adzhillani tahta dzhilli ‘arsyika, yauma laa dzhilla illa dzhillaka. Allahumma harrim sya’ri wa basyari ‘alan naar",
                "Ya Allah limpahkanlah aku dengan rahmatmu, turunkanlah keberkahan-Mu kepadaku. Dan naungilah aku di bawah naungan-Mu di hari tiada naungan kecuali naungan-Mu. Ya Allah haramkanlah rambut dan kulitku dari api neraka. Haramkanlah rambutku dan kulitku dari neraka, dan naungilah aku di bawah Arys-Mu pada hari tidak ada naungan kecuali naungan-Mu."
        ));

        list.add(new WudhuModel(
                "Membasuh kedua telinga",
                "Usaplah kedua telinga dengan tangan, baik telinga bagian luar maupun dalam. Masukkanlah telunjuk ke dalam lubang telinga dan usaplah daun telinga dengan jempol. Serta berdoa",
                "اللّهُمَّ اجْعَلْنِي مِنَ الَّذِيْنَ يَسْتَمِعُوْنَ القَوْلَ فَيَتَّبِعُوْنَ أَحْسَنَهُ، اللَّهُمَّ أَسْمِعْنِي مُنَادِيَ الجَنَّةِ فِيْ الجَنَّةِ مَعَ الأ بْرارِ",
                "Allahummaj’alni minal ladzina yastami’uunal qaula fa yattabi’una ahsanah. Allahumma asmi’ni munaadiyal jannati fil jannati ma’al abraar",
                "Ya Allah jadikanlah termasuk dari orang-orang yang mendengarkan perkataan dan mengikuti yang baik darinya. Ya Allah perdengarkanlah aku panggilan surga di surga nanti bersama orang-orang saleh."
        ));

        list.add(new WudhuModel(
                "Membasuh tengkuk/leher",
                "Ketika membasuh tengkuk/ leher bacalah doa",
                "اللَّهُمَّ فُكَّ رَقَبَتِي مِنَ النَّارِ، وَأَعُوذُ بِكَ مِنَ السَلاَسِلِ وَالأَغْلاَلَ",
                "Allahumma fukka raqabati minan naar, wa a’udzubika minas salaasili wal aghlaal",
                "Ya Allah lepaskanlah leherku dari api neraka dan aku berlindung kepada-Mu dari rantai."
        ));

        list.add(new WudhuModel(
                "Membasuh kedua kaki (Kanan)",
                "Basuhlah kaki kedua kaki dengan air hingga kedua mata kaki. Bersihkan sela-sela jari kaki dengan tangan kiri, dari mulai jari kelingking kaki kanan hingga berakhir di kelingking kaki kiri. Saat membasuh kaki kanan bacalah doa",
                "اللَّهُمَّ ثَبِّتْ قَدَمَيَّ عَلَى الصَّرَاطِ المُسْتَقيمِ مَعَ أَقْدَامِ عِبَادِكَ الصَالِحِيْن",
                "Allahumma tsabbit qadamayya ‘alash shiraathil mustaqiima ma’a aqdaami ‘ibaadikas shalihin",
                "Ya Allah tetapkanlah kedua telapak kakiku di atas shiratal mustaqim (jembatan yang lurus) bersama telapak kaki hamba-hamba-Mu yang salih."
        ));

        list.add(new WudhuModel(
                "Membasuh kedua kaki (Kiri)",
                "Ketika membasuh kaki kiri bacalah doa",
                "اللَّهُمَّ إِنَّي أَعُوذُ بِكَ أَنْ تَزِلُّ قَدَمِي عَلَى الصَّرَاطِ  فِي النَّارِ يَوْمَ  تَزِلُ أَقْدَامُ المنَافِقيْنَ والمُشْرِكِيْن",
                "Allahumma inni a’udzubika an tazillu qadami ‘alas shiraathi fin naari yauma tazillu aqdaamil munaafiqiina wal musyrikiin",
                "Ya Allah aku berlindung kepada-Mu dari jatuhnya telapak kakiku di atas shirat (jembatan) ke dalam api neraka di hari jatuhnya telapak kaki orang-orang munafik dan musyrik."
        ));

        list.add(new WudhuModel(
                "Doa setelah wudu",
                "Setelah selesai membasuh semua anggota wudu, bacalah doa",
                "أَشْهَدُ أَنْ لاَ إِلَهَ إِلأَ اللهُ وَحْدَهُ لاَ شَرِيْكَ لَهُ, وَأَشْهَدُ أَنَّ مُحَمَّدَا عَبْدُهُ وَرَسُوْلهُ, سُبْحَانَكَ اللَّهُمَّ وَبِحَمْدِكَ, أَ شْهَدُ أَنْ لاَ إِلَهَ إِلأَ أَنْتَ, عَمِلْتُ سُوْءً  وَظَلَمْتُ نَفْسِيْ, أسْتَغْفِرُكَ وَأَتُوبُ إِلَيْكَ, فَاغْفِرْ لي وَتُبْ عَلَيَّ, إِنَّكَ أَنْتَ التَّوَابُ الرَّحِيْم, اللَّهُمَّ اجْعَلْنِي مِنَ التَّوَّابِيْنَ, وَاجَعَلْنِي مِنَ المُتَطَهِرِينْ, وَاجَعَلْنِي مِنْ عِبَادِكَ الصَّالِحِينْ, وَاجَعَلْنِي صَبُوراً شَكُوراً, وَاجَعَلْنِي أَذْكُرُكَ ذِكراً كَثِيراً, وَأُسَبِّحُكَ بُكرةَ وَأصِيلاَ",
                "Asyhadu an laa ilaaha illallah wahdahu laa syariika lahu wa asyhadu anna Muhammadan ‘abduhu wa rasuuluhu. Subhanaka Allahumma wa bihamdika, asyhadu an laailaaha illa anta, ‘alimta suu-an wa dzalamtu nafsi, astaghfiruka wa atuubuh ilaika, faghfirli wa tub ‘alayya. Innaka antat tawwaabur rahim. Allahummaj’alnii minat tawwaabiina waj’alni minal mutathahhiriin, waj’alni min ‘ibaadikas shaalihin, waj’alni shaburran syakuuran, waj’alni adzkuruka dzikran katsiran wa usabbihuka bukratan wa ashiila.",
                "Aku bersaksi bahwasanya tiada tuhan selain Allah Yang Satu, tiada sekutu bagi-Nya. Dan aku bersaksi bahwa Muhammad adalah hamba-Nya dan rasul-Nya. Maha suci Enggau Ya Allah dengan pujianmu. Aku bersaksi bahwasanya tiada tuhan selain Engkau. Engkau mengetahui keburukan dan kezaliman diriku. Aku memohon ampunan dan bertaubat kepada-Mu, maka maafkan dan ampunilah aku. Sesungguhnya engkau Maha Pengampun lagi Maha Penyayang. Ya Allah jadikanlah aku bagian dari orang-orang yang bertaubat dan jadikanlah aku bagian dari orang-orang yang mensucikan diri. Jadikanlah aku bagian dari hamba-hamba-Mu yang saleh, jadikanlah aku sabar dan bersyukur, dan buatlah aku senantiasa berzikir kepadamu dengan zikir yang banyak, serta bertasbih kepada-Mu setiap pagi dan petang."
        ));

        WudhuAdapter adapter = new WudhuAdapter(list);
        recyclerViewWudhu.setAdapter(adapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
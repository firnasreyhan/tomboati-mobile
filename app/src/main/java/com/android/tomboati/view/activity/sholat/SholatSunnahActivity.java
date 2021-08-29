package com.android.tomboati.view.activity.sholat;

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

public class SholatSunnahActivity extends AppCompatActivity {

    private RecyclerView recyclerViewDoaUmrah;
    private DoaHajiUmrahAdapter doaHajiUmrahAdapter;
    private List<DoaHajiUmrahModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ThemeTomboAtiGreen);
        setContentView(R.layout.activity_sholat_sunnah);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("Niat Sholat Sunnah");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        models = new ArrayList<>();

        recyclerViewDoaUmrah = findViewById(R.id.recyclerViewSholatSunnah);
        recyclerViewDoaUmrah.setHasFixedSize(true);
        recyclerViewDoaUmrah.setLayoutManager(new LinearLayoutManager(this));

        addItem("Niat Sholat Wudhu","اُصَلِّى سُنَّةً الْوُضُوءِ رَكْعَتَيْنِ لِلّٰهِ تَعَالَى","Ushollii Sunnatal Wudhuu’i Rok’ataini Lillaahi Ta’aalaa.","Aku niat shalat sunnah wudhu dua rakaat karena Allah Ta’ala.","Niat Sholat ini dikerjakan dua raka’at sebagaimana Sholat yang lain dengan ikhlash sampai salam. Semoga kita dimudahkan oleh Allah untuk melakukan Sholat wudhu setiap kita selesai wudhu.");

        addItem("Niat Sholat Tahiyatul Masjid","أُصَلِّى سُنَّةَ التَّحِيَّةَ الْمَسْجِدِ رَكْعَتَيْنِ لله تعالى","Ushallii sunnatat tahiyatal masjidi rak'ataini lillahi ta'aala","Aku niat sholat sunnah tahiyatul masjid dua rakaat sunnah karena Allah Ta’ala.","disimpulkan bahwa ketika seseorang datang terlambat ke masjid, dan khatib sudah naik mimbar dan memulia khutbah, maka kesunnahan sholat tahiyatul masjid tetap berlaku.");

        addItem("Niat Sholat Dhuha","اُصَلِّى سُنَّةَ الضَّحٰى رَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ اَدَاءً ِللهِ تَعَالَى","Ushalli Sunnatadh-dhuhaa rak'ataini lillaahi ta'aalaa.","Aku niat sholat sunat dhuha dua rakaat, karena Allah ta'ala.","Barang siapa sholat dhuha 12 rakaat, Allah akan membuatkan untuknya istana di surga. (H.R. Tarmizi dan Abu Majah).");

        addItem("Niat Sholat Rawatib Sebelum Subuh","اُصَلِّى سُنَّةَ الصُّبْحِ رَكْعَتَيْنِ قَبْلِيَّةً مُسْتَقْبِلَ الْقِبْلَةِ ِللهِ تَعَالَى","Usholli Sunnatash Subhi Rok’ataini Qobliyatan Mustaqbilal Qiblati Lillahi Ta’ala.","Aku niat mengerjakan salat sunah sebelum subuh 2 rakaat, menghadap kiblat karena Allah Ta’ala.","");

        addItem("Niat Sholat Rawatib Sebelum Dhuhur","اُصَلِّى سُنَّةً الظُّهْرِرَكْعَتَيْنِ قَبْلِيَّةً مُسْتَقْبِلَ الْقِبْلَةِ ِللهِ تَعَالَى","Ushalli Sunnatadh Dhuhri Rok’ataini Qobliyatan Mustaqbilal Qiblati Lillahi Ta’ala.","Aku niat mengerjakan salat sunah sebelum zuhur 2 rakaat, menghadap kiblat karena Allah Ta’ala.","");

        addItem("Niat Sholat Rawatib Sesudah Dhuhur","اُصَلِّى سُنَّةً الظُّهْرِرَكْعَتَيْنِ بَعْدِيَّةً مُسْتَقْبِلَ الْقِبْلَةِ ِللهِ تَعَالَى","Ushalli Sunnatadh Dhuhri Rok’ataini Ba’diyah Mustaqbilal Qiblati Lillahi Ta’ala.","Aku niat mengerjakan salat sunah sesudah zuhur 2 rakaat, menghadap kiblat karena Allah Ta’ala.","");

        addItem("Niat Sholat Rawatib Sebelum Ashar","اُصَلِّيْ سُنَّةَ الْعَصْرِ رَكْعَتَيْنِ قَبْلِيَّةً مُسْتَقْبِلَ اْلقِبْلَةِ اَدَاءً لِلَّهِ تَعَالَى","Ushalli sunnatadlashri rok’ataini qabliyatan mustaqbilal qiblati ada’an lillahi ta’ala.", "Aku niat sholat sunnah sebelum ashar dua rakaat menghadap kiblat karena Allah Ta'ala","");

        addItem("Niat Sholat Rawatib Sesudah Maghrib","اُصَلِّى سُنَّةً الْمَغْرِبِ رَكْعَتَيْنِ بَعْدِيَّةً مُسْتَقْبِلَ الْقِبْلَةِ ِللهِ تَعَالَى","Usholli Sunnatal Maghribi Rok’ataini Ba’diyah Mustaqbilal Qiblati Lillahi Ta’ala.","Aku niat mengerjakan salat sunah sesudah magrib 2 rakaat, menghadap kiblat karena Allah Ta’ala.","");

        addItem("Niat Sholat Rawatib Sesudah Isya","اُصَلِّى سُنَّةً الْعِشَاءِ رَكْعَتَيْنِ بَعْدِيَّةً مُسْتَقْبِلَ الْقِبْلَةِ ِللهِ تَعَالَى","Usholi Sunnatal Isyaa’i Rok’ataini Ba’diyatta Mustaqbilal Qiblati Lillahi Ta’ala.","Aku niat mengerjakan salat sunah sesudah Isya 2 rakaat, menghadap Kiblat karena Allah Ta’ala.","");

        addItem("Niat Sholat Tahajjud","أُصَلِّيْ سُنَّةَ التَّهَجُّدِ رَكْعَتَيْنِ لِلَّهِ تَعَالَى","Usholli sunnatat tahajudi rok’ataini lillahi ta’aala.","Aku niat sholat tahajud dua rakaat sunnah karena Allah Ta’ala","Niat Sholat tahajud merupakan sholat sunnah istimewa yang perintah dan keutamaannya disebutkan langsung dalam Al Qur’an. Tak ada sholat sunnah lain yang disebutkan dalam Al Quran sebagaimana sholat ini.");

        addItem("Niat Sholat Istikharah","أُصَلِّيْ سُنَّةَ الاِسْتِخَارَةِ رَكْعَتَيْنِ لِلَّهِ تَعَالَى","Ushollii sunnatal istikhooroti rok’ataini lillaahi ta’aala.","Aku berniat melaksanakan shalat sunnah istikharah dua rakaat karena Allah Taala","Istikharah adalah upaya memohon kepada Allah SWT agar memberikan pilihan terbaik kepada kita.");

        addItem("Niat Sholat Hajat","اُصَلِّى سُنَّةَ الْحَاجَةِ رَكْعَتَيْنِ لِلهِ تَعَالَى","Usholli sunnatal-haajati rok’atayni lillahi ta’aala.","Saya berniat sholat sunnah hajat dua roka’at karena Allah Ta’aala","Niat Sholat hajat adalah salah satu sholat sunah yang sangat dianjurkan untuk dikerjakan, terutama saat memiliki hajat atau keinginan.");

        addItem("Niat Sholat Mutlak","اُصَلِّى سُنَّةً رَكْعَتَيْنِ ِللهِ تَعَالٰى","Ushollii sunnatan rok’ataini lillaahi ta’ala.","Aku niat sholat sunnah dua rakaat karena Allah Ta’ala.","Niat Sholat ini berjumlah sepuluh rakaat dan dilakukan setelah melaksanakan sholat Isya’ dan sebelum tidur.");

        addItem("Niat Sholat Taubat","أُصَلِّي سُنَّةَ التَّوْبَةِركعتين مستقبل القبلة لله تعالى","Usholli sunnatat taubati rok'ataini mustaqbilal qiblati lillaahitaala.","Saya berniat salat sunah tobat dua raka'at dengan menghadap kiblat karena Allah SWT.","Niat Sholat sunah taubat dilakukan dua rakaat dengan setiap dua rakaat melakukan salam.");

        addItem("Niat Sholat Tasbih 2 Rakaat","أُصَلِّيْ سُنَّةَ التَسْبِيْحِ رَكْعَتَيْنِ لِلهِ تَعَالَى","Ushalli sunnat tasbīhi rak‘ataini lillāhi ta‘ālā.","Aku menyengaja sembahyang sunnah tasbih dua rakaat karena Allah SWT","");

        addItem("Niat Sholat Tasbih 4 Rakaat","أُصَلِّيْ سُنَّةَ التَسْبِيْحِ أَرْبَعَ رَكَعَاتٍ لِلهِ تَعَالَى","Ushalli sunnat tasbīhi arba‘a rak‘ātin lillāhi ta‘ālā.","Aku menyengaja salat sunah tasbih empat rakaat karena Allah Ta'ala.","");

        addItem("Niat Sholat Idul Fitri","اُصَلِّى سُنَّةً عِيْدِ الْفِطْرِ رَكْعَتَيْنِ مَأْمُوْمًا للهِ تَعَالَى","Usholli sunnatan ‘iidil fithri rok’ataini ma’muuman lillaahi ta’aalaa.","Saya niat sholat sunnah idul fitri dua raka’at sebagai ma’mum karena Allah Ta’ala","Niat Sholat idul fitri disyariatkan dikerjakan secara berjamaah. Tempatnya lebih afdhol (utama) di tanah lapang, kecuali jika ada udzur seperti hujan.");

        addItem("Niat Sholat Idul Adha","أُصَلِّيْ سُنَّةً لعِيْدِ اْلأَضْحَى رَكْعَتَيْنِ مَأْمُوْمًا لِلهِ تَعَالَى","Ushallii sunnatan liidil adha rok'ataini makmuman lillahi ta'alaa.","Aku berniat salat Iduladha dua rakaat sebagai makmum karena Allah ta'ala.","Mengingat hari raya Iduladha merupakan hari penyembelihan kurban, maka disunahkan untuk mendirikan salat Ied di awal waktu. Tujuannya, agar waktu penyembelihan kurban bisa maksimal. Salat Iduladha dapat dimulai ketika matahari terbit hingga masuk waktu salat Zuhur.");

        addItem("Niat Sholat Khusuf (Gerhana)","أُصَلِّيْ سُنَّةً لِكُسُوْفِ الشَّمْسِ مَأْمُوْمًا لِلّهِ تَعَالَى","Ushalli sunnatan-likhusuufi-syamsi makmuman lillali ta'ala","Saya berniat mengerjakan salat sunah Gerhana Matahari sebagai imam/makmum karena Allah semata","Dalam salat gerhana yang dilakukan berjemaah, seusai salat, jemaah mendengarkan khotbah salat gerhana yang isinya anjuran untuk berzikir, berdoa, beristighfar.");

        addItem("Niat Sholat Istisqa (Minta Hujan)","أُصَلِّيْ سُنَّةَ الاِسْتِسْقَاءِ رَكْعَتَيْنِ مَأْمُوْمًا لِلهِ تَعَالَى","Ushallī sunnatal istisqā’i rak‘ataini ma’mūman lillāhi ta‘ālā.","Aku menyengaja salat sunnah minta hujan dua rakaat makmum karena Allah",".Salat istisqa ini dilakukan setelah kemarau panjang yang menyebabkan kebakaran hutan, juga keringnya lahan pertanian. Salat ini dilakukan setelah pemerintah melakukan berbagai upaya untuk mengantisipasi dan mengatasi kebakaran hutan.");

        addItem("Niat Sholat Witir", "(Rakaat 3)\nاُصَلِّى سُنًّةَ الْوِتْرِ ثَلاَثَ رَكَعَاتٍ مُسْتَقْبِلَ الْقِبْلَةِ اَدَاءً مَأْمُوْمًا ِللهِ تَعَالَى\n(Rakaat 1)\nاُصَلِّى سُنًّةَ الْوِتْرِرَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ اَدَاءً مَأْمُوْمًاِللهِ تَعَالَى", "Ushallii sunnatal witri tsalaasa roka’aatin mustaqbilal qiblati adaa’an (ma’muman/imaman) lillaahi ta’alaa (Rakaat 3), Ushallii sunnatal witri rok ‘atan mustaqbilal qiblati adaa’an (ma’muman / imaman) lillaahi ta’alaa (Rakaat 1).", "Aku berniat shalat witir tiga rakaat menghadap kiblat menjadi (ma’muman/imaman) karena Allah ta’alaa (Rakaat 3), Saya niat sholat witir satu rakaat menghadap qiblat menjadi makmum karena Allah ta’alaa (Rakaat 1).", "");

        addItem("Niat Sholat Tarawih Berjamaah", "اُصَلِّى سُنَّةَ التَّرَاوِيْحِ رَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ مَأْمُوْمًا ِللهِ تَعَالَى", "Ushalli sunnatat taraawiihi rak’ataini mustaqbilal qiblati ma’muman lillahi ta’aalaa.", "Aku niat Salat Tarawih dua rakaat menghadap kiblat sebagai makmum karena Allah Ta’ala.", "");

        addItem("Niat Sholat Tarawih Sendiri", "اُصَلِّى سُنَّةَ التَّرَاوِيْحِ رَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ ِللهِ تَعَالَى", "Usholli sunnatattarowihi rok’ataini mustaqbilal qiblati lillahi ta’ala.", "Aku niat Salat Tarawih dua rakaat menghadap kiblat karena Allah Ta’ala.", "");

        addItem("Niat Sholat Tarawih Sebagai Imam", "اُصَلِّى سُنَّةَ التَّرَاوِيْحِ رَكْعَتَيْنِ مُسْتَقْبِلَ الْقِبْلَةِ إِمَامًا ِللهِ تَعَالَى", "Ushollii sunnatat-taraawiihi rok’ataini mustaqbilal qiblati imaaman lillaahi ta’alaa.", "Aku niat sholat sunnah tarawih dua raka’at menghadap kiblat sebagai imam karena Allah Ta’ala.", "");

        addItem("Niat Sholat Jenazah Laki Laki", "اُصَلِّى عَلَى هَذَاالْمَيِّتِ اَرْبَعَ تَكْبِرَاتٍ فَرْضَ كِفَايَةِ اِمَامًا| مَأْمُوْمًا ِللهِ تَعَالَى", "Usholli ‘ala hadzal mayyiti arba’a takbirotin fardho kifayatin imaman/ma’muman lillahi ta’ala.", "Saya niat salat atas jenazah ini empat kali takbir fardu kifayah, sebagai imam/makmum karena Allah Ta’ala.", "");

        addItem("Niat Sholat Jenazah Perempuan", "اُصَلِّى عَلَى هَذِهِ الْمَيِّتَةِ اَرْبَعَ تَكْبِرَاتٍ فَرْضَ كِفَايَةِ اِمَامًا| مَأْمُوْمًا ِللهِ تَعَالَى", "Usholli ‘ala hadzahihil mayyitati arba’a takbirotin fardho kifayatin imaman/ma’muman lillahi ta’ala.", "Saya niat salat atas jenazah ini empat kali takbir fardu kifayah, sebagai imam/makmum karena Allah Ta’ala.", "");

        doaHajiUmrahAdapter = new DoaHajiUmrahAdapter(models);
        recyclerViewDoaUmrah.setAdapter(doaHajiUmrahAdapter);
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
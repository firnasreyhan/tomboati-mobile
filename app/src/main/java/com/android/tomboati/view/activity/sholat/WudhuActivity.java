package com.android.tomboati.view.activity.sholat;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.pdf.PdfRenderer;
import android.os.Build;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.view.View;
import android.widget.ImageView;

import com.android.tomboati.R;
import com.android.tomboati.adapter.WudhuAdapter;
import com.android.tomboati.model.WudhuModel;
import com.android.tomboati.utils.OnSwipeTouchListener;
import com.google.android.material.button.MaterialButton;
import com.jsibbold.zoomage.ZoomageView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class WudhuActivity extends AppCompatActivity {
//    private ZoomageView imageViewContent;
//
//    private static final String FILENAME = "Wudhu.pdf";
//    private int pageIndex;
//    private PdfRenderer pdfRenderer;
//    private PdfRenderer.Page page;
//    private ParcelFileDescriptor parcelFileDescriptor;

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
                "",
                "Rukun wudhu merupakan hal yang harus dilakukan saat wudhu, jika tidak dilakukan maka menyebabkan hukum wudhu tersebut tidak sah. Berikut beberapa cara berwudhu dengan benar yang harus diterapkan tanpa ada kesalahan atau kekeliruan.",
                "",
                "",
                ""
        ));
        list.add(new WudhuModel(
                "Niat berwudhu adalah:",
                "",
                "نَوَيْتُ الْوُضُوْءَلِرَفْعِ الْحَدَثِ الْاَصْغَرِفَرْضًالِلّٰهِ تَعَالٰى",
                "Nawaitul wudhuu-a liraf’ll hadatsil ashghari fardhal lilaahi ta’aalaa",
                "Saya niat berwudhu untuk menghilangkan hadats kecil fardu karena Allah."
        ));
        list.add(new WudhuModel(
                "Mencuci kedua telapak tangan sebanyak tiga kali",
                "Dengan gerakan menyeka pada sela-sela jari telapak tangan yang dimulai dari tangan kanan kemudian tangan kiri kemudian diriringi dengan membaca doa:",
                "اَلْحَمْدُ ِللهِ الَّذِي جَعَلَ اْلمَاءَ طَهُوْرًا",
                "Allhamdulillahilaziy ja’alal ma’a tohuro.",
                "Dengan nama Allah yang Maha Pemurah Lagi Maha Penyayang. Segala Puji bagi Allah yang menjadikan air itu suci."
        ));
        list.add(new WudhuModel(
                "Berkumur",
                "Berkumur sebanyak 3 kali, dengan gerakan utuh membersihkan mulut (bahkan dari sisa-sisa makanan yang masih ada pada mulut).",
                "اللَّهُمَّ اَعِنِّي عَلَى ذِكْرِكَ وَشُكْرِكَ وَحُسْنِ عِبَادَتِكَ",
                "Allahumma aini alay dzikrika wasukrika wahusni ibadatika.",
                "Ya Allah, bantulah aku supaya aku dapat berzikir kepadaMu, dan bersyukur kepadaMu, dan perelok ibadah kepadaMu."
        ));
        list.add(new WudhuModel(
                "Membasuh hidung",
                "Membasuh lubang hidung secara menyeluruh, sebanyak 3 kali gerakan.",
                "اَللَّهُمَّ أَرِحْنِي رَائِحَة الجَـنَّةْ",
                "Allahuma arihniy roihata janat.",
                "Ya Allah, berilah aku ciuman daripada haruman bau Syurga."
        ));
        list.add(new WudhuModel(
                "Membasuh Muka",
                "Membasuh seluruh permukaan wajah dengan rata, sebanyak 3 kali gerakan memutar sekeliling wajah.",
                "اَللَّهُمَّ بَيِّضْ وَجْهِى يَوْمَ تَبْيَضُّ وُجُوْهٌ وَتَسْوَدُّ وُجُوْهٌْ",
                "Allahuma bayadh wajhi yawmatabyaht wujudhu wataswadu wujdhu.",
                "Ya Allah, putihkanlah wajahku pada hari putihnya wajah-wajah dan hitamnya wajah-wajah."
        ));
        list.add(new WudhuModel(
                "Membasuh kedua tangan",
                "Membasuh kedua tangan hingga mencapai siku, sebanyak 3 kali gerakan memutar dan menyeluruh ke permukaan tangan.",
                "",
                "",
                ""
        ));
        list.add(new WudhuModel(
                "",
                "Tangan kanan",
                "اَللَّهُمَّ اَعْطِنِى كِتاَبِى بِيَمِيْنِى وَحَاسِبْنِى حِسَاباً يَسِيْرًا",
                "Allahumma a’tini kitabiy biyamiyni wahasibni hisaban yasiyron.",
                "Ya Allah! berikanlah kepadaku kitabku dari sebelah kanan dan hitunglah amalanku dengan perhitungan yang mudah."
        ));
        list.add(new WudhuModel(
                "",
                "Tangan kiri",
                "اَللَّهُمَّ لاَ تُعْطِنِى كِتاَبِى مِنْ يَساَرِىْ وَ لاَ مِنْ وَرَاءِ ظَهْرِىْ",
                "Allahumma latu’tini kitabi minyasariy wala minwaro’i tohriy.",
                "Ya Allah! aku berlindung denganMu dari menerima kitab amalanku dari sebelah kiri atau dari sebelah belakang."
        ));
        list.add(new WudhuModel(
                "Membasuh kepala mulai dari ubun-ubun",
                "Membasuh kening hingga ujung kening (ubun-ubun) sampai sebagian kepala, sebanyak 3 kali gerakan menyeluruh.",
                "اَللَّهُمَّ حَرِّمْ شَعْرِيْ وَبَشَرِيْ عَلَى النَّارِْ",
                "Allahumma harom sa’riy wabasariy a’la nnari.",
                "Ya Allah, haramkan rambutku dan kulit kepalaku dari pada neraka."
        ));
        list.add(new WudhuModel(
                "Membasuh keduan telinga",
                "Membasuh kedua tengila baik itu bagian dalam maupun luar telinga (daun telinga) hingga menyeluruh ke bagian telinga, sebanyak 3 kali gerakan.",
                "اَللَّهُمَّ اجْعَلْنِي مِنَ الَّذِيْنَ يَسْتَمِعُوْنَ اْلقَوْلَ فَيَتَّبِعُوْنَ أَحْسَنَهُ",
                "Allahummajalni minaladziyna yastami’uwnal qowla fayatabi’uwna ahnashu.",
                "Ya Allah, jadikanlah aku termasuk orang-orang yang mendengarkan kata dan mengikuti sesuatu yang terbaik."
        ));
        list.add(new WudhuModel(
                "Mencuci kedua kaki",
                "Membasuh kedua kaki dan diusahakan menyeluruh tidak pada bagian depan saja, basuh hingga ke seluruh kaki hingga ke mata kaki.",
                "",
                "",
                ""
        ));
        list.add(new WudhuModel(
                "",
                "Kaki kanan",
                "اَللَّهُمَّ ثَبِّتْ قدَمِي عَلَى الصِّرَاطِ يَوْمَ تَزِلُّ فِيْهِاْ لاَقْدَامِ",
                "Allahumma tabbatqodamiy a’lasoroti yawmatazilu fiyhil laqdami.",
                "Yaa Allah, yaa Tuhanku,tetapkanlah tumuitku diatas titian yang lurus bersama tumit hamba-hamba-Mu yang shaleh."
        ));
        list.add(new WudhuModel(
                "",
                "Kaki kiri",
                "اَللّهمَّ اِنِّى اَنْتُجِلَ قَدَمِ عَلَى صِرَاطِ فِى النَّارْ يَوْمَ تِجِلُ اَقْدَمِ المُنَافِقِيْنْ وَ المُشْرِكِينِْ",
                "Allahuma iniyantujila qodamia’la sirotifinari yawmatijilu akdami munafikiyn wamusyrikiyni.",
                "Ya Allah yaa Tuhanku,sesungguhnya aku-berlindung kepada-Mu dari keterpelesetan tumuitku dari atas jalan neraka,pada hari dikala terpeleset tumit orang-orang kafir."
        ));
        list.add(new WudhuModel(
                "Dan setelah berwudhu selesai membaca do’a:",
                "",
                "اَشْهَدُ اَنْ لاَّ اِلَهَ اِلاَّالله وَ اَشْهَدُ اَنَّ مُحَمَّدَ الرَّسُولُ الله اَللهُمَّ جْعَلْنِى مِنَ التَّوَّبِيْنَ وَجْعَلْنِى مِنَ الْمُتَطَهِرِ يْنْ وَجْعَلْنِى مِنْ عِبَادِكَ الصَّلِحِيْنْ",
                "Asyhadu allaa ilaaha illallaah, wahdahu laa syariika lahu, wa asyhadu anna mUhammadan ‘abduhu wa Rasuuluhu. Allahumma j’alnii minat tawwabiina, waj’alnii minal mutathahiriina waj’alnii min ‘ibaadikash shalihiina.",
                "Aku bersaksi bahwa tiada Tuhan melainkan Allah, tiada sekutu baginya, dan aku bersaksi bahwa Nabi Muhammad itu hamba dan utusanNya. Ya Allah! Jadikanlah aku dari golongan orang-orang yang bertaubat dan jadikanlah aku dari golongan orang-orang yang bersuci dan jadikanlah aku bagian dari hamba-hamba-Mu yang sholeh."
        ));

        WudhuAdapter adapter = new WudhuAdapter(list);
        recyclerViewWudhu.setAdapter(adapter);

//        imageViewContent = findViewById(R.id.imageViewContent);
//
//        pageIndex = 0;
//
//        imageViewContent.setOnTouchListener(new OnSwipeTouchListener(this) {
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            public void onSwipeRight() {
//                if (page.getIndex() > 0) {
//                    showPage(page.getIndex() - 1);
//                }
//            }
//            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//            public void onSwipeLeft() {
//                if (page.getIndex() < pdfRenderer.getPageCount()) {
//                    showPage(page.getIndex() + 1);
//                }
//            }
//        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onStart() {
//        super.onStart();
//        try {
//            openRenderer(getApplicationContext());
//            showPage(pageIndex);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    @Override
//    protected void onStop() {
//        try {
//            closeRenderer();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        super.onStop();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void openRenderer(Context context) throws IOException {
//        // In this sample, we read a PDF from the assets directory.
//        File file = new File(context.getCacheDir(), FILENAME);
//        if (!file.exists()) {
//            // Since PdfRenderer cannot handle the compressed asset file directly, we copy it into
//            // the cache directory.
//            InputStream asset = context.getAssets().open(FILENAME);
//            FileOutputStream output = new FileOutputStream(file);
//            final byte[] buffer = new byte[1024];
//            int size;
//            while ((size = asset.read(buffer)) != -1) {
//                output.write(buffer, 0, size);
//            }
//            asset.close();
//            output.close();
//        }
//        parcelFileDescriptor = ParcelFileDescriptor.open(file, ParcelFileDescriptor.MODE_READ_WRITE);
//        // This is the PdfRenderer we use to render the PDF.
//        if (parcelFileDescriptor != null) {
//            pdfRenderer = new PdfRenderer(parcelFileDescriptor);
//        }
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void closeRenderer() throws IOException {
//        if (null != page) {
//            page.close();
//        }
//        pdfRenderer.close();
//        parcelFileDescriptor.close();
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void showPage(int index) {
//        if (pdfRenderer.getPageCount() <= index) {
//            return;
//        }
//        // Make sure to close the current page before opening another one.
//        if (null != page) {
//            page.close();
//        }
//        // Use `openPage` to open a specific page in PDF.
//        page = pdfRenderer.openPage(index);
//        // Important: the destination bitmap must be ARGB (not RGB).
//        Bitmap bitmap = Bitmap.createBitmap(page.getWidth(), page.getHeight(),
//                Bitmap.Config.ARGB_8888);
//        // Here, we render the page onto the Bitmap.
//        // To render a portion of the page, use the second and third parameter. Pass nulls to get
//        // the default result.
//        // Pass either RENDER_MODE_FOR_DISPLAY or RENDER_MODE_FOR_PRINT for the last parameter.
//        page.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY);
//        // We are ready to show the Bitmap to user.
//        imageViewContent.setImageBitmap(bitmap);
//        updateUi();
//    }
//
//    /**
//     * Updates the state of 2 control buttons in response to the current page index.
//     */
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    private void updateUi() {
//        int index = page.getIndex();
//        int pageCount = pdfRenderer.getPageCount();
////        materialButtonSebelumnya.setEnabled(0 != index);
////        materialButtonSelanjutnya.setEnabled(index + 1 < pageCount);
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
//    public int getPageCount() {
//        return pdfRenderer.getPageCount();
//    }
}
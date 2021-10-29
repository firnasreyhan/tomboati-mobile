package com.tomboati.tour.view.activity.doa_dzikir;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.tomboati.tour.R;
import com.tomboati.tour.adapter.TempatMustajabAdapter;
import com.tomboati.tour.databinding.ActivityTampatMustajabBinding;
import com.tomboati.tour.model.TempatMustajabModel;
import com.tomboati.tour.view.activity.base.BaseToolbarActivity;

import java.util.ArrayList;
import java.util.List;

public class TampatMustajabActivity extends BaseToolbarActivity {

    private ActivityTampatMustajabBinding bind;
    private List<TempatMustajabModel> models;

    @Override
    protected void onViewReady(Bundle savedInstanceState, Intent intent) {
        super.onViewReady(savedInstanceState, intent);
        bind = ActivityTampatMustajabBinding.inflate(getLayoutInflater());
        setToolbar(bind.toolbar, "Tempat Mustajab");
        models = new ArrayList<>();
        addItem("Multazam", "Multazam merupakan dinding yang terletak antara hajar Aswad dan pintu Kakbah. Tempat ini diyakini para ulama sebagai tempat mustajab untuk berdoa di sekitar Kabah.", "https://id.wikipedia.org/wiki/Multazam", R.drawable.msb_multazam);
        addItem("Hajar Aswad", "Barangkali setiap muslim tahu Hajar Aswad, yaitu batu yang diyakini oleh umat Islam berasal dari surga. Orang yang pertama kali menemukannya adalah Nabi Ismail dan yang meletakkannya adalah Nabi Ibrahim. Hajar Aswad dijadikan pondasi Kakbah saat Nabi Ibrahim dan Nabi Ismail mendapat perintah dari Allah.", "https://id.wikipedia.org/wiki/Hajar_Aswad", R.drawable.msb_hajar_aswad);
        addItem("Hijir Ismail", "Hijir Ismail adalah setengah lingkaran kecil di samping Ka'bah.Ditempat ini sering dipakai jamaah haji maupun umrah untuk melakukan salat sunnah karena diyakini sebagai salah satu tempat mustajab untuk berdoa.", "https://id.wikipedia.org/wiki/Hijir_Ismail", R.drawable.msb_hijr_ismail);
        addItem("Maqam Ibrahim", "Makam Ibrahim adalah tempat bekas berdirinya Nabi Ibrahim AS tatkala membangun Kakbah terbuat dari batu. Letak makam Ibrahim ini berhadapan dengan Pintu Kakbah. Kini batu tersebut disimpan dalam bangunan kristal berkrangka besi dan tertutup kaca tebal.", "https://id.wikipedia.org/wiki/Maqam_Ibrahim", R.drawable.msb_maqam_ibrahim);
        addItem("Shafa dan Marwah", "Bukit Shafa dan Marwah merupakan saksi sejarah bagi umat Islam, masih dalam sejarah Nabi Ibrahim AS dan Nabi Ismail AS. Demikian, bukit Shafa dan Marwah termasuk bagian dari bangunan Masjidil Haram dan menjadi bagian dari pelaksanaan ibadah haji dan umrah, yakni Sa'i.", "https://id.wikipedia.org/wiki/Shofa_dan_Marwah", R.drawable.msb_shafa_marwah);
        addItem("Raudhah", "Tempat mustajab untuk berdoa selanjutnya yakni di Masjid Nabawi, tepatnya di Raudhah (Taman Surga), yaitu tempat antara mimbar dan kediaman Rasulullah Muhammad SAW saat beliau hidup yang menjadi salah satu tempat istimewa bagi masyarakat muslim.", "https://news.detik.com/berita/d-4828661/keutamaan-raudhah-taman-surga-tempat-mustajabnya-doa", R.drawable.msb_raudhah);
        setRecyclerView(bind.recyclerViewTempatMustajab, new TempatMustajabAdapter(models));
    }

    @Override
    protected View getContentView() {
        return bind.getRoot();
    }

    private void addItem(String judul, String keterangan, String link, int drawable) {
        models.add(new TempatMustajabModel(judul, keterangan, link, drawable));
    }
}
package com.tomboati.tour.helper;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.tomboati.tour.R;

import java.util.ArrayList;
import java.util.List;

public class Common {

    public static void startActivity(View v, Class<?> cls) {
        v.getContext().startActivity(new Intent(v.getContext(), cls));
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }


    public static void picassoLoad(String uri, ImageView imageView) {
        Picasso.get().load(uri).priority(Picasso.Priority.HIGH).placeholder(R.drawable.ic_logo).into(imageView);
    }

    public static List<String> splitText(String text) {
        List<String> list = new ArrayList<>();
        String[] texts = text.split(">");
        for (String s : texts) {
            String sz = s + ">";
            sz = sz.replaceAll("<.*?>", "");
            sz = sz.replaceAll("&.*?;", " dan ");
            if (!sz.isEmpty()) {
                list.add(sz);
            }
        }
        return list;
    }

    public static String splitTextToString(String text) {
        StringBuilder builder = new StringBuilder();
        String[] texts = text.split(">");
        for (String s : texts) {
            String sz = s + ">";
            sz = sz.replaceAll("<.*?>", "");
            sz = sz.replaceAll("&.*?;", " dan ");
            if (!sz.isEmpty()) {
                builder.append(sz);
            }
        }
        return builder.toString();
    }

}

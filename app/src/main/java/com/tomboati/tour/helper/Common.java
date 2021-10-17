package com.tomboati.tour.helper;

import android.content.Context;
import android.content.Intent;
import android.view.View;

public class Common {

    public static void startActivity(View v, Class<?> cls) {
        v.getContext().startActivity(new Intent(v.getContext(), cls));
    }

    public static void startActivity(Context context, Intent intent) {
        context.startActivity(intent);
    }


}

package com.tomboati.tour.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class ImageSaves {

    private Context c;

    public ImageSaves(Context c) {
        this.c = c;
    }


    public File saveToPictureFromUri(Uri u) {
        Bitmap b = null;
        try {
            b = MediaStore.Images.Media.getBitmap(this.c.getContentResolver(), u);
        } catch (Exception e) {
            Log.e("Error save images : ", e.getMessage());
        }
        return saveToPictureFromBitmap(b);
    }

    public File saveToPictureFromBitmap(Bitmap b) {
        String rootPath = this.c.getExternalFilesDir(Environment.DIRECTORY_PICTURES).toString();
        String nameTimeStamp = "" + System.currentTimeMillis();
        File file = new File(rootPath, nameTimeStamp + ".png");

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Bitmap scale = scaleDown(b, true);
        scale.compress(Bitmap.CompressFormat.PNG, 100, out);
        final byte[] byteOut = out.toByteArray();

        try {
            FileOutputStream outputStream = new FileOutputStream(file);
            outputStream.write(byteOut);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            Log.e("Error save bitmap : ", e.getMessage());
        }

        return file;
    }

    public Bitmap scaleDown(Bitmap b, boolean filter) {
        final double ratio = Math.min((double) 600 / b.getWidth(), (double) 600 / b.getHeight());
        final double width = Math.round(ratio * b.getWidth());
        final double height = Math.round(ratio * b.getHeight());
        return Bitmap.createScaledBitmap(b, (int) width, (int) height, filter);
    }

}

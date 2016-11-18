package com.example.baotoan.readwritefile;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by BaoToan on 10/31/2016.
 */

public class IOUtils {
    private final static String MY_FOLDER = "/ReadWriteFile/";

    public static boolean writeImageToInternal(Bitmap image, String fileName, Context context) {
        try {
            FileOutputStream fOut = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            // Viết xuống file
            fOut.flush();
            fOut.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean writeImageToSD(Bitmap image, String fileName, Context context) {
        // Get absolute path tới thẻ SD
        String absPath = Environment.getExternalStorageDirectory().getAbsolutePath() + MY_FOLDER;

        try {
            // Xác định folder chứa
            File disDir = new File(absPath);
            if (!disDir.exists()) {
                disDir.mkdir();
            }

            // Tạo file đích
            File disFile = new File(absPath, fileName);
            if (!disFile.exists()) {
                disFile.createNewFile();
            }

            FileOutputStream fOut = new FileOutputStream(disFile);
            image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            // Viết xuống file
            fOut.flush();
            fOut.close();
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static Bitmap readImage(String fileName, Context context) {
        Bitmap image = null;
        String absPath = Environment.getExternalStorageDirectory().getAbsolutePath() + MY_FOLDER + fileName;
        // Kiểm tra thẻ nhớ có tồn tại hay không
        if(checkSD()) {
            image = BitmapFactory.decodeFile(absPath);
        }
        if(null == image) {
            try {
                FileInputStream fIn = new FileInputStream(context.getFileStreamPath(fileName));
                image = BitmapFactory.decodeStream(fIn);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        return image;
    }

    public static boolean deleteImage(String fileName, Context context) {
        context.deleteFile(fileName);

        String absPath = Environment.getExternalStorageDirectory().getAbsolutePath() + MY_FOLDER + fileName;
        File file = new File(absPath);
        file.delete();
        return true;
    }

    private static boolean checkSD() {
        String states = Environment.getExternalStorageState();
        if(states.equals(Environment.MEDIA_MOUNTED) || states.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return true;
        }
        return false;
    }
}

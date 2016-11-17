package com.example.baotoan.personaldairy.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by BaoToan on 11/4/2016.
 */

public class Utils {
    public static boolean saveImageToSDCard(Bitmap image, String folder, String fileName) {
        // Get full absolute path SD card
        String absPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folder;
        File dir = new File(absPath);
        // Check directory is exist
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            // Check file is exist
            File file = new File(absPath, fileName);
            if (!file.exists()) {
                file.createNewFile();
            }

            // Save to SD card
            BufferedOutputStream bOs = new BufferedOutputStream(new FileOutputStream(file));
            image.compress(Bitmap.CompressFormat.PNG, 100, bOs);
            bOs.flush();
            bOs.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveImageToInternalMem(Context context, Bitmap image, String fileName) {
        try {
            BufferedOutputStream bOs = new BufferedOutputStream(context.openFileOutput(fileName, Context.MODE_PRIVATE));
            image.compress(Bitmap.CompressFormat.PNG, 100, bOs);
            bOs.flush();
            bOs.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isSDReadable() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return true;
        }
        return false;
    }

    public static Bitmap getImageFromMemory(Context context, String folder, String fileName) throws FileNotFoundException {
        Bitmap image = null;
        String absExtMemPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" + folder + "/" + fileName;
        // If SD card readable then get image from it
        if (isSDReadable()) {
            image = BitmapFactory.decodeFile(absExtMemPath);
        }
        // If image not got yet, then read from internal memory
        if (null == image) {
            try {
                BufferedInputStream file = new BufferedInputStream(new FileInputStream(context.getFileStreamPath(fileName)));
                image = BitmapFactory.decodeStream(file);
            } catch (Exception e) {}
        }
        return image;
    }

    public static void setBitmatToImageView(final Context context, final String folder, final String fileName, final ImageView imageView) {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                Bitmap image = (Bitmap) msg.obj;
                if (null != image) {
                    imageView.setImageBitmap(image);
                } else {
                    imageView.setVisibility(View.GONE);
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Bitmap image = getImageFromMemory(context, folder, fileName);
                    Message message = new Message();
                    message.obj = image;
                    handler.sendMessage(message);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public static String convertDateToString(Date date) {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(date);
    }

    public static Date convertStringToDate(String date) {
        try {
            return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Date());
    }

    public static String convertDatetimeToFileName(String date) {
        return date.replaceAll(":", "").replaceAll(" ", "");
    }
}

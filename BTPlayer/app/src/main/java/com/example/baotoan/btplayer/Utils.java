package com.example.baotoan.btplayer;

import android.media.MediaMetadataRetriever;
import android.os.Environment;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;

/**
 * Created by BaoToan on 11/8/2016.
 */

public class Utils {
    public static ArrayList<Song> getListSong() {
        ArrayList<Song> songs = new ArrayList<>();
        // Access to SD card
        File folder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Zing MP3");

        // Filter file
        File[] files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".mp3") || name.endsWith(".MP3");
            }
        });
        // Add info to song list
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();

        for(int i = 0; i < files.length; i++) {
            mediaMetadataRetriever.setDataSource(files[i].getAbsolutePath());
            String artist = mediaMetadataRetriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            songs.add(new Song(i, files[i].getName(), artist, files[i].getAbsolutePath()));
        }
        return songs;
    }

    public static String milliSecondsToTimer(long milliSeconds) {
        int hours = (int) (milliSeconds / (1000 * 3600));
        int minutes = (int) (milliSeconds % (1000 * 3600)) / (1000 * 60);
        int seconds = (int) ((milliSeconds % (1000 * 3600)) % (1000 * 60) / 1000);

        StringBuilder result = new StringBuilder();
        result.append(hours > 0 ? hours + ":" : "");
        result.append((minutes < 10 ? "0" + minutes : minutes) + ":");
        result.append(seconds < 10 ? "0" + seconds : seconds);

        return result.toString();
    }

    public static int getProgressPercentage(long currentDuration, long totalDuration) {
        // Convert millisecond to second
        long currentSeconds = currentDuration / 1000;
        long totalSeconds = totalDuration / 1000;

        Double percentage = ((double)currentSeconds / totalSeconds) * 100;
        return percentage.intValue();
    }

    public static int progressToTimer(int progress, int totalDuration) {
        totalDuration = totalDuration / 1000;
        int currentDuration = (int) (((double)(progress) / 100) * totalDuration);
        // return current duration in milliseconds
        return currentDuration * 1000;
    }
}

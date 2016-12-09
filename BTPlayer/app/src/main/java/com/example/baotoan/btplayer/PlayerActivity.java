package com.example.baotoan.btplayer;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
=======
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
>>>>>>> cd54f619a4c3a0e5beff79fec1069729482661ab
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

<<<<<<< HEAD
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
=======
import org.w3c.dom.Text;

import java.io.IOException;
>>>>>>> cd54f619a4c3a0e5beff79fec1069729482661ab
import java.util.ArrayList;

public class PlayerActivity extends AppCompatActivity implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, MediaPlayer.OnCompletionListener {
    private Toolbar toolbarPlayer;
    private TextView lblTitle;
    private TextView lblArtist;
    private TextView lblCurDuration;
    private TextView lblDuration;
    private SeekBar seekDuration;
    private ImageView btnPlay;
    private ImageView btnBackward;
    private ImageView btnDescrease;
    private ImageView btnIncrease;
    private ImageView btnNext;

    private ArrayList<Song> songs = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    private int currentSongIndex;

    private final int REQUEST_CODE_GET_CURRENT_SONG = 1;
    private final int SEEK_MOVE_SPEED = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        mediaPlayer = new MediaPlayer();
        songs = Utils.getListSong();

        // Set toolbar
        toolbarPlayer = (Toolbar) findViewById(R.id.tool_bar_player);
        toolbarPlayer.setNavigationIcon(R.drawable.brand);
        toolbarPlayer.setTitle("BTPlayer");
        toolbarPlayer.setSubtitle("25 minutes");
        setSupportActionBar(toolbarPlayer);

        // Mapping components
        lblTitle = (TextView) findViewById(R.id.lbl_title);
        lblArtist = (TextView) findViewById(R.id.lbl_artist);
        lblCurDuration = (TextView) findViewById(R.id.lbl_cr_duration);
        lblDuration = (TextView) findViewById(R.id.lbl_duration);
        seekDuration = (SeekBar) findViewById(R.id.seek_duration);
        btnPlay = (ImageView) findViewById(R.id.btn_play);
        btnBackward = (ImageView) findViewById(R.id.btn_backward);
        btnDescrease = (ImageView) findViewById(R.id.btn_descrease);
        btnIncrease = (ImageView) findViewById(R.id.btn_increase);
        btnNext = (ImageView) findViewById(R.id.btn_next);

        // Setup events
        btnPlay.setOnClickListener(this);
        btnBackward.setOnClickListener(this);
        btnDescrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        btnNext.setOnClickListener(this);

        seekDuration.setOnSeekBarChangeListener(this);
        mediaPlayer.setOnCompletionListener(this);
<<<<<<< HEAD

//        new ReadFromInternet().execute("http://mp3.zing.vn/album/Shake-It-Single-Diep-Lam-Anh-Mr-T/ZOU7W86W.html");

=======
>>>>>>> cd54f619a4c3a0e5beff79fec1069729482661ab
        play();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.toolbar_player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.playlist:
                Intent intent = new Intent(this, SongListActivity.class);
                startActivityForResult(intent, REQUEST_CODE_GET_CURRENT_SONG);
                break;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_play:
                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                    // update play button
                    btnPlay.setImageResource(R.drawable.play);
                } else {
                    mediaPlayer.start();
                    // update play button
                    btnPlay.setImageResource(R.drawable.pause);
                }
                break;
            case R.id.btn_backward:
                if(currentSongIndex > 0) {
                    currentSongIndex--;
                    play();
                }
                break;
            case R.id.btn_next:
                if(currentSongIndex < songs.size() - 1) {
                    currentSongIndex++;
                    play();
                }
                break;
            case R.id.btn_descrease:
                if(mediaPlayer.getCurrentPosition() - SEEK_MOVE_SPEED >= 0) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() - SEEK_MOVE_SPEED);
                }
                break;
            case R.id.btn_increase:
                if(mediaPlayer.getCurrentPosition() + SEEK_MOVE_SPEED <= mediaPlayer.getDuration()) {
                    mediaPlayer.seekTo(mediaPlayer.getCurrentPosition() + SEEK_MOVE_SPEED);
                }
                break;
        }
    }

    private void play() {
        if (songs.size() > 0) {
            try {
                Song song = songs.get(currentSongIndex);

                // Play music
                mediaPlayer.reset();
<<<<<<< HEAD
                mediaPlayer.setDataSource(song.getPath());
=======
                mediaPlayer.setDataSource("http://zmp3-mp3-s1.zmp3-bdhcm-1.za.zdn.vn/ca048e0086446f1a3655/7719081858336067360?key=7pYmwsNFFwynxdgE5Dvrug&expires=1479327235");
>>>>>>> cd54f619a4c3a0e5beff79fec1069729482661ab
                mediaPlayer.prepare();
                mediaPlayer.start();

                btnPlay.setImageResource(R.drawable.pause);

                // Set info view
                lblTitle.setText(song.getTitle());
                lblArtist.setText(song.getArtist());
                toolbarPlayer.setTitle(song.getTitle());
                toolbarPlayer.setSubtitle(song.getArtist());

                // Start progress
                seekDuration.setMax(100);
                seekDuration.setProgress(0);

                // Update seek duration
                updateProgress();

                // Notify
                buildNotification();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateProgress() {
        handler.postDelayed(updateTimeTask, 1000);
    }

    private Runnable updateTimeTask = new Runnable() {
        @Override
        public void run() {
            // Get time task from media player
            long duration = mediaPlayer.getDuration();
            long currentDuration = mediaPlayer.getCurrentPosition();

            // Update time label
            lblCurDuration.setText(Utils.milliSecondsToTimer(currentDuration));
            lblDuration.setText(Utils.milliSecondsToTimer(duration));

            // Update progress
            seekDuration.setProgress(Utils.getProgressPercentage(currentDuration, duration));

            // repeat
            handler.postDelayed(this, 1000);
        }
    };

    private void buildNotification() {

        Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivities(getApplicationContext(), 1, new Intent[] {intent}, 0);
        Song song = songs.get(currentSongIndex);
        Notification.Builder builder = new Notification.Builder(this)
                .setContentTitle(song.getTitle())
                .setContentText(song.getArtist())
                .setSmallIcon(R.drawable.small_icon)
                .setDeleteIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(100, builder.build());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE_GET_CURRENT_SONG) {
            Bundle bundle = data.getExtras();
            currentSongIndex = bundle.getInt("SONG_INDEX");
            play();
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        handler.removeCallbacks(updateTimeTask);
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        mediaPlayer.seekTo(Utils.progressToTimer(seekBar.getProgress(), mediaPlayer.getDuration()));
        updateProgress();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        btnPlay.setImageResource(R.drawable.play);
        if(currentSongIndex < songs.size() - 1) {
            currentSongIndex++;
        } else {
            currentSongIndex = 0;
        }
        play();
    }
<<<<<<< HEAD

    class ReadFromInternet extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... params) {
            try {
                URL url = new URL(params[0]);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String line = "";
                while((line = bufferedReader.readLine()) != null) {
                    int start = 0;
                    if(line.contains("album-xml/")) {
                        start = line.indexOf("album-xml/");
                        return "http://mp3.zing.vn/xml/album-xml/" + line.substring(start + 10, start + 34);
                    } else if(line.contains("song-xml/")) {
                        start = line.indexOf("song-xml/");
                        return "http://mp3.zing.vn/html5xml/song-xml/" + line.substring(start + 9, start + 33);
                    }
                }
                return null;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.i("Link", s);
        }
    }
=======
>>>>>>> cd54f619a4c3a0e5beff79fec1069729482661ab
}

package com.example.baotoan.btplayer;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SongListActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView lstSongs;
    private Toolbar toolbarListSong;

    public static ArrayList<Song> songs = Utils.getListSong();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_list);

        toolbarListSong = (Toolbar) findViewById(R.id.toolbar_list_song);
        lstSongs = (ListView) findViewById(R.id.lst_songs);

        // Set toolbar
        toolbarListSong.setTitle("List songs");
        toolbarListSong.setNavigationIcon(R.drawable.back);
        setSupportActionBar(toolbarListSong);

        SongListAdapter songListAdapter = new SongListAdapter(this, songs);
        lstSongs.setAdapter(songListAdapter);
        lstSongs.setOnItemClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent();
        intent.putExtra("SONG_INDEX", position);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}

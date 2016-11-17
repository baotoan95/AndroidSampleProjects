package com.example.baotoan.personaldairy.views;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.baotoan.personaldairy.R;
import com.example.baotoan.personaldairy.controllers.NoteDataSource;
import com.example.baotoan.personaldairy.controllers.NoteListAdapter;
import com.example.baotoan.personaldairy.models.NoteModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private Toolbar toolBar;
    private ListView lstDiary;

    private NoteDataSource noteDataSource;

    private ArrayList<NoteModel> notes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteDataSource = new NoteDataSource(this);
        noteDataSource.open();

        toolBar = (Toolbar) findViewById(R.id.index_tool_bar);
        toolBar.setTitle("PersonalDairy");
        toolBar.setSubtitle("BTIT95");

        setSupportActionBar(toolBar);

        lstDiary = (ListView) findViewById(R.id.lst_diary);
        lstDiary.setOnItemClickListener(this);
        updateListView();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noteDataSource.close();
    }

    @Override
    protected void onResume() {
        super.onResume();
        updateListView();
    }

    public void updateListView() {
        final Handler handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                NoteListAdapter noteListAdapter = new NoteListAdapter(MainActivity.this, notes);
                lstDiary.setAdapter(noteListAdapter);
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                notes = noteDataSource.getAllNotes();
                handler.sendEmptyMessage(0);
            }
        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.index_tool_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_index_add:
                Intent intent = new Intent(this, WriteNoteActivity.class);
                intent.putExtra("ACTION", "ADD");
                startActivity(intent);
                break;
            case R.id.mn_index_settings:
                ;
                break;
        }
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, ViewActivity.class);
        NoteModel note = notes.get(position);
        intent.putExtra("TITLE", note.getTitle());
        intent.putExtra("CONTENT", note.getContent());
        intent.putExtra("STATUS", note.getStatus());
        intent.putExtra("IMAGES", note.getImages());
        intent.putExtra("ID", note.getId());
        intent.putExtra("DATETIME", note.getDatetime());
        startActivity(intent);
    }
}

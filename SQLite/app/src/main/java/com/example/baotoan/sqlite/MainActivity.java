package com.example.baotoan.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText edtNote;
    private Button btnAdd;
    private ListView lviewNotes;
    private TextView lblAlert;

    private NoteListViewAdapter noteListViewAdapter;
    private NoteDataSource noteDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtNote = (EditText) findViewById(R.id.edt_note);
        btnAdd = (Button) findViewById(R.id.btn_add);
        lblAlert = (TextView) findViewById(R.id.lbl_alert);
        lviewNotes = (ListView) findViewById(R.id.lview_notes);

        // registry listener
        btnAdd.setOnClickListener(this);

        noteDataSource = new NoteDataSource(this);
        noteDataSource.open();

        updateListNote();
    }

    public void updateListNote() {
        ArrayList<NoteModel> arrNotes = noteDataSource.getAll();
        if(null != arrNotes && arrNotes.size() > 0) {
            noteListViewAdapter = new NoteListViewAdapter(this, arrNotes);
            lviewNotes.setAdapter(noteListViewAdapter);

            // show list view and hide alert
            lviewNotes.setVisibility(View.VISIBLE);
            lblAlert.setVisibility(View.INVISIBLE);
        } else {
            // No note to show
            lviewNotes.setVisibility(View.INVISIBLE);
            lblAlert.setVisibility(View.VISIBLE);
        }
    }

    public void deleteNote(NoteModel note) {
        noteDataSource.deleteNote(note.getId());
        updateListNote();
        Toast.makeText(this, "Task complete", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noteDataSource.close();
    }

    @Override
    public void onClick(View v) {
        if(v == btnAdd) {
            String note = edtNote.getText().toString().trim();
            if (note.length() > 0) {
                // add note
                noteDataSource.addNewNote(note);
                // update list view
                updateListNote();
            } else {
                // User is not typed
                Toast.makeText(this, "Input your note before", Toast.LENGTH_LONG).show();
            }

            edtNote.setText("");
        }
    }
}

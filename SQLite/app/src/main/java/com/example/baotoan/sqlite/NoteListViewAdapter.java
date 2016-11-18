package com.example.baotoan.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BaoToan on 11/1/2016.
 */

public class NoteListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NoteModel> arrNotes;

    public NoteListViewAdapter(Context context, ArrayList<NoteModel> arrNotes) {
        this.context = context;
        this.arrNotes = arrNotes;
    }

    @Override
    public int getCount() {
        return arrNotes.size();
    }

    @Override
    public Object getItem(int position) {
        return arrNotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rowView = layoutInflater.inflate(R.layout.note_item, parent, false);
        TextView lblNote = (TextView) rowView.findViewById(R.id.lbl_note);
        TextView lblDate = (TextView) rowView.findViewById(R.id.lbl_date);
        ImageView btnDel = (ImageView) rowView.findViewById(R.id.btn_del);

        NoteModel note = arrNotes.get(position);
        btnDel.setTag(note);
        lblNote.setText(note.getNote());
        lblDate.setText(note.getDatetime());
        btnDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)context).deleteNote((NoteModel) v.getTag());
            }
        });

        return rowView;
    }
}

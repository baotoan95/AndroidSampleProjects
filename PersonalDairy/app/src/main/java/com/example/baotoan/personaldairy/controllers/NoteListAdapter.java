package com.example.baotoan.personaldairy.controllers;

import android.content.Context;
import android.graphics.Color;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.baotoan.personaldairy.R;
import com.example.baotoan.personaldairy.models.NoteModel;
import com.example.baotoan.personaldairy.utils.Config;
import com.example.baotoan.personaldairy.utils.Utils;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by BaoToan on 11/3/2016.
 */

public class NoteListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<NoteModel> notes;

    public NoteListAdapter(Context context, ArrayList<NoteModel> notes) {
        this.context = context;
        this.notes = notes;
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Object getItem(int position) {
        return notes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return notes.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rowView = layoutInflater.inflate(R.layout.diary_item, parent, false);
        LinearLayout lyStatus = (LinearLayout) rowView.findViewById(R.id.ly_status);

        TextView lblWeekDays = (TextView) rowView.findViewById(R.id.lbl_weekdays);
        TextView lblDate = (TextView) rowView.findViewById(R.id.lbl_date);
        TextView lblTime = (TextView) rowView.findViewById(R.id.lbl_time);
        TextView lblTitle = (TextView) rowView.findViewById(R.id.lbl_title);
        TextView lblDetails = (TextView) rowView.findViewById(R.id.lbl_details);
        ImageView imgMem = (ImageView) rowView.findViewById(R.id.img_mem);

        NoteModel note = notes.get(position);
        lyStatus.setBackgroundColor(Color.parseColor(note.getStatus()));
        Date date = Utils.convertStringToDate(note.getDatetime());
        lblWeekDays.setText((String) DateFormat.format("EEEE", date));
        lblDate.setText((String) DateFormat.format("dd", date) + " " + DateFormat.format("MMM", date));
        lblTime.setText((String) DateFormat.format("hh:mm", date));
        try {
            Utils.setBitmatToImageView(context, Config.IMAGE_FOLDER, note.getImages().split(",")[0], imgMem);
        } catch (Exception e) {
            imgMem.setVisibility(View.GONE);
        }

        lblTitle.setText(note.getTitle());
        lblDetails.setText((note.getContent().length() > 30 ? (note.getContent().substring(0, 30) + "...") : note.getContent()));

        return rowView;
    }
}

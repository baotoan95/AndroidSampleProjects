package com.example.baotoan.btplayer;

import android.content.Context;
import android.media.MediaMetadataRetriever;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BaoToan on 11/8/2016.
 */

public class SongListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Song> songs;

    public SongListAdapter(Context context, ArrayList<Song> songs) {
        this.context = context;
        this.songs = songs;
    }

    @Override
    public int getCount() {
        return songs.size();
    }

    @Override
    public Object getItem(int position) {
        return songs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (null == row) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            row = layoutInflater.inflate(R.layout.song_item, parent, false);
        }
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.lblName = (TextView) row.findViewById(R.id.lbl_name);
        viewHolder.lblAuthor = (TextView) row.findViewById(R.id.lbl_author);
        row.setTag(viewHolder);

        ViewHolder holder = (ViewHolder) row.getTag();
        Song song = songs.get(position);
        holder.lblName.setText(song.getTitle());
        holder.lblAuthor.setText(song.getArtist());
        return row;
    }

    class ViewHolder {
        TextView lblName;
        TextView lblAuthor;
    }
}

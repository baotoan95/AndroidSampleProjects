package com.example.baotoan.englishstories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BaoToan on 10/29/2016.
 */

public class LessonAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<LessonItem> lessons;

    public LessonAdapter(Context context, ArrayList<LessonItem> lessons) {
        this.context = context;
        this.lessons = lessons;
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public Object getItem(int position) {
        return lessons.get(position);
    }

    @Override
    public long getItemId(int position) {
        return lessons.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View rowView = convertView;
        if(null == rowView) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            rowView = layoutInflater.inflate(R.layout.lesson_item, parent, false);
        }

        ViewHolder viewHolder = new ViewHolder();
        viewHolder.icon = (ImageView) rowView.findViewById(R.id.icon);
        viewHolder.lblName = (TextView) rowView.findViewById(R.id.lbl_name);
        rowView.setTag(viewHolder);

        LessonItem lessonItem = lessons.get(position);
        ViewHolder holder = (ViewHolder) rowView.getTag();
        holder.icon.setImageResource(lessonItem.getIcon());
        holder.lblName.setText(lessonItem.getName());

        return rowView;
    }

    class ViewHolder {
        ImageView icon;
        TextView lblName;
    }
}

package com.confidence.btit95.confidencesecretbeta.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.confidence.btit95.confidencesecretbeta.R;
import com.confidence.btit95.confidencesecretbeta.entities.SpinnerImageItem;

import java.util.List;

/**
 * Created by baotoan on 02/07/2017.
 */

public class SpinnerImageAdapter extends ArrayAdapter<SpinnerImageItem> {
    private int groupId;
    private LayoutInflater layoutInflater;
    private List<SpinnerImageItem> spinners;

    public SpinnerImageAdapter(Context context, int resource, List<SpinnerImageItem> spinners) {
        super(context, resource, spinners);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.spinners = spinners;
        this.groupId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = layoutInflater.inflate(groupId, parent, false);

        ImageView imageView =  (ImageView) itemView.findViewById(R.id.spinner_avatar);
        imageView.setImageResource(spinners.get(position).getImage());
        return itemView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}

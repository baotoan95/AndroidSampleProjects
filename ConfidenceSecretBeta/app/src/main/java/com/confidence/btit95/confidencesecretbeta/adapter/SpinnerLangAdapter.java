package com.confidence.btit95.confidencesecretbeta.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.confidence.btit95.confidencesecretbeta.R;
import com.confidence.btit95.confidencesecretbeta.entities.SpinnerLangItem;

import java.util.List;

/**
 * Created by baotoan on 02/07/2017.
 */

public class SpinnerLangAdapter extends ArrayAdapter<SpinnerLangItem> {
    private int groupId;
    private LayoutInflater layoutInflater;
    private List<SpinnerLangItem> spinners;

    public SpinnerLangAdapter(Context context, int resource, List<SpinnerLangItem> spinners) {
        super(context, resource, spinners);
        this.layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.spinners = spinners;
        this.groupId = resource;
    }

    public int getItemIndexByCountryCode(String countryCode) {
        for(int i = 0; i < spinners.size(); i++) {
            if(spinners.get(i).getCode().equalsIgnoreCase(countryCode)) {
                return i;
            }
        }
        return 0;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = layoutInflater.inflate(groupId, parent, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.item_lang_avatar);
        imageView.setImageResource(spinners.get(position).getImage());

        TextView txtTitle = (TextView) itemView.findViewById(R.id.item_lang_title);
        txtTitle.setText(spinners.get(position).getName());

        return itemView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}

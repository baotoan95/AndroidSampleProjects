package com.example.baotoan.wikicountry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by BaoToan on 10/31/2016.
 */

public class CountryListViewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<CountryModel> countries;

    public CountryListViewAdapter(Context context, ArrayList<CountryModel> countries) {
        this.context = context;
        this.countries = countries;
    }

    @Override
    public int getCount() {
        return countries.size();
    }

    @Override
    public Object getItem(int position) {
        return countries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View rowView = layoutInflater.inflate(R.layout.country_item, parent, false);

        ImageView flag = (ImageView) rowView.findViewById(R.id.icon_country);
        TextView lblName = (TextView) rowView.findViewById(R.id.lbl_country_name);

        CountryModel country = countries.get(position);
        // set image
        String urlFlag = "http://www.geognos.com/api/en/countries/flag/" + country.getIso2Code() + ".png";
        Utils.setBitmapToImage(urlFlag, flag);
        // set country name
        lblName.setText(country.getName());

        return rowView;
    }
}

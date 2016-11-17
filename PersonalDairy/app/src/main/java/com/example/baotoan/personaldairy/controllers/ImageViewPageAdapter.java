package com.example.baotoan.personaldairy.controllers;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.baotoan.personaldairy.R;
import com.example.baotoan.personaldairy.utils.Config;
import com.example.baotoan.personaldairy.utils.Utils;

import java.util.ArrayList;

/**
 * Created by BaoToan on 11/5/2016.
 */

public class ImageViewPageAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<String> images;

    public ImageViewPageAdapter(Context context, ArrayList<String> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View page = layoutInflater.inflate(R.layout.image_view_page, container, false);
        ImageView imgPage = (ImageView) page.findViewById(R.id.img_view_page);
        Utils.setBitmatToImageView(context, Config.IMAGE_FOLDER, images.get(position), imgPage);

        container.addView(page);
        return page;
    }
}

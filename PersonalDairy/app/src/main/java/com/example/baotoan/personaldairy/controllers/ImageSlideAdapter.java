package com.example.baotoan.personaldairy.controllers;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.baotoan.personaldairy.R;

import java.util.ArrayList;

/**
 * Created by BaoToan on 11/4/2016.
 */

public class ImageSlideAdapter extends PagerAdapter {
    private Context context;
    private ArrayList<Bitmap> images;

    public ImageSlideAdapter(Context context, ArrayList<Bitmap> images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public int getCount() {
        return (images.size() / 3) + ((images.size() % 3) > 0 ? 1 : 0);
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
        View pageView = layoutInflater.inflate(R.layout.image_slide, container, false);
        ImageView imgOne = (ImageView) pageView.findViewById(R.id.img_one);
        ImageView imgTwo = (ImageView) pageView.findViewById(R.id.img_two);
        ImageView imgThree = (ImageView) pageView.findViewById(R.id.img_three);

        int firstImage = position * 3;
        try {
            imgOne.setImageBitmap(images.get(firstImage));
            imgTwo.setImageBitmap(images.get(++firstImage));
            imgThree.setImageBitmap(images.get(++firstImage));
        } catch (Exception e) {}
        container.addView(pageView);

        return pageView;
    }
}

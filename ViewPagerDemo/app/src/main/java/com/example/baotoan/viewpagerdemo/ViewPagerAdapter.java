package com.example.baotoan.viewpagerdemo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by BaoToan on 10/29/2016.
 */

public class ViewPagerAdapter extends PagerAdapter {
    private Context context;
    private String[] colors;

    public ViewPagerAdapter(Context context, String[] colors) {
        this.context = context;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    /*
    * True: nếu đối tượng đã tồn tại
    * Giống như convertView của ListView
    * Nó sẽ lưu đối tượng đã tồn tại trước đó để sử dụng lại
    * */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        // Remove view khi đã trượt ra khỏi màn hình
        container.removeView((View)object);
    }

    /*
    * Gần giống với getView của ListView
    * */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.viewpager_item, container, false);
        view.setBackgroundColor(Color.parseColor(colors[position]));
        container.addView(view);
        return view;
    }
}

package com.example.baotoan.englishstories;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import java.util.ArrayList;

/**
 * Created by BaoToan on 10/30/2016.
 */

public class LessonViewPager extends PagerAdapter {
    private Context context;
    private ArrayList<LessonItem> lessons;

    public LessonViewPager(Context context, ArrayList<LessonItem> lessons) {
        this.context = context;
        this.lessons = lessons;
    }

    @Override
    public int getCount() {
        return lessons.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.lesson_detail, container, false);

        WebView webView = (WebView) view.findViewById(R.id.web_view);
        String url = "file:///android_asset/" + lessons.get(position).getUrl();

        webView.loadUrl(url);

        // show zoom toolbar
        webView.getSettings().setBuiltInZoomControls(true);

        container.addView(view);

        return view;
    }
}

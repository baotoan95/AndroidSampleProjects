package com.confidence.btit95.confidencesecretbeta.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.confidence.btit95.confidencesecretbeta.R;
import com.confidence.btit95.confidencesecretbeta.entities.DiaryEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by baotoan on 01/07/2017.
 */

public class DiaryViewPagerAdapter extends PagerAdapter {
    private Context context;
    private LinkedHashMap<String, List<DiaryEntity>> pages = new LinkedHashMap<>();
    private LayoutInflater layoutInflater;

    public DiaryViewPagerAdapter(Context context) {
        this.context = context;

        List<DiaryEntity> diaries2 = new ArrayList<>();
        diaries2.add(new DiaryEntity(0, "Title 7", "Toi voi nang", new Date()));
        diaries2.add(new DiaryEntity(0, "Title 8", "Toi voi nang", new Date()));
        diaries2.add(new DiaryEntity(0, "Title 9", "Toi voi nang", new Date()));
        diaries2.add(new DiaryEntity(0, "Title 10", "Toi voi nang", new Date()));
        diaries2.add(new DiaryEntity(0, "Title 11", "Toi voi nang", new Date()));
        diaries2.add(new DiaryEntity(0, "Title 12", "Toi voi nang", new Date()));
        pages.put("Confidences", diaries2);

        List<DiaryEntity> diaries = new ArrayList<>();
        diaries.add(new DiaryEntity(0, "Title 1", "Toi voi nang", new Date()));
        diaries.add(new DiaryEntity(0, "Title 2", "Toi voi nang", new Date()));
        diaries.add(new DiaryEntity(0, "Title 3", "Toi voi nang", new Date()));
        diaries.add(new DiaryEntity(0, "Title 4", "Toi voi nang", new Date()));
        diaries.add(new DiaryEntity(0, "Title 5", "Toi voi nang", new Date()));
        diaries.add(new DiaryEntity(0, "Title 6", "Toi voi nang", new Date()));
        pages.put("Diaries", diaries);

        layoutInflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return pages.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return (String) pages.keySet().toArray()[position];
    }

    private List<DiaryEntity> getElementByIndex(LinkedHashMap<String, List<DiaryEntity>> map, int index){
        return (List<DiaryEntity>) map.get( (map.keySet().toArray())[ index ] );
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.i("Position", String.valueOf(position));
        View view = layoutInflater.inflate(R.layout.list, container, false);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.diaries_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(new DiaryAdapter(recyclerView.getContext(), getElementByIndex(pages, position)));

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}



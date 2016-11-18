package com.example.baotoan.viewpagerdemo;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private ViewPagerAdapter viewPagerAdapter;

    private String[] colors = {"#ffffff", "#ff0000", "#d0d0d0"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPagerAdapter = new ViewPagerAdapter(this, colors);
        viewPager.setAdapter(viewPagerAdapter);
        // Chọn item hiển thị đầu tiên
        viewPager.setCurrentItem(1);
    }
}

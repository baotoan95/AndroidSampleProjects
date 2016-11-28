package com.example.baotoan.englishstories;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class LessonDetailActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private LessonViewPager lessonViewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_detail);

        viewPager = (ViewPager) findViewById(R.id.view);
        lessonViewPager = new LessonViewPager(this, MainActivity.lessons);

        viewPager.setAdapter(lessonViewPager);

        Bundle bundle = getIntent().getExtras();
        viewPager.setCurrentItem(bundle.getInt("INDEX"));
    }
}

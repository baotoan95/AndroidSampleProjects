package com.example.baotoan.englishstories;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, TextWatcher {
    private ListView listView;
    private LessonAdapter lessonAdapter;
    private EditText edtSearch;

    private final int NORMAL_MODE = 0;
    private final int SEARCH_MODE = 1;
    private int currentMode = NORMAL_MODE;

    public static ArrayList<LessonItem> lessons = new ArrayList<>();
    private ArrayList<LessonItem> searchResult = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtSearch = (EditText) findViewById(R.id.et_search);
        edtSearch.addTextChangedListener(this);
        listView = (ListView) findViewById(R.id.list_view);

        initData();

        lessonAdapter = new LessonAdapter(this, lessons);
        listView.setAdapter(lessonAdapter);
        listView.setOnItemClickListener(this);

    }

    private void initData() {
        lessons.add(new LessonItem(0, "The Winepress", R.drawable.flag, "story1.html"));
        lessons.add(new LessonItem(1, "The Chapel", R.drawable.flag, "story2.html"));
        lessons.add(new LessonItem(2, "The Metro", R.drawable.flag, "story3.html"));
        lessons.add(new LessonItem(3, "A Coward", R.drawable.flag, "story2.html"));
        lessons.add(new LessonItem(4, "A Dark Brown Dog", R.drawable.flag, "story1.html"));
        lessons.add(new LessonItem(5, "An Affair of State", R.drawable.flag, "story2.html"));
        lessons.add(new LessonItem(6, "A Haunted House", R.drawable.flag, "story3.html"));
        lessons.add(new LessonItem(7, "The Monkey's Paw", R.drawable.flag, "story2.html"));
        lessons.add(new LessonItem(8, "Araby", R.drawable.flag, "story1.html"));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, LessonDetailActivity.class);
        intent.putExtra("INDEX", currentMode == NORMAL_MODE ? lessons.get(position).getId() : searchResult.get(position).getId());
        startActivity(intent);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(edtSearch.getText().toString().trim().length() > 0) {
            searchResult.clear();
            for(LessonItem lessonItem : lessons) {
                if(lessonItem.getName().toLowerCase().trim().contains(s.toString().toLowerCase().trim())) {
                    searchResult.add(lessonItem);
                }
            }
            lessonAdapter = new LessonAdapter(this, searchResult);
            listView.setAdapter(lessonAdapter);
            currentMode = SEARCH_MODE;
        } else {
            lessonAdapter = new LessonAdapter(this, lessons);
            listView.setAdapter(lessonAdapter);
            currentMode = NORMAL_MODE;
        }
    }
}

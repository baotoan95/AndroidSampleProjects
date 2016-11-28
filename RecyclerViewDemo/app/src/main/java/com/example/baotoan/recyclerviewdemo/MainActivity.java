package com.example.baotoan.recyclerviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewPersons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewPersons = (RecyclerView) findViewById(R.id.rcv_persons);

        List<Person> persons = new ArrayList<>();
        persons.add(new Person("Toan", true));
        persons.add(new Person("Long", true));
        persons.add(new Person("Tham", false));
        persons.add(new Person("Quang", true));
        persons.add(new Person("Sen", false));
        persons.add(new Person("Ha", false));

        recyclerViewPersons.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerViewPersons.setHasFixedSize(true);
        recyclerViewPersons.setAdapter(new RecyclerAdapter(this, persons));

    }
}

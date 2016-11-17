package com.example.baotoan.wikicountry;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener, AbsListView.OnScrollListener {
    private ListView listViewCountries;
    private ProgressBar prgBar;

    private CountryListViewAdapter countryListViewAdapter;
    public static ArrayList<CountryModel> countries = new ArrayList<>();

    private int currentPage = 1;
    private final int TOTAL_PAGE = 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listViewCountries = (ListView) findViewById(R.id.lv_countries);
        prgBar = (ProgressBar) findViewById(R.id.prg_bar);
        listViewCountries.setOnItemClickListener(this);

        initData();

    }

    Handler networkHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            String content = msg.obj.toString();

            // hide progress bar
            prgBar.setVisibility(View.GONE);

            // parse data and put all to countries
            countries.addAll(parseData(content));

            if(null == countryListViewAdapter) {
                countryListViewAdapter = new CountryListViewAdapter(MainActivity.this, countries);
                listViewCountries.setAdapter(countryListViewAdapter);
            } else {
                countryListViewAdapter.notifyDataSetChanged();
            }

            listViewCountries.setOnScrollListener(MainActivity.this);
        }
    };

    private ArrayList<CountryModel> parseData(String content) {
        ArrayList<CountryModel> arrCountries = new ArrayList<>();

        try {
            JSONArray root = new JSONArray(content);
            JSONObject dataInfo = (JSONObject) root.get(0);

            JSONArray data = (JSONArray) root.get(1);
            for (int i = 0; i < data.length(); i++) {
                JSONObject country = (JSONObject) data.get(i);
                CountryModel countryModel = new CountryModel();
                countryModel.setId(country.getString("id"));
                countryModel.setName(country.getString("name"));
                countryModel.setRegion(country.getJSONObject("region").getString("value"));
                countryModel.setAdminRegion(country.getJSONObject("adminregion").getString("value"));
                countryModel.setCapitalCity(country.getString("capitalCity"));
                countryModel.setLongitude(country.getString("longitude"));
                countryModel.setLatitude(country.getString("latitude"));
                countryModel.setIso2Code(country.getString("iso2Code"));
                arrCountries.add(countryModel);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return arrCountries;
    }

    private void initData() {
        // show progress bar
        prgBar.setVisibility(View.VISIBLE);

        // get data for internet
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = "http://api.worldbank.org/countries?format=json&per_page=25&page=" + currentPage;
                String result = Utils.getDataFormUrl(url);
                Message message = new Message();
                message.obj = result;
                networkHandler.sendMessage(message);
            }
        }).start();
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        if(view.getId() == listViewCountries.getId()) {
            int lastItem = firstVisibleItem + visibleItemCount;
            if(lastItem >= totalItemCount) {
                this.listViewCountries.setOnScrollListener(null);
                if(currentPage < totalItemCount) {
                    currentPage++;
                    initData();
                }
            }
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, CountryDetailActivity.class);
        intent.putExtra("INDEX", position);
        startActivity(intent);
    }
}

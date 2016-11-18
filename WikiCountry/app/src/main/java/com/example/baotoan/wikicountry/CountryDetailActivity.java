package com.example.baotoan.wikicountry;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class CountryDetailActivity extends Activity {
    private CountryModel countryModel;

    private ImageView imgFlag;
    private TextView lblDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_detail);

        imgFlag = (ImageView) findViewById(R.id.img_flag);
        lblDetails = (TextView) findViewById(R.id.lbl_details);

        Bundle bundle = getIntent().getExtras();
        int position = bundle.getInt("INDEX");

        CountryModel country = MainActivity.countries.get(position);

        String url = "http://www.geognos.com/api/en/countries/flag/" + country.getIso2Code() + ".png";
        Utils.setBitmapToImage(url, imgFlag);

        // show info
        StringBuilder info = new StringBuilder();
        info.append("Country name: " + country.getName() + "\n");
        info.append("Region: " + country.getRegion() + "\n");
        info.append("Admin region: " + country.getAdminRegion() + "\n");
        info.append("ISO: " + country.getIso2Code() + "\n");

        lblDetails.setText(info.toString());
    }

}

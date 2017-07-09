package com.confidence.btit95.confidencesecretbeta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;

import com.confidence.btit95.confidencesecretbeta.adapter.SpinnerLangAdapter;
import com.confidence.btit95.confidencesecretbeta.entities.SpinnerLangItem;
import com.confidence.btit95.confidencesecretbeta.security.AppSecurity;
import com.github.orangegangsters.lollipin.lib.managers.AppLock;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private boolean isNewly = true;
    private static final int REQUEST_CODE_ENABLE = 11;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private Intent intent;

    private CheckBox cbEnableLock;
    private CheckBox cbEncryptCnt;
    private Spinner langSpinner;

    private SpinnerLangAdapter spinnerLangAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getSupportActionBar().setTitle(R.string.st_settings);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sharedPreferences = getSharedPreferences("ConfidenceSecret", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        cbEnableLock = (CheckBox) findViewById(R.id.cb_enable_lock);
        cbEncryptCnt = (CheckBox) findViewById(R.id.cb_encrypt_content);
        langSpinner = (Spinner) findViewById(R.id.spinner_lang);

        // Init data for components
        cbEnableLock.setChecked(AppSecurity.SECURITY_ENABLED);

        // Setting listener
        cbEnableLock.setOnClickListener(this);
        cbEnableLock.setOnClickListener(this);

        // Set item for spinner lang
        List<SpinnerLangItem> langs = new ArrayList<>();
        langs.add(new SpinnerLangItem("Việt Nam", "vi", R.drawable.flag_vietnam));
        langs.add(new SpinnerLangItem("English", "en", R.drawable.flag_united_kingdom));
        spinnerLangAdapter = new SpinnerLangAdapter(this, R.layout.spinner_lang_item, langs);
        langSpinner.setAdapter(spinnerLangAdapter);
        int indexOfLangs = spinnerLangAdapter.getItemIndexByCountryCode(sharedPreferences.getString("lang", "en"));
        langSpinner.setSelection(indexOfLangs);
        langSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onClick(View view) {
        intent = new Intent(SettingsActivity.this, ConSecretPinActivity.class);
        int viewId = view.getId();

        if (viewId == cbEnableLock.getId()) {
            if (cbEnableLock.isChecked()) {
                AppSecurity.setEnableLock(true);
                intent.putExtra(AppLock.EXTRA_TYPE, AppLock.ENABLE_PINLOCK);
                startActivityForResult(intent, REQUEST_CODE_ENABLE);
            } else {
                AppSecurity.setEnableLock(false);
                Toast.makeText(this, "Lock security disabled", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ENABLE) {
            Toast.makeText(this, "Enabled Lock", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String countryCode = sharedPreferences.getString("lang", "en");
        SpinnerLangItem item = (SpinnerLangItem) adapterView.getSelectedItem();

        if(!item.getCode().equalsIgnoreCase(countryCode)) {
            editor.putString("lang", item.getCode());
            editor.commit();
            setLocale(item.getCode());
        }
    }

    private void setLocale(String countryCode) {
        Locale locale = new Locale(countryCode);
        Configuration configuration = getResources().getConfiguration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());
        Intent intent = getIntent();
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        startActivity(intent);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

package com.confidence.btit95.confidencesecretbeta;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.confidence.btit95.confidencesecretbeta.adapter.DiaryViewPagerAdapter;
import com.confidence.btit95.confidencesecretbeta.security.AppSecurity;
import com.confidence.btit95.confidencesecretbeta.shared.AppConstant;
import com.github.orangegangsters.lollipin.lib.PinCompatActivity;
import com.github.orangegangsters.lollipin.lib.managers.AppLock;

import java.util.Locale;

import hotchemi.android.rate.AppRate;
import hotchemi.android.rate.OnClickButtonListener;

@SuppressWarnings("deprecation")
public class MainActivity extends PinCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Set language
        SharedPreferences sharedPreferences = getSharedPreferences("ConfidenceSecret", Context.MODE_PRIVATE);
        Locale locale = new Locale(sharedPreferences.getString("lang", "en"));
        Configuration configuration = getResources().getConfiguration();
        configuration.locale = locale;
        getResources().updateConfiguration(configuration, getResources().getDisplayMetrics());

        if(AppSecurity.SECURITY_ENABLED) {
            Intent intent = new Intent(MainActivity.this, ConSecretPinActivity.class);
            intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
            startActivity(intent);
        }

        // Rating dialog
        AppRate.with(this)
                .setInstallDays(0) // default 10, 0 means install day.
                .setLaunchTimes(3) // default 10
                .setRemindInterval(2) // default 1
                .setShowLaterButton(true) // default true
                .setDebug(false) // default false
                .setOnClickButtonListener(new OnClickButtonListener() { // callback listener.
                    @Override
                    public void onClickButton(int which) {
                        Log.d(MainActivity.class.getName(), Integer.toString(which));
                    }
                })
                .monitor();
        // Show a dialog if meets conditions
        AppRate.showRateDialogIfMeetsConditions(this);

        // Set action bar
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.getBackground().setAlpha(70);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        // Set menu drawer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        // Add listener for menu drawer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Init content
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        DiaryViewPagerAdapter diaryViewPagerAdapter = new DiaryViewPagerAdapter(this);
        viewPager.setAdapter(diaryViewPagerAdapter);

        tabLayout.setTabsFromPagerAdapter(diaryViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent editDiaryIntent = new Intent(MainActivity.this, EditDiaryActivity.class);
            editDiaryIntent.putExtra("ACTION", EditDiaryActivity.ADD_ACTION);
            startActivity(editDiaryIntent);
        }

        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_notification) {
            // Handle the camera action
        } else if (id == R.id.nav_profile) {

        } else if (id == R.id.nav_sync_cloud) {

        } else if (id == R.id.nav_settings) {
            Intent settingIntent = new Intent(MainActivity.this, SettingsActivity.class);
            startActivity(settingIntent);
        } else if (id == R.id.nav_share) {
            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, AppConstant.SHARE_SUBJECT);
            shareIntent.putExtra(android.content.Intent.EXTRA_TEXT, AppConstant.SHARE_BODY);
            startActivity(Intent.createChooser(shareIntent, AppConstant.SHARE_BOX_TITLE));
        } else if (id == R.id.nav_rating) {
            AppRate.with(this).showRateDialog(this);
        } else if (id == R.id.nav_feedback) {
            String[] to = {"baotoan.95@gmail.com"};
            String subject = "Report&Feedback";
            Intent emailIntent = new Intent(Intent.ACTION_SEND);
            emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
            emailIntent.setType("message/rfc822");
            startActivity(Intent.createChooser(emailIntent, "Report & Feedback"));
        } else if (id == R.id.nav_about) {
            startActivity(new Intent(this, AboutActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}

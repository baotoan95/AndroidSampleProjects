package com.confidence.btit95.confidencesecretbeta;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.confidence.btit95.confidencesecretbeta.adapter.SpinnerImageAdapter;
import com.confidence.btit95.confidencesecretbeta.components.NoteEditor;
import com.confidence.btit95.confidencesecretbeta.entities.SpinnerImageItem;
import com.confidence.btit95.confidencesecretbeta.utils.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by baotoan on 01/07/2017.
 */

public class EditDiaryActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    public static final String ADD_ACTION = "ADD";
    public static final String UPDATE_ACTION = "UPDATE";
    private final int PICK_IMAGE_REQUEST = 5;

    private int selectedDay, selectedMonth, selectedYear, selectedHour, selectedMinute;

    private Toolbar toolbar;
    private Spinner weatherSpinner;
    private Spinner moodSpinner;
    private ImageButton btnDelete;
    private ImageButton btnLocation;
    private ImageButton btnPicture;
    private NoteEditor noteEditor;
    private RelativeLayout calenderLayout;
    private TextView txtMonthYear;
    private TextView txtDayOfMonth;
    private TextView txtWeekdayTime;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private Intent transferIntent;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary);

        toolbar = (Toolbar) findViewById(R.id.diary_detail_toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_36dp);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Setting title
        transferIntent = getIntent();
        String action = transferIntent.getStringExtra("ACTION");
        if (EditDiaryActivity.ADD_ACTION.equals(action)) {
            getSupportActionBar().setTitle("New Diary");
        } else if (EditDiaryActivity.UPDATE_ACTION.equals(action)) {
            getSupportActionBar().setTitle("Update Diary");
        }

        weatherSpinner = (Spinner) findViewById(R.id.weather_spinner);
        List<SpinnerImageItem> weatherItems = new ArrayList<>();
        weatherItems.add(new SpinnerImageItem("Snow", R.drawable.ic_add_black_18dp));
        weatherItems.add(new SpinnerImageItem("Snow", R.drawable.ic_arrow_back_black_18dp));
        weatherItems.add(new SpinnerImageItem("Snow", R.drawable.ic_cloud_upload_black_18dp));
        weatherItems.add(new SpinnerImageItem("Snow", R.drawable.ic_share_black_18dp));
        ArrayAdapter weatherAdapter = new SpinnerImageAdapter(this, R.layout.spinner_image_item, weatherItems);
        weatherSpinner.setAdapter(weatherAdapter);

        moodSpinner = (Spinner) findViewById(R.id.mood_spinner);
        List<SpinnerImageItem> moodItems = new ArrayList<>();
        moodItems.add(new SpinnerImageItem("Snow", R.drawable.ic_add_black_18dp));
        moodItems.add(new SpinnerImageItem("Snow", R.drawable.ic_arrow_back_black_18dp));
        moodItems.add(new SpinnerImageItem("Snow", R.drawable.ic_cloud_upload_black_18dp));
        moodItems.add(new SpinnerImageItem("Snow", R.drawable.ic_share_black_18dp));
        ArrayAdapter moodAdapter = new SpinnerImageAdapter(this, R.layout.spinner_image_item, moodItems);
        moodSpinner.setAdapter(moodAdapter);

        btnDelete = (ImageButton) findViewById(R.id.btn_trash);
        btnLocation = (ImageButton) findViewById(R.id.btn_location);
        btnPicture = (ImageButton) findViewById(R.id.btn_picture);

        btnDelete.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnPicture.setOnClickListener(this);

        noteEditor = (NoteEditor) findViewById(R.id.diary_content);

        // Add action listener for get date
        calenderLayout = (RelativeLayout) findViewById(R.id.calendar);
        calenderLayout.setOnClickListener(this);

        txtDayOfMonth = (TextView) findViewById(R.id.day_of_month);
        txtMonthYear = (TextView) findViewById(R.id.month_year);
        txtWeekdayTime = (TextView) findViewById(R.id.weekday_time);

        // Init data for calender
        Calendar calendar = Calendar.getInstance();
        selectedDay = calendar.get(Calendar.DAY_OF_MONTH);
        selectedMonth = calendar.get(Calendar.MONTH);
        selectedYear = calendar.get(Calendar.YEAR);
        selectedHour = calendar.get(Calendar.HOUR_OF_DAY);
        selectedMinute = calendar.get(Calendar.MINUTE);

        txtMonthYear.setText(DateUtils.getMonthAsString(selectedMonth) + " | " + selectedYear);
        txtDayOfMonth.setText(String.valueOf(selectedDay));
        String weekday = DateUtils.getWeekdayAsString(DateUtils.getWeekday(selectedYear, selectedMonth + 1, selectedDay));
        String time = DateUtils.formatTime24Hours(selectedHour, selectedMinute);
        txtWeekdayTime.setText(weekday + " " + time);


        // Dialogs
        datePickerDialog = new DatePickerDialog(this, this, selectedYear, selectedMonth, selectedDay);
        timePickerDialog = new TimePickerDialog(this, this, selectedHour, selectedMinute, true);
    }



    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == btnDelete.getId()) {
            Toast.makeText(this, "Delete event", Toast.LENGTH_SHORT).show();
        } else if (viewId == btnPicture.getId()) {
            if (Build.VERSION.SDK_INT < 19) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
            } else {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("image/*");
                intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                startActivityForResult(intent, PICK_IMAGE_REQUEST);
            }
        } else if (viewId == btnLocation.getId()) {
            LocationListener locationListener = new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {

                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };


            LocationManager mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

            try {
                mLocationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 32, 2, locationListener);
            } catch (SecurityException e) {
                Toast.makeText(this, "You must be allow enable GSP to use this action", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(this, "Location event", Toast.LENGTH_SHORT).show();
        } else if(viewId == calenderLayout.getId()) {
            datePickerDialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST) {
            Toast.makeText(this, "Picked an image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.diary_detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.action_edit:
                Toast.makeText(this, "Edit", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        // Reset date store
        selectedYear = year;
        selectedMonth = month + 1;
        selectedDay = day;

        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker timePicker, int hour, int minute) {
        selectedHour = hour;
        selectedMinute = minute;

        // Update calendar in UI
        txtMonthYear.setText(DateUtils.getMonthAsString(selectedMonth) + " " + selectedYear);
        txtDayOfMonth.setText(String.valueOf(selectedDay));

        String weekday = DateUtils.getWeekdayAsString(DateUtils.getWeekday(selectedYear, selectedMonth, selectedDay));
        String time = DateUtils.formatTime24Hours(selectedHour, selectedMinute);
        txtWeekdayTime.setText(weekday + " " + time);
    }
}

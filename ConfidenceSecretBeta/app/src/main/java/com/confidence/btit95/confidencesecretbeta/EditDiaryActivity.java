package com.confidence.btit95.confidencesecretbeta;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.confidence.btit95.confidencesecretbeta.adapter.SpinnerImageAdapter;
import com.confidence.btit95.confidencesecretbeta.components.NoteEditor;
import com.confidence.btit95.confidencesecretbeta.entities.SpinnerImageItem;
import com.confidence.btit95.confidencesecretbeta.utils.DateUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static android.R.attr.value;

/**
 * Created by baotoan on 01/07/2017.
 */

public class EditDiaryActivity extends AppCompatActivity implements View.OnClickListener,
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener, AdapterView.OnItemSelectedListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    public static final String ADD_ACTION = "ADD";
    public static final String UPDATE_ACTION = "UPDATE";
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 1000;

    private final int TAKE_PIC = 1;
    private final int PICK_IMAGE_REQUEST = 2;
    private final int PLACE_PICKER = 3;

    private int selectedDay, selectedMonth, selectedYear, selectedHour, selectedMinute;

    private Toolbar toolbar;
    private Spinner weatherSpinner;
    private Spinner moodSpinner;
    private ImageButton btnDelete;
    private ImageButton btnLocation;
    private ImageButton btnTakePic;
    private ImageButton btnGallery;
    private NoteEditor noteEditor;
    private RelativeLayout calenderLayout;
    private TextView txtMonthYear;
    private TextView txtDayOfMonth;
    private TextView txtWeekdayTime;
    private TextView txtAddress;
    private TextView txtWeather;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private ImageView icWeather;

    private GoogleApiClient googleApiClient;
    private Location location;

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
        Map<String, Integer> cloudIcons = new HashMap<String, Integer>();
        cloudIcons.put("ic_18", R.drawable.ic_clouds_18_white);
        cloudIcons.put("ic_24", R.drawable.ic_clouds_24_black);
        weatherItems.add(new SpinnerImageItem("Clouds", cloudIcons, "WEATHER"));
        Map<String, Integer> moonIcons = new HashMap<String, Integer>();
        moonIcons.put("ic_18", R.drawable.ic_partly_cloudy_filled_18_white);
        moonIcons.put("ic_24", R.drawable.ic_partly_cloudy_filled_24_black);
        weatherItems.add(new SpinnerImageItem("Moon", moonIcons, "WEATHER"));
        Map<String, Integer> partlyIcons = new HashMap<String, Integer>();
        partlyIcons.put("ic_18", R.drawable.ic_moon_stars_18_white);
        partlyIcons.put("ic_24", R.drawable.ic_moon_stars_24_black);
        weatherItems.add(new SpinnerImageItem("Partly Cloudy Filled", partlyIcons, "WEATHER"));
        Map<String, Integer> rainIcons = new HashMap<String, Integer>();
        rainIcons.put("ic_18", R.drawable.ic_rain_filled_18_white);
        rainIcons.put("ic_24", R.drawable.ic_rain_filled_24_black);
        weatherItems.add(new SpinnerImageItem("Rain filled", rainIcons, "WEATHER"));
        Map<String, Integer> snowIcons = new HashMap<String, Integer>();
        snowIcons.put("ic_18", R.drawable.ic_snow_18_white);
        snowIcons.put("ic_24", R.drawable.ic_snow_24_black);
        weatherItems.add(new SpinnerImageItem("Snow", snowIcons, "WEATHER"));
        Map<String, Integer> springIcons = new HashMap<String, Integer>();
        springIcons.put("ic_18", R.drawable.ic_spring_18_white);
        springIcons.put("ic_24", R.drawable.ic_spring_24_black);
        weatherItems.add(new SpinnerImageItem("Spring", springIcons, "WEATHER"));
        Map<String, Integer> stormIcons = new HashMap<String, Integer>();
        stormIcons.put("ic_18", R.drawable.ic_storm_18_white);
        stormIcons.put("ic_24", R.drawable.ic_storm_24_black);
        weatherItems.add(new SpinnerImageItem("Storm", stormIcons, "WEATHER"));
        Map<String, Integer> sunIcons = new HashMap<String, Integer>();
        sunIcons.put("ic_18", R.drawable.ic_sun_18_white);
        sunIcons.put("ic_24", R.drawable.ic_sun_24_black);
        weatherItems.add(new SpinnerImageItem("Sun", sunIcons, "WEATHER"));
        Map<String, Integer> windyIcons = new HashMap<String, Integer>();
        windyIcons.put("ic_18", R.drawable.ic_windy_weather_18_white);
        windyIcons.put("ic_24", R.drawable.ic_windy_weather_24_black);
        weatherItems.add(new SpinnerImageItem("Windy", windyIcons, "WEATHER"));
        ArrayAdapter weatherAdapter = new SpinnerImageAdapter(this, R.layout.spinner_image_item, weatherItems);
        weatherSpinner.setAdapter(weatherAdapter);
        weatherSpinner.setOnItemSelectedListener(this);

        moodSpinner = (Spinner) findViewById(R.id.mood_spinner);
        List<SpinnerImageItem> moodItems = new ArrayList<>();
        Map<String, Integer> angryIcons = new HashMap<String, Integer>();
        angryIcons.put("ic_18", R.drawable.ic_add_black_18dp);
        angryIcons.put("ic_24", R.drawable.ic_add_black_18dp);
        moodItems.add(new SpinnerImageItem("Snow", angryIcons, "MOOD"));
        Map<String, Integer> funnyIcons = new HashMap<String, Integer>();
        funnyIcons.put("ic_18", R.drawable.ic_arrow_back_black_18dp);
        funnyIcons.put("ic_24", R.drawable.ic_arrow_back_black_18dp);
        moodItems.add(new SpinnerImageItem("Snow", funnyIcons, "MOOD"));
        ArrayAdapter moodAdapter = new SpinnerImageAdapter(this, R.layout.spinner_image_item, moodItems);
        moodSpinner.setAdapter(moodAdapter);

        btnDelete = (ImageButton) findViewById(R.id.btn_trash);
        btnLocation = (ImageButton) findViewById(R.id.btn_location);
        btnTakePic = (ImageButton) findViewById(R.id.btn_take_pic);
        btnGallery = (ImageButton) findViewById(R.id.btn_gallery);

        btnDelete.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnTakePic.setOnClickListener(this);
        btnGallery.setOnClickListener(this);

        noteEditor = (NoteEditor) findViewById(R.id.tf_diary_content);

        // Add action listener for get date
        calenderLayout = (RelativeLayout) findViewById(R.id.calendar);
        calenderLayout.setOnClickListener(this);

        txtDayOfMonth = (TextView) findViewById(R.id.txt_day_of_month);
        txtMonthYear = (TextView) findViewById(R.id.txt_month_year);
        txtWeekdayTime = (TextView) findViewById(R.id.txt_weekday_time);

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

        txtAddress = (TextView) findViewById(R.id.txt_address);
        txtWeather = (TextView) findViewById(R.id.txt_weather);
        icWeather = (ImageView) findViewById(R.id.ic_weather);

        // Set current location
        if(checkPlayServices()) {
            buildGoogleApiClient();
        }
    }

    protected synchronized void buildGoogleApiClient() {
        googleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if (viewId == btnDelete.getId()) {
            Toast.makeText(this, "Delete event", Toast.LENGTH_SHORT).show();
        } else if (viewId == btnGallery.getId()) {
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
        } else if(viewId == btnTakePic.getId()) {
            Intent takePicIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            takePicIntent.putExtra(MediaStore.EXTRA_OUTPUT, value);
            takePicIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
            startActivityForResult(takePicIntent, TAKE_PIC);
        } else if (viewId == btnLocation.getId()) {
            PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
            try {
                Intent intent = intentBuilder.build(this);
                startActivityForResult(intent, PLACE_PICKER);
            } catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
                Toast.makeText(this, "Sorry!\nYour device does not support this future", Toast.LENGTH_SHORT).show();
            }
        } else if(viewId == calenderLayout.getId()) {
            datePickerDialog.show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_CANCELED) {
            return;
        }

        // Result OK
        if (requestCode == PICK_IMAGE_REQUEST) {
            Toast.makeText(this, "Picked an image", Toast.LENGTH_SHORT).show();
            Uri imageUri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(imageUri);
                Drawable image = Drawable.createFromStream(inputStream, imageUri.toString());
                noteEditor.setCompoundDrawables(null, null, image, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (requestCode == PLACE_PICKER) {
            Place place = PlacePicker.getPlace(data, this);
            String address = place.getAddress().toString();
            txtAddress.setText(address.length() < 50 ? address : place.getName());
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Object selectedItem = adapterView.getSelectedItem();

        if(selectedItem instanceof SpinnerImageItem) {
            SpinnerImageItem spinnerImageItem = (SpinnerImageItem) selectedItem;
            String type = spinnerImageItem.getType();
            if(type.equalsIgnoreCase("WEATHER")) {
                txtWeather.setText(spinnerImageItem.getName());
                icWeather.setImageResource(spinnerImageItem.getImages().get("ic_18"));
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private boolean checkPlayServices() {
        int resultCode = GooglePlayServicesUtil
                .isGooglePlayServicesAvailable(this);
        if (resultCode != ConnectionResult.SUCCESS) {
            if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
                GooglePlayServicesUtil.getErrorDialog(resultCode, this,
                        PLAY_SERVICES_RESOLUTION_REQUEST).show();
            } else {
                Toast.makeText(getApplicationContext(),
                        "This device is not supported.", Toast.LENGTH_LONG)
                        .show();
                finish();
            }
            return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (googleApiClient != null) {
            googleApiClient.connect();
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "The application need have permission to detect current your locale", Toast.LENGTH_SHORT).show();
            return;
        }
        location = LocationServices.FusedLocationApi.getLastLocation(googleApiClient);
        if(location != null) {
            double latitude = location.getLatitude();
            double longitude = location.getLongitude();
            Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
            List<Address> addresses;
            try {
                addresses = geocoder.getFromLocation(latitude, longitude, 1);
                if (addresses != null) {
                    if (addresses.size() > 0) {
                        String cityName = addresses.get(0).getLocality();
                        txtAddress.setText(cityName);
                    }
                }
            } catch (IOException e) {
                Toast.makeText(this, "Fail when detect locale", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Can't detect your locale", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Can't connect to service to detect locale", Toast.LENGTH_SHORT).show();
    }
}

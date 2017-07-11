package com.confidence.btit95.confidencesecretbeta;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.confidence.btit95.confidencesecretbeta.entities.UserProfile;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener,
        GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private ImageButton userProfilePhoto;
    private TextView userProfileName;
    private TextView userProfileBio;
    private TextView userProfileEmail;
    private TextView userProfilePhone;
    private ImageButton btnLogout;
    private ImageButton btnSync;

    private GoogleApiClient googleApiClient;

    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.st_profile);

        FacebookSdk.sdkInitialize(this);

        initComponents();

        Intent profileData = getIntent();
        userProfile = (UserProfile) profileData.getSerializableExtra("profile");

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        googleApiClient.connect();

        initDataView();
    }

    private void initComponents() {
        userProfilePhoto = (ImageButton) findViewById(R.id.user_profile_photo);
        userProfileName = (TextView) findViewById(R.id.user_profile_name);
        userProfileBio = (TextView) findViewById(R.id.user_profile_short_bio);
        userProfileEmail = (TextView) findViewById(R.id.user_profile_email);
        userProfilePhone = (TextView) findViewById(R.id.user_profile_phone);
        btnLogout = (ImageButton) findViewById(R.id.btn_logout);
        btnLogout.setOnClickListener(this);
        btnSync = (ImageButton) findViewById(R.id.btn_sync);
        btnSync.setOnClickListener(this);
    }

    private void initDataView() {
        userProfileName.setText(userProfile.getFullName());
        userProfileEmail.setText("Email: " + userProfile.getEmail());
        Picasso.with(getApplicationContext()).load(userProfile.getAvatar()).into(userProfilePhoto);
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected()) {
            googleApiClient.disconnect();
        }
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if(viewId == btnLogout.getId()) {
            // Logout facebook
            LoginManager.getInstance().logOut();
            // Logout google plus
            if(googleApiClient.isConnected()) {
                Auth.GoogleSignInApi.signOut(googleApiClient);
            }

            // Return to login activity
            Intent loginIntent = new Intent(this, LoginActivity.class);
            loginIntent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            startActivity(loginIntent);
        } else if(viewId == btnSync.getId()) {
            startActivity(new Intent(this, SyncActivity.class));
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        googleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}

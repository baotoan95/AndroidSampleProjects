package com.confidence.btit95.confidencesecretbeta;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.confidence.btit95.confidencesecretbeta.entities.UserProfile;
import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.LoggingBehavior;
import com.facebook.Profile;
import com.facebook.ProfileTracker;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.OptionalPendingResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LoginActivity extends AppCompatActivity implements
        GoogleApiClient.OnConnectionFailedListener, View.OnClickListener, GoogleApiClient.ConnectionCallbacks {
    public static final int PROFILE_REQUEST = 1;
    public static final int SYNC_REQUEST = 2;

    private final int GOOGLE_SIGN_IN = 3;

    public static enum LOGIN_TYPE {
        FACEBOOK, GOOGLE, CS_ACCOUNT
    }

    // Login with facebook
    private LoginButton btnFbLogin;
    private CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;

    // Login with google plus
    private SignInButton btnGpLogin;
    private GoogleSignInOptions googleSignInOptions;
    private static GoogleApiClient googleApiClient;

    private int request;

    private UserProfile userProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Login");

        // Init facebook authentication
        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);

        // Init google plus authentication
        googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        LoginActivity.googleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this /* FragmentActivity */, this /* OnConnectionFailedListener */)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build();
        //LoginActivity.googleApiClient.connect();
        LoginActivity.googleApiClient.registerConnectionCallbacks(this);
        LoginActivity.googleApiClient.registerConnectionFailedListener(this);
        callbackManager = CallbackManager.Factory.create();
        accessTokenTracker = new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldAccessToken, AccessToken currentAccessToken) {
                checkFacebookLogged(currentAccessToken);
            }
        };
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(Profile oldProfile, Profile currentProfile) {

            }
        };
        accessTokenTracker.startTracking();
        profileTracker.startTracking();

        // Get request
        Intent intentRequest = getIntent();
        request = intentRequest.getIntExtra("REQUEST", PROFILE_REQUEST);

        // Check authenticated
        if(checkFacebookLogged(AccessToken.getCurrentAccessToken())) {
            return;
        }
        if(checkGoogleLogged(LoginActivity.googleApiClient)) {
            startNextActivity();
            return;
        }

        setContentView(R.layout.activity_login);

        initComponents();
    }

    private void initComponents() {
        btnFbLogin = (LoginButton) findViewById(R.id.btn_fb_login);
        btnFbLogin.setReadPermissions(Arrays.asList(
                "public_profile", "email"));

        // Google plus login
        btnGpLogin = (SignInButton) findViewById(R.id.btn_gp_button);
        btnGpLogin.setSize(SignInButton.SIZE_STANDARD);
        btnGpLogin.setScopes(googleSignInOptions.getScopeArray());
        btnGpLogin.setOnClickListener(this);

    }

    private void loginFacebook() {
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setMessage("Please wait...");
                progressDialog.show();

                checkFacebookLogged(loginResult.getAccessToken());
            }

            @Override
            public void onCancel() {
                Toast.makeText(LoginActivity.this, "Facebook login cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException error) {
                Toast.makeText(LoginActivity.this, "Login fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void sendProfileRequest(AccessToken accessToken) {
        // Prepare request
        GraphRequest request = GraphRequest.newMeRequest(accessToken,
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(JSONObject object, GraphResponse response) {
                        Bundle fbProfileData = getFacebookData(object);

                        userProfile = new UserProfile();
                        userProfile.setFirstName(fbProfileData.getString("first_name"));
                        userProfile.setLastName(fbProfileData.getString("last_name"));
                        userProfile.setEmail(fbProfileData.getString("email"));
                        userProfile.setPhone(fbProfileData.getString("phone"));
                        userProfile.setAvatar(fbProfileData.get("profile_pic").toString());

                        startNextActivity();
                    }
                });

        // Send request to facebook api
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id, first_name, last_name, email, gender, birthday, location");
        request.setParameters(parameters);
        request.executeAsync();
    }

    private void startNextActivity() {
        if (request == SYNC_REQUEST) {
            Intent intent = new Intent(LoginActivity.this, SyncActivity.class);
            startActivity(intent);
        } else if (request == PROFILE_REQUEST) {
            Intent intent = new Intent(LoginActivity.this, ProfileActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.putExtra("profile", userProfile);
            startActivity(intent);
        }
        finish();
    }

    private Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();
        try {
            String id = object.getString("id");

            // Get picture profile url
            bundle.putString("profile_pic", "https://graph.facebook.com/" + id + "/picture?width=200&height=200");

            // Get details profile
            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));
            if (object.has("phone"))
                bundle.putString("phone", object.getJSONObject("phone").getString("phone"));


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return bundle;
    }

    private boolean checkFacebookLogged(AccessToken accessToken) {
        if (accessToken != null) {
            sendProfileRequest(accessToken);
            return true;
        }
        return false;
    }

    private boolean checkGoogleLogged(GoogleApiClient googleApiClient) {
        OptionalPendingResult<GoogleSignInResult> optionalPendingResult = Auth.GoogleSignInApi.silentSignIn(googleApiClient);
        if(optionalPendingResult.isDone()) {
            GoogleSignInResult googleSignInResult = optionalPendingResult.get();
            handleSignInResult(googleSignInResult);
            return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 64206) {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        } else if(requestCode == GOOGLE_SIGN_IN && resultCode == RESULT_OK) {
            GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(googleSignInResult);

            startNextActivity();
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        if(result.isSuccess()) {
            GoogleSignInAccount googleSignInAccount = result.getSignInAccount();
            userProfile = new UserProfile();
            userProfile.setFullName(googleSignInAccount.getDisplayName());
            userProfile.setEmail(googleSignInAccount.getEmail());
            userProfile.setAvatar(googleSignInAccount.getPhotoUrl().toString());
        } else {
            Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        accessTokenTracker.stopTracking();
        profileTracker.stopTracking();
    }

    /*
    * Facebook connection failed
    * */
    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, "Can't sign in to G+", Toast.LENGTH_SHORT).show();
    }

    private void googleSignIn() {
        Intent googleSignInIntent = Auth.GoogleSignInApi.getSignInIntent(LoginActivity.googleApiClient);
        startActivityForResult(googleSignInIntent, GOOGLE_SIGN_IN);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {
        Toast.makeText(this, "Connection Suspended", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();

        if(viewId == btnGpLogin.getId()) {
            googleSignIn();
        } else if(viewId == btnFbLogin.getId()) {
            loginFacebook();
        }
    }
}

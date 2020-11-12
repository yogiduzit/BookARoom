package com.example.bookaroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String BCIT_URL = "https://id.bcit.ca";
    public static final String GO_FORWARD_URL = "https://www.bcit.ca/covid-19/return-to-operations/";
    SignInButton signin;
    int RC_SIGN_IN = 0;
    GoogleSignInClient mGoogleSignInClient;
    /**
     * Authentication status of the user
     */
    private boolean loggedIn = false;

    /**
     * Stores the key value pairs containing the button id
     * and link to be opened on clicking the button corresponding
     * to the id
     */
    private Map<Integer, String> linksMap = new HashMap();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
//        setupLinks();
//        setButtonTitle();
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        signin = findViewById(R.id.sign_in_button);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.sign_in_button:
                        signIn();
                        break;
                }
            }
        });
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//        // Check for existing Google Sign In account, if the user is already signed in
//// the GoogleSignInAccount will be non-null.
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);
//    }

    /**
     * Handles the click action on main button
     * @param view the clicked element
     */
//    public void onMainBtnClick(View view) {
//        if (!loggedIn) {
//            openURL(view.getId());
//        } else {
//            Intent viewBookings = new Intent(this, ViewBookings.class);
//            startActivity(viewBookings);
//        }
//    }
    public void onMainBtnClick(View view) {
        Intent i = new Intent(this, GoogleLoginActivity.class);
        startActivity(i);

    }

    /**
     * Handles the click action on Go Forward link
     * @param view the clicked element
     */
    public void onGoForwardClick(View view) {
        openURL(view.getId());
    }

    /**
     * Populates the links map with the key-value pairs
     * containing the view id and links to be used on main page.
     */
//    private void setupLinks() {
//        linksMap.put(R.id.mainBtn, MainActivity.BCIT_URL);
//        linksMap.put(R.id.go_forward_link, MainActivity.GO_FORWARD_URL);
//    }

    /**
     * Sets the button title for the main button depending
     * upon the user authentication status
     * If the user is logged in, set it to "View bookings"
     * If the user isn't logged in, set it to "Login"
     */
//    private void setButtonTitle() {
//        TextView mainBtn = (TextView) findViewById(R.id.mainBtn);
//        String title = getString(loggedIn ? R.string.view_bookings : R.string.login);
//        mainBtn.setText(title);
//    }

    /**
     * Opens a provided URL in external browser
     * @param urlId the url to be opened
     */
    private void openURL(Integer urlId) {
        String url = linksMap.get(urlId);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(launchBrowser);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }
    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(MainActivity.this, GoogleLoginActivity.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }
}

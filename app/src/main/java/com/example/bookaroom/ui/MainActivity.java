package com.example.bookaroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.bookaroom.R;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String BCIT_URL = "https://id.bcit.ca";
    public static final String GO_FORWARD_URL = "https://www.bcit.ca/covid-19/return-to-operations/";

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
        setupLinks();
        setButtonTitle();
    }

    /**
     * Handles the click action on main button
     * @param view the clicked element
     */
    public void onMainBtnClick(View view) {
        if (!loggedIn) {
            openURL(view.getId());
        } else {
            Intent viewBookings = new Intent(this, ViewBookings.class);
            startActivity(viewBookings);
        }
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
    private void setupLinks() {
        linksMap.put(R.id.mainBtn, MainActivity.BCIT_URL);
        linksMap.put(R.id.go_forward_link, MainActivity.GO_FORWARD_URL);
    }

    /**
     * Sets the button title for the main button depending
     * upon the user authentication status
     * If the user is logged in, set it to "View bookings"
     * If the user isn't logged in, set it to "Login"
     */
    private void setButtonTitle() {
        TextView mainBtn = (TextView) findViewById(R.id.mainBtn);
        String title = getString(loggedIn ? R.string.view_bookings : R.string.login);
        mainBtn.setText(title);
    }

    /**
     * Opens a provided URL in external browser
     * @param urlId the url to be opened
     */
    private void openURL(Integer urlId) {
        String url = linksMap.get(urlId);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(launchBrowser);
    }

}

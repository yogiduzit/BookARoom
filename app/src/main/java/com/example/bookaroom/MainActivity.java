package com.example.bookaroom;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static final String BCIT_URL = "https://id.bcit.ca";
    private boolean loggedIn = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);
        setButtonTitle();
    }

    public void openURL(View view) {
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(MainActivity.BCIT_URL));
        startActivity(launchBrowser);
    }

    private void setButtonTitle() {
        TextView mainBtn = (TextView) findViewById(R.id.mainBtn);
        String title = getString(loggedIn ? R.string.view_bookings : R.string.login);
        mainBtn.setText(title);
    }
}
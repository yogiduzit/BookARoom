package com.example.bookaroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookaroom.helpers.ToastHelper;
import com.example.bookaroom.ui.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

import java.util.HashMap;
import java.util.Map;


public class MyProfileFragment extends Fragment {

    ImageView imageView;
    TextView name, email, covidInformation, studentInformation, staffInformation;
    Button signOutButton;

    GoogleSignInClient mGoogleSignInClient;

    private Map<Integer, String> linksMap = new HashMap();

    public static final String COVID_INFORMATION_URL = "https://www.bcit.ca/covid-19/";
    public static final String INFORMATION_FOR_STUDENT = "https://www.bcit.ca/covid-19/information-for-students/";
    public static final String INFORMATION_FOR_STAFF = "https://www.bcit.ca/covid-19/information-for-faculty-staff/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(getActivity(), gso);
        imageView = rootView.findViewById(R.id.googleLogo);
        name = rootView.findViewById(R.id.nameInput);
        email = rootView.findViewById(R.id.emailInput);
        setupLinks();
        covidInformation = rootView.findViewById(R.id.go_covid_information);
        covidInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openURL(v.getId());
            }
        });
        studentInformation = rootView.findViewById(R.id.information_for_student);
        studentInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openURL(v.getId());
            }
        });
        staffInformation = rootView.findViewById(R.id.information_for_staff);
        staffInformation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openURL(v.getId());
            }
        });
        signOutButton = rootView.findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()) {
                    case R.id.signOutButton:
                        signOut();
                        break;
                }
            }

        });

        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(getActivity());
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            Uri personPhoto = acct.getPhotoUrl();
            name.setText(personName);
            email.setText(personEmail);
            if (personPhoto != null) {
                Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
            }
        }
        return rootView;
    }

    private void setupLinks() {
        linksMap.put(R.id.go_covid_information, MyProfileFragment.COVID_INFORMATION_URL);
        linksMap.put(R.id.information_for_student, MyProfileFragment.INFORMATION_FOR_STUDENT);
        linksMap.put(R.id.information_for_staff, MyProfileFragment.INFORMATION_FOR_STAFF);
    }

    private void signOut() {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), task -> {
                    ToastHelper.showToast(getContext(), ToastHelper.Severity.SUCCESS, "Signed out successfully!", Toast.LENGTH_SHORT);
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                });
    }



    public void onGoStudentInformation(View view) {
        openURL(view.getId());
    }

    public void onGoCovidInformation(View view) {
        openURL(view.getId());
    }

    private void openURL(Integer urlId) {
        String url = linksMap.get(urlId);
        Intent launchBrowser = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(launchBrowser);
    }
}
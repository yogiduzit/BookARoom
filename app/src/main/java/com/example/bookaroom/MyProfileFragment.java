package com.example.bookaroom;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.bookaroom.ui.MainActivity;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class MyProfileFragment extends Fragment {

    ImageView imageView;
    TextView name, email, id;
    Button signOutButton;

    GoogleSignInClient mGoogleSignInClient;

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
        id = rootView.findViewById(R.id.idInput);
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
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();
            name.setText(personName);
            email.setText(personEmail);
            id.setText(personId);
            if (personPhoto != null) {
                Glide.with(this).load(String.valueOf(personPhoto)).into(imageView);
            }
        }
        return rootView;
    }

    private void signOut() {

        mGoogleSignInClient.signOut()
                .addOnCompleteListener(getActivity(), task -> {
                    Toast.makeText(getContext(), "Signed out successfully!", Toast.LENGTH_LONG).show();
                    Intent i = new Intent(getActivity(), MainActivity.class);
                    startActivity(i);
                });
    }
}
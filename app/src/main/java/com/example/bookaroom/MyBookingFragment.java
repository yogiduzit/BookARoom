package com.example.bookaroom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookaroom.ui.adapter.MyBookingsAdapter;
import com.example.bookaroom.ui.viewModel.MyBookingsViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MyBookingFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_my_booking, container, false);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        String userID = account.getId();

        RecyclerView myBookingsView = rootView.findViewById(R.id.my_bookings);

        MyBookingsViewModel viewModel = new ViewModelProvider(this).get(MyBookingsViewModel.class);
        viewModel.getBookings(userID).observe(getViewLifecycleOwner(), bookings -> {
            MyBookingsAdapter adapter = new MyBookingsAdapter(bookings);
            GridLayoutManager layoutManager =  new GridLayoutManager(getActivity().getApplication(), 1);
            myBookingsView.setAdapter(adapter);
            myBookingsView.setLayoutManager(layoutManager);
        });
        return rootView;
    }
}
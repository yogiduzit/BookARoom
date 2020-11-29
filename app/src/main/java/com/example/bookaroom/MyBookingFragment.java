package com.example.bookaroom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.bookaroom.data.database.entity.Booking;
import com.example.bookaroom.ui.adapter.MyBookingsAdapter;
import com.example.bookaroom.ui.viewModel.MyBookingsViewModel;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import java.util.List;

public class MyBookingFragment extends Fragment {
    private List<Booking> bookings;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_my_booking, container, false);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(getContext());
        String userID = account.getId();

        RecyclerView myBookingsView = rootView.findViewById(R.id.my_bookings);
        TextView noBookings =  rootView.findViewById(R.id.no_bookings);

        MyBookingsViewModel viewModel = new ViewModelProvider(this).get(MyBookingsViewModel.class);
        viewModel.getBookings(userID).observe(getViewLifecycleOwner(), bookings -> {
            MyBookingsAdapter adapter = new MyBookingsAdapter(bookings);
            this.bookings = bookings;
            GridLayoutManager layoutManager =  new GridLayoutManager(getActivity().getApplication(), 1);
            myBookingsView.addItemDecoration(new DividerItemDecoration(getContext(), GridLayoutManager.VERTICAL));
            myBookingsView.setAdapter(adapter);
            myBookingsView.setLayoutManager(layoutManager);
        });

        if (this.bookings == null){
            myBookingsView.setVisibility(View.GONE);
            noBookings.setVisibility(View.VISIBLE);
        } else{
            myBookingsView.setVisibility(View.VISIBLE);
            noBookings.setVisibility(View.GONE);
        }
        return rootView;
    }
}
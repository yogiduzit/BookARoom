package com.example.bookaroom.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookaroom.data.database.access.BookableManager;
import com.example.bookaroom.data.database.access.BookingManager;
import com.example.bookaroom.data.database.entity.Bookable;
import com.example.bookaroom.data.database.entity.Booking;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class MyBookingsViewModel extends ViewModel {
    private static final String TAG = MyBookingsViewModel.class.getSimpleName();

    private MutableLiveData<List<Booking>> bookings;
    private List<Booking> temp;
    private BookingManager manager;

    public MyBookingsViewModel() {
        temp = new ArrayList<>();
        manager = new BookingManager();
    }

    public MutableLiveData<List<Booking>> getBookings(String userID) {
        if (bookings == null) {
            bookings = new MutableLiveData<>();
            loadBookings(userID);
        }
        return bookings;
    }

    public void loadBookings(String userID) {
        temp.clear();
        manager.getUserBookings(userID).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.d(TAG, "Error getting documents: ", task.getException());
                return;
            }
            for (QueryDocumentSnapshot bookingSnapshot : task.getResult()) {
                temp.add(new Booking(bookingSnapshot.getId(),
                        (String) bookingSnapshot.get("userId"),
                        (String) bookingSnapshot.get("roomId"),
                        (Double) bookingSnapshot.get("startTime"),
                        (Double) bookingSnapshot.get("endTime"),
                        (String) bookingSnapshot.get("date"),
                        (String) bookingSnapshot.get("userName"),
                        (String) bookingSnapshot.get("buildingId"))
                );
            }
            bookings.setValue(temp);
        });
    }
}
package com.example.bookaroom.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookaroom.data.database.access.BookingManager;
import com.example.bookaroom.data.database.entity.Booking;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingsViewModel extends ViewModel {

    private static final String TAG = BookingsViewModel.class.getSimpleName();

    private MutableLiveData<HashMap<String, List<Booking>>> bookings;
    private HashMap<String, List<Booking>> temp;

    private final BookingManager manager = new BookingManager();

    public BookingsViewModel() {
        temp = new HashMap<>();
    }

    public MutableLiveData<HashMap<String, List<Booking>>> getBookings(String buildingId) {
        if (bookings == null) {
            bookings = new MutableLiveData<>();
            loadBookings(buildingId);
        }
        return bookings;
    }

    private void loadBookings(String buildingId) {
        temp.clear();
        manager.getBookings(buildingId).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.d(TAG, "Error getting documents: ", task.getException());
                return;
            }
            for (QueryDocumentSnapshot bookingSnapshot: task.getResult()) {
                String bookableId = bookingSnapshot.getString("roomId");
                if (temp.get(bookableId) == null) {
                    temp.put(bookableId, new ArrayList<>());
                }
                temp.get(bookableId).add(new Booking(bookingSnapshot.getId(),
                        (String) bookingSnapshot.get("userId"),
                        (String) bookingSnapshot.get("roomId"),
                        (String) bookingSnapshot.get("startTime"),
                        (String) bookingSnapshot.get("endTime"),
                        (String) bookingSnapshot.get("date"),
                        (String) bookingSnapshot.get("userName"),
                        (String) bookingSnapshot.get("buildingId"))
                );
            }
            bookings.setValue(temp);
        });
    }


}

package com.example.bookaroom.data.database.access;

import androidx.annotation.NonNull;

import com.example.bookaroom.AdminPanel;
import com.example.bookaroom.data.database.entity.Booking;
import com.example.bookaroom.helpers.DateHelper;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;

public class BookingManager {

    static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String BOOKING_COLLECTION = "bookings";


    public Task<QuerySnapshot> addBooking(Booking booking) {
        return db.collection(BOOKING_COLLECTION)
                .whereEqualTo("roomId", booking.getRoomId())
                .whereLessThanOrEqualTo("startTime", booking.getStartTime())
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        int sameBookingCount = 0;
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            Double endTime = (Double) document.get("endTime");
                            if (endTime >= booking.getEndTime()) {
                                ++sameBookingCount;
                            }
                        }
                        if (sameBookingCount > AdminPanel.MAX_BOOKINGS_PER_ROOM) {
                            task.continueWithTask((Continuation<QuerySnapshot, Task<Void>>) task1 -> {
                                throw new Exception("Exceeded room restriction");
                            });

                        } else {
                            task.continueWithTask(bookingTask -> db.collection(BOOKING_COLLECTION)
                                    .document(booking.getId())
                                    .set(booking));
                        }
                    }
                });
    }

    public Task<QuerySnapshot> getUserBookings(String userId) {
        return db.collection(BOOKING_COLLECTION)
                .whereEqualTo("userId", userId)
                .whereEqualTo("date", DateHelper.getDate())
                .get();
    }

    public Task<QuerySnapshot> getBookings(String buildingId) {
        return db.collection(BOOKING_COLLECTION)
                .whereEqualTo("date", DateHelper.getDate())
                .whereEqualTo("buildingId", buildingId)
                .get();
    }

    public Task<Void> deleteBooking(String id){
        return db.collection(BOOKING_COLLECTION)
                .document(id)
                .delete();
    }
}

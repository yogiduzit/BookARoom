package com.example.bookaroom.data.database.access;

import com.example.bookaroom.data.database.entity.Booking;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

public class BookingManager {
    static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String BOOKING_COLLECTION = "bookings";

    public Task<Void> addBooking(Booking booking) {
        return db.collection(BOOKING_COLLECTION)
                .document(booking.getId())
                .set(booking);
    }
}

package com.example.bookaroom.data.database.access;

import com.example.bookaroom.data.database.entity.Booking;
import com.example.bookaroom.helpers.DateHelper;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class BookingManager {

    static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String BOOKING_COLLECTION = "bookings";


    public Task<Void> addBooking(Booking booking) {
        return db.collection(BOOKING_COLLECTION)
                .document(booking.getId())
                .set(booking);
    }

    public Task<QuerySnapshot> getUserBookings(String userId){
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

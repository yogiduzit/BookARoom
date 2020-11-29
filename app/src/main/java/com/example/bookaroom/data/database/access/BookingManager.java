package com.example.bookaroom.data.database.access;

import com.example.bookaroom.data.database.entity.Booking;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BookingManager {

    static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String BOOKING_COLLECTION = "bookings";
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd",Locale.US);
    public CollectionReference getBookings() {
        return db.collection(BOOKING_COLLECTION);
    }

    public Task<Void> addBooking(Booking booking) {
        return db.collection(BOOKING_COLLECTION)
                .document(booking.getId())
                .set(booking);
    }

    public Task<QuerySnapshot> getBookings(String userId){
        return db.collection(BOOKING_COLLECTION)
                .whereEqualTo("userId", userId)
                .whereEqualTo("date", dateFormat.format(new Date()))
                .get();
    }

    public Task<Void> deleteBooking(String id){
        return db.collection(BOOKING_COLLECTION)
                .document(id)
                .delete();
    }
}

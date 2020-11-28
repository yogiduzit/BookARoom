package com.example.bookaroom.data.database.access;

import androidx.annotation.NonNull;

import com.example.bookaroom.data.database.entity.Booking;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

public class BookingManager {
    static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String BOOKING_COLLECTION = "bookings";

    public CollectionReference getBookings() {
        return db.collection(BOOKING_COLLECTION);
    }

    public Task<QuerySnapshot> addBooking(Booking booking) throws Exception {
        ArrayList<Booking> listOfBookings = new ArrayList<>();


        return db.collection(BOOKING_COLLECTION).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        listOfBookings.add(new Booking(document.getId(), document.get("roomId").toString(),
                                document.get("startTime").toString(), document.get("endTime").toString(),
                                document.get("date").toString(), document.get("userName").toString()));
                    }
                    int sameBookingCount = 0;
                    for (Booking item : listOfBookings) {
                        if (item.getDate().equals(booking.getDate()) && item.getStartTime().equals(booking.getStartTime()) &&
                                item.getRoomId().equals(booking.getRoomId())) {
                            sameBookingCount++;
                        }
                    }
                    if (sameBookingCount > 10) {
                        task.continueWithTask(new Continuation<QuerySnapshot, Task<Void>>() {
                            @Override
                            public Task<Void> then(@NonNull Task<QuerySnapshot> task) throws Exception {
                                throw new Exception("Exceeded room restriction");
                            }

                        });

                    } else {
                        task.continueWithTask(new Continuation<QuerySnapshot, Task<Void>>() {
                            @Override
                            public Task<Void> then(@NonNull Task<QuerySnapshot> task) throws Exception {
                                return db.collection(BOOKING_COLLECTION)
                                        .document(booking.getId())
                                        .set(booking);
                            }
                        });
                    }
                }
            }
        });
    }
}

package com.example.bookaroom.data.database.access;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class CampusManager {
    public static String CAMPUS_COLLECTION = "campuses";

    static final FirebaseFirestore db = FirebaseFirestore.getInstance();

    public Task<QuerySnapshot> getCampuses() {
        return db.collection(CAMPUS_COLLECTION).get();
    }
}

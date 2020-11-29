package com.example.bookaroom.data.database.access;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class BookableManager {
    static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String BOOKABLE_COLLECTION = "bookables";

    public Task<QuerySnapshot> getBookables(String campusId, String buildingId) {
        return db.collection(CampusManager.CAMPUS_COLLECTION)
                .document(campusId)
                .collection(BuildingManager.BUILDING_COLLECTION)
                .document(buildingId)
                .collection(BOOKABLE_COLLECTION)
                .get();
    }
}

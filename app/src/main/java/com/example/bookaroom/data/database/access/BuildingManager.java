package com.example.bookaroom.data.database.access;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class BuildingManager {
    static final FirebaseFirestore db = FirebaseFirestore.getInstance();
    public static final String BUILDING_COLLECTION = "buildings";

    public Task<QuerySnapshot> getBuildingsInCampus(String campusId) {
        return db.collection(CampusManager.CAMPUS_COLLECTION)
                .document(campusId)
                .collection(BUILDING_COLLECTION)
                .get();
    }
}

package com.example.bookaroom.data.database.access;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class BuildingManager {
    CollectionReference buildings;

    public BuildingManager() {
        buildings = FirebaseFirestore.getInstance().collection("buildings");
    }

    public Task<QuerySnapshot> getBuildings() {
        return buildings.get();
    }
}

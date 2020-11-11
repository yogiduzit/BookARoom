package com.example.bookaroom.ui.viewModel;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookaroom.data.database.access.BuildingManager;
import com.example.bookaroom.data.database.entity.Bookable;
import com.example.bookaroom.data.database.entity.Building;
import com.example.bookaroom.data.database.entity.Campus;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class ViewBookingViewModel extends ViewModel {
    private static final String TAG = ViewModel.class.getSimpleName();

    private MutableLiveData<List<Campus>> campuses;
    private MutableLiveData<List<Building>> buildings;
    private MutableLiveData<List<Bookable>> bookables;

    private BuildingManager buildingManager;

    public LiveData<List<Building>> getBuildings() {
        if (buildings == null) {
            buildings = new MutableLiveData<>();
            loadBuildings();
        }
        return buildings;
    }

    private void loadBuildings() {
        BuildingManager manager = new BuildingManager();
        manager.getBuildings().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    ArrayList retrievedBuildings = new ArrayList<Building>();
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Building building = new Building(document.getDocumentReference("campus").getId(), document.getId(), document.getString("name"));
                        retrievedBuildings.add(building);
                    }
                    buildings.setValue(retrievedBuildings);
                } else {
                    Log.d(TAG, "Error getting documents: ", task.getException());
                }
            }
        });
    }
}

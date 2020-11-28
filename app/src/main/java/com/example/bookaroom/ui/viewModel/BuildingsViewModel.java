package com.example.bookaroom.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookaroom.data.database.access.BuildingManager;
import com.example.bookaroom.data.database.access.CampusManager;
import com.example.bookaroom.data.database.entity.Building;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class BuildingsViewModel extends ViewModel {
    private static final String TAG = BuildingsViewModel.class.getSimpleName();

    private MutableLiveData<HashMap<String, ArrayList<Building>>> buildingsMap;
    private HashMap<String, ArrayList<Building>> temp;

    private CampusManager campusManager;
    private BuildingManager buildingManager;

    public BuildingsViewModel() {
        campusManager = new CampusManager();
        buildingManager = new BuildingManager();
        temp = new HashMap<>();
    }

    public MutableLiveData<HashMap<String, ArrayList<Building>>> getBuildings() {
        if (buildingsMap == null) {
            buildingsMap = new MutableLiveData<>();
            loadCampuses();
        }
        return buildingsMap;
    }

    private void loadCampuses() {
        temp.clear();
        campusManager.getCampuses().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.d(TAG, "Error getting documents: ", task.getException());
                return;
            }
            ArrayList<Task<QuerySnapshot>> tasks = new ArrayList<>();
            for (QueryDocumentSnapshot campusSnapshot : task.getResult()) {
                String campusId = campusSnapshot.getId();
                if (temp.get(campusId) == null) {
                    temp.put(campusId, new ArrayList<>());
                    tasks.add(buildingManager.getBuildingsInCampus(campusId));
                }
            }
            Tasks.whenAllComplete(tasks).addOnCompleteListener(campusTasks -> {
                for (Task<?> campusTask : Objects.requireNonNull(campusTasks.getResult())) {
                    if (!campusTask.isSuccessful()) {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                        return;
                    }
                    QuerySnapshot buildingsSnapshot = (QuerySnapshot) campusTask.getResult();
                    for (QueryDocumentSnapshot buildingSnapshot : buildingsSnapshot) {
                        String buildingId = buildingSnapshot.getId();
                        String campusId = buildingSnapshot.getReference().getParent().getParent().getId();
                        temp.get(campusId).add(new Building(campusId, buildingId, buildingSnapshot.getString("name")));
                    }
                }
            }).addOnCompleteListener(finalTask -> {
                buildingsMap.setValue(temp);
            });
        });
    };
}

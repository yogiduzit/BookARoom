package com.example.bookaroom.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookaroom.data.database.access.BookableManager;
import com.example.bookaroom.data.database.access.BuildingManager;
import com.example.bookaroom.data.database.access.CampusManager;
import com.example.bookaroom.data.database.entity.Building;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ViewBookingViewModel extends ViewModel {
    private static final String TAG = ViewModel.class.getSimpleName();

    private MutableLiveData<HashMap<String, HashMap<String, Building>>> bookables;
    private HashMap<String, HashMap<String, Building>> temp;

    private CampusManager campusManager;
    private BuildingManager buildingManager;
    private BookableManager bookableManager;

    public ViewBookingViewModel() {
        campusManager = new CampusManager();
        buildingManager = new BuildingManager();
        bookableManager = new BookableManager();
        temp = new HashMap<>();
    }

    public MutableLiveData<HashMap<String, HashMap<String, Building>>> getBookables() {
        if (bookables == null) {
            bookables = new MutableLiveData<>();
            loadCampuses();
        }
        return bookables;
    }

    private void loadCampuses() {
        temp.clear();
        campusManager.getCampuses().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                ArrayList<Task<QuerySnapshot>> tasks = new ArrayList<>();
                for (QueryDocumentSnapshot campusSnapshot: task.getResult()) {
                    String campusId = campusSnapshot.getId();
                    if (temp.get(campusId) == null) {
                        temp.put(campusId, new HashMap<>());
                    }
                    tasks.add(loadBuildings(campusId));
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
    }

    private Task<QuerySnapshot> loadBuildings(String campusId) {
        return buildingManager.getBuildingsInCampus(campusId).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                List<Task<QuerySnapshot>> tasks = new ArrayList<>();
                for (QueryDocumentSnapshot document : task.getResult()) {
                    String buildingId = document.getId();
                    Building building = new Building(campusId, buildingId, (String) document.get("name"));
                    temp.get(campusId).put(buildingId, building);
                    tasks.add(loadBookables(campusId, buildingId));
                }
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });
    }

    private Task<QuerySnapshot> loadBookables(String campusId, String buildingId) {
        return bookableManager.getBookables(campusId, buildingId).addOnCompleteListener(task -> {
           if (task.isSuccessful()) {
               for (QueryDocumentSnapshot document : task.getResult()) {
                   temp.get(campusId).get(buildingId).addBookable(document.getId());
               }
           } else {
               Log.d(TAG, "Error getting documents: ", task.getException());
           }
        });
    }
}

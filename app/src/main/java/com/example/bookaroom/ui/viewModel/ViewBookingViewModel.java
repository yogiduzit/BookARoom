package com.example.bookaroom.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookaroom.data.database.access.BookableManager;
import com.example.bookaroom.data.database.access.BuildingManager;
import com.example.bookaroom.data.database.access.CampusManager;
import com.example.bookaroom.data.database.entity.Bookable;
import com.example.bookaroom.data.database.entity.Building;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

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
            if (!task.isSuccessful()) {
                Log.d(TAG, "Error getting documents: ", task.getException());
                return;
            }
            ArrayList<Task<QuerySnapshot>> tasks = new ArrayList<>();
            for (QueryDocumentSnapshot campusSnapshot : task.getResult()) {
                String campusId = campusSnapshot.getId();
                if (temp.get(campusId) == null) {
                    temp.put(campusId, new HashMap<>());
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
                    ArrayList<Task<QuerySnapshot>> buildingsTasks = new ArrayList<>();
                    for (QueryDocumentSnapshot buildingSnapshot : buildingsSnapshot) {
                        String buildingId = buildingSnapshot.getId();
                        String campusId = buildingSnapshot.getReference().getParent().getParent().getId();
                        if (temp.get(campusId).get(buildingId) == null) {
                            temp.get(campusId).put(buildingId, new Building(campusId, buildingId, buildingSnapshot.getString("name")));
                        }
                        buildingsTasks.add(bookableManager.getBookables(campusId, buildingId));
                    }
                    Tasks.whenAllComplete(buildingsTasks).addOnCompleteListener(buildingTasks -> {
                        for (Task<?> buildingTask : Objects.requireNonNull(buildingTasks.getResult())) {
                            if (!buildingTask.isSuccessful()) {
                                Log.d(TAG, "Error getting documents: ", task.getException());
                                return;
                            }
                            QuerySnapshot bookablesSnapshot = (QuerySnapshot) buildingTask.getResult();
                            for (QueryDocumentSnapshot bookableSnapshot: bookablesSnapshot) {
                                DocumentReference buildingRef = bookableSnapshot.getReference().getParent().getParent();
                                String buildingId = buildingRef.getId();
                                String campusId = buildingRef.getParent().getParent().getId();
                                temp.get(campusId).get(buildingId).addBookable(bookableSnapshot.getId());
                            }
                        }
                    }).addOnCompleteListener(finalTask -> {
                        bookables.setValue(temp);
                    });
                }
            });
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

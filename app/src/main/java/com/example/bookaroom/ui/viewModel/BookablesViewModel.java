package com.example.bookaroom.ui.viewModel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.bookaroom.data.database.access.BookableManager;
import com.example.bookaroom.data.database.entity.Bookable;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class BookablesViewModel extends ViewModel {
    private static final String TAG = BookablesViewModel.class.getSimpleName();

    private MutableLiveData<List<Bookable>> bookables;
    private List<Bookable> temp;
    private BookableManager manager;

    public BookablesViewModel() {
        temp = new ArrayList<>();
        manager = new BookableManager();
    }

    public MutableLiveData<List<Bookable>> getBookables(String campusId, String buildingId) {
        if (bookables == null) {
            bookables = new MutableLiveData<>();
            loadBookables(campusId, buildingId);
        }
        return bookables;
    }

    public void loadBookables(String campusId, String buildingId) {
        temp.clear();
        manager.getBookables(campusId, buildingId).addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                Log.d(TAG, "Error getting documents: ", task.getException());
                return;
            }
            for (QueryDocumentSnapshot bookableSnapshot: task.getResult()) {
                temp.add(new Bookable(bookableSnapshot.getId(), buildingId));
            }
            bookables.setValue(temp);
        });
    }
}

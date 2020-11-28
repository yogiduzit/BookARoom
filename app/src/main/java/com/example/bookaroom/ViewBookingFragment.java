package com.example.bookaroom;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookaroom.data.database.entity.Building;
import com.example.bookaroom.ui.adapter.CampusAdapter;
import com.example.bookaroom.ui.viewModel.BuildingsViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewBookingFragment extends Fragment {

    private RecyclerView campusList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_booking, container, false);

        campusList = rootView.findViewById(R.id.campus_list);

        BuildingsViewModel viewModel = new ViewModelProvider(this).get(BuildingsViewModel.class);
        viewModel.getBuildings().observe(getViewLifecycleOwner(), buildingsMap -> {
            setupRecyclerView(campusList, buildingsMap);
        });

        return rootView;
    }

    void setupRecyclerView(RecyclerView recyclerView, HashMap<String, ArrayList<Building>> buildingsMap) {
        CampusAdapter adapter = new CampusAdapter(buildingsMap);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager =  new GridLayoutManager(getActivity().getApplication(), 1);
        recyclerView.setLayoutManager(layoutManager);
    };
}

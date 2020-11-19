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
import com.example.bookaroom.ui.adapter.BuildingsAdapter;
import com.example.bookaroom.ui.viewModel.ViewBookingViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class ViewBookingFragment extends Fragment {

    private RecyclerView burnabyCampus;
    private RecyclerView downtownCampus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_booking, container, false);

        burnabyCampus = rootView.findViewById(R.id.burnaby_campus_list);
        downtownCampus = rootView.findViewById(R.id.downtown_campus_list);

        ViewBookingViewModel viewModel = new ViewModelProvider(this).get(ViewBookingViewModel.class);
        viewModel.getBookables().observe(getViewLifecycleOwner(), bookableMap -> {
            setupRecyclerView(burnabyCampus, bookableMap.get("burnaby"));
            setupRecyclerView(downtownCampus, bookableMap.get("downtown"));
        });

        return rootView;
    }

    void setupRecyclerView(RecyclerView recyclerView, HashMap<String, Building> buildingsMap) {
        ArrayList<Building> buildings = new ArrayList<>();
        for (String buildingKey: buildingsMap.keySet()) {
            buildings.add(buildingsMap.get(buildingKey));
        }
        BuildingsAdapter adapter = new BuildingsAdapter(buildings);
        recyclerView.setAdapter(adapter);
        GridLayoutManager layoutManager =  new GridLayoutManager(getActivity().getApplication(), 1);
        recyclerView.setLayoutManager(layoutManager);
    };
}

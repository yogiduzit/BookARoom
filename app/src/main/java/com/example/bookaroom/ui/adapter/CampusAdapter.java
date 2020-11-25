package com.example.bookaroom.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookaroom.data.database.entity.Building;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.WordUtils;

import com.example.bookaroom.R;
import com.example.bookaroom.ui.ChooseBooking;
import com.google.android.material.textfield.TextInputLayout;

public class CampusAdapter extends RecyclerView.Adapter<CampusAdapter.ViewHolder> {
    private ArrayList<String> campuses;
    private HashMap<String, ArrayList<Building>> campusesMap;

    public static String CAMPUS_NAME = "campus_name";
    public static String BUILDING_NAME = "building_name";

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public CampusAdapter(HashMap<String, ArrayList<Building>> campusesMap) {
        this.campusesMap = campusesMap;
        this.campuses = new ArrayList<>();
        this.campuses.addAll(campusesMap.keySet());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.dropdown_menu, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.view;
        String campusId = campuses.get(position);

        AutoCompleteTextView dropdown = view.findViewById(R.id.filled_exposed_dropdown);
        TextInputLayout caption = view.findViewById(R.id.filled_exposed_dropdown_container);

        caption.setHint(WordUtils.capitalize(campusId));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),
                R.layout.dropdown_menu_item, getBuildingNames(campusesMap.get(campusId)));
        addListener(dropdown, campusesMap.get(campusId));
        dropdown.setAdapter(adapter);
        dropdown.setText(" ", false);
    }


    @Override
    public int getItemCount() {
        return campuses.size();
    }

    private List<String> getBuildingNames(ArrayList<Building> buildings) {
        return buildings.stream().map(building -> building.getId() != null ? building.getId() : building.getName()).collect(Collectors.toList());
    }

    private void addListener(AutoCompleteTextView autoCompleteTextView, ArrayList<Building> buildings){
        autoCompleteTextView.setOnItemClickListener((parent, view, position, id) -> {
            Building building = buildings.get(position);
            Intent intent = new Intent(view.getContext(), ChooseBooking.class);

            String buildingName = building.getName() != null ? building.getName() : building.getId();
            intent.putExtra(CAMPUS_NAME, building.getCampus());
            intent.putExtra(BUILDING_NAME, buildingName);
            view.getContext().startActivity(intent);
        });
    }

}

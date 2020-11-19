package com.example.bookaroom.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookaroom.data.database.entity.Building;

import java.util.ArrayList;

import com.example.bookaroom.R;
import com.example.bookaroom.ui.ChooseBooking;
import com.google.android.material.textfield.TextInputLayout;

public class BuildingsAdapter extends RecyclerView.Adapter<BuildingsAdapter.ViewHolder> {
    private ArrayList<Building> buildings;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    public BuildingsAdapter(ArrayList<Building> buildings) {
        this.buildings = buildings;
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
        Building building = buildings.get(position);

        AutoCompleteTextView dropdown = view.findViewById(R.id.filled_exposed_dropdown);
        TextInputLayout caption = view.findViewById(R.id.filled_exposed_dropdown_container);
        caption.setHint(building.getName() != null ? building.getName() : building.getId());
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(view.getContext(),
                R.layout.dropdown_menu_item, building.getBookables());
        addListener(dropdown);
        dropdown.setAdapter(adapter);
        dropdown.setText(" ", false);
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }

    private void addListener(AutoCompleteTextView autoCompleteTextView){
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(view.getContext(), ChooseBooking.class);
                view.getContext().startActivity(intent);
            }
        });
    }

}

package com.example.bookaroom.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.bookaroom.R;

import java.util.ArrayList;
import java.util.List;


public class ViewBookings extends MainActivity {
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);
        String[] recreationalArr = getResources().getStringArray(R.array.recreational);
        String[] libraryArr = getResources().getStringArray(R.array.library);
        String[] downtownArr = getResources().getStringArray(R.array.downtown);
        addItemsToSpinner(R.id.recreational_spinner, recreationalArr);
        addItemsToSpinner(R.id.library_spinner, libraryArr);
        addItemsToSpinner(R.id.downtown_spinner, downtownArr);
    }
    public void addItemsToSpinner(Integer id, String[] items){
        spinner = findViewById(id);
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < items.length;i++){
            list.add(items[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        parent.getItemAtPosition(position);
    }

    public void onNothingSelected(AdapterView<?> parent){

    }
}
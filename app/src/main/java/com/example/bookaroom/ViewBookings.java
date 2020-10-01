package com.example.bookaroom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class ViewBookings extends MainActivity {
    private Spinner recreationalDropdown;
    private Spinner libraryDropdown;
    private Spinner downtownDropdown;
    private String[] recreationalArr;
    private String[] libraryArr;
    private String[] downtownArr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);
        addItemsToRecreationalSpinner();
        addItemsToLibrarySpinner();
        addItemsToDowntownSpinner();
    }


    public void addItemsToRecreationalSpinner(){
        recreationalDropdown = (Spinner)findViewById(R.id.recreational_spinner);
        List<String> list = new ArrayList<String>();
        recreationalArr = getResources().getStringArray(R.array.recreational);
        for(int i = 0; i < recreationalArr.length;i++){
            list.add(recreationalArr[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recreationalDropdown.setAdapter(adapter);
    }

    public void addItemsToLibrarySpinner(){
        libraryDropdown = (Spinner)findViewById(R.id.library_spinner);
        List<String> list = new ArrayList<String>();
        libraryArr = getResources().getStringArray(R.array.library);
        for (int i = 0; i < libraryArr.length;i++){
            list.add(libraryArr[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        libraryDropdown.setAdapter(adapter);
    }

    public void addItemsToDowntownSpinner(){
        downtownDropdown = (Spinner)findViewById(R.id.downtown_spinner);
        List<String> list = new ArrayList<String>();
        downtownArr = getResources().getStringArray(R.array.downtown);
        for(int i = 0; i < downtownArr.length; i++){
            list.add(downtownArr[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        downtownDropdown.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        parent.getItemAtPosition(position);
    }

    public void onNothingSelected(AdapterView<?> parent){

    }
}
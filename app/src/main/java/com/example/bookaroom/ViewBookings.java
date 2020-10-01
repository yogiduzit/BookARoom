package com.example.bookaroom;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class ViewBookings extends MainActivity {
    private Spinner recreationalDropdown;
    private Spinner libraryDropdown;
    private Spinner downtownDropdown;
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
        list.add(getString(R.string.basketball));
        list.add(getString(R.string.badminton));
        list.add(getString(R.string.gym));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        recreationalDropdown.setAdapter(adapter);
    }

    public void addItemsToLibrarySpinner(){
        libraryDropdown = (Spinner)findViewById(R.id.library_spinner);
        List<String> list = new ArrayList<String>();
        list.add("130D");
        list.add("130E");
        list.add("130F");
        list.add("130G");
        list.add("130H");
        list.add("130I");
        list.add("137");
        list.add("138");
        list.add("139");
        list.add("140");
        list.add("141");
        list.add("250A");
        list.add("250B");
        list.add("250C");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        libraryDropdown.setAdapter(adapter);
    }

    public void addItemsToDowntownSpinner(){
        downtownDropdown = (Spinner)findViewById(R.id.downtown_spinner);
        List<String> list = new ArrayList<String>();
        list.add("576");
        list.add("582");
        list.add("583");
        list.add("586");
        list.add("587");
        list.add("666");
        list.add("667");
        list.add("668");
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
package com.example.bookaroom;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


public class ViewBookings extends MainActivity {
    private AutoCompleteTextView autoCompleteTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);
        String[] recreationalArr = getResources().getStringArray(R.array.recreational);
        String[] libraryArr = getResources().getStringArray(R.array.library);
        String[] downtownArr = getResources().getStringArray(R.array.downtown);
        addItemsToDropdown(R.id.filled_exposed_dropdown, recreationalArr);
        addItemsToDropdown(R.id.filled_exposed_dropdown2, libraryArr);
        addItemsToDropdown(R.id.filled_exposed_dropdown3, downtownArr);
    }
    public void addItemsToDropdown(Integer id, String[] items){
        autoCompleteTextView = findViewById(id);
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < items.length;i++){
            list.add(items[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.dropdown_menu, list);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setText(" ", false);
    }
}
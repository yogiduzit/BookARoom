package com.example.bookaroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.bookaroom.R;

import java.util.ArrayList;
import java.util.List;


public class ViewBookings extends MainActivity {
    private AutoCompleteTextView autoCompleteTextView;
    private AutoCompleteTextView autoCompleteTextView2;
    private AutoCompleteTextView autoCompleteTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);
    }

    public void addItemsToDropdown(Integer id, String[] items, AutoCompleteTextView autoCompleteTextView){
        autoCompleteTextView = findViewById(id);
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < items.length;i++){
            list.add(items[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.dropdown_menu, list);
        addListener(autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setText(" ", false);
    }

    private void addListener(AutoCompleteTextView autoCompleteTextView){
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ViewBookings.this, ChooseBooking.class);
                startActivity(intent);
            }
        });
    }
}
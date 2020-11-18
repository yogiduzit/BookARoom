package com.example.bookaroom;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.bookaroom.ui.ChooseBooking;

import java.util.ArrayList;
import java.util.List;

public class ViewBookingFragment extends Fragment {

    private AutoCompleteTextView autoCompleteTextView;
    private AutoCompleteTextView autoCompleteTextView2;
    private AutoCompleteTextView autoCompleteTextView3;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_view_booking, container, false);
        String[] recreationalArr = getResources().getStringArray(R.array.recreational);
        String[] libraryArr = getResources().getStringArray(R.array.library);
        String[] downtownArr = getResources().getStringArray(R.array.downtown);

        addItemsToDropdown(rootView, R.id.filled_exposed_dropdown, recreationalArr, autoCompleteTextView);
        addItemsToDropdown(rootView, R.id.filled_exposed_dropdown2, libraryArr, autoCompleteTextView2);
        addItemsToDropdown(rootView, R.id.filled_exposed_dropdown3, downtownArr, autoCompleteTextView3);

        return rootView;

    }
    public void addItemsToDropdown(View view, Integer id, String[] items, AutoCompleteTextView autoCompleteTextView){
        autoCompleteTextView = view.findViewById(id);
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < items.length;i++){
            list.add(items[i]);
        }
        // Adds items to dropdown menu
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                R.layout.dropdown_menu_item, list);
        addListener(autoCompleteTextView);
        autoCompleteTextView.setAdapter(adapter);
        autoCompleteTextView.setText(" ", false);
    }

    private void addListener(AutoCompleteTextView autoCompleteTextView){
        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ChooseBooking.class);
                startActivity(intent);
            }
        });
    }
}
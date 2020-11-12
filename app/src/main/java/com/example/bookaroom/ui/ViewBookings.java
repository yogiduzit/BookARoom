package com.example.bookaroom.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.bookaroom.MyBookingFragment;
import com.example.bookaroom.R;
import com.example.bookaroom.ViewBookingFragment;
import com.example.bookaroom.ui.tableView.TopFragment;

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
        String[] recreationalArr = getResources().getStringArray(R.array.recreational);
        String[] libraryArr = getResources().getStringArray(R.array.library);
        String[] downtownArr = getResources().getStringArray(R.array.downtown);

//        addItemsToDropdown(R.id.filled_exposed_dropdown, recreationalArr, autoCompleteTextView);
//        addItemsToDropdown(R.id.filled_exposed_dropdown2, libraryArr, autoCompleteTextView2);
//        addItemsToDropdown(R.id.filled_exposed_dropdown3, downtownArr, autoCompleteTextView3);

        SectionsPageAdapter pageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pageAdapter);
    }
//    public void addItemsToDropdown(Integer id, String[] items, AutoCompleteTextView autoCompleteTextView){
//        autoCompleteTextView = findViewById(id);
//        List<String> list = new ArrayList<String>();
//        for(int i = 0; i < items.length;i++){
//            list.add(items[i]);
//        }
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
//                R.layout.dropdown_menu, list);
//        addListener(autoCompleteTextView);
//        autoCompleteTextView.setAdapter(adapter);
//        autoCompleteTextView.setText(" ", false);
//    }

//    private void addListener(AutoCompleteTextView autoCompleteTextView){
//        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Intent intent = new Intent(ViewBookings.this, ChooseBooking.class);
//                startActivity(intent);
//            }
//        });
//    }

    public class SectionsPageAdapter extends FragmentPagerAdapter{
        public SectionsPageAdapter(FragmentManager fragmentManager){super(fragmentManager);}

        @Override
        public int getCount(){return 3;}

        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new TopFragment();
                case 1:
                    return new ViewBookingFragment();
                case 2:
                    return new MyBookingFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 1:
                    return getResources().getText(R.string.view_bookings);
                case 2:
                    return getResources().getText(R.string.my_bookings);
            }
            return null;
        }
    }
}
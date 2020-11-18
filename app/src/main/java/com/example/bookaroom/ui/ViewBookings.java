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

        SectionsPageAdapter pageAdapter = new SectionsPageAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(pageAdapter);
    }

    public class SectionsPageAdapter extends FragmentPagerAdapter{
        public SectionsPageAdapter(FragmentManager fragmentManager){super(fragmentManager);}

        @Override
        public int getCount(){return 2;}

        @Override
        public Fragment getItem(int position){
            switch (position){
                case 0:
                    return new ViewBookingFragment();
                case 1:
                    return new MyBookingFragment();
            }
            return null;
        }

        @Override
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return getResources().getText(R.string.view_bookings);
                case 1:
                    return getResources().getText(R.string.my_bookings);
            }
            return null;
        }
    }
}
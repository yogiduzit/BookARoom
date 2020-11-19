package com.example.bookaroom.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.bookaroom.MyBookingFragment;
import com.example.bookaroom.R;

import com.example.bookaroom.ViewBookingFragment;

public class ViewBookings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bookings);

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
package com.example.bookaroom.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookaroom.data.database.entity.Building;

import com.example.bookaroom.R;

import java.util.List;

public class BookingsDropdownAdapter extends RecyclerView.Adapter<BookingsDropdownAdapter.ViewHolder> {

    private List<Building> buildings;

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textView;

        public ViewHolder(TextView v) {
            super(v);
            textView = v;
        }
    }

    public BookingsDropdownAdapter(List<Building> buildings) {
        this.buildings = buildings;
    }

    @NonNull
    @Override
    public BookingsDropdownAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        TextView cv = (TextView) LayoutInflater.from(parent.getContext()).inflate(R.layout.dropdown_menu_item, parent, false);
        return new ViewHolder(cv);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingsDropdownAdapter.ViewHolder holder, int position) {
        final TextView textView = holder.textView;
        textView.setText(buildings.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return buildings.size();
    }
}

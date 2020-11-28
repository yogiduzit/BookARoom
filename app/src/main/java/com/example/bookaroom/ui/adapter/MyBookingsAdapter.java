package com.example.bookaroom.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookaroom.R;
import com.example.bookaroom.data.database.entity.Booking;

import java.util.List;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.ViewHolder>{

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        public ViewHolder(View view) {
            super(view);
            this.view = view;
        }
    }

    private List<Booking> bookings;

    public MyBookingsAdapter(List<Booking> bookings) {
        this.bookings = bookings;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.booking_list_item, parent, false);
        MyBookingsAdapter.ViewHolder viewHolder = new MyBookingsAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final View view = holder.view;
        Booking booking = bookings.get(position);

        TextView start = view.findViewById(R.id.booking_start);
        TextView end = view.findViewById(R.id.booking_end);
        TextView location = view.findViewById(R.id.booking_location);

        start.setText(booking.getStartTime());
        end.setText(booking.getEndTime());
        location.setText(booking.getRoomId());
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }
}

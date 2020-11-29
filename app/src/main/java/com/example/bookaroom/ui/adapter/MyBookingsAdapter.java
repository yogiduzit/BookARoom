package com.example.bookaroom.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookaroom.MyBookingFragment;
import com.example.bookaroom.R;
import com.example.bookaroom.data.database.access.BookingManager;
import com.example.bookaroom.data.database.entity.Booking;

import java.util.List;

public class MyBookingsAdapter extends RecyclerView.Adapter<MyBookingsAdapter.ViewHolder>{
    BookingManager bookingManager = new BookingManager();
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

        start.setText("Start: " + booking.getStartTime());
        end.setText("End: " +booking.getEndTime());
        location.setText("Room/Rec: " +  booking.getRoomId());
        view.setOnClickListener(v -> showUpdateDialog(v.getContext(), position));
    }

    @Override
    public int getItemCount() {
        return bookings.size();
    }

    private void showUpdateDialog(Context context, int index){
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);

        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final Button buttonDelete = dialogView.findViewById(R.id.deleteBtn);
        final Button buttonCancel = dialogView.findViewById(R.id.cancelBtn);

        dialogBuilder.setTitle(R.string.deleteBooking);

        final AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.show();

        buttonCancel.setOnClickListener(v -> alertDialog.dismiss());

        buttonDelete.setOnClickListener(v -> {
            bookingManager.deleteBooking(bookings.get(index).getId());
            bookings.remove(bookings.get(index));
            notifyItemRemoved(index);
            alertDialog.dismiss();
        });
    }
}

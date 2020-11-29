package com.example.bookaroom.helpers;

import android.content.Intent;

import androidx.annotation.NonNull;

import com.example.bookaroom.AdminPanel;
import com.example.bookaroom.data.database.entity.Booking;

import java.util.ArrayList;
import java.util.List;

public class BookingHelper {
    public static int isIntervalBooked(String startTime, String endTime, List<Booking> bookings) {
        int booked = 0;
        if (bookings == null || bookings.isEmpty()) {
            return 0;
        }
        double start = parseTimeString(startTime);
        double end = parseTimeString(endTime);
        for (Booking b: bookings) {
            double bookingStart = b.getStartTime();
            double bookingEnd = b.getEndTime();
            if (end <= bookingStart || start >= bookingEnd) {
                continue;
            } else {
                ++booked;
            }
        }
        return booked;
    }

    @NonNull
    public static List<String> getBookingIntervals(int startTime, int endTime) {
        if (endTime > AdminPanel.DAY_END_TIME || startTime < AdminPanel.DAY_START_TIME) {
            throw new IllegalArgumentException("Can't choose bookings before 6am and after 12pm");
        }
        if (startTime >= endTime) {
            throw new IllegalArgumentException("End time must be greater than start time");
        }

        ArrayList<String> bookingIntervals = new ArrayList<>();
        for (double i = startTime; i < endTime; i += AdminPanel.BASE_BOOKING_INTERVAL) {
            bookingIntervals.add(stringifyTime(i) + " - " + stringifyTime(i + 0.5));
        }
        return bookingIntervals;
    }

    @NonNull
    public static String stringifyTime(double time) {
        int base = (int) time;
        return (time % 1 == 0) ? base + ":00" : base + ":30";
    }

    public static double parseTimeString(String time) {
        double base = Integer.valueOf(time.split(":")[0].trim());
        double fraction = Double.valueOf(time.split(":")[1].trim()) / 60;
        if (base > 23 || fraction > 59) {
            throw new IllegalArgumentException("Invalid hours or minutes");
        }
        return base + fraction;
    }
}

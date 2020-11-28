package com.example.bookaroom.helpers;

import com.example.bookaroom.AdminPanel;
import androidx.annotation.NonNull;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class DateHelper {

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

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        int currHour = LocalDateTime.now().getHour();
        return (currHour > AdminPanel.DAY_END_TIME ? dateFormat.format(new Date()) : dateFormat.format(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant())));
    }

    @NonNull
    private static String stringifyTime(double time) {
        int base = (int) time;
        return (time % 1 == 0) ? base + ":00" : base + ":30";
    }
}

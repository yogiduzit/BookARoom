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

    public static String getDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        int currHour = LocalDateTime.now().getHour();
        return (currHour > AdminPanel.DAY_END_TIME ? dateFormat.format(Date.from(LocalDate.now().plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant())) : dateFormat.format(new Date()));
    }
}

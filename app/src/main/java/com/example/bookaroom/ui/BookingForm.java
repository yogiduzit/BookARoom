package com.example.bookaroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bookaroom.R;

import com.example.bookaroom.data.database.access.BookingManager;
import com.example.bookaroom.data.database.entity.Booking;
import com.example.bookaroom.helpers.BookingHelper;
import com.example.bookaroom.helpers.DateHelper;
import com.example.bookaroom.helpers.ToastHelper;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingForm extends AppCompatActivity {
    TextView roomNum;
    EditText startTime, endTime, etDate, etName;
    CheckBox checkBox;
    private final Calendar myCalendar = Calendar.getInstance();
    private final Calendar myClock = Calendar.getInstance();
    private SimpleDateFormat dateFormat;
    DatePickerDialog datePicker;
    TimePickerDialog timePicker;
    Button bookBtn;

    private BookingManager bookingManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);
        roomNum = findViewById(R.id.room_no_input);
        startTime = findViewById(R.id.start_time_input);
        endTime = findViewById(R.id.end_time_input);
        etDate = findViewById(R.id.date_input);
        etName = findViewById(R.id.booking_name_input);
        bookBtn = findViewById(R.id.book_button);
        checkBox = findViewById(R.id.terms_and_conditions);
        dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        final int[] ids = new int[]{R.id.room_no_input, R.id.start_time_input, R.id.end_time_input, R.id.date_input, R.id.booking_name_input};

        setupForm();

        bookingManager = new BookingManager();

        bookBtn.setOnClickListener(v -> {
            if (!checkBox.isChecked()) {
                ToastHelper.showToast(getApplicationContext(), ToastHelper.Severity.ERROR, "Please agree to terms and conditions.", Toast.LENGTH_SHORT);
                return;
            }
            if (!validate(ids)) {
                    addBooking();
            } else {
                ToastHelper.showToast(getApplicationContext(), ToastHelper.Severity.ERROR, "Enter missing values", Toast.LENGTH_SHORT);
            }
        });

        startTime.setOnClickListener(v -> {
            int hour = myClock.get(Calendar.HOUR_OF_DAY);
            int minute = myClock.get(Calendar.MINUTE);
            timePicker = new TimePickerDialog(BookingForm.this, (view, hourOfDay, minute1) -> startTime.setText(hourOfDay + ":" + minute1), hour, minute, true);
            timePicker.setTitle("Select Time");
            timePicker.show();
        });

        endTime.setOnClickListener(v -> {
            int hour = myClock.get(Calendar.HOUR_OF_DAY);
            int minute = myClock.get(Calendar.MINUTE);
            timePicker = new TimePickerDialog(BookingForm.this, (view, hourOfDay, minute12) -> endTime.setText(hourOfDay + ":" + minute12), hour, minute, true);
            timePicker.setTitle("Select Time");
            timePicker.show();
        });

        etDate.setOnClickListener(v -> {
            datePicker = new DatePickerDialog(BookingForm.this, (view, year, month, dayOfMonth) -> {
                myCalendar.set(year, month, dayOfMonth);
                etDate.setText(dateFormat.format(myCalendar.getTime()));
            }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
            datePicker.setTitle("Select Date:");
            datePicker.show();
        });
    }

    void setupForm() {
        String bookableName = getIntent().getStringExtra("bookable");
        String start = getIntent().getStringExtra("startTime");
        String end = getIntent().getStringExtra("endTime");

        roomNum.setText(bookableName);
        startTime.setText(start);
        endTime.setText(end);
        etDate.setText(DateHelper.getDate());
    }

    public boolean validate(int[] ids) {
        boolean isEmpty = false;
        for (int id : ids) {
            TextView editText = findViewById(id);
            if (TextUtils.isEmpty(editText.getText().toString())) {
                editText.setError("Enter a Value");
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    public void onBookingConfirmed() {
        ToastHelper.showToast(getApplicationContext(), ToastHelper.Severity.SUCCESS, getString(R.string.booking_confirmed), Toast.LENGTH_SHORT);
        Intent intent = new Intent(this, ViewBookings.class);
        startActivity(intent);
    }

    private void addBooking() {
        String roomID = roomNum.getText().toString().trim();
        String start = startTime.getText().toString().trim();
        String end = endTime.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String userID;
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        userID = account.getId();
        String id = userID + "-" + name + "-" + start + "-" + end;
        String buildingId = getIntent().getStringExtra("building");
        Booking booking = new Booking(id, userID, roomID, BookingHelper.parseTimeString(start), BookingHelper.parseTimeString(end), date, name, buildingId);

        bookingManager.addBooking(booking)
                .addOnSuccessListener((OnSuccessListener) o -> onBookingConfirmed())
                .addOnFailureListener(e -> ToastHelper.showToast(getApplicationContext(), ToastHelper.Severity.ERROR, "Booking wasn't added.", Toast.LENGTH_SHORT));
    }
}
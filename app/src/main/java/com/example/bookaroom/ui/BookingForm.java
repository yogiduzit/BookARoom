package com.example.bookaroom.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bookaroom.R;

import com.example.bookaroom.data.database.access.BookingManager;
import com.example.bookaroom.data.database.entity.Booking;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingForm extends AppCompatActivity {
    EditText roomNum, startTime, endTime, etDate, etName;
    CheckBox checkBox;
    private final Calendar myCalendar = Calendar.getInstance();
    private final Calendar myClock = Calendar.getInstance();
    private SimpleDateFormat dateFormat;
    DatePickerDialog datePicker;
    TimePickerDialog timePicker;
    Button bookBtn;
    FirebaseFirestore db;
    String id;

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

        bookingManager = new BookingManager();

        bookBtn.setOnClickListener(v -> {
            if(!checkBox.isChecked()){
                Toast.makeText(getApplicationContext(), "Please agree to terms and conditions.", Toast.LENGTH_SHORT).show();
                return;
            }
            if(!validate(ids)){
                addBooking();
                Intent intent = new Intent(BookingForm.this, BookingConfirmation.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Enter missing values", Toast.LENGTH_SHORT).show();
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

    public boolean validate(int[] ids){
        boolean isEmpty = false;
        for(int id: ids){
            EditText editText = findViewById(id);
            if(TextUtils.isEmpty(editText.getText().toString())){
                editText.setError("Enter a Value");
                isEmpty = true;
            }
        }
        return isEmpty;
    }

    public void onBookingConfirmed(View view){
        Intent intent = new Intent(this, BookingConfirmation.class);
        startActivity(intent);
    }

    private void addBooking(){
        String roomID = roomNum.getText().toString().trim();
        String start = startTime.getText().toString().trim();
        String end = endTime.getText().toString().trim();
        String date = etDate.getText().toString().trim();
        String name = etName.getText().toString().trim();
        String userID;
        //user id = google login id;
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        userID = account.getId();
        String id = userID + "-" + name + "-" + start + "-" + end;
        Booking booking = new Booking(id, userID, roomID, start, end, date, name);

        //query put in bookings manager function get my bookings
        //db.collection where date is = to today date
        bookingManager.addBooking(booking)
                .addOnSuccessListener((OnSuccessListener) o -> Toast.makeText(BookingForm.this, "Booking added", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(BookingForm.this, "Booking wasn't added.", Toast.LENGTH_SHORT).show());
    }
}
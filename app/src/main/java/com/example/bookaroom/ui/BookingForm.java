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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.bookaroom.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class BookingForm extends AppCompatActivity {
    EditText roomNum, startTime, endTime, date, name;
    CheckBox checkBox;
    private Calendar myCalendar = Calendar.getInstance();
    private Calendar myClock = Calendar.getInstance();
    private SimpleDateFormat dateFormat;
    DatePickerDialog datePicker;
    TimePickerDialog timePicker;
    Button bookBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_form);

        roomNum = (EditText) findViewById(R.id.room_no_input);
        startTime = (EditText) findViewById(R.id.start_time_input);
        endTime = (EditText) findViewById(R.id.end_time_input);
        date = (EditText) findViewById(R.id.date_input);
        name = (EditText) findViewById(R.id.booking_name_input);
        bookBtn = (Button) findViewById(R.id.book_button);
        checkBox = (CheckBox)findViewById(R.id.terms_and_conditions);
        dateFormat = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
        final int[] ids = new int[]{R.id.room_no_input, R.id.start_time_input, R.id.end_time_input, R.id.date_input, R.id.booking_name_input};

        bookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!checkBox.isChecked()){
                    Toast.makeText(getApplicationContext(), "Please agree to terms and conditions.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(!validate(ids)){
                    Intent intent = new Intent(BookingForm.this, BookingConfirmation.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Enter missing values", Toast.LENGTH_SHORT).show();
                }
            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = myClock.get(Calendar.HOUR_OF_DAY);
                int minute = myClock.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(BookingForm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        startTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                timePicker.setTitle("Select Time");
                timePicker.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int hour = myClock.get(Calendar.HOUR_OF_DAY);
                int minute = myClock.get(Calendar.MINUTE);
                timePicker = new TimePickerDialog(BookingForm.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        endTime.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
                timePicker.setTitle("Select Time");
                timePicker.show();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker = new DatePickerDialog(BookingForm.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(year, month, dayOfMonth);
                        date.setText(dateFormat.format(myCalendar.getTime()));
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH));
                datePicker.setTitle("Select Date:");
                datePicker.show();
            }
        });
    }

    public boolean validate(int[] ids){
        boolean isEmpty = false;
        for(int id: ids){
            EditText editText = (EditText)findViewById(id);
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
}
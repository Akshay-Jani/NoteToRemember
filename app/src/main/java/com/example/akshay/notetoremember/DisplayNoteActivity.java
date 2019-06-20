package com.example.akshay.notetoremember;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class DisplayNoteActivity extends AppCompatActivity {

    TextView tvdn_title;
    TextView tvdn_desc;
    String title;
    String desc;
    FloatingActionButton fabReminder;
    Calendar calendar;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_note);

        tvdn_title = findViewById(R.id.tvdn_title);
        tvdn_desc = findViewById(R.id.tvdn_desc);
        fabReminder = findViewById(R.id.fabReminder);

        Intent intent = getIntent();

        title = intent.getStringExtra("title");
        desc = intent.getStringExtra("desc");

        tvdn_title.setText(title);
        tvdn_desc.setText(desc);

        fabReminder.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View view) {

                calendar = Calendar.getInstance();
                year = calendar.get(Calendar.YEAR);
                month = calendar.get(Calendar.MONTH);
                day = calendar.get(Calendar.DAY_OF_MONTH);

                datePickerDialog = new DatePickerDialog(DisplayNoteActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        Log.d("maymay", String.valueOf(year));
                        Log.d("maymay",String.valueOf(month+1));
                        Log.d("maymay",String.valueOf(day));
                    }
                },year,month+1,day);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();
            }
        });
    }
}

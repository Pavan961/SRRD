package com.example.srrd.TicketBooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.srrd.R;

public class TicketBookingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_booking);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Ticket Booking");
    }
}
package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NewDoctorProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_doctor_profile);
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String nationality = intent.getStringExtra("nationality");
        String email = intent.getStringExtra("email");
        String cvURL = intent.getStringExtra("cvURL");



        ImageView imageViewDoctor = (ImageView) findViewById(R.id.imageViewDoctorLogo);
        TextView textViewDocName = (TextView) findViewById(R.id.textViewDoctorName);
        TextView textViewEmail = (TextView) findViewById(R.id.textViewDoctorEmail);
        TextView textViewSpeciality = (TextView) findViewById(R.id.textViewDoctorSpeciality);
        TextView textViewNationality = (TextView) findViewById(R.id.textViewDoctorNationality);
        TextView textViewCV = (TextView) findViewById(R.id.textViewDoctorCV);
        Button buttonRequest = (Button) findViewById(R.id.buttonSendDoctorRequest);

        textViewDocName.setText(name);






    }
}

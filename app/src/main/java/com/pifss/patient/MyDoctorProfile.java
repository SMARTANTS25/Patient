package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MyDoctorProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctor_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.MyDoctorProfile_toolbar);


        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String nationality = intent.getStringExtra("nationality");
        String email = intent.getStringExtra("email");
        String cvURL = intent.getStringExtra("cvURL");
        String specialty = intent.getStringExtra("specialty");
        String profileImg = intent.getStringExtra("imageURL");

        toolbar.setTitle(name);

        ImageView imageViewDoctor = (ImageView) findViewById(R.id.MyDoctorProfile_doctorImage);
        TextView textViewDocName = (TextView) findViewById(R.id.MyDoctorProfile_doctorNameTV);
        TextView textViewEmail = (TextView) findViewById(R.id.MyDoctorProfile_doctorEmailTV);
        TextView textViewSpeciality = (TextView) findViewById(R.id.MyDoctorProfile_doctorSpecialtyTV);
        TextView textViewNationality = (TextView) findViewById(R.id.MyDoctorProfile_NationalityTV);
        TextView textViewCV = (TextView) findViewById(R.id.MyDoctorProfile_doctorCvTV);
        //Button buttonRequest = (Button) findViewById(R.id.buttonSendDoctorRequest);


        textViewDocName.setText(name);
        textViewCV.setText(cvURL);
        textViewEmail.setText(email);
        textViewNationality.setText(nationality);
        textViewSpeciality.setText(specialty);

        if (profileImg != null && profileImg.length() >= 1 ) {
            Picasso.with(this).load(profileImg).into(imageViewDoctor);
        }

    }
}

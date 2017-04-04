package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MyDoctorProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_doctor_profile);

        /*Toolbar toolbar = (Toolbar) findViewById(R.id.);
        toolbar.setTitle("Hospitals");

        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });*/

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String nationality = intent.getStringExtra("nationality");
        String email = intent.getStringExtra("email");
        String cvURL = intent.getStringExtra("cvURL");

        /*ImageView imageViewDoctor = (ImageView) findViewById(R.id.imageViewDoctorLogo);
        TextView textViewDocName = (TextView) findViewById(R.id.textViewDoctorName);
        TextView textViewEmail = (TextView) findViewById(R.id.textViewDoctorEmail);
        TextView textViewSpeciality = (TextView) findViewById(R.id.textViewDoctorSpeciality);
        TextView textViewNationality = (TextView) findViewById(R.id.textViewDoctorNationality);
        TextView textViewCV = (TextView) findViewById(R.id.textViewDoctorCV);
        Button buttonRequest = (Button) findViewById(R.id.buttonSendDoctorRequest);*/

        TextView textViewDocName = (TextView) findViewById(R.id.textView17);

        textViewDocName.setText(name);
    }
}

package com.pifss.patient;

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

        ImageView imageViewDoctor = (ImageView) findViewById(R.id.imageViewDoctorLogo);
        TextView textViewDocName = (TextView) findViewById(R.id.textViewDoctorName);
        TextView textViewEmail = (TextView) findViewById(R.id.textViewDoctorEmail);
        TextView textViewSpeciality = (TextView) findViewById(R.id.textViewDoctorSpeciality);
        TextView textViewNationality = (TextView) findViewById(R.id.textViewDoctorNationality);
        TextView textViewCV = (TextView) findViewById(R.id.textViewDoctorCV);
        Button buttonRequest = (Button) findViewById(R.id.buttonSendDoctorRequest);








    }
}

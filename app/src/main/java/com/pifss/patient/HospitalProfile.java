package com.pifss.patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class HospitalProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_profile);


        ImageView imageViewHosLogo = (ImageView) findViewById(R.id.imageViewHospital);
        TextView textViewHosName = (TextView) findViewById(R.id.textViewHospitalName);
        TextView textViewHosEmail = (TextView) findViewById(R.id.textViewHospitalEmail);
        TextView textViewPhone = (TextView) findViewById(R.id.textViewHospitalPhone);
        TextView textViewType = (TextView) findViewById(R.id.textViewHospitalType);
        TextView textViewWorkingH = (TextView) findViewById(R.id.textViewHospitalWorkingHours);
        TextView textViewExtraInfo = (TextView) findViewById(R.id.textViewHospitalExtraInfo);





    }
}

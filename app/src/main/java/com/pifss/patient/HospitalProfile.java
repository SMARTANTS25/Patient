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


        ImageView imageViewHosLogo = (ImageView) findViewById(R.id.ListHospitalView_HospitalImageView);
        TextView textViewHosName = (TextView) findViewById(R.id.HospitalProfile_TypeTV);
        TextView textViewHosEmail = (TextView) findViewById(R.id.HospitalProfile_EmailTV);
        TextView textViewPhone = (TextView) findViewById(R.id.HospitalProfile_PhoneNumberTV);
        TextView textViewType = (TextView) findViewById(R.id.textViewHospitalType);
        TextView textViewWorkingH = (TextView) findViewById(R.id.HospitalProfile_WorkingHourseTV);
        TextView textViewExtraInfo = (TextView) findViewById(R.id.HospitalProfile_AdditionalInfoTV);





    }
}

package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ViewPatientProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_patient_profile);

        ImageView imageProfile = (ImageView) findViewById(R.id.profileImgView);

        Button editButton=(Button) findViewById(R.id.editPersonalInfoButton);
        Button myDrButton=(Button) findViewById(R.id.mydrGeneralInfoButton);
        Button medicalProfileButton = (Button) findViewById(R.id.medicalInfoViewButton);


        TextView name=(TextView) findViewById(R.id.profilePatientNameText);
        TextView gender=(TextView) findViewById(R.id.genderViewText);
        TextView birthdate=(TextView) findViewById(R.id.birthdateViewText);
        TextView email=(TextView) findViewById(R.id.emailViewText);
        TextView phone=(TextView) findViewById(R.id.phoneViewText);

        TextView emergencyNo=(TextView) findViewById(R.id.emergencyNoView);

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewPatientProfile.this, EditPatientProfile.class);
                startActivity(i);
            }
        });

        medicalProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(ViewPatientProfile.this, ViewMedicalInfo.class);
                startActivity(i);
            }
        });
    }
}

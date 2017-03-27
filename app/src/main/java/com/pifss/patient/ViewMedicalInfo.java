package com.pifss.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class ViewMedicalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_info);

        TextView bloodtype = (TextView) findViewById(R.id.bloodtypeViewText);
        TextView diabetes = (TextView) findViewById(R.id.diabeteViewText);
        TextView asthma = (TextView) findViewById(R.id.asthmaViewText);
        TextView allergies = (TextView) findViewById(R.id.allergiesViewText);
        TextView medications = (TextView) findViewById(R.id.medicationViewText);

        Button myDoctor = (Button) findViewById(R.id.mydrMedicalInfoButton);
    }
}

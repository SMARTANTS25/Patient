package com.pifss.patient;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

public class ViewMedicalInfo extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_info);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Medical Info");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        final TextView bloodtypeText = (TextView) findViewById(R.id.ViewMedicalInfo_bloodTypeTV);
        final TextView diabetesText = (TextView) findViewById(R.id.ViewMedicalInfo_diabetesTV);
        final TextView asthmaText = (TextView) findViewById(R.id.ViewMedicalInfo_asthmaTV);
        final TextView allergiesText = (TextView) findViewById(R.id.ViewMedicalInfo_AllergiesTV);
        final TextView medicationsText = (TextView) findViewById(R.id.ViewMedicalInfo_medicationVT);


        SharedPreferences pref1 = getSharedPreferences("PatientData1", MODE_PRIVATE);
        String patientProfile = pref1.getString("Patient1","notfound");
        Patient currentPatientObject = new Gson().fromJson(patientProfile,Patient.class);

        bloodtypeText.setText(currentPatientObject.getBloodType());
        if (currentPatientObject.getDiabetes()){

            diabetesText.setText("No");
        }else{
            diabetesText.setText("Yes");
        }

        if (currentPatientObject.getAsthma()){

            asthmaText.setText("No");
        }else{
            asthmaText.setText("Yes");
        }

        allergiesText.setText(currentPatientObject.getAllergies());
        medicationsText.setText(currentPatientObject.getMedications());

        Button editMedicalInfobutton=(Button) findViewById(R.id.ViewMedicalInfo_EditProfileButton);
        editMedicalInfobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(ViewMedicalInfo.this, EditMedicalInfo .class);
                startActivity(i);
            }
        });



    }
}

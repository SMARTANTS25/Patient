package com.pifss.patient;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.json.JSONException;
import org.json.JSONObject;

public class MyReports extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_reports);

        String Patientid = getSharedPreferences("PatientData", MODE_PRIVATE).getString("Patient", " ");

        // Step 2 converting String to Json Object

        try {
            JSONObject obj = new JSONObject(Patientid);
            String id = obj.getString("patientId");


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
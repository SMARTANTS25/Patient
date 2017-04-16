package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class EditPatientProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_patient_profile);

        Button saveChanges= (Button) findViewById(R.id.editPatientInfo_SaveChangesButton);


        saveChanges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Intent i = new Intent(EditMedicalInfo.this, .class);
                // startActivity(i);
            }
        });

        final EditText genderText=(EditText) findViewById(R.id.editPatientProfile_genderET);
        final EditText birthDateText=(EditText) findViewById(R.id.editPatientProfile_birthDateET);
        final EditText emailText=(EditText) findViewById(R.id.editPatientProfile_emailET);
        final EditText phoneText=(EditText) findViewById(R.id.editPatientProfile__phoneNumberET);
        final EditText emergencyText=(EditText) findViewById(R.id.editPatientProfile_emergencyNumberET);

//change URL
        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/";
        RequestQueue queue = MySingleton.getInstance().getRequestQueue(this);

        JSONObject obj = new JSONObject();
        try {
            obj.put("gender", genderText.getText().toString());
            obj.put("birthDate", birthDateText.getText().toString());

            obj.put("email", emailText.getText().toString());
            obj.put("phone", phoneText.getText().toString());

            obj.put("emergency", emergencyText.getText().toString());



            JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {



                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

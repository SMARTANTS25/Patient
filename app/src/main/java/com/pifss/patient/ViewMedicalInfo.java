package com.pifss.patient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

public class ViewMedicalInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_medical_info);

        final TextView bloodtypeText = (TextView) findViewById(R.id.bloodtypeViewText);
        final TextView diabetesText = (TextView) findViewById(R.id.diabeteViewText);
        final TextView asthmaText = (TextView) findViewById(R.id.asthmaViewText);
        final TextView allergiesText = (TextView) findViewById(R.id.allergiesViewText);
        final TextView medicationsText = (TextView) findViewById(R.id.medicationViewText);

        Button myDoctor = (Button) findViewById(R.id.mydrMedicalInfoButton);

        String url="";
        RequestQueue queue= MySingleton.getInstance().getRequestQueue(this);
        StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

               // Patient p=new Patient();

                Patient p=new Gson().fromJson(response,Patient.class);

                bloodtypeText.setText(p.getBloodType());
                diabetesText.setText(p.getDiabetes());
                asthmaText.setText(p.getAsthma());
                allergiesText.setText(p.getAllergies());
                medicationsText.setText(p.getMedications());

                Toast.makeText(ViewMedicalInfo.this, p.getBloodType(), Toast.LENGTH_LONG).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });




    }
}

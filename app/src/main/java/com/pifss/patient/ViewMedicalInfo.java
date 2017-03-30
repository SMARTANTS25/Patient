package com.pifss.patient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

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
        Button viewGeneralInfo=(Button) findViewById(R.id.generalinfoViewButton);
        Button editMedicalInfobutton=(Button) findViewById(R.id.EditMedicalInfoButton);
        editMedicalInfobutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(ViewMedicalInfo.this, EditMedicalInfo .class);
                startActivity(i);
            }
        });

        viewGeneralInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(ViewMedicalInfo.this, ViewPatientProfile.class);
                startActivity(i);
            }
        });

        myDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i= new Intent(ViewMedicalInfo.this, MyDoctors.class);
                startActivity(i);
            }
        });

        String url="";
        RequestQueue queue= MySingleton.getInstance().getRequestQueue(this);
        String link = "http://34.196.107.188:8081/MhealthWeb/webresources/patient/";

        RequestQueue regQue = MySingleton.getInstance().getRequestQueue(this);

        JSONObject obj = new JSONObject();
        try {
            obj.put("bloodType", bloodtypeText);
            obj.put("diabetes", diabetesText);
            obj.put("astha", asthmaText);
            obj.put("allergies", allergiesText);
            obj.put("medications", medicationsText);


            JsonObjectRequest req = new JsonObjectRequest(Request.Method.GET, link, obj, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(final JSONObject response) {

                    Patient p=new Gson().fromJson(response.toString(), Patient.class);
                    bloodtypeText.setText(p.getBloodType());
                    diabetesText.setText(p.getDiabetes()+"");
                    asthmaText.setText(p.getAsthma()+"");
                    allergiesText.setText(p.getAllergies());
                    medicationsText.setText(p.getMedications());

                    Toast.makeText(ViewMedicalInfo.this, "", Toast.LENGTH_SHORT).show();


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(ViewMedicalInfo.this, "error", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }
}

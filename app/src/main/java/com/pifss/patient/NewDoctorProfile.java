package com.pifss.patient;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class NewDoctorProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_doctor_profile); //
        int pID = 0;
        Toolbar toolbar = (Toolbar) findViewById(R.id.NewDoctorProfile_toolbar);
        String patient  = getSharedPreferences("PatientData1",MODE_PRIVATE).getString("Patient1","poor");
        try {
            JSONObject object = new JSONObject(patient);
            pID = object.getInt("patientId");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        toolbar.setNavigationIcon(android.R.drawable.arrow_up_float);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Intent intent = getIntent();
        int dID = intent.getIntExtra("drId2",0);
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String nationality = intent.getStringExtra("nationality");
        String email = intent.getStringExtra("email");
        String cvURL = intent.getStringExtra("cvURL");
        String specialty = intent.getStringExtra("specialty");
        String profileImg = intent.getStringExtra("imageURL");

        toolbar.setTitle(name);

        ImageView imageViewDoctor = (ImageView) findViewById(R.id.NewDoctorProfile_DoctorLogo);
        TextView textViewDocName = (TextView) findViewById(R.id.NewDoctorProfile_DoctorNameTV);
        TextView textViewEmail = (TextView) findViewById(R.id.NewDoctorProfile_DoctorEmailTV);
        TextView textViewSpeciality = (TextView) findViewById(R.id.NewDoctorProfile_DoctorSpecialityTV);
        TextView textViewNationality = (TextView) findViewById(R.id.NewDoctorProfile_DoctorNationalityTV);
        TextView textViewCV = (TextView) findViewById(R.id.NewDoctorProfile_DoctorCvTV);


        Button buttonRequest = (Button) findViewById(R.id.NewDoctorProfile_SendRequestButton);

        String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patientdrlink/";


        Toast.makeText(this, pID+"pid  "+ , Toast.LENGTH_SHORT).show();
        JSONObject obj = new JSONObject();
        try {
            obj.put("drId",dID);
            obj.put("patientId",pID);
            obj.put("status",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(NewDoctorProfile.this);

        final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Toast.makeText(NewDoctorProfile.this, "the request is sent", Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(NewDoctorProfile.this, "ERROR", Toast.LENGTH_SHORT).show();
            }
        });

        textViewDocName.setText(name);
        textViewCV.setText(cvURL);
        textViewEmail.setText(email);
        textViewNationality.setText(nationality);
        textViewSpeciality.setText(specialty);

        if (profileImg != null && profileImg.length() >= 4 &&  profileImg.isEmpty() == false ) {
            Picasso.with(NewDoctorProfile.this).load(profileImg).into(imageViewDoctor);
        }




    }
}

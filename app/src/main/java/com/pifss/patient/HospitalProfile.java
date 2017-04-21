package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class HospitalProfile extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.HospitalProfile_toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        toolbar.setTitle(name);
        String workingHours = intent.getStringExtra("workingHours");

        String email = intent.getStringExtra("email");
        String phoneNumber = intent.getStringExtra("phoneNumber");
        String type = intent.getStringExtra("type");
        String address = intent.getStringExtra("address");
        String extraInfo = intent.getStringExtra("extraInfo");
        int specialtyId = intent.getIntExtra("specialityId",0);
        String logoURL = intent.getStringExtra("logoURL");

        Toast.makeText(HospitalProfile.this, specialtyId+" ", Toast.LENGTH_LONG);
        System.out.println(specialtyId);

        updateSpecialtyName(specialtyId);




        ImageView imageViewHosLogo = (ImageView) findViewById(R.id.HospitalProfile_HospitalLogoImageView);
        TextView textViewHosName = (TextView) findViewById(R.id.HospitalProfile_TypeTV);
        TextView textViewHosEmail = (TextView) findViewById(R.id.HospitalProfile_EmailTV);
        TextView textViewPhone = (TextView) findViewById(R.id.HospitalProfile_PhoneNumberTV);
        TextView textViewType = (TextView) findViewById(R.id.textViewHospitalType);
        TextView textViewWorkingH = (TextView) findViewById(R.id.HospitalProfile_WorkingHourseTV);
        TextView textViewExtraInfo = (TextView) findViewById(R.id.HospitalProfile_AdditionalInfoTV);
        TextView textViewAddress = (TextView) findViewById(R.id.ViewMedicalInfo_AsthmaTitle);
        //TextView hosSpec = (TextView) findViewById(R.id.HospitalProfile_SpecialityTV);


        updateSpecialtyName(specialtyId);


        textViewHosName.setText(name);
        textViewHosEmail.setText(email);
        textViewPhone.setText(phoneNumber);
        textViewWorkingH.setText(workingHours);
        textViewExtraInfo.setText(extraInfo);

        if (type.equals("public"))
            textViewType.setText(R.string.HospitalTypePublic);
        else if (type.equals("private"))
            textViewType.setText(R.string.HospitalTypePrivate);
        else textViewType.setText(type);

        textViewAddress.setText(address);

     //   Toast.makeText(this, logoURL, Toast.LENGTH_LONG);

        /*if (!logoURL.isEmpty() && logoURL.length() >= 4 && logoURL != null) {
            try {
                Picasso.with(this).load(logoURL).into(imageViewHosLogo);
            } catch (Exception e) {
                imageViewHosLogo.setImageResource(R.mipmap.hospital);
            }
        }*/
        imageViewHosLogo.setImageResource(R.mipmap.hospital);



    }

    private void updateSpecialtyName(int specialtyId) {
        String url = "http://34.196.107.188:8080/mHealthWS/ws/speciality/"+specialtyId;
        final StringBuilder specialty = new StringBuilder();

        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(HospitalProfile.this);
        final ProgressDialog progressDialog = new ProgressDialog(HospitalProfile.this);

        final StringRequest jsonReq = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
progressDialog.hide();
                        String specialtyName = "";
                        JSONObject resp = null;
                        try {
                            resp = new JSONObject(response);
                            specialtyName = resp.getString("specialityName");
                        } catch (JSONException e) {
                            Toast.makeText(HospitalProfile.this, e.getMessage(), Toast.LENGTH_LONG);
                        }

                        TextView textViewSpecialty = (TextView) findViewById(R.id.HospitalProfile_SpecialityTV);
                        textViewSpecialty.setText(specialtyName);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        // Handle error
                        Toast.makeText(HospitalProfile.this, error.getMessage(), Toast.LENGTH_LONG);
                        TextView textViewSpecialty = (TextView) findViewById(R.id.HospitalProfile_SpecialityTV);
                        textViewSpecialty.setText("N/A");
                    }
                });

        if (!isNetworkAvailable()){
            Toast.makeText(HospitalProfile.this, ""+R.string.NoInternetConnection, Toast.LENGTH_SHORT).show();
        }

        progressDialog.setMessage("Connecting...");
        progressDialog.show();
        queue.add(jsonReq);
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}

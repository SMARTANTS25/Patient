package com.pifss.patient;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class NewDoctorProfile extends AppCompatActivity {

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_doctor_profile);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.myToolbar);

        toolbar.setTitle("Doctor profile");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);


        int pID = 0;
        String patient  = getSharedPreferences("PatientData1",MODE_PRIVATE).getString("Patient1","poor");
        try {
            JSONObject object = new JSONObject(patient);
            pID = object.getInt("patientId");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        Intent intent = getIntent();
        final int dID = intent.getIntExtra("drId",0);
        String name = intent.getStringExtra("name");
        String gender = intent.getStringExtra("gender");
        String nationality = intent.getStringExtra("nationality");
        String email = intent.getStringExtra("email");
        String cvURL = intent.getStringExtra("cvURL");
        String specialty = intent.getStringExtra("specialty");
        String profileImg = intent.getStringExtra("imageURL");

        ImageView imageViewDoctor = (ImageView) findViewById(R.id.NewDoctorProfile_DoctorLogo);
        TextView textViewDocName = (TextView) findViewById(R.id.NewDoctorProfile_DoctorNameTV);
        TextView textViewEmail = (TextView) findViewById(R.id.NewDoctorProfile_DoctorEmailTV);
        TextView textViewSpeciality = (TextView) findViewById(R.id.NewDoctorProfile_DoctorSpecialityTV);
        TextView textViewNationality = (TextView) findViewById(R.id.NewDoctorProfile_DoctorNationalityTV);
        TextView textViewCV = (TextView) findViewById(R.id.NewDoctorProfile_DoctorCvTV);


        Button buttonRequest = (Button) findViewById(R.id.NewDoctorProfile_SendRequestButton);




        textViewDocName.setText(name);
        textViewCV.setText(cvURL);
        textViewEmail.setText(email);
        textViewNationality.setText(nationality);
        textViewSpeciality.setText(specialty);

        if (profileImg != null && profileImg.length() >= 4 &&  profileImg.isEmpty() == false ) {
            Picasso.with(NewDoctorProfile.this).load(profileImg).into(imageViewDoctor);
        }


        Toast.makeText(this, pID+" pid  "+dID , Toast.LENGTH_SHORT).show();
        final JSONObject obj = new JSONObject();
        try {
            obj.put("drId",dID);
            obj.put("patientId",pID);
            obj.put("status",0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final RequestQueue queue= MySingleton.getInstance().getRequestQueue(NewDoctorProfile.this);

        buttonRequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String url = "http://34.196.107.188:8081/MhealthWeb/webresources/patientdrlink";



                final ProgressDialog progressDialog = new ProgressDialog(NewDoctorProfile.this);

                final JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url, obj, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        progressDialog.hide();
                        try {
                        if (response.getBoolean("status") == true) {

                        System.out.print("response"+response);
                        Toast.makeText(NewDoctorProfile.this, R.string.NewDoctor_Request, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(NewDoctorProfile.this,"Error, Failed to send request", Toast.LENGTH_SHORT).show();

                        }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                                            }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        System.out.print("error: "+error);

                        
                        Toast.makeText(NewDoctorProfile.this, "ERROR", Toast.LENGTH_SHORT).show();

                    }
                }){
                    @Override
                        protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {
                            String jsonString = "";
                            JSONObject object = new JSONObject();

                            try {
                                System.out.println("statuscode: "+response.statusCode);
                                if (response.statusCode <200 || response.statusCode >300){

                                    object.put("status",false);
                                    return Response.success(object,
                                            HttpHeaderParser.parseCacheHeaders(response));
                                }
                                jsonString = new String(response.data, "UTF-8");
                                System.out.println("jsonString");

                                System.out.println("\""+jsonString+"\"");
                                if (jsonString.equals("")){
                                    object.put("status",true);
                                }else{
                                    object.put("status",false);
                                }

                                return Response.success(object,
                                    HttpHeaderParser.parseCacheHeaders(response));

                            } catch (UnsupportedEncodingException e) {
                                e.printStackTrace();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return Response.success(object,
                                    HttpHeaderParser.parseCacheHeaders(response));

                        }
                    };


                progressDialog.setMessage("Connecting...");
                progressDialog.show();
                queue.add(req);

            }
        });













    }
}
